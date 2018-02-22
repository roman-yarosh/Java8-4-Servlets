<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Manufacturers List</title>
</head>
<body>
<a href="/index.html">Home</a><br>
<h3>Manufacturers List</h3>

<p style="color: red;">${errorString}</p>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>UUID</th>
        <th>Name</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${manufacturersList}" var="manufacturer" >
        <tr>
            <td>${manufacturer.id}</td>
            <td>${manufacturer.name}</td>
            <td>
                <a href="/manufacturer/update?id=${manufacturer.id}">Update</a>
            </td>
            <td>
                <a href="/manufacturer/delete?id=${manufacturer.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="/manufacturer/add">Create new Manufacturer</a>

</body>
</html>