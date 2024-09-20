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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Akio
 */
@WebServlet(name = "AlterarReservaServlet", urlPatterns = {"/alterarReserva"})
public class AlterarReservaServlet extends HttpServlet {
    final UsuarioService usuarioService= new UsuarioService();
    final LivroService livroService= new LivroService();
    final ReservaService reservaService = new ReservaService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println("id = " + id);
        Reserva reserva = reservaService.getBy(new Reserva(id)).getFirst();
        System.out.println("reserva = " + reserva);

        List<Livro> livrosReservados = reserva.getLivros();
        Usuario usuario = reserva.getUsuario();
        
        List<Livro> livros = livroService.getAllLivros();
        
        request.setAttribute("livros", livros);
        request.getSession().setAttribute("idReserva", id);
        request.getSession().setAttribute("livrosReservados", livrosReservados);
        request.getSession().setAttribute("usuarioReserva", usuario);
        request.getRequestDispatcher("selecionarLivrosReserva.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = (String)request.getSession().getAttribute("idReserva");
        List<Livro> livros= (List<Livro>) request.getSession().getAttribute("livrosReservados");
        Reserva reserva = reservaService.getBy(new Reserva(id)).getFirst();
        reserva.setLivros(livros);
        if(reservaService.updateReserva(reserva)){
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
