<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
</head>
<body>
<h3>Edit Product</h3>

<p style="color: red;">${errorString}</p>

<form method="POST" action="/product/update">
    <table border="0">
        <tr>
            <td>Product name</td>
            <td><input type="text" name="productName" value="${product.productName}" />
                <input type="hidden" name="productId" value="${product.productId}" /></td>
        </tr>
        <tr>
            <td>Product price</td>
            <td><input type="text" name="productPrice" value="${product.productPrice}"/></td>
        </tr>
        <tr>
            <td>Manufacturer name</td>
            <td>
                <select name="manufacturerId">
                    <c:forEach items="${manufacturersList}" var="manufacturer">
                        <option value="${manufacturer.manufacturerId}"<c:if test="${product.manufacturer.manufacturerId.equals(manufacturer.manufacturerId)}"> selected</c:if>>${manufacturer.manufacturerName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit" />&nbsp;
                <a href="/product/all">Cancel</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
