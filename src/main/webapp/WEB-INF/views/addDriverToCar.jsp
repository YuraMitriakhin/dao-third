<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4 style="color:red">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/driver/car/register">
    Input car id <input type="number" name="car_id" placeholder="car id" required>
    <br>Input driver id <input type="number" name="driver_id" placeholder="driver id" required>
    <br><button type="submit">Add driver to car</button>
</form>
</body>
</html>
