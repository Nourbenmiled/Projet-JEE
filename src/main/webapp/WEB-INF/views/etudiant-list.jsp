<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@include file="headers/headeradmin_inc.jsp" %>
<%@ page import="java.util.List" %>
<div class="container mt-5">
    <h2 class="text-center mb-4 text-primary">Liste des �tudiants</h2>
    <!--  <a href="EtudiantController?action=add" class="btn btn-primary mb-3">Ajouter un �tudiant</a> -->
    <table class="table table-striped table-bordered">
        <thead class="table-dark">
            <tr>
                <th>Nom</th>
                <th>Pr�nom</th>
                <th>Contact</th>
                <th>Moyenne</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<model.Etudiant> etudiants = (List<model.Etudiant>) request.getAttribute("etudiantList");
                if (etudiants != null) {
                    for (model.Etudiant etudiant : etudiants) {
            %>
                        <tr>
                            <td><%= etudiant.getNom() %></td>
                            <td><%= etudiant.getPrenom() %></td>
                            <td><%= etudiant.getContact() %></td>
                            <td><%= etudiant.getMoyenne() != 0.0 ? etudiant.getMoyenne() : "Non Disponible" %></td>
                            <td>
                                <!-- <a href="EtudiantController?action=edit&id=<%= etudiant.getIdEtu() %>" class="btn btn-warning btn-sm">Modifier</a> -->
                                <a href="EtudiantController?action=detail&id=<%= etudiant.getIdEtu() %>" class="btn btn-primary btn-sm">d�tails</a>
                                <a href="EtudiantController?action=delete&id=<%= etudiant.getIdEtu() %>" class="btn btn-danger btn-sm" onclick="return confirm('�tes-vous s�r de vouloir supprimer cet �tudiant ?')">Supprimer</a>
                            </td>
                        </tr>
            <%
                    }
                } else {
            %>
                    <tr>
                        <td colspan="5" class="text-center">Aucun �tudiant disponible</td>
                    </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>
<%@include file="footer_inc.jsp" %>
