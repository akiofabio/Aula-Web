<%@page import="com.umc.bibliotecav2web.util.Mask"%>
<%@page import="com.umc.bibliotecav2web.util.CalculoMulta"%>
<%@page import="com.umc.bibliotecav2web.model.Multa"%>
<%@page import="com.umc.bibliotecav2web.model.Emprestimo"%>
<%@page import="com.umc.bibliotecav2web.model.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="com.umc.bibliotecav2web.model.Livro"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Gerar Recibo</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1 class="mt-4 mb-4">Gerar Recibo</h1>
            <% if ( request.getAttribute("mensagem") != null) { %>
                <div class="alert alert-success m-5" role="alert">
                    <%= request.getAttribute("mensagem") %>
                </div>
            <% } %>
            
            <div class="row m-4">
                <div class="col">
                    <form action="/gerarRecibo" method="get">
                        <button type="submit" class="btn btn-success">Gerar Recibo</button>
                    </form>
                </div>
                <div class="col">
                    <a href="/index.jsp" class="btn btn-danger">Voltar</a>
                </div>
            </div>
            
            
        </div>
        <!-- Link para o JavaScript do Bootstrap (opcional) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>