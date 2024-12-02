<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<% HttpSession sessionAuth = request.getSession(false); %>
<%@include file="headers/headerenseignant_inc.jsp" %>
<div class="container mt-5">
    <h2 class="text-center mb-4">
        <%= (request.getAttribute("enseignant") != null) ? "Modifier un étudiant" : "Votre Première Connexion, Renseignez vos informations svp" %>
    </h2>
    <form action="EnseignantController" method="post" class="shadow p-4">
        <input type="hidden" name="id" value="<%= sessionAuth.getAttribute("user") %>" />

        <div class="mb-3">
            <label for="nom" class="form-label">Nom :</label>
            <input type="text" id="nom" name="nom" class="form-control" value="<%= (request.getAttribute("enseignant") != null) ? ((model.Enseignant) request.getAttribute("enseignat")).getNom() : "" %>" required />
        </div>

        <div class="mb-3">
            <label for="prenom" class="form-label">Prénom :</label>
            <input type="text" id="prenom" name="prenom" class="form-control" value="<%= (request.getAttribute("enseignant") != null) ? ((model.Enseignant) request.getAttribute("enseignant")).getPrenom() : "" %>" required />
        </div>

        <div class="mb-3">
            <label for="contact" class="form-label">Contact :</label>
            <input type="text" id="contact" name="contact" class="form-control" value="<%= (request.getAttribute("enseignant") != null) ? ((model.Enseignant) request.getAttribute("enseignant")).getContact() : "" %>" required />
        </div>


        <div class="btn-container">
            <button type="submit" class="btn btn-primary">Enregistrer</button>
            
        </div>
    </form>
</div>
<%@include file="footer_inc.jsp" %>
