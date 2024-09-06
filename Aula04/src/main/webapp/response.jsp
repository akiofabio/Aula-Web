<%-- 
    Document   : response
    Created on : 31 de ago. de 2024, 16:47:03
    Author     : Akio
--%>

<%@page import="java.util.List"%>
<%@page import="com.aulaweb.aula04.model.Teste"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resposta</title>
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
        <h1><%= request.getParameter("nome") %>, obrigado por enviar seu contato </h1>
        <h2>e de novo (Teste Session) </h2>
        <h1> <%= request.getSession().getAttribute("nome") %>, obrigado por enviar seu contato </h1>
        
        
        <% if(request.getAttribute("testes") != null){ %>
        <table class="table table-striped table-hover table-bordered m-2">
                <thead>
                    <tr>
                        <th scope="col">Nome</th>
                        <th scope="col">Email</th>
                    </tr>
                </thead>
                <tbody>
                    <%    
                    List<Teste> testes =(List<Teste>) request.getAttribute("testes");
                    for(Teste teste : testes){
                    %>
                        <tr>
                            <td><%= teste.getNome()%></td>
                            <td><%= teste.getEmail()%></td>
                        </tr>
                    <%}%>
                </tbody>
            </table>
        <% }else{ %>
            <form action="FormServlet" method="get"><input class="button bt-primary" type="submit" value="Ver todos"></form>
        <%}%>
            <a href="/Aula04/index.jsp" class="m-3">Voltar</a>
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
