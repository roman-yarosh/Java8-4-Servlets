<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>${PRODUCTS_TITLE}</title>
</head>
<body>
<a href="/index.html">${HOME}</a><br>
<h3>${PRODUCTS_TITLE}</h3>

<p style="color: red;">${errorString}</p>

<table border="${BORDER_WIDTH_1}" cellpadding="${CELL_PADDING_5}" cellspacing="${CELL_SPACING_1}">
    <tr>
        <th>${UUID_HEADER}</th>
        <th>${NAME}</th>
        <th>${PRICE}</th>
        <th>${MANUFACTURER}</th>
        <th>${EDIT}</th>
        <th>${DELETE}</th>

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
                <form method="POST" action="/product/delete">
                    <input type="hidden" name="id" value="${product.id}" />
                    <input type="submit" value="Delete" />
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="/product/add">${PRODUCT_CREATE}</a>

</body>
</html>