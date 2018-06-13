package org.nearsoft.controller;

import java.util.ArrayList;
import java.util.List;

import org.common.dto.ProductDTO;
import org.common.model.Product;
import org.nearsoft.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/*
 * 
 * Controller for all users views
 * 
 * */

//add general paths

@Controller
public class ProductController {
	
	@Autowired
	private ProducerService producerService;

	@RequestMapping(value = { "/newProduct" }, method = RequestMethod.GET)
	public ModelAndView newUser() {
		Product product = new Product();
		ModelAndView modelAndView = new ModelAndView("product/newProduct", "product", product);
		return modelAndView;
	}

	@RequestMapping(value = { "/newProduct" }, method = RequestMethod.POST)
	public void newUserSave(ProductDTO product) {
		// Logic for saving element, maybe calling rabbit service here.
		producerService.produceMessage(product);
		System.out.println("print");
	}

	@RequestMapping(value = { "/productList" }, method = RequestMethod.GET)
	public ModelAndView userList() {
		List<Product> myProducts = new ArrayList<Product>();
		Product product = new Product();
		product.setName("name1");
		product.setLength(34);
		product.setWidth(22);
		product.setWeight(1.23);
		product.setHeigth(23.12);
		
		myProducts.add(product); 
		myProducts.add(product);
		myProducts.add(product);
		myProducts.add(product);

		ModelAndView modelAndView = new ModelAndView("product/productList", "myProducts", myProducts);
		return modelAndView;
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}


	
	@RequestMapping("/sendMessage")
	public void sendMessage() {
		producerService.produceMessage("example text");
	}
	
	@RequestMapping("/sendMessage2")
	public void sendMessageObject(@RequestBody ProductDTO product) {
		producerService.produceMessage(product);
	}

}
