<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Drivers</title>
</head>
<body>
<table border="2">
<tr>
    <th>ID</th>
    <th>NAME</th>
    <th>LICENSE NUMBER</th>
</tr>
<c:forEach var="driver" items="${drivers}">
    <tr>
        <td>
            <c:out value="${driver.id}"/>
        </td>
        <td>
            <c:out value="${driver.name}"/>
        </td>
        <td>
            <c:out value="${driver.licenseNumber}"/>
        </td>
    </tr>
</c:forEach>
</table>
</body>
</html>
