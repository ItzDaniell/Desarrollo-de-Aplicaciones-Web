<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar bg-primary navbar-expand-lg fixed-top">
    <div class="container">
        <a class="navbar-brand link-warning fs-3" href="#">Stack</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll"
                aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarScroll">
            <ul class="navbar-nav mx-auto my-2 my-lg-0 navbar-nav-scroll " style="--bs-scroll-height: 100px;">
                <li class="nav-item">
                    <a class="nav-link  link-light" aria-current="page" href="/">Inicio</a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link link-light dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Gestión Cursos
                    </a>
                    <ul class="dropdown-menu ">
                        <li><a class="dropdown-item" href="/demo_war_exploded/cController">Cursos</a></li>
                    </ul>
                </li>
            </ul>

            <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Buscar" aria-label="Search">
                <button class="btn btn-outline-light" type="submit">Búsqueda</button>
            </form>
        </div>
    </div>
</nav>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" ></script>
</body>
</html>