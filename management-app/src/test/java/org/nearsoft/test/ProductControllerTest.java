package org.nearsoft.test;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nearsoft.controller.ProductControllerImpl;
import org.nearsoft.interfaces.ProductController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

@RunWith(SpringRunner.class)
public class ProductControllerTest {

	@Autowired
	private ProductController productController;

	@BeforeClass
	public void beforeClass() throws Exception {

	}

	@Test
	public void newProductTest() {
		Model m = new ExtendedModelMap();
		productController.newProduct(m);
	}

}
