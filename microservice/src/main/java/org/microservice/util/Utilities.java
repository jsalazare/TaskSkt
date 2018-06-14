package org.microservice.util;

import java.util.ArrayList;
import java.util.List;

import org.common.dto.ProductDTO;
import org.microservice.dbmodel.Product;

public class Utilities {

public static List<ProductDTO> fromProductToProductDTO(List<Product> products) {
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		
		products.forEach(e -> {
			ProductDTO p = new ProductDTO();
			p.setName(e.getName());
			p.setLength(e.getLength());
			p.setWidth(e.getWidth());
			p.setHeigth(e.getHeigth());
			p.setWeight(e.getWeight());
			list.add(p);
		});
		
		return list;
		
	}
}