package org.nearsoft.service;

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

	@Autowired
	private Configurations configurations;

	private ConnectionFactory factory;

	private Connection connection;
	private Channel channel;

	@Autowired
	public ProducerService(Configurations configurations) {
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
	 * This method publishes a message
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void produceMessage(String message) throws IOException {

		channel.queueDeclare(configurations.getQueueMicroservice(), true, false, false, null);

		channel.basicPublish("", configurations.getQueueMicroservice(), null, Utilities.getBytes(message));

		System.out.println(" [x] Sent '" + message + "'");

	}

	/**
	 * This method publishes a message
	 * 
	 * @param message
	 * @throws IOException 
	 */
	public void produceMessage(Object message) throws IOException {

		channel.queueDeclare(configurations.getQueueMicroservice(), true, false, false, null);

		channel.basicPublish("", configurations.getQueueMicroservice(), null, Utilities.getBytes(message));

		System.out.println(" [x] Sent '" + message + "'");

	}

}
