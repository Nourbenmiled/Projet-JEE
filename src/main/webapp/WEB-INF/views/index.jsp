<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="headers/headeracceuil_inc.jsp" %>

<style>
    /* Style global pour la page avec une image de fond */
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-image:  linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),  url('https://image.cdn2.seaart.me/2023-09-10/16750824840551429/b1326c94a4d8be317f490713b5a06298d1ef0567_high.webp'); /* Chemin vers l'image de fond */
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        min-height: 100vh;
        display: flex;
        justify-content: center;
         
        
    }

    /* Conteneur principal */
    .welcome-container {
        background-color: rgba(255, 255, 255, 0.85); /* Fond blanc semi-transparent */
        padding: 60px 30px;
        margin-top: 8em;
        margin-left: 20em;
        border-radius: 8px;
        box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.2);
        max-width: 1000px;
        width: 100%; /* S’étend sur 80% de la largeur de la page */
        text-align: center;
        align-items: center;
        justify-content: center;
    }

    .welcome-container h1 {
        color: #333;
        font-size: 2.5em;
        margin-bottom: 20px;
    }

    .welcome-container p {
        font-size: 1.2em;
        color: #666;
        margin-bottom: 30px;
        line-height: 1.6;
    }

    .welcome-container .btn-connexion {
        background-color: #4CAF50;
        color: white;
        padding: 12px 30px;
        font-size: 1.2em;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        text-decoration: none;
        transition: background-color 0.3s;
    }

    .welcome-container .btn-connexion:hover {
        background-color: #45a049;
    }
</style>

<section>
    <div class="welcome-container center">
        <h1>Bienvenue sur notre Application</h1>
        <p>
            Simplifiez la gestion de vos cours, enseignants, et etudiants en un seul endroit.
            Veuillez vous connecter pour acceder a vos fonctionnalites personnalisees.
        </p>
       
    </div>
</section>

<%@include file="footer_inc.jsp" %>
