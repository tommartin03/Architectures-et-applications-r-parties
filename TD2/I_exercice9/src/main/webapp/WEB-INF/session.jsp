<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div style="display:block; border: solid thin darkblue;">
    <span>Le login stock&eacute; en session est :</span>
    <c:if test="${not empty sessionScope.courant}">
        <span>${sessionScope.courant}</span>
    </c:if>
    <c:if test="${sessionScope.courant == null}">
        <span>Pas de login stock&eacute; en session</span>
    </c:if>
</div>

