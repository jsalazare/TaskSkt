package org.nearsoft.interfaces;

import org.common.dto.ProductDTO;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


public interface ProductController {

    String newProduct(Model model);

    String newProductSave(ProductDTO product) throws InterruptedException, ExecutionException, IOException, TimeoutException;

    String productList(Model model) throws InterruptedException, ExecutionException, IOException, TimeoutException;

    ModelAndView handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response, ModelAndView modelAndView) throws IOException;
}
