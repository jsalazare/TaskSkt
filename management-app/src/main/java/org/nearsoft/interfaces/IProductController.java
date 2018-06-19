package org.nearsoft.interfaces;

import org.common.dto.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;


public interface IProductController {

    ModelAndView newProduct();

    String newProductSave(ProductDTO product) throws InterruptedException, ExecutionException, IOException;

    String productList(Model model) throws InterruptedException, ExecutionException, IOException;

    ModelAndView handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException;
}
