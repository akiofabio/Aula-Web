<%@page import="com.aulaweb.r1parcial.model.Aluno"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Sistema de Gestão de Alunos</title>
        <!-- Link para o arquivo CSS do Bootstrap hospedado no CDN -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1 class="mt-5 mb-3">Sistema de Gestão de Alunos</h1>
            <!-- Mensgem de sucesso caso houver -->
            <% if (request.getAttribute("mensagem") != null) { %>
            <div class="alert alert-success" role="alert">
                <%= request.getAttribute("mensagem")%>
            </div>
            <% } %>
            <a href="adicionarAluno.jsp" class="m-5">
                <button class="btn btn-success">Adicionar Novo Aluno</button>
            </a>
            <div  class="table-responsive m-2" >
                <h3 class="mt-3">Todos os Alunos</h3>
                <table class="table table-striped table-hover table-bordered">
                    <thead>
                        <tr>
                            <th class="col-md-3">Nome</th>
                            <th class="col-md-3">Email</th>
                            <th class="col-md-3">Curso</th>
                            <th class="col-md-3">Ano de Ingresso</th>
                            <th class="col-md-1">Ações</th>
                            <th class="col-md-1">Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Aluno> alunos = (ArrayList<Aluno>) request.getAttribute("alunos");
                            for (Aluno aluno : alunos) {
                        %>
                        <tr>
                            <td class="col-md-3"><%= aluno.getNome()%></td>
                            <td class="col-md-3"><%= aluno.getEmail()%></td>
                            <td class="col-md-3"><%= aluno.getCurso()%></td>
                            <td class="col-md-2"><%= aluno.getAnoDeIngresso()%></td>
                            <td class="col-md-1">
                                <form action="editarAluno" method="get">
                                    <input type="hidden" name="id" value="<%= aluno.getId()%>" >
                                    <input class="btn btn-primary" type="submit" value="Editar">
                                </form>
                            </td>
                            <td class="col-md-1">
                                <form action="deletarAluno" method="post">
                                    <input type="hidden" name="id" value="<%= aluno.getId()%>" >
                                    <input class="btn btn-danger" type="submit" value="Deletar">
                                </form>
                            </td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- Link para o arquivo JavaScript do Bootstrap hospedado no CDN (opcional, se necessário) -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
