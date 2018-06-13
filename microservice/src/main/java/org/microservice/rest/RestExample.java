package org.microservice.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.microservice.dbmodel.Product;
import org.microservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestExample {

	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping("/")
    public String index() {
		
        return "Hello world!";
    }
	
	/*@RequestMapping("/getProducts")
    public List<Product> getProducts() {
		List<Product>  myProducts = productRepository.getAllProducts();
		
		ArrayList<Product>  products =  (ArrayList<Product>) productRepository.findAll();
        return myProducts;
    }*/
	
	@RequestMapping("/insertProduct")
    public void insertProduct() {
		productRepository.insertProduct("name5", 14, 14, 10.6f, 23.23f);
		
    }
}
