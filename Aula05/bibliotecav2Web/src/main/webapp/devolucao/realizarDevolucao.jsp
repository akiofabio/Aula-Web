<%@page import="com.umc.bibliotecav2web.model.Emprestimo"%>
<%@page import="com.umc.bibliotecav2web.model.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="com.umc.bibliotecav2web.model.Livro"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Fazer Devolução</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1 class="mt-4 mb-4">Devolução</h1>
            <% if (request.getParameter("error") != null) { %>
                <div class="alert alert-danger" role="alert">
                    Numero de identificação incorreto. Por favor, tente novamente.
                </div>
            <% } %>
            <% if (request.getAttribute("mensagem") != null) { %>
                <div class="alert alert-success" role="alert">
                    <%= request.getAttribute("mensagem") %>
                </div>
            <% } %>
            <% 
            Usuario usuario = (Usuario)request.getAttribute("usuario"); 
            List<Emprestimo> emprestimos = (List<Emprestimo>) request.getAttribute("emprestimos");
            %>
            <div class="row ml-2">
                <h3>Nome: <%= usuario.getNome() %></h3>
            </div>
            <div class="row ml-2">
                <h3>N de Idendificação: <%= usuario.getNumeroIdentificacao()%></h3>
            </div>
            <% for(Emprestimo emprestimo : emprestimos){ %>
            <div class="card m-3">
                <div class="card-header">
                    <div class="row">
                        <div class="col">
                            <%= emprestimo.getLivro().getTitulo() %>
                        </div>
                        <div class="col">
                            Status: <%= emprestimo.getStatusDevolucao()%>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col">
                            Autor: <%= emprestimo.getLivro().getAutor() %>
                        </div>
                        <div class="col">
                            Ano de Publicação <%= emprestimo.getLivro().getAnoPublicacao() %>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            Data Inicio: <%= emprestimo.getDataInicio()%>
                        </div>
                        <div class="col">
                            Data Devolução <%= emprestimo.getDataDevolucao() %>
                        </div>
                    </div>
                    <form action="realizarDevolucao" method="post" class="mt-2">
                        <input type="hidden" name="id" value="<%= emprestimo.getId() %>">
                        <button type="submit" class="btn btn-primary">Realizar Devolução</button>
                    </form>
                </div>
            </div>
            <% } %>
            <a href="/index.jsp" class="btn btn-danger m-3">Cancelar</a>
        </div>
        <!-- Link para o JavaScript do Bootstrap (opcional) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>