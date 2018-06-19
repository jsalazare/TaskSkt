package org.nearsoft.service;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.common.dto.ProductDTO;
import org.common.util.SerializationUtilities;
import org.nearsoft.interfaces.IConsumerService;
import org.nearsoft.interfaces.IProducerService;
import org.nearsoft.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


@Service
public class ProductService implements IProductService {

	public static List<ProductDTO> productList = new ArrayList<ProductDTO>();

	private IProducerService producerService;
	private IConsumerService consumerService;


	public ProductService(ProducerService producerService, ConsumerService consumerService) {
		this.producerService = producerService;
		this.consumerService = consumerService;
	}

	@PostConstruct
	private void init() throws InterruptedException, ExecutionException, IOException {
		producerService.produceMessage("testMessage");
        requestAllProducts();
	}

	@Override
	public void requestAllProducts() throws InterruptedException, ExecutionException, IOException {
		producerService.produceMessage("getAllProducts");
		
		consumerService.listenerService(new DefaultConsumer(consumerService.getChannel()) {
			@SuppressWarnings("unchecked")
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				productList = (List<ProductDTO>) SerializationUtilities.fromBytes(body);
			}
		});
	}

	@Override
	public void insertProduct(ProductDTO product) throws IOException {
		producerService.produceMessage(product);
	}

	@Override
	public List<ProductDTO> getProductList() {
		return productList;
	}

}
