<%-- 
    Document   : index
    Created on : 31 de ago. de 2024, 15:21:27
    Author     : Akio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aula 04</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link href="styles.css" rel="stylesheet">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-lg bg-light">
                <div class="container-fluid">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="/Aula04/index.jsp">Home</a>
                        </li>
                        <li class="nav-item">
                            <form action="/Aula04/ContatoServlet/contatos" method="get"><input class="nav-link" type="submit" value="Contatos"></form>
                        </li>
                    </ul>
                    <ul class="navbar-nav justify-content-end">
                        <li>
                            <a class="nav-link" href="https://akiofabio.github.io/Aula-Web/">Voltar</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <h1 class="m-5">Bem-vindo ao meu site!</h1>
        <div class='card m-3'>
            <div class='card-body'>
                <form method="post" action="FormServlet">
                    <div class="form-control" >
                        <label>Nome:</label>
                        <input type='text' name="nome"/>
                    </div>
                    <div class="form-control">
                        <label>Email:</label>
                        <input type='text' name="email"/>
                    </div>
                    <button type="submit">Salvar</button>
                </form>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
    <footer class="bg-light  fixed-bottom">
        <div class="row text-center">
            <div class="col">
                <span>Email: fabio.ikenoue@fatec.sp.gov.br</span>
            </div>
            <div class="col">
                <a class="nav-link" href="https://github.com/akiofabio">GitHub</a>
            </div>
        </div>
    </footer>
</html>
