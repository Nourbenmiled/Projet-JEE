<!DOCTYPE html>
<html lang="fr">
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
                        <a class="nav-link active" href="/teachers">Enseignants</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/note">Notes</a>
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
        <h1 class="mt-4">${teacher.id != null ? 'Edit' : 'Create'} teacher</h1>
        <!-- Vérification si l'ID du cours est null ou non -->
        <form action="${teacher.id != null ? '/teachers/save' : '/teachers/save'}" method="post">
            <div class="mb-3">
                <label for="nom" class="form-label">teacher Name</label>
                <input type="text" class="form-control" id="nom" name="nom" value="${teacher.nom}" required>
            </div>
            <div class="mb-3">
                <label for="prenom" class="form-label">teacher First Name</label>
                <input type="text" class="form-control" id="prenom" name="prenom" value="${teacher.prenom}" required>
            </div>
            <!-- Si c'est un cours existant, on ajoute un champ caché pour l'ID -->
            <c:if test="${teacher.id != null}">
                <input type="hidden" name="id" value="${teacher.id}">
            </c:if>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    </div>
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>