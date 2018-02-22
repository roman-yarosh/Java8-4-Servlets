<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Products List</title>
</head>
<body>
<a href="/index.html">Home</a><br>
<h3>Products List</h3>

<p style="color: red;">${errorString}</p>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>UUID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Manufacturer</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${productsList}" var="product" >
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.getManufacturer().getName()}</td>
            <td>
                <a href="/product/update?id=${product.id}">Update</a>
            </td>
            <td>
                <a href="/product/delete?id=${product.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="/product/add">Create new Product</a>

</body>
</html>