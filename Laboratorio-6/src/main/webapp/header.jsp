<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
<body class="body">
<header class="header">
    <nav class="nav flexbox">
        <div class="title flexbox">
            <span class="title-header">Mantenimiento de Cursos</span>
        </div>
        <ul class="list flexbox gap-2">
            <li>
                <a href="{% url 'index' %}" class="link">Página de Inicio</a>
            </li>
            <li>
                <a href="{% url 'faq' %}" class="link">FAQ</a>
            </li>
            <li>
                <a href="#" class="link">Acerca de Mi</a>
            </li>
        </ul>
    </nav>
</header>
</body>
</html>