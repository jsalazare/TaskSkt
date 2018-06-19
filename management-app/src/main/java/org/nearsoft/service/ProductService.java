package org.nearsoft.service;

import org.common.dto.ProductDTO;
import org.nearsoft.interfaces.IConsumerService;
import org.nearsoft.interfaces.IProducerService;
import org.nearsoft.interfaces.IProductService;
import org.nearsoft.interfaces.IRPCClientService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


@Service
public class ProductService implements IProductService {


	private IRPCClientService rpcClientService;
	private IProducerService producerService;


	public ProductService(IProducerService producerService, IRPCClientService rpcClientService) {
		this.producerService = producerService;
		this.rpcClientService = rpcClientService;
	}



	@Override
	public List<ProductDTO> requestAllProducts() throws InterruptedException, ExecutionException, IOException, TimeoutException {
		return rpcClientService.produceMessage("getAllProducts");
	}

	@Override
	public void insertProduct(ProductDTO product) throws IOException, InterruptedException, TimeoutException {
		producerService.produceMessage(product);
	}


}
