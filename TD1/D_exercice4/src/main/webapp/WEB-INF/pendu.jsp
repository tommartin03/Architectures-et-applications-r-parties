
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Pendu</title>
</head>
<body>
<p>bienvenu dans le jeu de pendu...</p>
Quel mot proposez-vous ?
<form method="post">
    <input type="text" name="lemot">
    <button type="submit" name="TODO" value="pendu">Envoyer</button> <!-- TODO compléter le bouton -->
</form>
${msg}
</body>
</html>
