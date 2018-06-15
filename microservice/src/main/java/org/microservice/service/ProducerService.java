package org.microservice.service;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.common.configuration.Configurations;
import org.common.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

	private Configurations configurations;
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;

    @Autowired
    public ProducerService (Configurations configurations) {
        this.configurations = configurations;
    	factory = new ConnectionFactory();
    	factory.setHost(configurations.getHost());
        factory.setUsername(configurations.getUsername());
        factory.setPassword(configurations.getPassword());
        
        try {
        	connection = factory.newConnection();
			channel = connection.createChannel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     *  This method publishes a message
     * @param message
     */
    public void produceMessage(String message) {
        try {
            channel.queueDeclare(configurations.getQueueManagnent(), true, false, false, null);
           
            channel.basicPublish("", configurations.getQueueManagnent(), null, message.getBytes());
            
            System.out.println(" [x] Sent '" + message + "'");
          
        } catch (IOException io) {
            System.out.println("IOException");
            io.printStackTrace();
        } 
    }
    
    /**
     *  This method publishes a message
     * @param message
     */
    public void produceMessage(Object message) {
        try {
            
            
            channel.queueDeclare(configurations.getQueueManagnent(), true, false, false, null);
            
            channel.basicPublish("", configurations.getQueueManagnent(), null, Utilities.getBytes(message));
            
            System.out.println(" [x] Sent '" + message + "'");
          
        } catch (IOException io) {
            System.out.println("IOException");
            io.printStackTrace();
        } 
    }
    
}
