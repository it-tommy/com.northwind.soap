package com.northwind.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.northwind.dto.Product;

@WebService(name="com.northwind.soap", targetNamespace="http://northwind.com")
public interface IProducts {
	
	@WebMethod(exclude=false)
	@WebResult(name="Product")
	public abstract String getProducts() throws Exception;
	
	@WebMethod(exclude=false)
	@WebResult(name="Products")
	public abstract String getProductsWithIDAttribute() throws Exception;
	
	@WebMethod(exclude=false)
	@WebResult(name="fetchProducts")
	public abstract String fetchAllProducts() throws Exception;

}
