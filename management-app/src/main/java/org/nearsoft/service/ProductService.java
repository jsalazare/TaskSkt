package org.nearsoft.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Supplier;

import org.common.dto.ProductDTO;
import org.common.util.Utilities;
import org.ehcache.xml.model.TimeUnit;
import org.nearsoft.WebApplication;
import org.nearsoft.controller.ProductController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.terracotta.statistics.Time;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

//propiedades por constructo o por setter.
@Service
public class ProductService {

	@Autowired
	private ProducerService producerService;

	private volatile List<ProductDTO> products;

	@Autowired
	private ConsumerService consumerService;

	public void requestAllProducts() throws InterruptedException, ExecutionException {
		producerService.produceMessage(new String("getAllProducts"));
		
		consumerService.listenerService(new DefaultConsumer(consumerService.getChannel()) {
			@SuppressWarnings("unchecked")
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				WebApplication.productList = (List<ProductDTO>) Utilities.fromBytes(body);
			}
		});
	}

	/*
	 * public List<ProductDTO> requestAllProducts() throws InterruptedException,
	 * ExecutionException { producerService.produceMessage(new
	 * String("getAllProducts")); final CompletableFuture<List<ProductDTO>>
	 * completableFuture = new CompletableFuture<List<ProductDTO>>();
	 * //List<ProductDTO> products = null; completableFuture.runAsync(() -> {
	 * consumerService.listenerService(new
	 * DefaultConsumer(consumerService.getChannel()) {
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public void handleDelivery(String consumerTag, Envelope envelope,
	 * AMQP.BasicProperties properties, byte[] body) throws IOException {
	 * WebApplication.productList = (List<ProductDTO>) Utilities.fromBytes(body); //
	 * ProductController.products = products;
	 * //completableFuture.complete(products); } });
	 * 
	 * });
	 * 
	 * return Collections.unmodifiableList(completableFuture.get()); }
	 */

	/*
	 * public List<ProductDTO> requestAllProducts() throws InterruptedException,
	 * ExecutionException { producerService.produceMessage(new
	 * String("getAllProducts")); final CompletableFuture<List<ProductDTO>>
	 * completableFuture = CompletableFuture.supplyAsync(new
	 * Supplier<List<ProductDTO>>() {
	 * 
	 * 
	 * @Override public List<ProductDTO> get() { consumerService.listenerService(new
	 * DefaultConsumer(consumerService.getChannel()) {
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public void handleDelivery(String consumerTag, Envelope envelope,
	 * AMQP.BasicProperties properties, byte[] body) throws IOException {
	 * WebApplication.productList = (List<ProductDTO>) Utilities.fromBytes(body);
	 * //completableFuture.complete(products);
	 * 
	 * try { Thread.sleep(2000); } catch (InterruptedException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * });
	 * 
	 * return products; }
	 * 
	 * 
	 * 
	 * });
	 * 
	 * return completableFuture.get(); }
	 */

}
