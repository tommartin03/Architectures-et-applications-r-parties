<%--
  Created by IntelliJ IDEA.
  User: o22102488@campus.univ-orleans.fr
  Date: 24/09/2025
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
  <meta charset="UTF-8">
  <title>Changer le mot de passe</title>
</head>
<body>
<h1>Changement de mot de passe</h1>

<form method="post" action="Exercice9Servlet">
  <p>Ancien mot de passe: <input type="password" name="oldPassword" /></p>
  <p>Nouveau mot de passe: <input type="password" name="newPassword" /></p>
  <p>Confirmer le nouveau mot de passe: <input type="password" name="confirmPassword" /></p>
  <p><button type="submit" name="TODO" value="DoChangePassword">Envoyer</button></p>
</form>



<c:if test="${not empty errors}">
  <c:forEach items="${errors}" var="error">
    <p style="color: red">${error}</p>
  </c:forEach>
</c:if>

</body>
</html>

