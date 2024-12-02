<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.List" %>
<%@ include file="headers/headeradmin_inc.jsp" %>
 
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
    .table-dark th {
        background-color: #007bff !important;
        color: white;
    }
    .btn-primary {
        background-color: #0056b3;
        border-color: #0056b3;
    }
    .btn-primary:hover {
        background-color: #004085;
        border-color: #004085;
    }
    .btn-danger {
        background-color: #e60000;
        border-color: #e60000;
    }
    .btn-danger:hover {
        background-color: #cc0000;
        border-color: #cc0000;
    }
</style>
 
<section class="py-5">
    <div class="container mt-5">
        <h2 class="text-center mb-4 text-primary">Liste des Enseignants</h2>
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Contact</th>
                    <th>Utilisateur</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<model.Enseignant> enseignantList = (List<model.Enseignant>) request.getAttribute("enseignantList");
                    if (enseignantList != null) {
                        for (model.Enseignant enseignant : enseignantList) {
                %>
                            <tr>
                                <td><%= enseignant.getIdEns() %></td>
                                <td><%= enseignant.getNom() %></td>
                                <td><%= enseignant.getPrenom() %></td>
                                <td><%= enseignant.getContact() %></td>
                                <td><%= enseignant.getUtilisateur() != null ? enseignant.getUtilisateur().toString() : "Non spécifié" %></td>
                                <td>
                                    <a href="EnseignantController?action=delete&id=<%= enseignant.getIdEns() %>"
                                       class="btn btn-danger btn-sm"
                                       onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet enseignant ?')">
                                       Supprimer
                                    </a>
                                </td>
                            </tr>
                <%
                        }
                    } else {
                %>
                        <tr>
                            <td colspan="6" class="text-center">Aucun enseignant disponible</td>
                        </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</section>
 
<%@ include file="footer_inc.jsp" %>