package org.common.dto;

import java.io.Serializable;

public class ProductDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7544567975802613970L;
	private String name;
	private int length;
	private int width;
	private float heigth;
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

	
	
}
