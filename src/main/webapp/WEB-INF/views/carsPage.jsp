<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Drivers</title>
</head>
<body>
<table border="2">
<tr>
    <th>id</th>
    <th>model</th>
    <th>manufacturer name</th>
</tr>
<c:forEach var="car" items="${cars}">
    <tr>
        <td>
            <c:out value="${car.id}"/>
        </td>
        <td>
            <c:out value="${car.model}"/>
        </td>
        <td>
            <c:out value="${car.manufacturer.name}"/>
        </td>
    </tr>
</c:forEach>
</table>
</body>
</html>
