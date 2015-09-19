package com.northwind.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="product")
//@XmlAccessorType(XmlAccessType.FIELD)
public class Product {

	private int productId;
	private String productName;
	private String quantityPerUnit;
	private double unitPrice;
	private int unitsInStock;
	private int unitsOnOrder;
	private int recorderLevel;
	private boolean discontinued;
	
	//@XmlElement(name="ID")
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	//@XmlElement(name="ProductName")
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	//@XmlElement(name="QuantityPerUnit")
	public String getQuantityPerUnit() {
		return quantityPerUnit;
	}
	public void setQuantityPerUnit(String quantityPerUnit) {
		this.quantityPerUnit = quantityPerUnit;
	}
	//@XmlElement(name="UnitPrice")
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	//@XmlElement(name="UnitsInStock")
	public int getUnitsInStock() {
		return unitsInStock;
	}
	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}
	//@XmlElement(name="UnitsOnOrder")
	public int getUnitsOnOrder() {
		return unitsOnOrder;
	}
	public void setUnitsOnOrder(int unitsOnOrder) {
		this.unitsOnOrder = unitsOnOrder;
	}
	//@XmlElement(name="RecorderLevel")
	public int getRecorderLevel() {
		return recorderLevel;
	}
	public void setRecorderLevel(int recorderLevel) {
		this.recorderLevel = recorderLevel;
	}
	//@XmlElement(name="Discontinued")
	public boolean getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}
	
	

}
