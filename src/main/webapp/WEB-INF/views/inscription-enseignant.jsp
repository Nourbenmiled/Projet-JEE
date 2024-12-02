<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Enseignant" %>
<%@include file="headers/headeradmin_inc.jsp" %>

<section>
<div class="container mt-4">
    <h2 class="mb-4">Liste des Enseignants</h2>

    <% 
        // Exemple de données statiques pour les enseignants (remplacez-les par vos données réelles depuis la base de données)
        List<Enseignant> enseignants = (List<Enseignant>) request.getAttribute("enseignantList");
    	//String idCours = (String) request.getAttribute("idCours");
        if (enseignants == null) {
            enseignants = new ArrayList<>();
           
        }

        // Récupération du paramètre de recherche
        String searchQuery = request.getParameter("search");
        List<Enseignant> filteredEnseignants = new ArrayList<>();
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            for (Enseignant enseignant : enseignants) {
                if (enseignant.getNom().toLowerCase().contains(searchQuery.toLowerCase())) {
                    filteredEnseignants.add(enseignant);
                }
            }
        } else {
            filteredEnseignants = enseignants; // Si pas de recherche, affichez tous les enseignants
        }
    %>

    <!-- Barre de recherche -->
    <form method="GET" action="InscriptionEnseignantController">
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
    <form method="POST" action="InscriptionEnseignantController">
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
                    if (filteredEnseignants != null && !filteredEnseignants.isEmpty()) {
                        for (Enseignant enseignant : filteredEnseignants) { 
                %>
                <tr>
                    <td><%= enseignant.getIdEns() %></td>
                    <td><%= enseignant.getNom() %></td>
                    <td><%= enseignant.getPrenom() %></td>
                    <td><%= enseignant.getContact() %></td>
                    <td>
                    	<input type="hidden" name="idCours" value="<%= request.getAttribute("idCours") %>" />
                        <input type="checkbox" name="selectedEnseignants" value="<%= enseignant.getIdEns() %>">
                    </td>
                </tr>
                <% 
                        }
                    } else { 
                %>
                <tr>
                    <td colspan="5" class="text-center">Aucun enseignant trouvé.</td>
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

