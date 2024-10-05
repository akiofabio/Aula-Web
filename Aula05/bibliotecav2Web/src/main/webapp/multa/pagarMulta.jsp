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
        <title>Pagar Multa</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1 class="mt-4 mb-4">Pagar Multas</h1>
            
            
            <% List<Emprestimo> emprestimos = (List<Emprestimo>)request.getSession().getAttribute("emprestimos"); %>
            <% List<Emprestimo> emprestimosRetirados = (List<Emprestimo>) request.getSession().getAttribute("emprestimosRetirados"); %>
            <% Usuario usuario = (Usuario) request.getSession().getAttribute("usuario"); %>
            <% if ( request.getAttribute("mensagem") != null) { %>
                <div class="alert alert-success m-5" role="alert">
                    <%= request.getAttribute("mensagem") %>
                </div>
            <% } %>
            <div class="row ml-2">
                <h3>Nome: <%= usuario.getNome() %></h3>
            </div>
            <div class="row ml-2">
                <h3>N de Idendificação: <%= usuario.getNumeroIdentificacao()%></h3>
            </div>
            
            
            <div class="card">
                <div class="card-body">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th class="col-md-2">Valor</th>
                                <th class="col-md-2">Livro</th>
                                <th class="col-md-2">Data de Emprestimo</th>
                                <th class="col-md-2">Data de Prevista</th>
                                <th class="col-md-2">Data de Devolição</th>
                                <th class="col-md-2">Dias de Atraso</th>
                                <th class="col-md-1">Ação</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for(Emprestimo emprestimo : emprestimos){ 
                                Multa multa = emprestimo.getMulta(); %>
                                <tr>
                                    <td>R$ <%= Mask.DinheiroMask(multa.getValor()) %></td>
                                    <td><%= emprestimo.getLivro().getTitulo()%></td> 
                                    <td><%= Mask.DataMask(emprestimo.getDataInicio()) %></td> 
                                    <td><%= Mask.DataMask(emprestimo.getDataDevolucao()) %></td> 
                                    <td><%= Mask.DataMask(multa.getData())%></td>
                                    <td><%= CalculoMulta.calcularDiasAtrasos(multa.getData(),emprestimo.getDataDevolucao())%></td>
                                    <td>
                                        <form action="/retirarMulta" method="post">
                                            <input type="hidden" name="id" value="<%= emprestimo.getId() %>">
                                            <button type="submit" class="btn btn-danger">Retirar</button>
                                        </form>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                    <div>
                        Total: R$ <%= Mask.DinheiroMask((double)request.getSession().getAttribute("total"))  %>
                    </div>
                </div>
            </div>
            <form action="/pagarMulta" method="post" style="display:inline;">
                <button type="submit" class="btn btn-success m-3">Pagar</button>
            </form>
            <a href="/index.jsp" class="btn btn-danger m-3" style="display:inline;">Cancelar</a>
        </div>
        <h3>Multas há pagar</h3>
        <% if(emprestimosRetirados.isEmpty()){ %>
            <div class="alert alert-success m-5" role="alert">
                Não há outras multas a apgar!!
            </div>
        <% } else {%>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th class="col-md-2">Valor</th>
                        <th class="col-md-2">Livro</th>
                        <th class="col-md-2">Data de Emprestimo</th>
                        <th class="col-md-2">Data de Prevista</th>
                        <th class="col-md-2">Data de Devolição</th>
                        <th class="col-md-2">Dias de Atraso</th>
                        <th class="col-md-1">Ação</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Emprestimo emprestimo : emprestimosRetirados) {
                        Multa multa = emprestimo.getMulta();%>
                    <tr>
                        <td>R$ <%= Mask.DinheiroMask(multa.getValor())%></td>
                        <td><%= emprestimo.getLivro().getTitulo()%></td> 
                        <td><%= Mask.DataMask(emprestimo.getDataInicio())%></td> 
                        <td><%= Mask.DataMask(emprestimo.getDataDevolucao())%></td> 
                        <td><%= Mask.DataMask(multa.getData())%></td>
                        <td><%= CalculoMulta.calcularDiasAtrasos(multa.getData(), emprestimo.getDataDevolucao())%></td>
                        <td>
                            <form action="/adicionarMulta" method="post">
                                <input type="hidden" name="id" value="<%= emprestimo.getId() %>">
                                <button type="submit" class="btn btn-success">Adicionar</button>
                            </form>
                        </td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        <% } %>
        <!-- Link para o JavaScript do Bootstrap (opcional) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
