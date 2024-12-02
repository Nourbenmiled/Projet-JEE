<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="headers/headeracceuil_inc.jsp" %>

<section class="d-flex justify-content-center align-items-center vh-100" style="background-color: #f5f5f5;">
    <div class="card p-5 shadow" style="width: 500px; border-radius: 10px;">
        <h3 class="text-center mb-4" style="color: #007bff;">Inscription</h3>
        
        
        <% if (request.getAttribute("succes") != null) { %>
            <div class="alert alert-success text-center">
                <%= request.getAttribute("succes") %>
            </div>
        <% } %>
        
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-error text-center">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>
        
        <form id="registerForm" action="RegisterController" method="post" onsubmit="return validateForm()">
            
            <!-- Champ Nom d'utilisateur -->
            <div class="form-group mb-3">
                <label for="username">Nom d'utilisateur</label>
                <input type="text" class="form-control" id="username" name="username" required>
                <small id="usernameError" class="form-text text-danger" style="display: none;">Veuillez entrer un nom d'utilisateur valide.</small>
            </div>
            
            <!-- Champ Mot de passe -->
            <div class="form-group mb-3">
                <label for="password">Mot de passe</label>
                <input type="password" class="form-control" id="password" name="password" required>
                <small id="passwordError" class="form-text text-danger" style="display: none;">
                    Le mot de passe doit contenir au moins 6 caractères, une majuscule, un caractère spécial et ne doit pas contenir une partie de votre nom d'utilisateur.
                </small>
            </div>
            
            <!-- Champ Confirmation du mot de passe -->
            <div class="form-group mb-3">
                <label for="confirmPassword">Confirmer le mot de passe</label>
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                <small id="confirmPasswordError" class="form-text text-danger" style="display: none;">Les mots de passe ne correspondent pas.</small>
            </div>
            
            <!-- Sélection du rôle -->
            <div class="form-group mb-4">
                <label for="role">Rôle</label>
                <select class="form-control" id="role" name="role" required>
                    <option value="0">Admin</option>
                    <option value="1">Enseignant</option>
                    <option value="2">Étudiant</option>
                </select>
            </div>
            
            <!-- Bouton de validation -->
            <div class="text-center">
                <button type="submit" class="btn btn-primary mt-3" style="width: 100%;">Valider</button>
            </div>
        </form>
    </div>
</section>

<script>
    function validateForm() {
        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("confirmPassword").value;
        
        let isValid = true;

        // Vérification de l'email
        const emailPattern = /^[a-zA-Z0-9._%+-]+[a-zA-Z0-9.-]+[a-zA-Z]{2,}$/;
        if (!emailPattern.test(username)) {
            document.getElementById("usernameError").style.display = "block";
            isValid = true;
        } else {
            document.getElementById("usernameError").style.display = "none";
        }

        // Vérification du mot de passe
        const passwordPattern = /^(?=.*[A-Z])(?=.*[!@#$%^&*])(?=.{6,})/;
        if (!passwordPattern.test(password) || password.toLowerCase().includes(username.split("@")[0].toLowerCase())) {
            document.getElementById("passwordError").style.display = "block";
            isValid = false;
        } else {
            document.getElementById("passwordError").style.display = "none";
        }

        // Vérification de la confirmation du mot de passe
        if (password !== confirmPassword) {
            document.getElementById("confirmPasswordError").style.display = "block";
            isValid = false;
        } else {
            document.getElementById("confirmPasswordError").style.display = "none";
        }

        return isValid;
    }
</script>

<%@include file="footer_inc.jsp" %>
