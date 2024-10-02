<%@page import="com.umc.bibliotecav2web.model.Livro"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Alterar de Livros</title>
    <!-- Link para o CSS do Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1 class="mt-4 mb-4">Alteração de Livro</h1>
        <form action="alterarLivro" method="post">
            <% Livro livro = (Livro) request.getAttribute("livro"); %>
            <input type="hidden" name="id" value="<%= livro.getId()%>">
            <div class="form-group">
                <label for="titulo">Título:</label>
                <input type="text" class="form-control" id="titulo" name="titulo" value="<%= livro.getTitulo()%>">
            </div>
            <div class="form-group">
                <label for="autor">Autor:</label>
                <input type="text" class="form-control" id="autor" name="autor" value="<%= livro.getAutor() %>">
            </div>
            <div class="form-group">
                <label for="anoPublicacao">Ano de Publicação:</label>
                <input type="text" class="form-control" id="anoPublicacao" name="anoPublicacao" value="<%= livro.getAnoPublicacao() %>">
            </div>
            <div class="form-group">
                <label for="quantidadeDisponivel">Quantidade Disponível:</label>
                <input type="text" class="form-control" id="quantidadeDisponivel" name="quantidadeDisponivel" value="<%= livro.getNumeroCopiasDisponiveis() %>">
            </div>
            <button type="submit" class="btn btn-primary">Alterar Livro</button>
        </form>
    </div>
    <!-- Link para o JavaScript do Bootstrap (opcional) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>