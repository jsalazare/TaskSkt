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
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public class ConsumerService2 {


	private IConfigurations configurations;
	private ProductRepository productRepository;
	private IProducerService producerService;
	private static final String RPC_QUEUE_NAME = "rpc_queue";
	private Channel channel;


	public ConsumerService2(ProductRepository productRepository, IProducerService producerService, IConfigurations configurations, IChannelFactory channelFactory) throws IOException, TimeoutException {
		this.configurations = configurations;
		this.producerService = producerService;
		this.productRepository = productRepository;
		channel = channelFactory.getNewChannel();

	}


	public void listenerService() throws IOException {

		channel.queueDeclare(RPC_QUEUE_NAME, true, false, false, null);
		channel.basicQos(1);
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				AMQP.BasicProperties replyProps = new AMQP.BasicProperties
						.Builder()
						.correlationId(properties.getCorrelationId())
						.build();

				String response = "";
				List<ProductDTO> products =  new ArrayList<ProductDTO>();
				try {

					Object message = SerializationUtilities.fromBytes(body);
					if (message instanceof String) {
						if (message.equals("getAllProducts")) {

							 products = ProductUtilities
									.fromProductToProductDTO(productRepository.getAllProducts());
							producerService.produceMessage(products);
						}
					} else if (message instanceof ProductDTO) {
						ProductDTO product = (ProductDTO) SerializationUtilities.fromBytes(body);


						productRepository.insertProduct(product.getName(), product.getLength(), product.getWidth(),
								product.getHeight(), product.getWeight());
					}

				} catch (RuntimeException e) {
					System.out.println(" [.] " + e.toString());
				} finally {
					channel.basicPublish("", properties.getReplyTo(), replyProps, SerializationUtilities.getBytes(products));
					channel.basicAck(envelope.getDeliveryTag(), false);
					// RabbitMq consumer worker thread notifies the RPC server owner thread
					synchronized (this) {
						this.notify();
					}
				}

			}
		};
		channel.basicConsume(RPC_QUEUE_NAME, false, consumer);
		// Wait and be prepared to consume the message from RPC client.
		while (true) {
			synchronized (consumer) {
				try {
					consumer.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}




	@PostConstruct
	private void postConstruct() throws IOException {
		listenerService();
	}
}
