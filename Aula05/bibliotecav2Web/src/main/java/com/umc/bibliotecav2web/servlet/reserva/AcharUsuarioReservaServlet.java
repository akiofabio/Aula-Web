/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.umc.bibliotecav2web.servlet.reserva;

import com.umc.bibliotecav2web.model.Livro;
import com.umc.bibliotecav2web.model.Usuario;
import com.umc.bibliotecav2web.service.LivroService;
import com.umc.bibliotecav2web.service.UsuarioService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Akio
 */
@WebServlet(name = "AcharUsuarioReservaServlet", urlPatterns = {"/acharUsuarioReserva"})
public class AcharUsuarioReservaServlet extends HttpServlet {
    final UsuarioService usuarioService= new UsuarioService();
    final LivroService livroService= new LivroService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String numeroIdentificacao = (String) request.getParameter("numeroIdentificacao");
        Document usuarioQuery = new Document("numeroIdentificacao",numeroIdentificacao);
        Usuario usuario = usuarioService.getBy(usuarioQuery).getFirst();
        if(usuario == null){
            response.sendRedirect("reserva/acharUsuarioReserva.jsp?error=true");
        }
        else{
            List<Livro> livros = livroService.getAllLivros();
            List<Livro> livrosReservados = new ArrayList<>();
            request.setAttribute("livros", livros);
            request.getSession().setAttribute("idReserva", null);
            request.getSession().setAttribute("livrosReservados", livrosReservados);
            request.getSession().setAttribute("usuarioReserva", usuario);
            request.getRequestDispatcher("reserva/selecionarLivrosReserva.jsp").forward(request, response);
        }
    }
}
