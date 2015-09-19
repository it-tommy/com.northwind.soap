package com.northwind.dao;

import java.util.List;

import javax.xml.bind.JAXBException;

import com.northwind.dto.Product;

public interface IProductsDao {
	
	public abstract String getProducts() throws Exception;
	
	public abstract String getProductsWithIDAttribute() throws Exception;
	
	public abstract String fetchAllProducts() throws Exception;


}
