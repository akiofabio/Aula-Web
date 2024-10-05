<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Biblioteca</title>
    <!-- Link para o arquivo CSS do Bootstrap hospedado no CDN -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    
    <div class="container">
        <h1 class="mt-5">Biblioteca Mogiana</h1>
        <% if ( request.getAttribute("mensagem") != null) { %>
            <div class="alert alert-success m-5" role="alert">
                <%= request.getAttribute("mensagem") %>
            </div>
        <% } %>
        <p class="lead">O que você gostaria de fazer?</p>
        <div class="list-group">
            <a href="/visualizarLivros" class="list-group-item list-group-item-action">Livros Cadastrados</a>
            <a onclick="naoImplementado()" class="list-group-item list-group-item-action">Usuários Cadastrados</a>
            <a href="/historico/acharUsuarioHistorico.jsp" class="list-group-item list-group-item-action">Histórico</a>
            <a href="/multa/acharUsuarioMulta.jsp" class="list-group-item list-group-item-action" >Pendencias</a>
            <a href="/visualizarReservas" class="list-group-item list-group-item-action">Reservas</a>
            <a onclick="naoImplementado()" class="list-group-item list-group-item-action">Realisar Emprestimo</a>
            <a href="/devolucao/acharUsuarioDevolucao.jsp" class="list-group-item list-group-item-action">Realisar Devolução</a>
        </div>
    </div>
        <script>
            function naoImplementado(){
                alert("Não Implentado!!!");
            }
        </script>
    <!-- Link para o arquivo JavaScript do Bootstrap hospedado no CDN (opcional, se necessário) -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
