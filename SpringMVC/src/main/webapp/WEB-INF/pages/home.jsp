<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Home page</title>
</head>
<body>
<h1>Welcome to Spring MVC Demo example</h1>
<p style="color: blue;">${message}<br/></p>

<p>
<a href="${pageContext.request.contextPath}/product/productpage.html">Add new product</a><br/>
<a href="${pageContext.request.contextPath}/product/listofproducts.html">List all the products</a><br/>
</p>


<p>
<h4>Technologies used in the project : Spring MVC, Hibernate, Maven, Log4j, Junit </h4>


</p>
</body>
</html>