package org.nearsoft.interfaces;

import org.common.dto.ProductDTO;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


public interface ProductService {


	List<ProductDTO> requestAllProducts() throws InterruptedException, ExecutionException, IOException, TimeoutException;

    void insertProduct(ProductDTO product) throws IOException, InterruptedException, TimeoutException;

}
