package org.nearsoft.service;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.common.dto.ProductDTO;
import org.common.interfaces.IChannelFactory;
import org.common.interfaces.IConfigurations;
import org.common.util.SerializationUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;


@Service
public class ProducerService2{
    private static final Logger logger = LoggerFactory.getLogger(ProducerService2.class);

    private IConfigurations configurations;

    private Channel channel;

    private String requestQueueName = "rpc_queue";
    private String replyQueueName;

    private IChannelFactory channelFactory;


    public ProducerService2(IConfigurations configurations, IChannelFactory channelFactory) throws IOException, TimeoutException {
        this.configurations = configurations;
        channel = channelFactory.getNewChannel();
        replyQueueName = channel.queueDeclare().getQueue();
        this.channelFactory = channelFactory;
    }

    /**
     * This method publishes an object

     * @param message
     * @throws IOException
     */

    public List<ProductDTO> produceMessage(Object message) throws IOException, InterruptedException, TimeoutException {

        channel = channelFactory.getNewChannel();
        replyQueueName = channel.queueDeclare().getQueue();

        final String corrId = UUID.randomUUID().toString();

        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        channel.basicPublish("", requestQueueName, props,SerializationUtilities.getBytes(message));

        final BlockingQueue<List<ProductDTO>> response = new ArrayBlockingQueue<List<ProductDTO>>(1);

        channel.basicConsume(replyQueueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                if (properties.getCorrelationId().equals(corrId)) {
                    List<ProductDTO> list = (List<ProductDTO>) SerializationUtilities.fromBytes(body);
                    response.offer(list);
                }
            }
        });

        return response.take();

    }

}
