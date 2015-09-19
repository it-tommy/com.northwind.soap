package com.northwind.service;

import java.util.List;

import com.northwind.dto.Product;

public interface IProductService {
	
	public abstract String getProducts() throws Exception;

	public abstract String getProductsWithIDAttribute() throws Exception;
	
	public abstract String fetchAllProducts() throws Exception;

}
