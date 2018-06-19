package org.microservice.service;

import com.rabbitmq.client.*;
import org.common.dto.ProductDTO;
import org.common.interfaces.IChannelFactory;
import org.common.interfaces.IConfigurations;
import org.common.util.SerializationUtilities;
import org.microservice.interfaces.IConsumerService;
import org.microservice.interfaces.IProducerService;
import org.microservice.interfaces.IRPCServerService;
import org.microservice.repository.ProductRepository;
import org.microservice.util.ProductUtilities;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public class RPCServerService implements IRPCServerService {


	private IConfigurations configurations;
	private ProductRepository productRepository;
	private Channel channel;


	public RPCServerService(ProductRepository productRepository, IConfigurations configurations, IChannelFactory channelFactory) throws IOException, TimeoutException {
		this.configurations = configurations;
		this.productRepository = productRepository;
		channel = channelFactory.getNewChannel();

	}


	public void listenerService() throws IOException {

		channel.queueDeclare(configurations.getRpcQueue(), true, false, false, null);
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
					 products = ProductUtilities
							.fromProductToProductDTO(productRepository.getAllProducts());

				} catch (RuntimeException e) {
					e.printStackTrace();
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
		channel.basicConsume(configurations.getRpcQueue(), false, consumer);
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
