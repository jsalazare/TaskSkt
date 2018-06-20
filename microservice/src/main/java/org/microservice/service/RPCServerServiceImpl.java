package org.microservice.service;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.apache.commons.lang3.StringUtils;
import org.common.configuration.Configurations;
import org.common.dbmodel.Product;
import org.common.interfaces.ChannelFactory;
import org.common.util.SerializationUtilities;
import org.microservice.interfaces.RPCServerService;
import org.microservice.repository.ProductRepository;
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

    @Override
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

                List<Product> products = productRepository.getAllProducts();

                channel.basicPublish(StringUtils.EMPTY, properties.getReplyTo(), replyProps, SerializationUtilities.getBytes(products));
                channel.basicAck(envelope.getDeliveryTag(), false);

            }
        };
        channel.basicConsume(configurations.getRpcQueue(), false, consumer);

    }


    @PostConstruct
    private void postConstruct() throws IOException, InterruptedException {
        listenerService();
    }
}
