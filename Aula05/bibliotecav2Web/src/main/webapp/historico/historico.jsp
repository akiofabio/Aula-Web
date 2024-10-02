<%@page import="com.umc.bibliotecav2web.model.Emprestimo"%>
<%@page import="java.util.ArrayList"%>
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
        
    </head>
    <body>
        <div class="container">
            <h1 class="mt-4 mb-4">Reservas</h1>
            
            <%
            List<Reserva> reservas = new ArrayList();
            if(request.getAttribute("reservas") != null){
            %>
            <h3 class="mt-4 mb-4 alert-danger">Nenhuma reserva encontrado</h3>
            <%
            }
            else{
                reservas = (List<Reserva>) request.getAttribute("reservas");
            }
            for (Reserva reserva : reservas) {
            %>
                <div class="card m-3">
                    <div class="card-header">
                        <div class="row">
                            <div class="col">
                                Status: <%= reserva.getStatus() %>
                            </div>
                            <div class="col">
                                Data: <%= reserva.getDataReserva() %>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="row ml-2">
                            Nome: <%= reserva.getUsuario().getNome() %>              
                        </div>
                        <div class="row ml-2">
                            N de Idendificação: <%= reserva.getUsuario().getNumeroIdentificacao()%>
                        </div>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Título</th>
                                    <th>Autor</th>
                                    <th>Ano de Publicação</th>
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
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            <%}%>
            <a href="/index.jsp" class="btn btn-danger m-3">Voltar</a>
            <h1 class="mt-4 mb-4">Emprestimo</h1>
            <%
            List<Emprestimo> emprestimos = new ArrayList();
            if(request.getAttribute("emprestimos") == null){
            %>
            <h3 class="mt-4 mb-4 alert-danger">Nenhum emprestimo encontrado</h3>
            <%
            }
            else{
                emprestimos = (List<Emprestimo>) request.getAttribute("emprestimos");
            }
            for(Emprestimo emprestimo : emprestimos){ 
            %>
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
            <a href="/index.jsp" class="btn btn-danger m-3">Voltar</a>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>