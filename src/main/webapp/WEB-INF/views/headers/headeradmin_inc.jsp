<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Projet JEE</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRmRuBL5fvZ0/rFLYJflJnuFbE9K+oMocGcBkmnwl" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	 <link rel="stylesheet" href="../../../assets/css/menu.css"/>
	 
	<!-- Font Awesome CSS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
		
    <!-- Favicon (icone de l'onglet) -->
    <link rel="icon" href="https://cytech.cyu.fr/uas/CYTech/LOGO_TAMPON/MicrosoftTeams-image+%2813%29+%281%29.png" type="image/png">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .navbar {
            background-color: #007bff;
        }
        .navbar-brand, .nav-link {
            color: white;
        }
        .navbar-brand img {
            max-height: 40px; /* Taille maximale du logo dans la barre de navigation */
        }
    </style>
</head>
<body>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
        <!-- Logo avec lien -->
        <a class="navbar-brand" href="#">
            <img src="https://cytech.cyu.fr/uas/CYTech/LOGO_TAMPON/MicrosoftTeams-image+%2813%29+%281%29.png" alt="Logo de Gestion de Cours">
            Projet JEE
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">Accueil</a>
                </li>
                 <li class="nav-item">
    				<form action="CoursControlller" method="get">
        				<button class="nav-link btn" type="submit" style="border: none; background: none;">Gestion Cours</button>
   					</form>
				</li>
                
                <li class="nav-item">
    				<form action="EnseignantController" method="get">
       					 <button class="nav-link btn" type="submit" style="border: none; background: none;">Gestion Enseignant</button>
    				</form>
			   </li>
               <li class="nav-item">
    				<form action="EtudiantController" method="get">
       					 <button class="nav-link btn" type="submit" style="border: none; background: none;">Gestion Etudiant</button>
    				</form>
			   </li>
                
               <li class="nav-item">
    				<form action="ValidationController" method="get">
       					 <button class="nav-link btn" type="submit" style="border: none; background: none;">Validation</button>
    				</form>
			   </li>


<li class="nav-item">
    <form action="LogoutController" method="get">
        <button class="nav-link btn btn-danger" type="submit" style="color: #ffffff;">Déconnexion</button>
    </form>
</li>
            </ul>
        </div>
    </div>
</nav>