package org.microservice.dbmodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = -108097060161639783L;

	@Id
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
	private float heigth;

	@Column(name = "weight")
	@NotEmpty(message = "*Please provide a weight")
	private float weight;

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

	public float getHeigth() {
		return heigth;
	}

	public void setHeigth(float heigth) {
		this.heigth = heigth;
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
