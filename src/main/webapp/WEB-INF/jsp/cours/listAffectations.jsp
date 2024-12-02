<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Affectations du cours ${cours.nom}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/css/bootstrap.min.css">
</head>
<body>
 <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
           <div class="container-fluid">
               <a class="navbar-brand" href="#">Mon Application</a>
               <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                   <span class="navbar-toggler-icon"></span>
               </button>
               <div class="collapse navbar-collapse" id="navbarNav">
                   <ul class="navbar-nav">

                       <li class="nav-item">
                           <a class="nav-link active" href="/cours">Courses</a>
                       </li>
                       <li class="nav-item">
                           <a class="nav-link" href="/students">Etudiants</a>
                       </li>
                       <li class="nav-item">
                         <a class="nav-link" href="/teachers">Enseignants</a>
                       </li>
                         <li class="nav-item">
                            <a class="nav-link" href="/validation">Validation</a>
                         </li>
                   </ul>
               </div>
                  <form class="d-flex">
                     <a href="/auth/login" class="btn btn-danger">Log out</a>
                 </form>
           </div>
       </nav>
<div class="container mt-4">
    <h1>Affectations pour le cours : ${cours.nom}</h1>

    <a href="/cours" class="btn btn-secondary mb-3">Retour a la liste des cours</a>

    <table class="table table-striped">
        <thead>
            <tr>
                <th>Enseignant</th>
                <th>Etudiant</th>

            </tr>
        </thead>
        <tbody>
            <c:forEach var="inscription" items="${inscriptions}">
                <tr>
                    <td>
                        <c:forEach var="teacher" items="${inscription.teachers}">
                            ${teacher.nom} ${teacher.prenom}<br/>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach var="student" items="${inscription.students}">
                            ${student.nom} ${student.prenom}<br/>
                        </c:forEach>
                    </td>

                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>
