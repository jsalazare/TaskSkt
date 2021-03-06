package org.nearsoft;


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nearsoft.controller.ProductControllerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {

	@Autowired
	private ProductControllerImpl productController;

	@BeforeClass
	public static void beforeClass() throws Exception {

	}

	@Test
	public void newProductShouldAddAttributeProduct() {
		Model m = new ExtendedModelMap();
		productController.newProduct(m);
        boolean result =  m.containsAttribute("product");
        assertThat(result).isTrue();
    }

    @Test
    public void productListShouldAddAttributeMyProducts() throws InterruptedException, ExecutionException, TimeoutException, IOException {
        Model m = new ExtendedModelMap();
        productController.productList(m);
        boolean result =  m.containsAttribute("myProducts");
        assertThat(result).isTrue();
    }
}
