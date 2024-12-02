<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@include file="headers/headeradmin_inc.jsp" %>

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
    /* Personnalisation des couleurs */
    .table-dark th {
        background-color: #007bff !important; /* Bleu pour les en-têtes */
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
    .btn-warning {
        background-color: #ffcc00;
        border-color: #ffcc00;
        color: black;
    }
    .btn-warning:hover {
        background-color: #e0b800;
        border-color: #e0b800;
    }
    .btn-danger {
        background-color: #e60000;
        border-color: #e60000;
    }
    .btn-danger:hover {
        background-color: #cc0000;
        border-color: #cc0000;
    }
    .btn-success {
        background-color: #28a745;
        border-color: #28a745;
    }
    .btn-success:hover {
        background-color: #218838;
        border-color: #218838;
    }
</style>

<section class="py-5">
    <div class="container mt-5">
        <h2 class="text-center mb-4 text-primary">Liste des Cours</h2>
        <a href="CoursControlller?action=add" class="btn btn-primary mb-3">Ajouter un Cours</a>
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>Nom</th>
                    <th>Description</th>
                    <th>Coefficient</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<model.Cours> coursList = (List<model.Cours>) request.getAttribute("coursList");
                    if (coursList != null) {
                        for (model.Cours cours : coursList) {
                %>
                            <tr>
                                <td><%= cours.getNom() %></td>
                                <td><%= cours.getDescription() %></td>
                                <td><%= cours.getCoef() %></td>
                                <td>
                                    <a href="CoursControlller?action=edit&id=<%= cours.getIdCours() %>" class="btn btn-warning btn-sm">Modifier</a>
                                    
                                    
                                    
                                    <a href="EnseignantController?action=inscriptionEnseignant&id=<%= cours.getIdCours() %>" class="btn btn-secondary btn-sm">Attribuer</a>
                                    
                                    
                                    
                                    <a href="EtudiantController?action=inscriptionEtudiant&id=<%= cours.getIdCours() %>" class="btn btn-success btn-sm">Inscrire</a>
                                    
                                    
                                    
                                    <a href="CoursControlller?action=delete&id=<%= cours.getIdCours() %>" class="btn btn-danger btn-sm" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce cours ?')">Supprimer</a>
                                </td>
                            </tr>
                <%
                        }
                    } else {
                %>
                        <tr>
                            <td colspan="4" class="text-center">Aucun cours disponible</td>
                        </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</section>

<%@include file="footer_inc.jsp" %>
