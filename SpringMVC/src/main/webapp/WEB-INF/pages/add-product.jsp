<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product</title>
</head>
<body>
<form:form method="POST" commandName="product" action="${pageContext.request.contextPath}/product/addproduct.html">

<table>
<tbody>
	<tr>
		<td>Product Name :</td>
		<td><form:input path="productName" /></td>
	</tr>
	<tr>
		<td>Price :</td>
		<td><form:input path="price" /></td>
	</tr>
	<tr>
		<td><input type="submit" value="Add" /></td>
		<td></td>
	</tr>
</tbody>
</table>



</form:form>
</body>
</html>