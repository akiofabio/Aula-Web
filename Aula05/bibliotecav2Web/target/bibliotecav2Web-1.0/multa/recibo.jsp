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
        <title>Recibo</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <% List<Emprestimo> emprestimos = (List<Emprestimo>)request.getSession().getAttribute("emprestimos"); %>
            <% List<Emprestimo> emprestimosRetirados = (List<Emprestimo>) request.getSession().getAttribute("emprestimosRetirados"); %>
            <% Usuario usuario = (Usuario) request.getSession().getAttribute("usuario"); %>
            <% double total = (double) request.getSession().getAttribute("total"); %>
            
            
            
            <div class="card m-3">
                <div class="card-body">
                    <h1 class="mt-4 mb-4">Bliblioteca Mogiana</h1>
                    <h3 class="mt-4 mb-4">Recibo</h3>
                    <p>Data do Pagamento: <%= Mask.DataMask(emprestimos.getFirst().getMulta().getDataPagamento()) %></p>
                    <p>Nome: <%= usuario.getNome() %></p>
                    <p>N de Idendificação: <%= usuario.getNumeroIdentificacao()%></p>
                    <p>Pagamento refrente a(s) multa(s): </p>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th class="col-md-2">Livro</th>
                                <th class="col-md-2">Dias em Atraso</th>
                                <th class="col-md-2">Valor</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Emprestimo emprestimo : emprestimos) {
                                Multa multa = emprestimo.getMulta();%>
                            <tr>
                                <td><%= emprestimo.getLivro().getTitulo()%></td>
                                <td><%= CalculoMulta.calcularDiasAtrasos(multa.getData(), emprestimo.getDataDevolucao())%></td>
                                <td>R$ <%= Mask.DinheiroMask(multa.getValor())%></td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                    <p>Valor Total pago: <%= Mask.DinheiroMask(total) %></p>
                </div>
            </div>
        </div>
        <div class="d-flex justify-content-center"><a href="/index.jsp" class="btn btn-danger m-2">Voltar</a></div>
        
        <!-- Link para o JavaScript do Bootstrap (opcional) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>