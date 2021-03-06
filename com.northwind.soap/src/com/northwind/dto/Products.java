package com.northwind.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="products")
@XmlAccessorType(XmlAccessType.FIELD)
public class Products {
	
	@XmlElement(name="product")
	private List<Product> products = null;

	public List<Product> getProductsList() {
		return products;
	}

	public void setProductsList(List<Product> productsList) {
		this.products = productsList;
	}
	

}
