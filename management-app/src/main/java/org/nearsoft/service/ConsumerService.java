package org.nearsoft.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;

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
	 * This configurations should come from common Library.
	 */
	private final static String QUEUE_NAME = "microservice-management";
	private final static String USERNAME = "admin";
	private final static String PASSWORD = "admin";
	private final static String HOST = "localhost";

	private String consumerTag;

	private ConnectionFactory factory;

	private Connection connection;
	private Channel channel;

	public ConsumerService() {
		factory = new ConnectionFactory();

		// Values should come from common library
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

	// Permanent Listener
	public void listenerService() {
		try {

			channel.queueDeclare(QUEUE_NAME, true, false, false, null);
			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {

				}
			};
			channel.basicConsume(QUEUE_NAME, true, consumer);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void listenerService(Consumer consumer) {
		try {

			channel.queueDeclare(QUEUE_NAME, true, false, false, null);

			consumerTag = channel.basicConsume(QUEUE_NAME, true, consumer);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void stopListening() {
		try {
			channel.basicCancel(consumerTag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

	public Channel getChannel() {
		return channel;
	}

	
	@PostConstruct
	public void postConstruct() {
		listenerService();
	}
}
