<%--
Cette JSP accueillera un form avec les deux champs login et password
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>LOGIN</title>
</head>
<body>
<form method="post" action="">
    <label>Login :
        <input type="text" name="login" required>
    </label>
    <br/><br/>
    <label>Mot de passe :
        <input type="password" name="password" required>
    </label>
    <br/><br/>
    <button type="submit">Se connecter</button>
</form>

<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>
</body>
</html>
