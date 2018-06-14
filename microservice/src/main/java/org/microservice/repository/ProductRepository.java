package org.microservice.repository;

import java.util.List;

import org.microservice.dbmodel.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends CrudRepository<Product, Long>{
	
	@Procedure(name = "insertProduct")
	void insertProduct(@Param("nameParam") String nameParam,
			@Param("lengthParam") int lengthParam,
			@Param("widthParam") int widthParam,
			@Param("heigthParam") float heigthParam,
			@Param("weightParam") float weightParam);
	
	@Query(value = "call get_all_products", nativeQuery = true)
	List<Product> getAllProducts();
	
}
