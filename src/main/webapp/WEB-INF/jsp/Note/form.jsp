<!DOCTYPE html>
<html lang="fr">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>teacher Creation</title>
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
                        <a class="nav-link" href="/students">Etudiants</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/teachers">Enseignants</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="/note">Notes</a>
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
        <h1 class="mt-4">${note.id != null ? 'Edit' : 'Create'} Note</h1>
        <!-- Vérification si l'ID du cours est null ou non -->
        <form action="${note.id != null ? '/note/save/' : '/note/save'}" method="post">
            <div class="mb-3">
                <label for="note" class="form-label">Note</label>
                <input type="number" class="form-control" id="note" name="note" value="${note.note}" required>
            </div>
            <div class="mb-3">
                <label for="cours_id" class="form-label">Choisir un Cours :</label>
                <select id="cours_id" name="cours_id" class="form-select" required>
                    <option value="">-- Select the Course --</option>
                    <c:forEach var="course" items="${cours}">
                        <option value="${course.id}" ${note.teacher != null && note.teacher.id == course.id ? 'selected' : ''}>
                                ${course.nom}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="student_id" class="form-label">Choisir un enseignant :</label>
                <select id="student_id" name="student_id" class="form-select" required>
                    <option value="">-- Select the student --</option>
                    <c:forEach var="student" items="${students}">
                        <option value="${student.id}" ${note.teacher != null && note.teacher.id == student.id ? 'selected' : ''}>
                                ${student.nom} ${student.prenom}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="teacher_id" class="form-label">Choisir un enseignant :</label>
                <select id="teacher_id" name="teacher_id" class="form-select" required>
                    <option value="">-- Select the teacher --</option>
                    <c:forEach var="teacher" items="${teachers}">
                        <option value="${teacher.id}" ${note.teacher != null && note.teacher.id == teacher.id ? 'selected' : ''}>
                                ${teacher.nom} ${teacher.prenom}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <!-- Si c'est un cours existant, on ajoute un champ caché pour l'ID -->
            <c:if test="${note.id != null}">
                <input type="hidden" name="id" value="${note.id}">
            </c:if>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    </div>

    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>
