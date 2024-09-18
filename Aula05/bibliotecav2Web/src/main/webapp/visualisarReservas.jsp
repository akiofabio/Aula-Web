<%@page import="com.umc.bibliotecav2web.model.Reserva"%>
<%@page import="java.util.List"%>
<%@page import="com.umc.bibliotecav2web.model.Livro"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Reserva</title>
    <!-- Adicione o link para o arquivo CSS do Bootstrap -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .floating-button {
            position: fixed;
            bottom: 20px;
            right: 20px;
            z-index: 1000;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="mt-4 mb-4">Reservas</h1>
        
            <% 
            List<Reserva> reservas = (List<Reserva>) request.getAttribute("reservas");
            for (Reserva reserva : reservas) {
            %>
                <div class="card">
                    <div class="card-header">
                        <tr>
                            <td>Status: <%= reserva.getStatus() %></td>
                            <td>Data: <%= reserva.getDataReserva() %></td>
                        </tr>
                    </div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Título</th>
                                    <th>Autor</th>
                                    <th>Ano de Publicação</th>
                                    <th>Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                for (Livro livro : reserva.getLivros()) { 
                                %>
                                    <tr>
                                    <td><%= livro.getTitulo() %></td>
                                    <td><%= livro.getAutor() %></td>
                                    <td><%= livro.getAnoPublicacao() %></td>
                                    <td>
                                        <form action="alterarLivro" method="get" style="display:inline;">
                                            <input type="hidden" name="id" value="<%= livro.getId()%>">
                                            <button type="submit" class="btn btn-success">Alterar</button>
                                        </form>

                                        <form action="deletarLivro" method="post" style="display:inline;">
                                            <input type="hidden" name="id" value="<%= livro.getId()%>">
                                            <button type="submit" class="btn btn-danger">Excluir</button>
                                        </form>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        <%}%>
    </div>

    <!-- Botão flutuante para chamar a página de cadastro de livros -->
    <a href="acharUsuarioReserva.jsp" class="btn btn-primary floating-button">
        <span style="font-size: 1.5em;">+</span>
    </a>
    <!-- Adicione o link para o arquivo JS do Bootstrap (opcional, para funcionalidades extras) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>