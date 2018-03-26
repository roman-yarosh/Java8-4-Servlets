<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>${MANUFACTURER_EDIT}</title>
</head>
<body>
<h3>${MANUFACTURER_EDIT}</h3>

<p style="color: red;">${errorString}</p>

<form method="POST" action="/manufacturer/update">
    <table border="${BORDER_WIDTH_0}">
        <tr>
            <td>${MANUFACTURER_NAME}</td>
            <td><input type="text" name="manufacturerName" value="${manufacturerName}" />
                <input type="hidden" name="manufacturerId" value="${manufacturerId}" /></td>
        </tr>
        <tr>
            <td colspan="${TWO_COLUMNS}">
                <input type="submit" value="Submit" />&nbsp;
                <a href="/manufacturer/all">Cancel</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
