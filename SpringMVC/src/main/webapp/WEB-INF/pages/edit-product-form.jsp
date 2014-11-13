<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Edit product page</title>
</head>
<body>
<h1>Edit product page</h1>
<p>You can edit the product here.</p>
<p>${message}</p>
<form:form method="POST" commandName="product" action="${pageContext.request.contextPath}/product/updateproduct.html">
  <form:hidden path="productID"/>
<table>
<tbody>
	<tr>
		<td>Product Name:</td>
		<td><form:input path="productName" /></td>
	</tr>
	<tr>
		<td>Price:</td>
		<td><form:input path="price" /></td>
	</tr>
	<tr>
		<td><input type="submit" value="Update" /></td>
		<td></td>
	</tr>
</tbody>
</table>
</form:form>

<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>
</body>
</html>