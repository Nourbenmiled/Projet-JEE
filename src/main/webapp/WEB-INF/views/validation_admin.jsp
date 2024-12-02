<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 



<%@include file="headers/headeradmin_inc.jsp" %>
<%@ page import="model.Utilisateur" %>
<%@ page import="java.util.List" %>






<section class="py-5">
    <div class="container">
    
   <%-- Vérification et affichage du message --%>
    <%
        String message = (String) request.getAttribute("message");
        if (message != null && !message.isEmpty()) {
    %>
        <div class="alert alert-info text-center" role="alert">
            <%= message %>
        </div>
    <%
        }
    %>
    
    
    
        <h2 class="text-center mb-4">Validation des Inscriptions</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Nom d'utilisateur</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    // Récupérer la liste des utilisateurs depuis l'attribut de requête
                    List<Utilisateur> userList = (List<Utilisateur>) request.getAttribute("userList");
                    if (userList != null && !userList.isEmpty()) {
                        for (Utilisateur user : userList) {
                %>
                    <tr>
                        <td><%= user.getUsername() %></td>
                        <td>
                            <form method="post" action="ValiderInscriptionController" onsubmit="return confirm('Êtes-vous sûr de vouloir valider cette inscription ?');">
                                <input type="hidden" name="userId" value="<%= user.getIdUser() %>" />
                                <button type="submit" class="btn btn-success btn-sm">Valider</button>
                            </form>
                            <form method="post" action="RefuserInscriptionController" onsubmit="return confirm('Êtes-vous sûr de vouloir refuser cette inscription ?');" style="display: inline;">
                                <input type="hidden" name="userId" value="<%= user.getIdUser() %>" />
                                <button type="submit" class="btn btn-danger btn-sm">Refuser</button>
                            </form>
                        </td>
                    </tr>
                <% 
                        }
                    } else { 
                %>
                    <tr>
                        <td colspan="2" class="text-center">Aucun utilisateur à valider.</td>
                    </tr>
                <% 
                    } 
                %>
            </tbody>
        </table>
    </div>
</section>





<%@include file="footer_inc.jsp" %>
