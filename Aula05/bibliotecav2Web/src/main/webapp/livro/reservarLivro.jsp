<%@page import="java.util.List"%>
<%@page import="com.umc.bibliotecav2web.model.Livro"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Livros</title>
    <!-- Adicione o link para o arquivo CSS do Bootstrap -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="mt-4 mb-4">Livros</h1>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Título</th>
                    <th>Autor</th>
                    <th>Ano de Publicação</th>
                    <th>Qtde. Disponível</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <% 
                List<Livro> livros = (List<Livro>) request.getAttribute("livros");
                for (Livro livro : livros) { 
                %>
                    <tr>
                        <td><%= livro.getTitulo() %></td>
                        <td><%= livro.getAutor() %></td>
                        <td><%= livro.getAnoPublicacao() %></td>
                        <td><%= livro.getNumeroCopiasDisponiveis()%></td>
                        <td>
                            <form action="deletarLivro" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%= livro.getId()%>">
                                <button type="submit" class="btn btn-danger">Reservar</button>
                            </form>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>

    <!-- Botão flutuante para chamar a página de cadastro de livros -->
    <a href="cadastrarLivro.jsp" class="btn btn-primary floating-button">
        <span style="font-size: 1.5em;">+</span>
    </a>
    <!-- Adicione o link para o arquivo JS do Bootstrap (opcional, para funcionalidades extras) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
