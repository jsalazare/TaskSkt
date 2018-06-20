package org.nearsoft.service;

import org.common.dbmodel.Product;
import org.common.dto.ProductDTO;
import org.nearsoft.interfaces.ProducerService;
import org.nearsoft.interfaces.ProductService;
import org.nearsoft.interfaces.RPCClientService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


@Service
public class ProductServiceImpl implements ProductService {


	private RPCClientService rpcClientService;
	private ProducerService producerService;


	public ProductServiceImpl(ProducerService producerService, RPCClientService rpcClientService) {
		this.producerService = producerService;
		this.rpcClientService = rpcClientService;
	}



	@Override
	public List<Product> requestAllProducts() throws InterruptedException, ExecutionException, IOException, TimeoutException {
		return rpcClientService.produceMessage("getAllProducts");
	}

	@Override
	public void insertProduct(ProductDTO product) throws IOException, InterruptedException, TimeoutException {
		producerService.produceMessage(product);
	}


}
