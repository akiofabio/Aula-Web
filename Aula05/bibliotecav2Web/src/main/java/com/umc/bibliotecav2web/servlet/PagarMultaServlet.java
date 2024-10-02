/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.umc.bibliotecav2web.servlet;

import com.umc.bibliotecav2web.model.Livro;
import com.umc.bibliotecav2web.model.Multa;
import com.umc.bibliotecav2web.model.Pagamento;
import com.umc.bibliotecav2web.service.MultaService;
import com.umc.bibliotecav2web.service.PagamentoService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Akio
 */
@WebServlet(name = "pagarServlet", urlPatterns = {"/pagarServlet"})
public class PagarMultaServlet extends HttpServlet {
    
    final MultaService multaService = new MultaService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id =  request.getParameter("id");
        Document multaQuery = new Document("_id",new ObjectId(id));
        Multa multa = multaService.getBy(multaQuery).getFirst();
        request.setAttribute("multa", multa);
        request.getRequestDispatcher("multa/pagarMulta.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id =  request.getParameter("id");
        String meio = request.getParameter("meio");
        
        Document multaQuery = new Document("_id",new ObjectId(id));
        Multa multa = multaService.getBy(multaQuery).getFirst();
        
        Pagamento pagamento = new Pagamento(id, multa.getValor(), new Date(), meio, multa);
        PagamentoService pagamentoService = new PagamentoService();
        pagamentoService.newPagamento(pagamento);
        
        request.setAttribute("mensagem", "Pagamento Realizado com Sucesso!!");
        request.getRequestDispatcher("multa/pagarMulta.jsp").forward(request, response);
    }
}
