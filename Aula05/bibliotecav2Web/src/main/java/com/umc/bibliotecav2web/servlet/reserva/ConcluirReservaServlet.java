/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.umc.bibliotecav2web.servlet.reserva;

import com.umc.bibliotecav2web.model.Emprestimo;
import com.umc.bibliotecav2web.model.Livro;
import com.umc.bibliotecav2web.model.Reserva;
import com.umc.bibliotecav2web.model.Usuario;
import com.umc.bibliotecav2web.service.EmprestimoService;
import com.umc.bibliotecav2web.service.LivroService;
import com.umc.bibliotecav2web.service.ReservaService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Akio
 */
@WebServlet(name = "ConcluirReservaServlet", urlPatterns = {"/concluirReserva"})
public class ConcluirReservaServlet extends HttpServlet {
ReservaService reservaService = new ReservaService();
    LivroService livroService = new LivroService();
    EmprestimoService emprestimoService = new EmprestimoService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id =  request.getParameter("id");
        Document query = new Document("_id", new ObjectId(id));
        Reserva reserva = reservaService.getBy(query).getFirst();
        for(Livro livro : reserva.getLivros()){
            Date dataInicio = new Date();
            Date dataDevolucao = new Date(dataInicio.getTime() + TimeUnit.MILLISECONDS.convert(7, TimeUnit.DAYS));
            
            Usuario usuario = reserva.getUsuario();
            String statusDevolucao = "Aberto";
            Emprestimo emprestimo = new Emprestimo(dataInicio, dataDevolucao, livro, usuario, statusDevolucao, null);
            emprestimoService.newEmprestimo(emprestimo);
        }
        reserva.setStatus("Concluida");
        reservaService.updateReserva(reserva);
        response.sendRedirect("visualizarReservas");
    }
}
