<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@include file="WEB-INF/views/headers/headeracceuil_inc.jsp" %>



<section class="d-flex justify-content-center align-items-center vh-100">
    <div class="card p-4" style="width: 600px;">
        <h3 class="text-center mb-4" style="color: #007bff;">Connexion</h3>
        
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger text-center">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>
        
        <form action="AuthController" method="post">
            <div class="form-group">
                <label for="username">Nom d'utilisateur</label>
                <input type="text" class="form-control" id="username" name="username" <% if(request.getAttribute("username")!=null) { %>value="<%=request.getAttribute("username")%>"<% }%>  required>
            </div>
            <div class="form-group">
                <label for="password">Mot de passe</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-primary mt-2 content-center">Se connecter</button>
            </div>
        </form>
    </div>
</section>


<%@include file="WEB-INF/views/footer_inc.jsp" %>
