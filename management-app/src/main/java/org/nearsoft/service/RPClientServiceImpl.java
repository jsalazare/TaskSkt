package org.nearsoft.service;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.common.configuration.Configurations;
import org.common.dbmodel.Product;
import org.common.dto.ProductDTO;
import org.common.interfaces.ChannelFactory;
import org.common.util.SerializationUtilities;
import org.nearsoft.interfaces.RPCClientService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;


@Service
public class RPClientServiceImpl implements RPCClientService {

    private Configurations configurations;

    private ChannelFactory channelFactory;

    public RPClientServiceImpl(Configurations configurations, ChannelFactory channelFactory) throws IOException, TimeoutException {
        this.configurations = configurations;
        this.channelFactory = channelFactory;
    }

    /**
     * This method publishes an object

     * @param message
     * @throws IOException
     */

    public List<Product> produceMessage(Object message) throws IOException, InterruptedException, TimeoutException {

        Channel channel = channelFactory.getNewChannel();
        String replyQueueName = channel.queueDeclare().getQueue();

        final String corrId = UUID.randomUUID().toString();

        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        channel.basicPublish("", configurations.getRpcQueue(), props,SerializationUtilities.getBytes(message));

        final BlockingQueue<List<Product>> response = new ArrayBlockingQueue<List<Product>>(1);

        channel.basicConsume(replyQueueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                if (properties.getCorrelationId().equals(corrId)) {
                    List<Product> list = (List<Product>) SerializationUtilities.fromBytes(body);
                    response.offer(list);
                }
            }
        });

        return response.take();

    }

}
