<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Note</title>
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
                           <a class="nav-link" href="/coursStudent">Courses</a>
                       </li>
                       <li class="nav-item">
                           <a class="nav-link" href="/studentStudent">Etudiants</a>
                       </li>
                       <li class="nav-item">
                         <a class="nav-link" href="/teacherStudent">Enseignants</a>
                       </li>
                       <li class="nav-item">
                           <a class="nav-link active" href="/noteStudent">Notes</a>
                       </li>
                   </ul>
               </div>
                  <form class="d-flex">
                     <a href="/auth/login" class="btn btn-danger">Log out</a>
                 </form>
           </div>
       </nav>
    <div class="container mt-4">
        <h1 class="mt-4">Note List</h1>
        <a href="/note/create" class="btn btn-primary mb-3">Add Note</a>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>Note</th>
                <th>Cours</th>
                <th>Teacher</th>
                <th>Student</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="note" items="${notes}">
                <tr>
                    <td>${note.id}</td>
                    <td>${note.note}</td>
                    <td>${note.cours.nom}</td>
                    <td>${note.teacher.nom} ${note.teacher.prenom}</td>
                    <td>${note.student.nom} ${note.student.prenom}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>
