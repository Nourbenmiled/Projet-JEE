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
        <h1 class="mt-4">Course List</h1>
                <a href="/cours/create" class="btn btn-primary mb-3">Add Course</a>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Course Name</th>
                            <th>Description</th>
                            <th>Coefficient</th>
                            <th>Affectation</th>

                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                       <c:forEach var="cours" items="${courses}">
                           <tr>
                               <td>${cours.nom}</td>
                               <td>${cours.description}</td>
                               <td>${cours.coef}</td>
                               <td>
                                   <a href="/cours/edit/${cours.id}" class="btn btn-warning">Edit</a>
                                   <a href="/cours/delete/${cours.id}" class="btn btn-danger">Delete</a>
                               </td>
                                <td>
                                   <a href="/inscription/assign?coursId=${cours.id}" class="btn btn-primary">Affecter</a>
                                   <a href="/inscription/list?coursId=${cours.id}" class="btn btn-info">Voir les affectations</a>
                                </td>
                           </tr>
                       </c:forEach>
                    </tbody>
                </table>
    </div>

    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>
