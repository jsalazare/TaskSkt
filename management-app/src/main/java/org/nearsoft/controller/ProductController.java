package org.nearsoft.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.common.dto.ProductDTO;
import org.nearsoft.WebApplication;
import org.nearsoft.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/*
 * 
 * Controller for all users views
 * 
 * */

//add general paths

@RequestMapping("product/")
@Controller
public class ProductController {

	private ProductService productService;

    @Autowired
    public ProductController (ProductService productService){
        this.productService = productService;
    }

	@RequestMapping(value = { "/newProduct" }, method = RequestMethod.GET)
	public ModelAndView newUser() {
		ProductDTO product = new ProductDTO();

		ModelAndView modelAndView = new ModelAndView("product/newProduct", "product", product);
		return modelAndView;
	}

	@RequestMapping(value = { "/newProduct" }, method = RequestMethod.POST)
	public String newUserSave(ProductDTO product) throws InterruptedException, ExecutionException, IOException {

		productService.insertProduct(product);

		return "redirect:newProduct";
	}

	@RequestMapping(value = { "/productList" }, method = RequestMethod.GET)
	public String productList(Model model) throws InterruptedException, ExecutionException, IOException {

		productService.requestAllProducts();
        Thread.sleep(2000);
		model.addAttribute("myProducts", productService.getProductList());
	    
		return "product/productList";
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}

	@ExceptionHandler
	public ModelAndView handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response)
			throws IOException {
		e.printStackTrace();
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return modelAndView;
	}

}
