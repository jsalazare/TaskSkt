package org.microservice.service;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.common.configuration.Configurations;
import org.common.util.SerializationUtilities;
import org.microservice.interfaces.IProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService implements IProducerService{

	private Configurations configurations;
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;

    @Autowired
    public ProducerService (Configurations configurations) throws IOException, TimeoutException {
        this.configurations = configurations;
    	factory = new ConnectionFactory();
    	factory.setHost(configurations.getHost());
        factory.setUsername(configurations.getUsername());
        factory.setPassword(configurations.getPassword());

        connection = factory.newConnection();
        channel = connection.createChannel();

    }
    /**
     *  This method publishes a message
     * @param message
     */

    @Override
    public void produceMessage(String message) throws IOException {

        channel.queueDeclare(configurations.getQueueManagnent(), true, false, false, null);

        channel.basicPublish("", configurations.getQueueManagnent(), null, message.getBytes());

        System.out.println(" [x] Sent '" + message + "'");
          

    }
    
    /**
     *  This method publishes a message
     * @param message
     */
    @Override
    public void produceMessage(Object message) throws IOException {

            
            
        channel.queueDeclare(configurations.getQueueManagnent(), true, false, false, null);

        channel.basicPublish("", configurations.getQueueManagnent(), null, SerializationUtilities.getBytes(message));

        System.out.println(" [x] Sent '" + message + "'");
          

    }
    
}
