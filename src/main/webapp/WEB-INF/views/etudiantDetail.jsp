<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@include file="headers/headeradmin_inc.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page import="model.Etudiant" %>
<%@ page import="model.Inscription" %>
<%@ page import="model.Resultat" %>
<%@ page import="model.Cours" %>
<%@ page import="java.util.ArrayList"%>
 
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 20px;
        background-color: #f8f9fa;
    }
    .container {
        max-width: 800px;
        margin: auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    h1, h2 {
        text-align: center;
    }
    .student-info, .course-list, .grades-table {
        margin-bottom: 20px;
    }
    .student-info p {
        font-size: 1.2em;
    }
    .course-list ul {
        list-style-type: none;
        padding: 0;
    }
    .course-list li {
        background-color: #e9ecef;
        margin: 5px 0;
        padding: 10px;
        border-radius: 4px;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
    }
    table, th, td {
        border: 1px solid #dee2e6;
    }
    th, td {
        padding: 10px;
        text-align: left;
    }
    th {
        background-color: #343a40;
        color: #fff;
    }
    td {
        background-color: #f8f9fa;
    }
    .average {
        font-weight: bold;
    }
    .extra-space {
        height: 50px;
    }
</style>
 
<div class="container">
    <h1>Informations de l'étudiant</h1>
    
    <div class="student-info">
        <% model.Etudiant etudiant = (model.Etudiant) request.getAttribute("etudiant"); %>
        <p><strong>Nom:</strong> <span id="student-last-name"><%= etudiant.getNom() %></span></p>
        <p><strong>Prénom:</strong> <span id="student-first-name"><%= etudiant.getPrenom() %></span></p>
        <p><strong>Contact:</strong> <span id="student-contact"><%= etudiant.getContact() %></span></p>
    </div>
 
    <div class="course-list">
        <h2>Liste des cours</h2>
        <ul id="courses">
            <%
            List<model.Inscription> inscriptionList = (List<model.Inscription>) request.getAttribute("inscriptionList");
            if (inscriptionList != null) {
                for (model.Inscription inscription : inscriptionList) {
            %>
                    <li><%= inscription.getCours().getNom() %></li>
            <%
                }
            } else {
            %>
                <li>Aucun cours trouvé</li>
            <%
            }
            %>
        </ul>
    </div>
 
    <div class="grades-table">
        <h2>Tableau des notes</h2>
        <table>
            <thead>
                <tr>
                    <th>Nom du cours</th>
                    <th>Notes</th>
                    <th>Moyenne</th>
                </tr>
            </thead>
            <tbody>
                <%
                if (inscriptionList != null) {
                    for (model.Inscription inscription : inscriptionList) {
                        // Utilisation de @SuppressWarnings pour supprimer l'avertissement de cast non vérifié
                        @SuppressWarnings("unchecked")
                        Set<model.Resultat> resultatSet = inscription.getCours().getResultats();
                        //List<model.Resultat> resultatList = new ArrayList<>();
 
                       /* if (resultatSet != null && !resultatSet.isEmpty()) {
                            resultatList.addAll(resultatSet); // ou utilisez directement new ArrayList<>(resultatSet);
                        } else {
                            System.out.println("Le Set de résultats est vide ou null.");
                        }*/
                        float moyenne = 0;
                        int nombre_element = 0;
                        boolean firstRow = true;
                %>
                        <tr>
                            <td ><%= inscription.getCours().getNom() %></td>
                <%
                        for (model.Resultat resultat : resultatSet) {
                            if (resultat.getEtudiant().getIdEtu() == etudiant.getIdEtu()) {
                                moyenne += resultat.getNote();
                                nombre_element++;
                                if (!firstRow) {
                %>
                        <tr>
                <%
                                }
                %>
                            <td><%= resultat.getNote() %></td>
                <%
                                if (firstRow) {
                                    firstRow = false;
                                }
                            }
                        }
                        if (nombre_element > 0) {
                %>
                            <td class="average"><%= moyenne / nombre_element %></td>
                <%
                        } else {
                %>
                            <td class="average">N/A</td>
                <%
                        }
                %>
                        </tr>
                <%
                    }
                }
                %>
            </tbody>
        </table>
    </div>
 
    <div class="download-btn">
        <a href="EtudiantController?action=pdf&id=<%= etudiant.getIdEtu() %>" class="btn btn-primary">Télécharger en PDF</a>
    </div>
    
    <div class="extra-space"></div>
    <div class="extra-space"></div>
</div>
<%@include file="footer_inc.jsp" %>


