package org.microservice.util;

import org.common.dto.ProductDTO;
import org.microservice.dbmodel.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductUtilities {

    public static List<ProductDTO> fromProductToProductDTO(List<Product> products) {

        return products.stream().map(e -> {
            ProductDTO p = new ProductDTO();
            p.setId(e.getId());
            p.setName(e.getName());
            p.setLength(e.getLength());
            p.setWidth(e.getWidth());
            p.setHeight(e.getHeight());
            p.setWeight(e.getWeight());
            return p;
        }).collect(Collectors.toList());


    }
}
