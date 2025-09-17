<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Bienvenue</title>
</head>
<body>
Bienvenue, ${username}.
<br>
<div>
    Votre humeur stockée en session :
    <c:if test="${ not empty sessionScope.humeur}">
        ${sessionScope.humeur}
    </c:if>
    <c:if test="${ empty sessionScope.humeur}">
        Pas d'humeur stockée en session
    </c:if>
</div>

<form action="${pageContext.request.contextPath}/logout">
    <button type="submit">se déconnecter</button>
</form>

<form action="${pageContext.request.contextPath}/simplecheck">
    <button type="submit">Vérifier le login en session (en trace de serveur)</button>
</form>

<jsp:include page="session.jsp"></jsp:include>


</body>
</html>
