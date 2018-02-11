<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Manufacturer</title>
</head>
<body>
<h3>Edit Manufacturer</h3>

<p style="color: red;">${errorString}</p>

<form method="POST" action="/manufacturer/update">
    <table border="0">
        <tr>
            <td>Manufacturer name</td>
            <td><input type="text" name="manufacturerName" value="${manufacturerName}" />
                <input type="hidden" name="manufacturerId" value="${manufacturerId}" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit" />&nbsp;
                <a href="/manufacturer/all">Cancel</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
