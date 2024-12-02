<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.InscriptionHome" %>
<%@ page import="model.Inscription" %>
<%@ page import="java.util.List" %>

 
<%@include file="headers/headeretudiant_inc.jsp" %>


<% 
  
     HttpSession sessionAuth = request.getSession(false);
    if (sessionAuth == null || sessionAuth.getAttribute("etudiant") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    model.Etudiant etudiant = (model.Etudiant) sessionAuth.getAttribute("etudiant");
%>
<% 
	InscriptionHome iHome = new InscriptionHome();
	List<Inscription> inscriptions = iHome.findByEtudiant(etudiant);

%>


<!-- Main Section -->
<section class="py-5">
    <div class="container">
    	
    	 <!-- Titre de la page d'accueil -->
        <h1>Bienvenue Etudiant  <%= etudiant.getNom() %></h1>

        <!-- Description du projet -->
        <p class="intro">
            Ce système vous permet de gérer les cours, les enseignants, les étudiants, et bien plus encore.
            Utilisez le tableau de bord ci-dessous pour accéder aux fonctionnalités principales.
        </p>
    
    
    
        <h2 class="text-center mb-5">Liste des Cours</h2>
        
        <div class="row">
        
         		<% 	
                 	if (inscriptions!= null && !inscriptions.isEmpty()) {
                        for (Inscription inscription : inscriptions) { 
                %>
        
        <form method="post" action="">
        
            <div class="col-md-6 mb-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title"><%= inscription.getCours().getNom() %></h5>
                        <p class="card-text"><%= inscription.getCours().getDescription() %></p>
                        <a href="#" class="btn btn-primary">Détails du cours</a>
                        <a href="EtudiantController?action=detail&id=<%= etudiant.getIdEtu() %>" class="btn btn-primary btn-sm">Détails des Notes</a>
                    </div>
                </div>
            </div>
            
         </form>  
         
          		<% 
                        }
                    } else { 
                %>
                <div>
                    <div colspan="5" class="text-center">Aucun enseignant trouvé.</div>
                </div>
                <% } %>
                                            
        </div>
    </div>
</section>

<%@include file="footer_inc.jsp" %>