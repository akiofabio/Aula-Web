<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Adicionar Aluno</title>
        <!-- Link para o arquivo CSS do Bootstrap hospedado no CDN -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1 class="mt-5">Adicionar Novo Aluno</h1>
            <form action="adicionarAluno" method="post">
                <div class="form-group">
                    <label for="nome">Nome</label>
                    <% if( request.getAttribute("nome") == null) {%>
                        <input type="text" class="form-control" name="nome" id="nome"  placeholder="Nome Completo">
                    <%} else if( request.getAttribute("nomeErroMensagem") == null){%>
                        <input type="text" class="form-control is-valid" name="nome" id="nome"  placeholder="Nome Completo" value="<%= request.getAttribute("nome") %>">
                    <%} else {%>
                        <input type="text" class="form-control is-invalid" name="nome" id="nome"  placeholder="Nome Completo" value="<%= request.getAttribute("nome")%>">
                        <div class="invalid-feedback"> <%= request.getAttribute("nomeErroMensagem") %>  </div>
                    <%}%>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <% if( request.getAttribute("email") == null) {%>
                        <input type="email" class="form-control" name="email" id="email"  placeholder="exemplo@email.com">
                    <%} else if (request.getAttribute("emailErroMensagem") == null) {%>
                        <input type="email" class="form-control is-valid" name="email" id="email"  placeholder="exemplo@email.com" value="<%= request.getAttribute("email")%>">
                    <%} else {%>
                        <input type="email" class="form-control is-invalid" name="email" id="email"  placeholder="exemplo@email.com" value="<%= request.getAttribute("email")%>">
                        <div class="invalid-feedback"> <%= request.getAttribute("emailErroMensagem") %>  </div>
                    <%}%>
                </div>
                <div class="form-group">
                    <label for="curso">Curso</label>
                    <% if( request.getAttribute("curso") == null) {%>
                        <input type="text" class="form-control" name="curso" id="curso"  placeholder="Ex. ADS, Adimistração etc.">
                    <%} else if( request.getAttribute("cursoErroMensagem") == null) {%>
                        <input type="text" class="form-control is-valid" name="curso" id="curso"  placeholder="Ex. ADS, Adimistração etc." value="<%= request.getAttribute("curso") %>">
                    <%} else {%>
                        <input type="text" class="form-control is-invalid" name="curso" id="curso"  placeholder="Ex. ADS, Adimistração etc." value="<%= request.getAttribute("curso") %>">
                        <div class="invalid-feedback"> <%= request.getAttribute("cursoErroMensagem") %>  </div>
                    <%}%>
                </div>
                <div class="form-group">
                    <label for="anoDeIngresso">Ano de Ingresso</label>
                    <% if( request.getAttribute("anoDeIngresso") == null) {%>
                        <input type="number" class="form-control" name="anoDeIngresso" id="anoDeIngresso">
                    <%}
                    else if( request.getAttribute("anoDeIngressoErroMensagem") == null)
                    {%>
                        <input type="number" class="form-control is-valid" name="anoDeIngresso" id="anoDeIngresso" value="<%= request.getAttribute("anoDeIngresso") %>">
                    <%} else {%>
                        <input type="number" class="form-control is-invalid" name="anoDeIngresso" id="anoDeIngresso" value="<%= request.getAttribute("anoDeIngresso") %>">
                        <div class="invalid-feedback"> <%= request.getAttribute("anoDeIngressoErroMensagem")%>  </div>
                    <%}%>
                </div>
                <button type="submit" class="btn btn-primary">Salvar</button>
            </form>
        </div>
        <!-- Link para o arquivo JavaScript do Bootstrap hospedado no CDN (opcional, se necessário) -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>