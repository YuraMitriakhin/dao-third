<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Car</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/car/register">
    Input model name <input type="text" name="model" placeholder="model" required>
    <br>Input manufacturer id <input type="number" name="id_manufacturer" placeholder="manufacturer id" required>
    <br><button type="submit">Add car</button>
</form>
</body>
</html>
