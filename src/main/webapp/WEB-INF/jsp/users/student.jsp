<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page 2</title>
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
                           <a class="nav-link" href="/cours">Courses</a>
                       </li>
                        <li class="nav-item">
                          <a class="nav-link active" href="/students">Etudiants</a>
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

    <div class="container mt-5">
                           <h1 class="text-center mb-4">Liste des Etudiants</h1>
                           <table class="table table-striped">
                               <thead>
                                   <tr>
                                       <th>ID</th>
                                       <th>Nom</th>
                                       <th>Prenom</th>
                                       <th>Nom d'utilisateur</th>
                                       <th>Action</th>
                                   </tr>
                               </thead>
                               <tbody>
                                   <c:forEach var="student" items="${students}">
                                       <tr>
                                           <td>${student.id}</td>
                                           <td>${student.nom}</td>
                                           <td>${student.prenom}</td>
                                           <td>${student.username}</td>
                                           <td>
                                               <form method="post" action="/students/delete" class="d-inline">
                                                   <input type="hidden" name="id" value="${student.id}" />
                                                   <button type="submit" class="btn btn-danger btn-sm">Supprimer</button>
                                               </form>
                                           </td>
                                       </tr>
                                   </c:forEach>
                               </tbody>
                           </table>
                       </div>


    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>
