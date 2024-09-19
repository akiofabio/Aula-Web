<%@page import="java.util.List"%>
<%@page import="com.umc.bibliotecav2web.model.Livro"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fazer Reserva</title>
    <!-- Link para o CSS do Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1 class="mt-4 mb-4">Nova Reserva</h1>
        <% if (request.getParameter("error") != null) { %>
            <div class="alert alert-danger" role="alert">
                Numero de identificação incorreto. Por favor, tente novamente.
            </div>
        <% } %>
        <form action="acharUsuarioReserva" method="get">
            <div class="form-group">
                <label for="titulo">Digite o Numero de identificação do usuario:</label>
                <input type="text" class="form-control" id="numeroIdentificacao" name="numeroIdentificacao" >
            </div>
            <button type="submit" class="btn btn-primary">Confimar</button>
        </form>
        
    </div>
    <!-- Link para o JavaScript do Bootstrap (opcional) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
