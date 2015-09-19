package com.northwind.service;

import java.util.List;

import com.northwind.dao.IProductsDao;
import com.northwind.dao.ProductsDaoImpl;
import com.northwind.dto.Product;

public class ProductServiceImpl implements IProductService {

	@Override
	public String getProducts() throws Exception {

		IProductsDao products = new ProductsDaoImpl();
		return products.getProducts();
	}

	@Override
	public String getProductsWithIDAttribute() throws Exception {
		IProductsDao products = new ProductsDaoImpl();
		return products.getProductsWithIDAttribute();
	}

	@Override
	public String fetchAllProducts() throws Exception {
		IProductsDao products = new ProductsDaoImpl();
		return products.fetchAllProducts();
	}

}
