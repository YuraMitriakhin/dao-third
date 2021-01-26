<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Driver</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/driver/register">
    Input login <input type="text" name="driver_login" placeholder="login" required>
    <br>Input name <input type="text" name="driver_name" placeholder="name" required>
    <br>Input license number <input type="text" name="license number" placeholder=" license number" required>
    <br>Input password <input type="password" name="driver_password" placeholder="password" required>
    <br><button type="submit">Add driver</button>
</form>
</body>
</html>
