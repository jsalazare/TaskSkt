package org.microservice.service;

import com.rabbitmq.client.*;
import org.common.dto.ProductDTO;
import org.common.interfaces.IChannelFactory;
import org.common.interfaces.IConfigurations;
import org.common.util.SerializationUtilities;
import org.microservice.interfaces.IConsumerService;
import org.microservice.interfaces.IProducerService;
import org.microservice.repository.ProductRepository;
import org.microservice.util.ProductUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public class ConsumerService implements IConsumerService {


	private IConfigurations configurations;
	private ProductRepository productRepository;
	private IProducerService producerService;

	private Channel channel;


	public ConsumerService(ProductRepository productRepository, IProducerService producerService, IConfigurations configurations, IChannelFactory channelFactory) throws IOException, TimeoutException {
		this.configurations = configurations;
		this.producerService = producerService;
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
					if (message instanceof ProductDTO) {
						ProductDTO product = (ProductDTO) SerializationUtilities.fromBytes(body);
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
