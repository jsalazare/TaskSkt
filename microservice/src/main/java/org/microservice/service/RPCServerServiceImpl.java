package org.microservice.service;

import com.rabbitmq.client.*;
import org.common.configuration.Configurations;
import org.common.dto.ProductDTO;
import org.common.interfaces.ChannelFactory;
import org.common.util.SerializationUtilities;
import org.microservice.interfaces.RPCServerService;
import org.microservice.repository.ProductRepository;
import org.microservice.util.ProductUtilities;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public class RPCServerServiceImpl implements RPCServerService {


    private Configurations configurations;
    private ProductRepository productRepository;
    private Channel channel;


    public RPCServerServiceImpl(ProductRepository productRepository, Configurations configurations, ChannelFactory channelFactory) throws IOException, TimeoutException {
        this.configurations = configurations;
        this.productRepository = productRepository;
        channel = channelFactory.getNewChannel();

    }

    public void listenerService() throws IOException, InterruptedException {
        channel.queueDeclare(configurations.getRpcQueue(), true, false, false, null);
        channel.basicQos(1);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(properties.getCorrelationId())
                        .build();

                List<ProductDTO> products = ProductUtilities
                        .fromProductToProductDTO(productRepository.getAllProducts());

                channel.basicPublish("", properties.getReplyTo(), replyProps, SerializationUtilities.getBytes(products));
                channel.basicAck(envelope.getDeliveryTag(), false);
                synchronized (this) {
                    this.notify();
                }
            }
        };
        channel.basicConsume(configurations.getRpcQueue(), false, consumer);
        while (true) {
            synchronized (consumer) {
                consumer.wait();
            }
        }
    }


    @PostConstruct
    private void postConstruct() throws IOException, InterruptedException {
        listenerService();
    }
}
