package org.nearsoft.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;

import org.common.util.Utilities;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

@Service
public class ConsumerService {
	
	/**
	 *  This configurations should come from common Library.
	 */
	private final static String QUEUE_NAME = "hello";
	private final static String USERNAME = "admin";
	private final static String PASSWORD = "admin";
	private final static String HOST = "localhost";
	
	public void listenerService () {
	    try {
	    	ConnectionFactory factory = new ConnectionFactory();
		    factory.setHost(HOST);
		    factory.setUsername(USERNAME);
		    factory.setPassword(PASSWORD);
		    Connection connection = factory.newConnection();
		    Channel channel = connection.createChannel();
			channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		    Consumer consumer = new DefaultConsumer(channel) {
		      @Override
		      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
		          throws IOException {
		    	  Object o = Utilities.fromBytes(body);
		         System.out.println(" [x] Received '" + body + "'");
		      }
		    };
		    channel.basicConsume(QUEUE_NAME, true, consumer);
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	
	@PostConstruct
	public void postConstruct() {
		listenerService();
	}
}
