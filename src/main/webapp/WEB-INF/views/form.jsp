<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Formulaire de Cours</title>
</head>
<body>
    <h1>
        <c:choose>
            <c:when test="${cours != null}">Modifier un Cours</c:when>
            <c:otherwise>Ajouter un Cours</c:otherwise>
        </c:choose>
    </h1>
    <form action="CoursControlller" method="post">
        <input type="hidden" name="id" value="${cours != null ? cours.idCours : ''}" />
        <div>
            <label>Nom :</label>
            <input type="text" name="nom" value="${cours != null ? cours.nom : ''}" required />
        </div>
        <div>
            <label>Description :</label>
            <input type="text" name="description" value="${cours != null ? cours.description : ''}" required />
        </div>
        <div>
            <label>Coefficient :</label>
            <input type="number" step="0.1" name="coef" value="${cours != null ? cours.coef : ''}" required />
        </div>
        <div>
            <button type="submit">Enregistrer</button>
            <a href="CoursControlller?action=list">Annuler</a>
        </div>
    </form>
</body>
</html>
