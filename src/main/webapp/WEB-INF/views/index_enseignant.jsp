<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@include file="headers/headerenseignant_inc.jsp" %>

<style>

 body {
        font-family: Arial, sans-serif;
    }
    .container {
        width: 80%;
        margin: auto;
        padding: 20px;
        text-align: center;
    }
    h1 {
        color: #007bff;
        margin-bottom: 20px;
    }
    .intro {
        font-size: 1.1em;
        color: rgb(0, 0, 0);
        margin-bottom: 30px;
    }
    .dashboard {
        display: flex;
        justify-content: space-around;
        margin-top: 40px;
    }
    .dashboard-item {
        width: 200px;
        padding: 20px;
        border: 1px solid #ddd;
        border-radius: 8px;
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        text-align: center;
        transition: transform 0.2s;
    }
    .dashboard-item:hover {
        transform: scale(1.05);
        box-shadow: 0px 6px 12px rgba(0, 0, 0, 0.15);
    }
    .dashboard-item i {
        font-size: 2em;
        color: #007bff;
        margin-bottom: 10px;
    }
    .dashboard-item p {
        margin: 0;
        font-weight: bold;
        color: #333;
    }


</style>

<%@ page import="model.Enseignant" %>

<% 
  
     HttpSession sessionAuth = request.getSession(false);
    if (sessionAuth == null || sessionAuth.getAttribute("enseignant") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    Enseignant enseignant = (Enseignant) sessionAuth.getAttribute("enseignant");
%>



<section class="py-5">
    <div class="container">
        <!-- Titre de la page d'accueil -->
        <h1>Bienvenue Enseignant  <%= enseignant.getNom() %></h1>

        <!-- Description du projet -->
        <p class="intro">
            Ce système vous permet de gérer vos cours et les notes de vos étudiants.
            Utilisez le tableau de bord ci-dessous pour accéder aux fonctionnalités principales.
        </p>

        <!-- Tableau de bord avec les sections principales -->
        <div class="dashboard">
            <!-- Item 1 : Gestion des Cours -->
            <div class="dashboard-item">
                <i class="fas fa-book"></i> <!-- Icône de livre -->
                <p>Consulter Cours</p>
            </div>
            
            <!-- Item 2 : Gestion des Étudiants -->
            <div class="dashboard-item">
                <i class="fas fa-user-graduate"></i> <!-- Icône d'étudiant -->
                <p>Consulter Notes</p>
            </div>
            
            <!-- Item 2 : Gestion des Étudiants -->
            <div class="dashboard-item">
                <i class="fas fa-user-graduate"></i> <!-- Icône d'étudiant -->
                <p>Modifier Informations</p>
            </div>

        </div>
    </div>
</section>

<%@include file="footer_inc.jsp" %>
