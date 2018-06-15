package org.nearsoft.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;

import org.common.configuration.Configurations;
import org.springframework.beans.factory.annotation.Autowired;
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


	private Configurations configurations;

	private String consumerTag;

	private ConnectionFactory factory;

	private Connection connection;
	private Channel channel;


	@Autowired
	public ConsumerService(Configurations configurations) {
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

	// Permanent Listener
	public void listenerService() {
		try {

			channel.queueDeclare(configurations.getQueueManagnent(), true, false, false, null);
			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {

				}
			};
			channel.basicConsume(configurations.getQueueManagnent(), true, consumer);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void listenerService(Consumer consumer) throws IOException {

		channel.queueDeclare(configurations.getQueueManagnent(), true, false, false, null);

		consumerTag = channel.basicConsume(configurations.getQueueManagnent(), true, consumer);

	}

	public void stopListening() throws IOException {

		channel.basicCancel(consumerTag);

	}

	public Channel getChannel() {
		return channel;
	}

	@PostConstruct
	public void postConstruct() {
		listenerService();
	}
}
