package com.northwind.ws;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.northwind.dto.Product;
import com.northwind.service.IProductService;
import com.northwind.service.ProductServiceImpl;


@WebService(endpointInterface="org.sss.ProductInterface", portName="TestProductPort", serviceName="TestProductService")
public class  ProductsImpl implements IProducts {

	@WebMethod(exclude=false)
	@WebResult(name="Product")
	public String getProducts() throws Exception{
		IProductService iProdServ = new ProductServiceImpl();
		return iProdServ.getProducts();
	}

	@WebMethod(exclude=false)
	@WebResult(name="Products")
	public String getProductsWithIDAttribute() throws Exception {
		IProductService iProdServ = new ProductServiceImpl();
		return iProdServ.getProductsWithIDAttribute();
	}

	@WebMethod(exclude=false)
	@WebResult(name="fetchAllProducts")
	public String fetchAllProducts() throws Exception {
		IProductService iProdServ = new ProductServiceImpl();
		return iProdServ.fetchAllProducts();
	}

}
