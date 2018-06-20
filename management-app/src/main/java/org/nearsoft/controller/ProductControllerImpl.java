package org.nearsoft.controller;

import org.common.dto.ProductDTO;
import org.nearsoft.interfaces.ProductController;
import org.nearsoft.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/*
 * 
 * Controller for all users views
 * 
 * */

//add general paths

@RequestMapping("/product")
@Controller
public class ProductControllerImpl implements ProductController {


    private ProductService productService;

    @Autowired
    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    @RequestMapping(value = {"/newProduct"}, method = RequestMethod.GET)
    public String newProduct(Model model) {
        ProductDTO product = new ProductDTO();

        model.addAttribute("product", product);
        return "product/newProduct";

    }

    @Override
    @RequestMapping(value = {"/newProduct"}, method = RequestMethod.POST)
    public String newProductSave(ProductDTO product) throws InterruptedException, ExecutionException, IOException, TimeoutException {

        productService.insertProduct(product);

        return "redirect:newProduct";
    }

    @Override
    @RequestMapping(value = {"/productList"}, method = RequestMethod.GET)
    public String productList(Model model) throws InterruptedException, ExecutionException, IOException, TimeoutException {

    	List<ProductDTO> products = productService.requestAllProducts();
        model.addAttribute("myProducts", products);

        return "product/productList";
    }

    @Override
    @ExceptionHandler
    public ModelAndView handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response, ModelAndView modelAndView)
            throws IOException {
        e.printStackTrace();
        modelAndView.setViewName("error");
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return modelAndView;
    }

}
