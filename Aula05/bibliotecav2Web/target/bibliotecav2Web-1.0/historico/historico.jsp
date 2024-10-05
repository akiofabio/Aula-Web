<%@page import="java.util.Date"%>
<%@page import="com.umc.bibliotecav2web.util.CalculoMulta"%>
<%@page import="com.umc.bibliotecav2web.model.Usuario"%>
<%@page import="com.umc.bibliotecav2web.util.Mask"%>
<%@page import="java.time.ZoneId"%>
<%@page import="com.umc.bibliotecav2web.model.Emprestimo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.umc.bibliotecav2web.model.Reserva"%>
<%@page import="java.util.List"%>
<%@page import="com.umc.bibliotecav2web.model.Livro"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Lista de Reserva</title>
        <!-- Adicione o link para o arquivo CSS do Bootstrap -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
        .floating-detalhes {
            position: fixed;
            top: 50%;
            left: 50%;
            z-index: 1000;
        }
        </style>
    </head>
   

    <body>
        <div class="container">
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Detalhes</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body">
                          <p>Titulo: <span id="dtTitulo"></span></p>
                            <p>Autor: <span id="dtAutor"></span></p>
                            <p>Ano de publicação: <span id="dtAno"></p>
                            <p>Quantidae Disponivel: <span id="dtQuantidade"></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <% Usuario usuario = (Usuario) request.getAttribute("usuario"); %>
            <div class="row ml-2">
                Nome: <%= usuario.getNome() %>              
            </div>
            <div class="row ml-2">
                N de Idendificação: <%= usuario.getNumeroIdentificacao()%>
            </div>
            <h1 class="mt-4 mb-4">Reservas</h1>
            
            <%
            List<Reserva> reservas = new ArrayList();
            if(request.getAttribute("reservas") == null){
            %>
                <h3 class="mt-4 mb-4 alert-danger">Nenhuma reserva encontrado</h3>
            <%
            }
            else{
                reservas = (List<Reserva>) request.getAttribute("reservas");
            }
            for (Reserva reserva : reservas) {
            %>
                <div class="card m-3">
                    <div class="card-header">
                        <div class="row">
                            <div class="col">
                                Status: <%= reserva.getStatus() %>
                            </div>
                            <div class="col">
                                Data: <%= Mask.DataMask(reserva.getDataReserva()) %>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th class="col-md-6">Título</th>
                                    <th class="col-md-5">Autor</th>
                                    <th class="col-md-1">Ação</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                for (Livro livro : reserva.getLivros()) { 
                                %>
                                    <tr>
                                        <td><%= livro.getTitulo() %></td>
                                        <td><%= livro.getAutor() %></td>
                                        <td>
                                            <button type="button" class="btn btn-info btn-lg" onclick="detalhar('<%= livro.getTitulo() %>','<%= livro.getAutor()%>','<%= livro.getAnoPublicacao()%>','<%= livro.getNumeroCopiasDisponiveis()%>')" >Detalhes</button>
                                        </td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            <%}%>
            <a href="/index.jsp" class="btn btn-danger m-3">Voltar</a>
            <h1 class="mt-4 mb-4">Emprestimo</h1>
            <%
            List<Emprestimo> emprestimos = new ArrayList();
            if(request.getAttribute("emprestimos") == null){
            %>
                <h3 class="mt-4 mb-4 alert-danger">Nenhum emprestimo encontrado</h3>
            <%
            }
            else{
                emprestimos = (List<Emprestimo>) request.getAttribute("emprestimos");
            }
            for(Emprestimo emprestimo : emprestimos){ 
            %>
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
                        <% Livro livro = emprestimo.getLivro(); %>
                        <div class="col">
                            Titulo: <%= livro.getTitulo() %>
                        </div>
                        <div class="col">
                            Autor: <%= livro.getAutor() %>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col">
                            Data Inicio: <%= Mask.DataMask(emprestimo.getDataInicio()) %>
                        </div>
                        <div class="col">
                            Data Devolução: <%= Mask.DataMask(emprestimo.getDataDevolucao()) %>
                        </div>
                    </div>
                    
                    <div class="row ml-3 mt-3">
                        <button type="button" class="btn btn-info btn-lg" onclick="detalhar('<%= livro.getTitulo() %>','<%= livro.getAutor()%>','<%= livro.getAnoPublicacao()%>','<%= livro.getNumeroCopiasDisponiveis()%>')" >Detalhes</button>
                    </div>
                    <% 
                    if(emprestimo.getMulta()!= null){ 
                        if(emprestimo.getMulta().getStatus().equals("Aberta")){
                    %>
                            <div class="alert alert-danger mt-3" role="alert">
                                Obs: A multa de valor R$ <%=Mask.DinheiroMask(emprestimo.getMulta().getValor()) %> está em Aberto
                            </div>
                    <%  }
                        else if(emprestimo.getMulta().getStatus().equals("Pago")){
                    %>
                            <div class="alert alert-success mt-3" role="alert">
                                Obs: A multa de valor R$ <%=Mask.DinheiroMask(emprestimo.getMulta().getValor()) %> pago no dia <%=Mask.DataMask(emprestimo.getMulta().getDataPagamento()) %>
                            </div>
                    <% }
                    }  
                    else if(emprestimo.getStatusDevolucao().equals("Aberto") && emprestimo.getDataDevolucao().before(new Date())){
                    %>
                        <div class="alert alert-warning mt-3" role="alert">
                            Obs: O emprestimo está atrasado! A multa será de R$ <%=Mask.DinheiroMask(CalculoMulta.calcularMulta(new Date(), emprestimo.getDataDevolucao())) %> se devolvido hoje!
                        </div>
                    <% } %>
                </div>
            </div>
            <% } %>
            <a href="/index.jsp" class="btn btn-danger m-3">Voltar</a>
        </div>
        <script>
            function detalhar(titulo,autor,ano,qtd){
                document.getElementById("dtTitulo").innerHTML = titulo;
                document.getElementById("dtAutor").innerHTML = autor;
                document.getElementById("dtAno").innerHTML = ano;
                document.getElementById("dtQuantidade").innerHTML = qtd;
                $('#myModal').modal('show'); 
            };
        </script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
    </body>
</html>