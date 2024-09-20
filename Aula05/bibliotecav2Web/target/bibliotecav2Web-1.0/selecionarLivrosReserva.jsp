<%@page import="java.util.List"%>
<%@page import="com.umc.bibliotecav2web.model.Usuario"%>
<%@page import="com.umc.bibliotecav2web.model.Livro"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nova Reserva</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    
    <div class="container">
        <h1 class="mt-4 mb-4">Nova Reserva</h1>
        <div class="card m-3">
            <div class="card-body">
                <%
                if(request.getAttribute("error") == "true"){
                %>
                    <div class="alert alert-danger" role="alert">
                        Livro(s) não disponível(is)
                    </div>
                <%}%>
                <% 
                Usuario usuario = (Usuario)request.getSession().getAttribute("usuarioReserva"); 
                List<Livro> livrosReservados = (List<Livro>) request.getSession().getAttribute("livrosReservados");
                %>
                <div class="row ml-2">
                    <h3>ID: <%= request.getSession().getAttribute("idReserva") %></h3>
                </div>
                <div class="row ml-2">
                    <h3>Nome: <%= usuario.getNome() %></h3>
                </div>
                <div class="row ml-2">
                    <h3>N de Idendificação: <%= usuario.getNumeroIdentificacao()%></h3>
                </div>
                <h3 class="mt-4 mb-4">Livros a Reservar</h3>
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
                        for (Livro livro : livrosReservados) { 
                        %>
                            <tr>
                                <td><%= livro.getTitulo() %></td>
                                <td><%= livro.getAutor() %></td>
                                <td><%= livro.getAnoPublicacao() %></td>
                                <td>
                                    <form action="excluirLivroReserva" method="post" style="display:inline;">
                                        <input type="hidden" name="index" value="<%= livrosReservados.indexOf(livro)%>">
                                        <button type="submit" class="btn btn-danger">Excluir</button>
                                    </form>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
                <% 
                if(livrosReservados.isEmpty()){ 
                    if(request.getSession().getAttribute("idReserva")==null){
                %>
                        <input type="submit" class="btn btn-primary" value="Finalizar Reserva" disabled="true">
                        <a href="/index.jsp" class="btn btn-danger">Cancelar</a>
                <%
                    }
                    else{
                %>
                    <input type="submit" class="btn btn-primary" value="Aleterar Reserva" disabled="true">
                    <a href="/index.jsp" class="btn btn-danger">Cancelar</a>
                <%
                    }
                }
                else{
                    if(request.getSession().getAttribute("idReserva")==null){
                %>
                        <form action="finalizarReserva" method="post" class="mt-4">
                            <input type="submit" class="btn btn-primary" value="Finalizar Reserva">
                            <a href="/index.jsp" class="btn btn-danger">Cancelar</a>
                        </form>
                <%
                    }
                    else{
                %>
                        <form action="alterarReserva" method="post" class="mt-4">
                            <input type="submit" class="btn btn-primary" value="Alterar Reserva">
                            <a href="/index.jsp" class="btn btn-danger">Cancelar</a>
                        </form>
                <%
                    }
                }
                %>
            </div>
        </div>
        <form >
            <div class="input-group mb-3" >
                <input name="pesquisa" placeholder="Pesquisar Livro" type="search" class="form-control">
                <div class="input-group-append">
                <select name="tipo">
                    <option value="titulo" selected>Titulo</option>
                    <option value="autor">Autor</option>
                    <option value="anoPublicacao">Ano de Publicação</option>
                </select>
                <button type="submit" class="btn btn-primary ">Pesquisar</button>
                </div>
            </div>
        </form>
        <h3 class="mt-4 mb-4">Livros</h3>
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
                            <form action="reservar" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%= livro.getId()%>">
                                <button type="submit" class="btn btn-success">Reservar</button>
                            </form>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
    <!-- Link para o JavaScript do Bootstrap (opcional) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>