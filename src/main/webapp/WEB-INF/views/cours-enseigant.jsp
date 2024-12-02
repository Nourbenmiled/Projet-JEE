<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.InscriptionEnseignantHome" %>
<%@ page import="model.InscriptionEnseignant" %>
<%@ page import="java.util.List" %>

 
<%@include file="headers/headerenseignant_inc.jsp" %>


<% 
  
     HttpSession sessionAuth = request.getSession(false);
    if (sessionAuth == null || sessionAuth.getAttribute("enseignant") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    model.Enseignant enseignant = (model.Enseignant) sessionAuth.getAttribute("enseignant");
%>
<% 
	InscriptionEnseignantHome iHome = new InscriptionEnseignantHome();
	List<InscriptionEnseignant> inscriptions = iHome.findByEnseignant(enseignant);

%>


<!-- Main Section -->
<section class="py-5">
    <div class="container">
    	
    
        <h2 class="text-center mb-5">Liste des Cours Qui Vous Sont Attribués</h2>
        
        <div class="row">
        
         		<% 	
                 	if (inscriptions!= null && !inscriptions.isEmpty()) {
                        for (InscriptionEnseignant inscription : inscriptions) { 
                %>
        
        <form method="post" action="CoursEnseignantRedirection">
        
            <div class="col-md-6 mb-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title"><%= inscription.getCours().getNom() %></h5>
                        <p class="card-text"><%= inscription.getCours().getDescription() %></p>
                        <input type="hidden" name="inscription" value="<%= inscription.getIdIns() %>">
                        <button type="submit" class="btn btn-primary">Editer Notes</button>
                        <!--  <a href="#" class="btn btn-primary">Editer Notes</a>-->
                    </div>
                </div>
            </div>
            
         </form>  
         
          		<% 
                        }
                    } else { 
                %>
                <div>
                    <div colspan="5" class="text-center">Vous n'êtes chargé d'aucun cours pour l'instant.</div>
                </div>
                <% } %>
                                            
        </div>
    </div>
</section>

<%@include file="footer_inc.jsp" %>