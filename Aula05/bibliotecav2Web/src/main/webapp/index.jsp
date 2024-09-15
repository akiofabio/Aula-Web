<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Biblioteca</title>
    <!-- Link para o arquivo CSS do Bootstrap hospedado no CDN -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="mt-5">Biblioteca Mogiana</h1>
        <p class="lead">O que você gostaria de fazer?</p>
        <div class="list-group">
            <a href="/visualizarLivros" class="list-group-item list-group-item-action">Listar Livros Cadastrados</a>
            <a href="/reservarLivro" class="list-group-item list-group-item-action">Listar Reservas de Livros</a>
        </div>
    </div>
    <!-- Link para o arquivo JavaScript do Bootstrap hospedado no CDN (opcional, se necessário) -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
