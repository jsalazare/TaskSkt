package org.microservice.dbmodel;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;



@Entity
@Table(name = "Product")
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "getAllProducts", procedureName = "get_all_products", resultClasses = Product.class),
		
		@NamedStoredProcedureQuery(name = "insertProduct", procedureName = "insert_product", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "nameParam", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "lengthParam", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "widthParam", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "heigthParam", type = Float.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "weightParam", type = Float.class),
				})
		})
public class Product implements Serializable {


	private static final long serialVersionUID = -108097060161639783L;

	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name")
	@NotEmpty(message = "*Please provide your name")
	private String name;

	@Column(name = "length")
	@NotEmpty(message = "*Please provide a length")
	private int length;

	@Column(name = "width")
	@NotEmpty(message = "*Please provide a width")
	private int width;

	@Column(name = "heigth")
	@NotEmpty(message = "*Please provide your a heigth")
	private float height;

	@Column(name = "weight")
	@NotEmpty(message = "*Please provide a weight")
	private float weight;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	

}
