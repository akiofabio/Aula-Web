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
        <title>Pagar Multa</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1 class="mt-4 mb-4">Pagar Multa</h1>
            <% Multa multa = (Multa)request.getAttribute("multa"); %>
            <div class="alert alert-danger m-5" role="alert">
               Multa de R$ <%= multa.getValor() %> foi gerada pelo atrazo!!
            </div>
            <% 
            if ( request.getAttribute("mensagem") != null) { %>
                <div class="alert alert-success m-5" role="alert">
                    <%= request.getAttribute("mensagem") %>
                </div>
            <% } %>
            <form action="/pagarMulta" method="get" style="display:inline;">
                <input type="hidden" name="id" value="<%= multa.getId()%>" >
                <div class="form-group">
                    <label for="Meio de Pagamento"></label>
                    <input type="text" class="form-control" id="meio" name="meio">
                </div>
                <div class="form-group">
                    <label for="Valor"></label>
                    <input type="number" class="form-control" id="valor" name="valor" value="<%= multa.getValor()%>">
                </div>
                
                <button class="btn btn-success" type="submit" >Pagar</button>
            </form>
            <a href="/index.jsp" class="btn btn-danger m-3" style="display:inline;">Voltar</a>
        </div>
        <!-- Link para o JavaScript do Bootstrap (opcional) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
