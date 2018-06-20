package org.nearsoft.interfaces;

import org.common.dbmodel.Product;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


public interface ProductService {


	List<Product> requestAllProducts() throws InterruptedException, ExecutionException, IOException, TimeoutException;

    void insertProduct(Product product) throws IOException, InterruptedException, TimeoutException;

}
