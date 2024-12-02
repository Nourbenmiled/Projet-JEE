<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="headers/headeradmin_inc.jsp" %>

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
    /* Styles personnalisés */
    body {
        background-color: #f8f9fa; /* Gris clair */
    }

    h2 {
        color: #007bff; /* Bleu pour le titre */
        font-weight: bold;
    }

    .btn-primary {
        background-color: #0056b3; /* Bleu foncé */
        border-color: #0056b3;
    }

    .btn-primary:hover {
        background-color: #004085;
        border-color: #004085;
    }

    .btn-secondary {
        background-color: #6c757d; /* Gris */
        border-color: #6c757d;
    }

    .btn-secondary:hover {
        background-color: #5a6268;
        border-color: #5a6268;
    }

    .form-label {
        font-weight: bold;
        color: #343a40; /* Noir */
    }

    .form-control {
        border-color: #007bff; /* Bordure bleue */
    }

    .form-control:focus {
        border-color: #0056b3;
        box-shadow: 0 0 5px rgba(0, 123, 255, 0.5); /* Ombre bleue */
    }

    .shadow {
        background-color: white; /* Fond blanc pour le formulaire */
        border-radius: 10px;
    }

    .btn-container {
        display: flex;
        justify-content: space-between;
    }
</style>

<section class="py-5">
    <div class="container mt-5">
        <h2 class="text-center mb-4">
            <%= (request.getAttribute("cours") != null) ? "Modifier un cours" : "Ajouter un cours" %>
        </h2>
        <form action="CoursControlller" method="post" class="shadow p-4">
            <input type="hidden" name="id" value="<%= (request.getAttribute("cours") != null) ? ((model.Cours) request.getAttribute("cours")).getIdCours() : "" %>" />

            <div class="mb-3">
                <label for="nom" class="form-label">Nom :</label>
                <input type="text" id="nom" name="nom" class="form-control" value="<%= (request.getAttribute("cours") != null) ? ((model.Cours) request.getAttribute("cours")).getNom() : "" %>" required />
            </div>

            <div class="mb-3">
                <label for="description" class="form-label">Description :</label>
                <textarea id="description" name="description" class="form-control" rows="3" required><%= (request.getAttribute("cours") != null) ? ((model.Cours) request.getAttribute("cours")).getDescription() : "" %></textarea>
            </div>

            <div class="mb-3">
                <label for="coef" class="form-label">Coefficient :</label>
                <input type="number" id="coef" name="coef" class="form-control" step="0.1" value="<%= (request.getAttribute("cours") != null) ? ((model.Cours) request.getAttribute("cours")).getCoef() : "" %>" required />
            </div>

            <div class="btn-container">
                <button type="submit" class="btn btn-primary">Enregistrer</button>
                <a href="CoursControlller?action=list" class="btn btn-secondary">Annuler</a>
            </div>
        </form>
    </div>
</section>

<%@include file="footer_inc.jsp" %>
