<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>List of products</title>
</head>
<body>
<h1>List of products</h1>
<p>You can edit/delete the products.</p>
<table border="1px" cellpadding="0" cellspacing="0" >
<thead>
<tr>
<th width="40%">Product Name</th><th width="20%">Price</th><th width="30%">actions</th>
</tr>
</thead>
<tbody>
<c:forEach var="product" items="${products}">
<tr>
	<td>${product.productName}</td>
	<td>${product.price}</td>
	<td>
	<a href="${pageContext.request.contextPath}/product/geteditpage/${product.productID}.html">Edit</a><br/>
	<a href="${pageContext.request.contextPath}/product/delete/${product.productID}.html">Delete</a><br/>
	</td>
</tr>
</c:forEach>
</tbody>
</table>

<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>

</body>
</html>