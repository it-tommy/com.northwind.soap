package com.northwind.dao;

import java.io.File;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.northwind.dto.Product;
import com.northwind.dto.Products;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class ProductsDaoImpl implements IProductsDao {
	
	@Override
	public String getProducts() throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		Products products = new Products();
		products.setProductsList(new ArrayList<Product>());
		PreparedStatement query = null;
		Connection conn = null;
		//List<Product> productList = new ArrayList<Product>();
		
		try{
			
			conn = MySQLDB.MySQLDBConn().getConnection();
			query = conn.prepareStatement("select ProductID, ProductName, QuantityPerUnit, UnitPrice, UnitsInStock, " +
										  "UnitsOnOrder, ReorderLevel, Discontinued from products");
			
				ResultSet rs = query.executeQuery();
				while(rs.next()){
					Product product = new Product();
					product.setProductId(rs.getInt("ProductID"));
					product.setProductName(rs.getString("ProductName"));
					product.setQuantityPerUnit(rs.getString("QuantityPerUnit"));
					product.setUnitPrice(rs.getDouble("UnitPrice"));
					product.setUnitsInStock(rs.getInt("UnitsInStock"));
					product.setRecorderLevel(rs.getInt("ReorderLevel"));
					product.setDiscontinued(rs.getBoolean("Discontinued"));
					products.getProductsList().add(product);
				}
				//jaxbMarshaller.marshal(products, System.out);
				jaxbMarshaller.marshal(products, new File("c:/temp/products.xml"));
				query.close();
		}catch(SQLException sqlError){
			sqlError.printStackTrace();
			List<Product> emptyProductList = new ArrayList<Product>();
			return "Error occured";
		}finally {
			if (conn != null)
				conn.close();
		}
		
	    //XStream xstream = new XStream();
	    XStream xstream = new XStream(new DomDriver());
	    xstream.alias("ListProducts", Products.class);
	    xstream.alias("product", Product.class);
	    System.out.println(xstream.toXML(products));
		
		return xstream.toXML(products);
	}
	
	@Override
	public String getProductsWithIDAttribute() throws Exception {

		PreparedStatement query = null;
		Connection conn = null;
		// Used for the xml
		Document doc = null;
		Element root = null;
		String xmlString = null;
		
		try{
			
	        // Create a document object
	        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
	        doc = docBuilder.newDocument();
	        
	        //create the root element and add it to the document
	        root = doc.createElement("products");
	        doc.appendChild(root);
			
			conn = MySQLDB.MySQLDBConn().getConnection();
			query = conn.prepareStatement("select ProductID, ProductName, QuantityPerUnit, UnitPrice, UnitsInStock, " +
										  "UnitsOnOrder, ReorderLevel, Discontinued from products");
			
				ResultSet rs = query.executeQuery();
				while(rs.next()){
					Integer i = rs.getRow(); // used to populate the record tag id attribute
		            // child 1 - ProductID
					Element record = doc.createElement("record");
					record.setAttribute("id",i.toString());
			        root.appendChild(record);
					Element child1 = doc.createElement("child");
		            child1.setAttribute("id", rs.getMetaData().getColumnName(1));
		            record.appendChild(child1);
		            Text text1 = doc.createTextNode(rs.getString("ProductID").trim());
		            child1.appendChild(text1);
		            // child 2- ProductName
		            Element child2 = doc.createElement("child");
		            child2.setAttribute("id", rs.getMetaData().getColumnName(2));
		            record.appendChild(child2);
		            Text text2 = doc.createTextNode(rs.getString("ProductName").trim());
		            child2.appendChild(text2);
		            // child 3- QuantityPerUnit
		            Element child3 = doc.createElement("child");
		            child3.setAttribute("id", rs.getMetaData().getColumnName(3));
		            record.appendChild(child3);
		            Text text3 = doc.createTextNode(rs.getString("QuantityPerUnit").trim());
		            child3.appendChild(text3);
		            // child 4- UnitPrice
		            Element child4 = doc.createElement("child");
		            child4.setAttribute("id", rs.getMetaData().getColumnName(4));
		            record.appendChild(child4);
		            Text text4 = doc.createTextNode(rs.getString("UnitPrice").trim());
		            child4.appendChild(text4);
		            // child 5- UnitsInStock
		            Element child5 = doc.createElement("child");
		            child5.setAttribute("id", rs.getMetaData().getColumnName(5));
		            record.appendChild(child5);
		            Text text5 = doc.createTextNode(rs.getString("UnitsInStock").trim());
		            child5.appendChild(text5);
		            // child 6- UnitsOnOrder
		            Element child6 = doc.createElement("child");
		            child6.setAttribute("id", rs.getMetaData().getColumnName(6));
		            record.appendChild(child6);
		            Text text6 = doc.createTextNode(rs.getString("UnitsOnOrder").trim());
		            child6.appendChild(text6);
		            // child 7- ReorderLevel
		            Element child7 = doc.createElement("child");
		            child7.setAttribute("id", rs.getMetaData().getColumnName(7));
		            record.appendChild(child7);
		            Text text7 = doc.createTextNode(rs.getString("ReorderLevel").trim());
		            child7.appendChild(text7);
		            // child 8- Discontinued
		            Element child8 = doc.createElement("child");
		            child8.setAttribute("id", rs.getMetaData().getColumnName(8));
		            record.appendChild(child8);
		            Text text8 = doc.createTextNode(rs.getString("Discontinued").trim());
		            child8.appendChild(text8);
				}
				try{
		            //set up a transformer
		            TransformerFactory transfac = TransformerFactory.newInstance();
		            Transformer trans = transfac.newTransformer();
		            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		            trans.setOutputProperty(OutputKeys.INDENT, "yes");

		            //create string from xml tree
		            StringWriter sw = new StringWriter();
		            StreamResult result = new StreamResult(sw);
		            DOMSource source = new DOMSource(doc);
		            trans.transform(source, result);
		            xmlString = sw.toString();
				}
				catch(Exception e){
					e.printStackTrace();
				}

				query.close();
		}catch(SQLException sqlError){
			sqlError.printStackTrace();
			return "Error occured";
		}finally {
			if (conn != null)
				conn.close();
		}
		System.out.println(xmlString);
		return xmlString;
	}
	
	@Override
	public String fetchAllProducts() throws Exception {

		PreparedStatement query = null;
		Connection conn = null;
		// Used for the xml
		Document doc = null;
		Element root = null;
		String xmlString = null;
		
		try{
			
	        // Create a document object
	        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
	        doc = docBuilder.newDocument();
	        
	        //create the root element and add it to the document
	        root = doc.createElement("products");
	        doc.appendChild(root);
			
			conn = MySQLDB.MySQLDBConn().getConnection();
			query = conn.prepareStatement("select ProductID, ProductName, QuantityPerUnit, UnitPrice, UnitsInStock, " +
										  "UnitsOnOrder, ReorderLevel, Discontinued from products");
			
				ResultSet rs = query.executeQuery();
				while(rs.next()){
					Integer i = rs.getRow(); // used to populate the record tag id attribute
		            // child 1 - ProductID
					Element product = doc.createElement("product");
					product.setAttribute("id",i.toString());
			        root.appendChild(product);
					Element ProductID = doc.createElement("ProductID");
		            product.appendChild(ProductID);
		            Text text1 = doc.createTextNode(rs.getString("ProductID").trim());
		            ProductID.appendChild(text1);
		            // ProductName
		            Element ProductName = doc.createElement("ProductName");
		            product.appendChild(ProductName);
		            Text text2 = doc.createTextNode(rs.getString("ProductName").trim());
		            ProductName.appendChild(text2);
		            // QuantityPerUnit
		            Element QuantityPerUnit = doc.createElement("QuantityPerUnit");
		            product.appendChild(QuantityPerUnit);
		            Text text3 = doc.createTextNode(rs.getString("QuantityPerUnit").trim());
		            QuantityPerUnit.appendChild(text3);
		            // UnitPrice
		            Element UnitPrice = doc.createElement("UnitPrice");
		            product.appendChild(UnitPrice);
		            Text text4 = doc.createTextNode(rs.getString("UnitPrice").trim());
		            UnitPrice.appendChild(text4);
		            // UnitsInStock
		            Element UnitsInStock = doc.createElement("UnitsInStock");
		            product.appendChild(UnitsInStock);
		            Text text5 = doc.createTextNode(rs.getString("UnitsInStock").trim());
		            UnitsInStock.appendChild(text5);
		            // UnitsOnOrder
		            Element UnitsOnOrder = doc.createElement("UnitsOnOrder");
		            product.appendChild(UnitsOnOrder);
		            Text text6 = doc.createTextNode(rs.getString("UnitsOnOrder").trim());
		            UnitsOnOrder.appendChild(text6);
		            // ReorderLevel
		            Element ReorderLevel = doc.createElement("ReorderLevel");
		            product.appendChild(ReorderLevel);
		            Text text7 = doc.createTextNode(rs.getString("ReorderLevel").trim());
		            ReorderLevel.appendChild(text7);
		            // Discontinued
		            Element Discontinued = doc.createElement("Discontinued");
		            product.appendChild(Discontinued);
		            Text text8 = doc.createTextNode(rs.getString("Discontinued").trim());
		            Discontinued.appendChild(text8);
				}
				try{
		            //set up a transformer
		            TransformerFactory transfac = TransformerFactory.newInstance();
		            Transformer trans = transfac.newTransformer();
		            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		            trans.setOutputProperty(OutputKeys.INDENT, "yes");

		            //create string from xml tree
		            StringWriter sw = new StringWriter();
		            StreamResult result = new StreamResult(sw);
		            DOMSource source = new DOMSource(doc);
		            trans.transform(source, result);
		            xmlString = sw.toString();
				}
				catch(Exception e){
					e.printStackTrace();
				}

				query.close();
		}catch(SQLException sqlError){
			sqlError.printStackTrace();
			return "Error occured";
		}finally {
			if (conn != null)
				conn.close();
		}
		System.out.println(xmlString);
		return xmlString;
	}

}
