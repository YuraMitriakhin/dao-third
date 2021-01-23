<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2 style="color: red">${error}</h2>
<form method="post" action="${pageContext.request.contextPath}/login">
    login <input type="text" name="driver_login" placeholder="login" required>
    <br>password <input type="password" name="driver_password" placeholder="password" required>
    <br><button type="submit">Sign in</button>
</form>
</body>
</html>
