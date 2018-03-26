<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>${MANUFACTURERS_TITLE}</title>
</head>
<body>
<a href="/index.html">${HOME}</a><br>
<h3>${MANUFACTURERS_TITLE}</h3>

<p style="color: red;">${errorString}</p>

<table border="${BORDER_WIDTH_1}" cellpadding="${CELL_PADDING_5}" cellspacing="${CELL_SPACING_1}">
    <tr>
        <th>${UUID_HEADER}</th>
        <th>${NAME}</th>
        <th>${EDIT}</th>
        <th>${DELETE}</th>
    </tr>
    <c:forEach items="${manufacturersList}" var="manufacturer" >
        <tr>
            <td>${manufacturer.id}</td>
            <td>${manufacturer.name}</td>
            <td>
                <a href="/manufacturer/update?id=${manufacturer.id}">Update</a>
            </td>
            <td>
                <form method="POST" action="/manufacturer/delete">
                    <input type="hidden" name="id" value="${manufacturer.id}" />
                    <input type="submit" value="Delete" />
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="/manufacturer/add">${MANUFACTURER_CREATE}</a>

</body>
</html>