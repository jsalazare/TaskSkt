package org.microservice.test;


import java.util.List;

import org.microservice.dbmodel.Product;
import org.microservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductTest {

	
	@Autowired
	private ProductRepository productRepository;
	
	/*@Test
	public void getProducts() {
		List<Product> mypProducts = (List<Product>) productRepository.findAll();
		
		assertNotEquals(0, mypProducts.size());
		assertNotNull(mypProducts);
	}*/

}
