/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.umc.bibliotecav2web.servlet;

import com.umc.bibliotecav2web.model.Livro;
import com.umc.bibliotecav2web.model.Reserva;
import com.umc.bibliotecav2web.model.Usuario;
import com.umc.bibliotecav2web.service.LivroService;
import com.umc.bibliotecav2web.service.ReservaService;
import com.umc.bibliotecav2web.service.UsuarioService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Akio
 */
@WebServlet(name = "finalizarReservaServlet", urlPatterns = {"/finalizarReserva"})
public class FinalizarReservaServlet extends HttpServlet {
    final ReservaService reservaService = new ReservaService();
    final LivroService livroService = new LivroService();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario = (Usuario)request.getSession().getAttribute("usuarioReserva");
        List<Livro> livrosReservados = (List<Livro>)request.getSession().getAttribute("livrosReservados");
        Reserva reserva = new Reserva(usuario,livrosReservados,new Date(),"Aberta");
        if(reservaService.newReserva(reserva)){
            response.sendRedirect("visualizarReservas");
        }
        else{
            List<Livro> todosLivros = livroService.getAllLivros();
            request.setAttribute("error", "true");
            request.setAttribute("livros", todosLivros);
            request.getRequestDispatcher("selecionarLivrosReserva.jsp").forward(request, response);
        }
    }
    
}
