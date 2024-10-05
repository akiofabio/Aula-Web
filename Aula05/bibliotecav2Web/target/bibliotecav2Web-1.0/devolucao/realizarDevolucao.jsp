<%@page import="com.umc.bibliotecav2web.model.Multa"%>
<%@page import="com.umc.bibliotecav2web.util.Mask"%>
<%@page import="com.umc.bibliotecav2web.model.Emprestimo"%>
<%@page import="com.umc.bibliotecav2web.model.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="com.umc.bibliotecav2web.model.Livro"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Fazer Devolução</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <% 
            Usuario usuario = (Usuario)request.getAttribute("usuario"); 
            List<Emprestimo> emprestimos = (List<Emprestimo>) request.getAttribute("emprestimos");
            %>
            <h1 class="mt-4 mb-4">Devolução</h1>
            <% if (request.getAttribute("multaMensagem") != null) { %>
                <div class="alert alert-danger" role="alert">
                    <%= request.getAttribute("multaMensagem") %>
                </div>
            <% } %>
            <% 
            if (request.getAttribute("multas") != null) {
                List<Multa> multas = (List<Multa>) request.getAttribute("multas");
                if(!multas.isEmpty()){
            %>
                    <div class="alert alert-warning" role="alert">
                        Há multa(s) pedente(s):
                        <% for(Multa multa : multas){ %>
                            <div class="row ml-1">
                                <div class="col">
                                    Valor: R$ <%= Mask.DinheiroMask(multa.getValor())%> 
                                </div>
                                <div class="col">
                                    Data: <%= Mask.DataMask(multa.getData())%> 
                                </div>
                            </div>
                        <% } %>
                        <form action="/pagarMulta" method="get">
                            <input type="hidden" name="numeroIdentificacao" value="<%= usuario.getNumeroIdentificacao() %>">
                            <button class="btn btn-success mt-2" type="submit">Pagar</button>
                        </form>
                    </div>
            <% 
                }
            }
            %>
            
            <% if (request.getAttribute("mensagem") != null) { %>
                <div class="alert alert-success" role="alert">
                    <%= request.getAttribute("mensagem") %>
                </div>
            <% } %>
            
            <div class="row ml-2">
                <h3>Nome: <%= usuario.getNome() %></h3>
            </div>
            <div class="row ml-2">
                <h3>N de Idendificação: <%= usuario.getNumeroIdentificacao()%></h3>
            </div>
            
            
            <% for(Emprestimo emprestimo : emprestimos){ %>
            <div class="card m-3">
                <div class="card-header">
                    <div class="row">
                        <div class="col">
                            Status: <%= emprestimo.getStatusDevolucao()%>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col">
                            Titulo: <%= emprestimo.getLivro().getTitulo() %>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            Autor: <%= emprestimo.getLivro().getAutor() %>
                        </div>
                    
                        <div class="col">
                            Ano de Publicação: <%= emprestimo.getLivro().getAnoPublicacao() %>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            Data Inicio: <%= Mask.DataMask(emprestimo.getDataInicio())%>
                        </div>
                        <div class="col">
                            Data Devolução: <%= Mask.DataMask(emprestimo.getDataDevolucao())  %>
                        </div>
                    </div>
                    <form action="realizarDevolucao" method="post" class="mt-2">
                        <input type="hidden" name="id" value="<%= emprestimo.getId() %>">
                        <button type="submit" class="btn btn-primary">Realizar Devolução</button>
                    </form>
                </div>
            </div>
            <% } %>
            <a href="/index.jsp" class="btn btn-danger m-3">Cancelar</a>
        </div>
        <!-- Link para o JavaScript do Bootstrap (opcional) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>