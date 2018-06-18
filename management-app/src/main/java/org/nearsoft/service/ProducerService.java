package org.nearsoft.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import org.common.interfaces.IConfigurations;
import org.common.util.SerializationUtilities;
import org.nearsoft.interfaces.IProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

;

@Service
public class ProducerService implements IProducerService{


    private IConfigurations configurations;

    private Channel channel;

    @Autowired
    public ProducerService(IConfigurations configurations) throws IOException, TimeoutException {
        this.configurations = configurations;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(configurations.getHost());
        factory.setUsername(configurations.getUsername());
        factory.setPassword(configurations.getPassword());
        channel = factory.newConnection().createChannel();
    }

    /**
     * This method publishes a message
     *
     * @param message
     * @throws IOException
     */

    @Override
    public void produceMessage(String message) throws IOException {

        channel.queueDeclare(configurations.getQueueMicroservice(), true, false, false, null);

        channel.basicPublish("", configurations.getQueueMicroservice(), null, SerializationUtilities.getBytes(message));

        System.out.println(" [x] Sent '" + message + "'");

    }

    /**
     * This method publishes an object
     *
     * @param message
     * @throws IOException
     */

    @Override
    public void produceMessage(Object message) throws IOException {

        channel.queueDeclare(configurations.getQueueMicroservice(), true, false, false, null);

        channel.basicPublish("", configurations.getQueueMicroservice(), null, SerializationUtilities.getBytes(message));

        System.out.println(" [x] Sent '" + message + "'");

    }

}
