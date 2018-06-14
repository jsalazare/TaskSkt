package org.nearsoft.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.common.dto.ProductDTO;
import org.common.model.Product;
import org.nearsoft.service.ProducerService;
import org.nearsoft.service.ProductService;
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
	
	private static List<ProductDTO> products;
	
	@Autowired
	private ProductService productService;

	@RequestMapping(value = { "/newProduct" }, method = RequestMethod.GET)
	public ModelAndView newUser() {
		Product product = new Product();
		ModelAndView modelAndView = new ModelAndView("product/newProduct", "product", product);
		return modelAndView;
	}

	@RequestMapping(value = { "/newProduct" }, method = RequestMethod.POST)
	public String newUserSave(ProductDTO product) {
		// Logic for saving element, maybe calling rabbit service here.
		producerService.produceMessage(product);
		return "redirect:newProduct";
	}

	@RequestMapping(value = { "/productList" }, method = RequestMethod.GET)
	public ModelAndView userList() {

		try {
			products = productService.requestAllProducts().get();
			products.size();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 List<ProductDTO> products2 = new ArrayList<ProductDTO>();
		
		 products = new ArrayList<ProductDTO>();
		 ProductDTO p = new ProductDTO();
		 p.setName("cacaca");
		 products.add(p);
		ModelAndView modelAndView = new ModelAndView("product/productList", "myProducts", products);
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
