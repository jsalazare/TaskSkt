package org.microservice.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;

import org.common.configuration.Configurations;
import org.common.dto.ProductDTO;
import org.common.util.Utilities;
import org.microservice.dbmodel.Product;
import org.microservice.repository.ProductRepository;
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

	/**
	 * This configurations should come from common Library.
	 */
	private String QUEUE_NAME = Configurations.rabbitQueueManagementToMicroservice;
	private String USERNAME = Configurations.rabbitUsername;
	private String PASSWORD = Configurations.rabbitPassword;
	private String HOST = Configurations.rabbitHost;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProducerService producerService;

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

	public void listenerService() throws IOException {
		

			channel.queueDeclare(QUEUE_NAME, true, false, false, null);
			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {

					Object message = Utilities.fromBytes(body);
					if (message instanceof String) {
						if (message.equals("getAllProducts")) {

							List<ProductDTO> products = org.microservice.util.Utilities
									.fromProductToProductDTO(productRepository.getAllProducts());
							producerService.produceMessage(products);
						}
					} else if (message instanceof ProductDTO) {
						ProductDTO product = (ProductDTO) Utilities.fromBytes(body);

						System.out.println(" [x] Received '" + body + "'");
						productRepository.insertProduct(product.getName(), product.getLength(), product.getWidth(),
								product.getHeigth(), product.getWeight());
					}

				}
			};
			channel.basicConsume(QUEUE_NAME, true, consumer);

		

	}

	@PostConstruct
	public void postConstruct() throws IOException {
		listenerService();
	}
}
