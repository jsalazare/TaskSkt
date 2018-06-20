package org.microservice.service;

import com.rabbitmq.client.*;
import org.common.configuration.Configurations;
import org.common.dbmodel.Product;
import org.common.dto.ProductDTO;
import org.common.interfaces.ChannelFactory;
import org.common.util.SerializationUtilities;
import org.microservice.interfaces.ConsumerService;
import org.microservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class ConsumerServiceImpl implements ConsumerService {


	private Configurations configurations;
	private ProductRepository productRepository;

	private Channel channel;


	public ConsumerServiceImpl(ProductRepository productRepository, Configurations configurations, ChannelFactory channelFactory) throws IOException, TimeoutException {
		this.configurations = configurations;
		this.productRepository = productRepository;
		channel = channelFactory.getNewChannel();
	}

	@Override
	public void listenerService() throws IOException {

			channel.queueDeclare(configurations.getQueueMicroservice(), true, false, false, null);
			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					Object message = SerializationUtilities.fromBytes(body);
					if (message instanceof Product) {
						Product product = (Product) SerializationUtilities.fromBytes(body);
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
