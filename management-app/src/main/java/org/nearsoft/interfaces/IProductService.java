package org.nearsoft.interfaces;

import org.common.dto.ProductDTO;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by jsalazar on 6/18/18.
 */
public interface IProductService {


    void requestAllProducts() throws InterruptedException, ExecutionException, IOException;

    void insertProduct(ProductDTO product) throws IOException;

    List<ProductDTO> getProductList();
}
