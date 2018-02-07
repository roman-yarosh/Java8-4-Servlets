<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Create new Manufacturer</title>
</head>
<body>
<h3>Create new Manufacturer</h3>

<p style="color: red;">${errorString}</p>

<form method="POST" action="/manufacturer/add">
    <table border="0">
        <tr>
            <td>Manufacturer name</td>
            <td><input type="text" name="manufacturerName" value="" /></td>
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
