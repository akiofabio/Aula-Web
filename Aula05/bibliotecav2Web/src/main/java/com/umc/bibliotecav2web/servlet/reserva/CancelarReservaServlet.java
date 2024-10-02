/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.umc.bibliotecav2web.servlet.reserva;

import com.umc.bibliotecav2web.model.Livro;
import com.umc.bibliotecav2web.model.Reserva;
import com.umc.bibliotecav2web.service.LivroService;
import com.umc.bibliotecav2web.service.ReservaService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Akio
 */
@WebServlet(name = "CancelarReservaServlet", urlPatterns = {"/cancelarReserva"})
public class CancelarReservaServlet extends HttpServlet {
    ReservaService reservaService = new ReservaService();
    LivroService livroService = new LivroService();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id =  request.getParameter("id");
        Document query = new Document("_id", new ObjectId(id));
        Reserva reserva = reservaService.getBy(query).getFirst();
        for(Livro livro : reserva.getLivros()){
            livro.setNumeroCopiasDisponiveis(livro.getNumeroCopiasDisponiveis() + 1);
            livroService.updateLivro(livro);
        }
        reserva.setStatus("Cancelado");
        reservaService.updateReserva(reserva);
        response.sendRedirect("visualizarReservas");
    }
    
}
