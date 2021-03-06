<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>${PRODUCT_TITLE}</title>
</head>
<body>
<h3>${PRODUCT_TITLE}</h3>

<p style="color: red;">${errorString}</p>

<form method="POST" action="/product/add">
    <table border="${BORDER_WIDTH_0}">
        <tr>
            <td>${PRODUCT_NAME}</td>
            <td><input type="text" name="productName" value=""/></td>
        </tr>
        <tr>
            <td>${PRODUCT_PRICE}</td>
            <td><input type="text" name="productPrice" value=""/></td>
        </tr>
        <tr>
            <td>${MANUFACTURER_NAME}</td>
            <td>
                <select name="manufacturerId">
                    <c:forEach items="${manufacturersList}" var="manufacturer">
                    <option value="${manufacturer.id}">${manufacturer.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="${TWO_COLUMNS}">
                <input type="submit" value="Submit"/>&nbsp;
                <a href="/product/all">Cancel</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
