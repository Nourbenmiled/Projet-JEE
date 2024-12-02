<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<% HttpSession sessionAuth = request.getSession(false); %>
<%@include file="headers/headeretudiant_inc.jsp" %>
<div class="container mt-5">
    <h2 class="text-center mb-4">
        <%= (request.getAttribute("etudiant") != null) ? "Modifier un étudiant" : "Ajouter un étudiant" %>
    </h2>
    <form action="EtudiantController" method="post" class="shadow p-4">
        <input type="hidden" name="id" value="<%= sessionAuth.getAttribute("user") %>" />

        <div class="mb-3">
            <label for="nom" class="form-label">Nom :</label>
            <input type="text" id="nom" name="nom" class="form-control" value="<%= (request.getAttribute("etudiant") != null) ? ((model.Etudiant) request.getAttribute("etudiant")).getNom() : "" %>" required />
        </div>

        <div class="mb-3">
            <label for="prenom" class="form-label">Prénom :</label>
            <input type="text" id="prenom" name="prenom" class="form-control" value="<%= (request.getAttribute("etudiant") != null) ? ((model.Etudiant) request.getAttribute("etudiant")).getPrenom() : "" %>" required />
        </div>

        <div class="mb-3">
            <label for="contact" class="form-label">Contact :</label>
            <input type="text" id="contact" name="contact" class="form-control" value="<%= (request.getAttribute("etudiant") != null) ? ((model.Etudiant) request.getAttribute("etudiant")).getContact() : "" %>" required />
        </div>

       <input type="hidden" name="moyenne" value="0" />

        <div class="btn-container">
            <button type="submit" class="btn btn-primary">Enregistrer</button>
            
        </div>
    </form>
</div>
<%@include file="footer_inc.jsp" %>
