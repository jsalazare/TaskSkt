package org.nearsoft.service;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.common.dto.ProductDTO;
import org.common.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

//propiedades por constructo o por setter.
@Service
public class ProductService {

	public static List<ProductDTO> productList = new ArrayList<ProductDTO>();

	private ProducerService producerService;
	private ConsumerService consumerService;

	@Autowired
	public ProductService(ProducerService producerService, ConsumerService consumerService) {
		this.producerService = producerService;
		this.consumerService = consumerService;
	}



	@PostConstruct
	private void init() throws InterruptedException, ExecutionException, IOException {
		//requestAllProducts();
		producerService.produceMessage("testMessage");
        requestAllProducts();
	}

	public void requestAllProducts() throws InterruptedException, ExecutionException, IOException {
		producerService.produceMessage("getAllProducts");
		
		consumerService.listenerService(new DefaultConsumer(consumerService.getChannel()) {
			@SuppressWarnings("unchecked")
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				productList = (List<ProductDTO>) Utilities.fromBytes(body);
			}
		});
	}
	
	public void insertProduct(ProductDTO product) throws IOException {
		producerService.produceMessage(product);
	}

	public List<ProductDTO> getProductList() {
		return productList;
	}

}
