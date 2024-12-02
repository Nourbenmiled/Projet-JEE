<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Etudiant" %>
<%@include file="headers/headeradmin_inc.jsp" %>

<section>
<div class="container mt-4">
    <h2 class="mb-4">Liste des Etudiants</h2>

    <% 
        // Exemple de données statiques pour les enseignants (remplacez-les par vos données réelles depuis la base de données)
        List<Etudiant> etudiants = (List<Etudiant>) request.getAttribute("etudiantList");
    	//String idCours = (String) request.getAttribute("idCours");
        if (etudiants == null) {
            etudiants = new ArrayList<>();
           
        }

        // Récupération du paramètre de recherche
        String searchQuery = request.getParameter("search");
        List<Etudiant> filteredEtudiants = new ArrayList<>();
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            for (Etudiant etudiant : etudiants) {
                if (etudiant.getNom().toLowerCase().contains(searchQuery.toLowerCase())) {
                    filteredEtudiants.add(etudiant);
                }
            }
        } else {
            filteredEtudiants = etudiants; // Si pas de recherche, affichez tous les enseignants
        }
    %>

    <!-- Barre de recherche -->
    <form method="GET" action="InscriptionEtudiantController">
        <div class="mb-3 row">
            <label for="search" class="col-sm-2 col-form-label">Rechercher par nom :</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="search" name="search" value="<%= searchQuery != null ? searchQuery : "" %>" placeholder="Entrez un nom">
            </div>
            <div class="col-sm-2">
                <button type="submit" class="btn btn-primary">Rechercher</button>
            </div>
        </div>
    </form>
<% if (request.getAttribute("message") != null) { %>
            <div class="alert alert-success text-center">
                <%= request.getAttribute("message") %>
            </div>
        <% } %>
        
    <!-- Table des enseignants -->
    <form method="POST" action="InscriptionEtudiantController">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nom</th>
                    <th scope="col">Prénom</th>
                    <th scope="col">Contact</th>
                    <th scope="col">Sélection</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    if (filteredEtudiants != null && !filteredEtudiants.isEmpty()) {
                        for (Etudiant etudiant : filteredEtudiants) { 
                %>
                <tr>
                    <td><%= etudiant.getIdEtu() %></td>
                    <td><%= etudiant.getNom() %></td>
                    <td><%= etudiant.getPrenom() %></td>
                    <td><%= etudiant.getContact() %></td>
                    <td>
                    	<input type="hidden" name="idCours" value="<%= request.getAttribute("idCours") %>" />
                        <input type="checkbox" name="selectedEtudiants" value="<%= etudiant.getIdEtu() %>">
                    </td>
                </tr>
                <% 
                        }
                    } else { 
                %>
                <tr>
                    <td colspan="5" class="text-center">Aucun étudiant trouvé.</td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <div class="text-end">
            <button type="submit" class="btn btn-success">Valider</button>
        </div>
    </form>
</div>

<%@include file="footer_inc.jsp" %>
</section>

