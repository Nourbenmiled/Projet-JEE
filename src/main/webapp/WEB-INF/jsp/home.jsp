<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page 1</title>
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
                              <a class="nav-link " href="/cours">Courses</a>
                          </li>
                      </ul>

                  </div>
                     <form class="d-flex">

                                         <a href="/auth/login" class="btn btn-danger">Log out</a>
                                        </form>
              </div>
          </nav>

    <div class="container mt-4">
        <h1>Bienvenue sur la Page 1</h1>
        <p>Voici le contenu de la Page 1.</p>
    </div>

    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>
