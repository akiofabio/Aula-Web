<%-- 
    Document   : contatos
    Created on : 2 de set. de 2024, 21:53:57
    Author     : Akio
--%>

<%@page import="com.aulaweb.aula04.model.Contato"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link href="styles.css" rel="stylesheet">
        <title>JSP Page</title>
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
        <h1>Contatos</h1>
        <form action="/Aula04/ContatoServlet/novo" method="post"><input type="submit" class="btn btn-success m-3" value="Novo Contato"/></form>
            <div  class="table-responsive m-4" >
                <table class="table table-striped table-hover table-bordered">
                    <thead>
                        <tr>
                            <th >Nome</th>
                            <th>Email</th>
                            <th class="fixed-width-1"></th>
                            <th class="fixed-width-1"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                        List<Contato> contatos = (List<Contato>) request.getAttribute("contatos");
                        for(Contato contato : contatos ){
                        %>
                            <tr>
                                <td class="col-md-6"><%= contato.getNome()%></td>
                                <td class="col-md-6"><%= contato.getEmail()%></td>
                                <td class="col-md-1">
                                    <form action="/Aula04/ContatoServlet/alterar" method="post" style="width: auto;">
                                        <input type="hidden" name="id" value="<%= contato.getId()%>" >
                                        <input class="btn btn-primary" type="submit" value="Alterar">
                                    </form>
                                </td>
                                <td class="col-md-1">
                                    <form action="/Aula04/ContatoServlet/deletar" method="post">
                                        <input type="hidden" name="id" value="<%= contato.getId()%>" >
                                        <input class="btn btn-danger" type="submit" value="Excluir">
                                    </form>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
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
