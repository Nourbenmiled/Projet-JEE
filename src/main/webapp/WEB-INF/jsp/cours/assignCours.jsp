<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Affecter un cours</title>
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

    <h1>Affecter le cours : ${cours.nom}</h1>

    <form action="/inscription/save" method="post">
        <input type="hidden" name="coursId" value="${cours.id}">

        <div class="mb-3">
            <label for="teachers" class="form-label">Choisir les enseignants :</label>
            <select name="teacherIds" id="teachers" class="form-select" >
            <option value="0">..</option>
                <c:forEach var="teacher" items="${teachers}">

                    <option value="${teacher.id}">${teacher.nom} ${teacher.prenom}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label for="students" class="form-label">Choisir les etudiants :</label>
            <select name="studentIds" id="students" class="form-select" >
             <option value="0">..</option>
                <c:forEach var="student" items="${students}">

                    <option value="${student.id}">${student.nom} ${student.prenom}</option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn btn-success">Enregistrer</button>
    </form>
</div>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>
