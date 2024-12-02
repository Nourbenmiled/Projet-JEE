<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@include file="headers/headerenseignant_inc.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="model.InscriptionEnseignantHome" %>
<%@ page import="model.InscriptionEnseignant" %>
<%@ page import="model.InscriptionHome" %>
<%@ page import="model.Inscription" %>








<% 
  
     HttpSession sessionAuth = request.getSession(false);
    if (sessionAuth == null || sessionAuth.getAttribute("enseignant") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    //model.Enseignant enseignant = (model.Enseignant) sessionAuth.getAttribute("enseignant");
    String id = (String) request.getAttribute("inscription");
    int idIns = Integer.parseInt(id);
    System.out.println(idIns);
%>
<% 
	InscriptionEnseignantHome ieHome = new InscriptionEnseignantHome();
	InscriptionEnseignant inscriptionEns = ieHome.findById(idIns);
	
	InscriptionHome iHome = new InscriptionHome();
	List<Inscription> inscriptions = iHome.findByCours(inscriptionEns.getCours());
	

%>





<section>
    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-header text-white bg-primary">
                <h3 class="card-title">Attribuer une Note � un �tudiant</h3>
                
               <% 
    // V�rifier si un message de succ�s existe dans la requ�te
    if (request.getAttribute("message") != null) {
%>
    <div class="alert alert-success" role="alert">
        <%= request.getAttribute("message") %>
    </div>
<%
    }
%>

                
            </div>
            <div class="card-body">
                <form action="EditNoteController" method="post"> <!-- Modifiez l'URL si n�cessaire -->
                    <!-- S�lection du cours -->
                    <div class="mb-3">
                        <label for="cours" class="form-label">Cours :</label>
                        <select name="cours" id="cours" class="form-select" required>
                            <option value="" disabled selected>-- S�lectionnez un cours --</option>
                            <option value="<%= inscriptionEns.getCours().getIdCours() %>"><%= inscriptionEns.getCours().getNom() %></option>
                        </select>
                    </div>
					<input type="hidden" name="idIns" value="<%= idIns %>">
                    <!-- S�lection de l'�tudiant -->
                    <div class="mb-3">
                        <label for="etudiant" class="form-label">�tudiant :</label>
                        <select name="etudiant" id="etudiant" class="form-select" required>
                            <option value="" disabled selected>-- S�lectionnez un �tudiant --</option>
                            <%
                                // Exemple d'extraction de la liste des �tudiants depuis une session ou une base de donn�es
                                //List<String> etudiantList = (List<String>) request.getAttribute("etudiantList");
                                if (inscriptions != null) {
                                    for (Inscription i : inscriptions) {
                            %>
                                    <option value="<%= i.getEtudiant().getIdEtu() %>"><%= i.getEtudiant().getNom()+" "+i.getEtudiant().getPrenom() %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <!-- Entr�e de la note -->
                    <div class="mb-3">
                        <label for="note" class="form-label">Note :</label>
                        <input type="number" id="note" name="note" class="form-control" min="0" max="20" step="0.1" required>
                    </div>

                    <!-- Bouton de soumission -->
                    <button type="submit" class="btn btn-success w-100">Attribuer la Note</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Lien vers Bootstrap JS et Popper.js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</section>


<%@include file="footer_inc.jsp" %>

