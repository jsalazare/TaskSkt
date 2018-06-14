package org.nearsoft.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.common.dto.MyMessage;
import org.common.dto.ProductDTO;
import org.common.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

//propiedades por constructo o por setter.
@Service
public class ProductService {

	@Autowired
	private ProducerService producerService;

	@Autowired
	private ConsumerService consumerService;

	public Future<List<ProductDTO>> requestAllProducts() throws InterruptedException {
		MyMessage message = new MyMessage();
		message.setMessage("getAllProducts");
		producerService.produceMessage(new String("getAllProducts"));
		final CompletableFuture<List<ProductDTO>> completableFuture = new CompletableFuture<List<ProductDTO>>();
		List<ProductDTO> products = null;
		consumerService.listenerService(new DefaultConsumer(consumerService.getChannel()) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				List<ProductDTO> products = (List<ProductDTO>) Utilities.fromBytes(body);
				completableFuture.complete(products);
				
			}
		});
		return completableFuture;
	}
}
