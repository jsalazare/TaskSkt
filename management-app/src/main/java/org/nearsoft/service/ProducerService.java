package org.nearsoft.service;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.common.util.Utilities;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

	/**
     *  The name of the Queue
     */
    private final static String QUEUE_NAME = "hello";
    private final static String USERNAME = "admin";
    private final static String PASSWORD = "admin";
    private final static String HOST = "localhost";
    
    private ConnectionFactory factory;
    
    private Connection connection;
    private Channel channel;
    
    public ProducerService () {
    	factory = new ConnectionFactory();
    	
    	//Values should come from common library
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
          
            channel.close();
            connection.close();
        } catch (IOException io) {
            System.out.println("IOException");
            io.printStackTrace();
        } catch (TimeoutException toe) {
            System.out.println("TimeoutException : " + toe.getMessage());
            toe.printStackTrace();
        }
    }
    
    /**
     *  This method publishes a message
     * @param message
     */
    public void produceMessage(Object message) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            factory.setUsername("admin");
            factory.setPassword("admin");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            
            channel.basicPublish("", QUEUE_NAME, null, Utilities.getBytes(message));
            
            System.out.println(" [x] Sent '" + message + "'");
          
            channel.close();
            connection.close();
        } catch (IOException io) {
            System.out.println("IOException");
            io.printStackTrace();
        } catch (TimeoutException toe) {
            System.out.println("TimeoutException : " + toe.getMessage());
            toe.printStackTrace();
        }
    }
    
}
