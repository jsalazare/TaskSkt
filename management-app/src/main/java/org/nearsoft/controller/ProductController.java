package org.nearsoft.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.common.dto.ProductDTO;
import org.nearsoft.WebApplication;
import org.nearsoft.service.ProducerService;
import org.nearsoft.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	private ProductService productService;

	@RequestMapping(value = { "/newProduct" }, method = RequestMethod.GET)
	public ModelAndView newUser() {
		ProductDTO product = new ProductDTO();

		ModelAndView modelAndView = new ModelAndView("product/newProduct", "product", product);
		return modelAndView;
	}

	@RequestMapping(value = { "/newProduct" }, method = RequestMethod.POST)
	public String newUserSave(ProductDTO product) throws InterruptedException, ExecutionException, IOException {
		// Logic for saving element, maybe calling rabbit service here.
		productService.insertProduct(product);
		productService.requestAllProducts();
		Thread.sleep(2000);//sleep to thread just for waiting rabbit answer (not good practice)
		return "redirect:newProduct";
	}

	@RequestMapping(value = { "/productList" }, method = RequestMethod.GET)
	public String productList(Model model) throws InterruptedException, ExecutionException, IOException {
		
		productService.requestAllProducts();
		
		model.addAttribute("myProducts",WebApplication.productList);
	    
		return "product/productList";
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}

}
