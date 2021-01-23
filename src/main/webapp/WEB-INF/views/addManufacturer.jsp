<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manufacturer</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/manufacturer/register">
    Input name <input type="text" name="name" placeholder="name" required>
    <br>Input country <input type="text" name="country" placeholder="country" required>
    <br><button type="submit">Add manufacturer</button>
</form>
</body>
</html>
