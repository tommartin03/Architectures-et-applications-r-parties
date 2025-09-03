
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>METEO</title>
</head>
<body>
<h2>Choisissez la météo :</h2>
<ul>
    <c:forEach items="${counters}" var="entry">
        <li>${entry.key} : ${entry.value} fois</li>
    </c:forEach>
</ul>

<form method="post">
    <select name="meteo">
        <c:forEach items="${options}" var="opt">
        <<option value="${opt}" <c:if test="${opt eq selected}">selected</c:if>>
            ${opt}
            </option>
        </c:forEach>
    </select>
    <button type="submit">Valider</button>
</form>

</body>
</html>
