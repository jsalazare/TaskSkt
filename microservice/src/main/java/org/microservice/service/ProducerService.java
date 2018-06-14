package org.microservice.service;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.common.configuration.Configurations;
import org.common.util.Utilities;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

	/**
     *  The name of the Queue
     */
	private String QUEUE_NAME = Configurations.rabbitQueueMicroserviceToManagement;
    private String USERNAME = Configurations.rabbitUsername;
	private String PASSWORD = Configurations.rabbitPassword;
	private String HOST = Configurations.rabbitHost;
    
    private ConnectionFactory factory;
    
    private Connection connection;
    private Channel channel;
    
    public ProducerService () {
    	factory = new ConnectionFactory();
    	factory.setHost(HOST);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        
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
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
           
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            
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
            
            
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            
            channel.basicPublish("", QUEUE_NAME, null, Utilities.getBytes(message));
            
            System.out.println(" [x] Sent '" + message + "'");
          
        } catch (IOException io) {
            System.out.println("IOException");
            io.printStackTrace();
        } 
    }
    
}
