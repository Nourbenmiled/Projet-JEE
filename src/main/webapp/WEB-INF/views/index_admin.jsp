<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@include file="headers/headeradmin_inc.jsp" %>

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


<% 
     HttpSession sessionAuth = request.getSession(false);
    if (sessionAuth == null || sessionAuth.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>



<section class="py-5">
    <div class="container">
        <!-- Titre de la page d'accueil -->
        <h1>Bienvenue Admin  <%= sessionAuth.getAttribute("username") %></h1>

        <!-- Description du projet -->
        <p class="intro">
            Ce système vous permet de gérer les cours, les enseignants, les étudiants, et bien plus encore.
            Utilisez le tableau de bord ci-dessous pour accéder aux fonctionnalités principales.
        </p>

        <!-- Tableau de bord avec les sections principales -->
        <div class="dashboard">
            <!-- Item 1 : Gestion des Cours -->
            <div class="dashboard-item">
                <i class="fas fa-book"></i> <!-- Icône de livre -->
                <p>Gestion des Cours</p>
            </div>
            
            <!-- Item 2 : Gestion des Étudiants -->
            <div class="dashboard-item">
                <i class="fas fa-user-graduate"></i> <!-- Icône d'étudiant -->
                <p>Gestion des Étudiants</p>
            </div>

            <!-- Item 3 : Gestion des Enseignants -->
            <div class="dashboard-item">
                <i class="fas fa-chalkboard-teacher"></i> <!-- Icône de professeur -->
                <p>Gestion des Enseignants</p>
            </div>

            <!-- Item 4 : Rapports -->
            <div class="dashboard-item">
                <i class="fas fa-chart-bar"></i> <!-- Icône de rapport -->
                <p>Rapports</p>
            </div>
        </div>
    </div>
</section>

<%@include file="footer_inc.jsp" %>
