/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.umc.bibliotecav2web.servlet.multa;

import com.umc.bibliotecav2web.model.Emprestimo;
import com.umc.bibliotecav2web.model.Livro;
import com.umc.bibliotecav2web.model.Multa;
import com.umc.bibliotecav2web.model.Pagamento;
import com.umc.bibliotecav2web.model.Reserva;
import com.umc.bibliotecav2web.model.Usuario;
import com.umc.bibliotecav2web.service.EmprestimoService;
import com.umc.bibliotecav2web.service.MultaService;
import com.umc.bibliotecav2web.service.PagamentoService;
import com.umc.bibliotecav2web.service.UsuarioService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Akio
 */
@WebServlet(name = "pagarServlet", urlPatterns = {"/pagarMulta"})
public class PagarMultaServlet extends HttpServlet {
    
    final EmprestimoService emprestimoService = new EmprestimoService();
    final MultaService multaService = new MultaService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numeroIdentificacao = request.getParameter("numeroIdentificacao");
        Document query = new Document("numeroIdentificacao",numeroIdentificacao);
        UsuarioService usuarioService = new UsuarioService();       
        List<Usuario> usuarios = usuarioService.getBy(query);
        if(usuarios.isEmpty()){
            response.sendRedirect("acharUsuarioMulta.jsp?error=true");
        }
        else{
            Usuario usuario = usuarios.getFirst();
            Document emprestimoAtrasadoQuery = new Document("usuario", new ObjectId(usuario.getId()))
                    .append("statusDevolucao", "Devolvido com Atraso");
            List<Emprestimo> emprestimosTemp = emprestimoService.getBy(emprestimoAtrasadoQuery);
            List<Emprestimo> emprestimos = new ArrayList<>();
            double total = 0.0;
            for (Emprestimo emprestimo : emprestimosTemp) {
                if (emprestimo.getMulta().getStatus().equals("Aberta")) {
                    emprestimos.add(emprestimo);
                    total+=emprestimo.getMulta().getValor();
                }
            }
            List<Emprestimo> emprestimosRetirados = new ArrayList<>();
            request.getSession().setAttribute("total", total);
            request.getSession().setAttribute("emprestimosRetirados", emprestimosRetirados);
            request.getSession().setAttribute("emprestimos", emprestimos);
            request.getSession().setAttribute("usuario", usuario);
            request.getRequestDispatcher("multa/pagarMulta.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Emprestimo> emprestimos = (List<Emprestimo>)request.getSession().getAttribute("emprestimos");
        Date agora = new Date();
        for(Emprestimo emprestimo : emprestimos){
            emprestimo.getMulta().setDataPagamento(agora );
            emprestimo.getMulta().setStatus("Pago");
            multaService.update(emprestimo.getMulta());
        }
        request.setAttribute("mensagem", "Pagamento realizado com sucesso");
        request.getRequestDispatcher("multa/gerarRecibo.jsp").forward(request, response);
    }
}
