<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Driver</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/driver/register">
    Input name <input type="text" name="name" placeholder="name" required>
    <br>Input license number <input type="text" name="license number" placeholder=" license number" required>
    <br><button type="submit">Add driver</button>
</form>
</body>
</html>
