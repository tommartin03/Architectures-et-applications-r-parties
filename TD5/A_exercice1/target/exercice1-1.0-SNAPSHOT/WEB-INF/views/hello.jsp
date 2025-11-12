<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Détails Employé</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background-color: #f8f9fa;
        }
        h1 {
            color: #333;
            border-bottom: 2px solid #007bff;
            padding-bottom: 5px;
        }
        .section {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 15px 20px;
            margin-bottom: 30px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.05);
        }
        strong {
            color: #007bff;
        }
        ul {
            list-style: none;
            padding-left: 0;
        }
        ul li {
            margin-bottom: 4px;
        }
    </style>
</head>
<body>

<h1>Détails de l'employé n°${employe.id} (DTO)</h1>

<div class="section">
    <p><strong>Nom :</strong> ${employe.prenom} ${employe.nom}</p>
    <p><strong>Adresse :</strong> ${employe.adresse}</p>
    <p><strong>Machine :</strong> ${employe.machine}</p>
    <p><strong>Service :</strong> ${employe.service}</p>

    <c:if test="${not empty employe.telephones}">
        <p><strong>Téléphones :</strong></p>
        <ul>
            <c:forEach var="tel" items="${employe.telephones}">
                <li>${tel}</li>
            </c:forEach>
        </ul>
    </c:if>

    <c:if test="${not empty employe.projetsEnCours}">
        <p><strong>Projets en cours :</strong></p>
        <ul>
            <c:forEach var="proj" items="${employe.projetsEnCours}">
                <li>${proj}</li>
            </c:forEach>
        </ul>
    </c:if>

    <c:if test="${not empty employe.projetsTermines}">
        <p><strong>Projets terminés :</strong></p>
        <ul>
            <c:forEach var="proj" items="${employe.projetsTermines}">
                <li>${proj}</li>
            </c:forEach>
        </ul>
    </c:if>
</div>

<h1>Détails de l'employé n°${employeJoinFetch.idEmp} (join fetch)</h1>

<div class="section">
    <p><strong>Nom :</strong> ${employeJoinFetch.prenom} ${employeJoinFetch.nom}</p>
    <p><strong>Adresse :</strong>
        <c:choose>
            <c:when test="${employeJoinFetch.adresse != null}">
                ${employeJoinFetch.adresse.rue}, ${employeJoinFetch.adresse.codepostal} ${employeJoinFetch.adresse.ville}
            </c:when>
            <c:otherwise>Aucune adresse</c:otherwise>
        </c:choose>
    </p>
    <p><strong>Machine :</strong>
        <c:choose>
            <c:when test="${employeJoinFetch.machine != null}">
                ${employeJoinFetch.machine.idMachine} ${employeJoinFetch.machine.adresseMac} (${employeJoinFetch.machine.modele})
            </c:when>
            <c:otherwise>Aucune machine</c:otherwise>
        </c:choose>
    </p>
    <p><strong>Service :</strong>
        <c:choose>
            <c:when test="${employeJoinFetch.service != null}">
                ${employeJoinFetch.service.nomService}
            </c:when>
            <c:otherwise>Aucun service</c:otherwise>
        </c:choose>
    </p>

    <c:if test="${not empty employeJoinFetch.telephones}">
        <p><strong>Téléphones :</strong></p>
        <ul>
            <c:forEach var="tel" items="${employeJoinFetch.telephones}">
                <li>${tel.numero}</li>
            </c:forEach>
        </ul>
    </c:if>

    <c:if test="${not empty employeJoinFetch.projetsEnCours}">
        <p><strong>Projets en cours :</strong></p>
        <ul>
            <c:forEach var="proj" items="${employeJoinFetch.projetsEnCours}">
                <li>${proj.codeProjet} - ${proj.nomProjet}</li>
            </c:forEach>
        </ul>
    </c:if>
</div>

</body>
</html>
