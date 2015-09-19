var xmlHttp; 
	function SoapCall(){

	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");

	// Calling the web service using post and true means asynchronous call

	xmlHttp.open("post", "http://localhost/asmx/Service1.asmx", true);

	// Setting the request header to let the web service identify the soap request we would be sending

	xmlHttp.setRequestHeader("Content-Type","text/xml; charset=utf-8" );

	// http://myNamespace/HelloWorld - name of the webmethod

	//[WebService(Namespace = "http://tempuri.org/")] which we had applied to our web service class

	xmlHttp.setRequestHeader("SOAPAction", "http://tempuri.org/AddNo");

	xmlHttp.onreadystatechange=doUpdate;

	// setting the soap request body

/*	var soapRequest="<?xml version='1.0' encoding='utf-8'?>" +
					"<soap:Envelope xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'"+
					" xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:soap='http://schemas.xmlsoap.org/soap/envelope/'>"+
					"<soap:Body>" +
					"<AddNo xmlns='http://tempuri.org/'>"+ 
					"<a>5</a> "+
					"<b>5</b> "+
					"</AddNo>"+
					" </soap:Body>"+
					"</soap:Envelope>";*/
	var soapRequest = "<?xml version='1.0' encoding='utf-8'?>" +
					  "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ws='http://ws.northwind.com'>" +
					  "<soapenv:Header/>" + 
					  "<soapenv:Body>" +
					  		"<ws:getProductsWithIDAttribute/>" +
					  "</soapenv:Body>" +
					  "</soapenv:Envelope>"


	xmlHttp.send(soapRequest);
	return false; 
}

function doUpdate() {if(xmlHttp.readyState==4){
	var responseXMLResult=xmlHttp.responseXML;
	var result = responseXMLResult.lastChild.nodeTypedValue; 
	alert(result);

}