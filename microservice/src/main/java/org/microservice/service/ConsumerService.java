package org.microservice.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;

import org.common.configuration.Configurations;
import org.common.dto.ProductDTO;
import org.common.interfaces.IConfigurations;
import org.common.util.SerializationUtilities;
import org.microservice.interfaces.IConsumerService;
import org.microservice.interfaces.IProducerService;
import org.microservice.repository.ProductRepository;
import org.microservice.util.ProductUtilities;
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
public class ConsumerService implements IConsumerService {

	private IConfigurations configurations;
	private ProductRepository productRepository;
	private IProducerService producerService;

	private Channel channel;

	@Autowired
	public ConsumerService(ProductRepository productRepository, IProducerService producerService, IConfigurations configurations) throws IOException, TimeoutException {
		this.configurations = configurations;
		this.producerService = producerService;
		this.productRepository = productRepository;
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(configurations.getHost());
		factory.setUsername(configurations.getUsername());
		factory.setPassword(configurations.getPassword());
		channel = factory.newConnection().createChannel();
	}

	@Override
	public void listenerService() throws IOException {

			channel.queueDeclare(configurations.getQueueMicroservice(), true, false, false, null);
			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {

					Object message = SerializationUtilities.fromBytes(body);
					if (message instanceof String) {
						if (message.equals("getAllProducts")) {

							List<ProductDTO> products = ProductUtilities
									.fromProductToProductDTO(productRepository.getAllProducts());
							producerService.produceMessage(products);
						}
					} else if (message instanceof ProductDTO) {
						ProductDTO product = (ProductDTO) SerializationUtilities.fromBytes(body);

						System.out.println(" [x] Received '" + body + "'");
						productRepository.insertProduct(product.getName(), product.getLength(), product.getWidth(),
								product.getHeight(), product.getWeight());
					}

				}
			};
			channel.basicConsume(configurations.getQueueMicroservice(), true, consumer);
	}

	@PostConstruct
	private void postConstruct() throws IOException {
		listenerService();
	}
}
