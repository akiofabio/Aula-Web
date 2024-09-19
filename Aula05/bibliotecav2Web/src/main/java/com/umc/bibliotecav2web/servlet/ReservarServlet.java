/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.umc.bibliotecav2web.servlet;

import com.umc.bibliotecav2web.model.Livro;
import com.umc.bibliotecav2web.model.Usuario;
import com.umc.bibliotecav2web.service.LivroService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Akio
 */
@WebServlet(name = "ReservarServlet", urlPatterns = {"/reservar"})
public class ReservarServlet extends HttpServlet {
    final LivroService livroService= new LivroService();
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Livro> livrosReservados = (List<Livro>)request.getSession().getAttribute("livrosReservados");
        Livro livro = new Livro();
        String id = request.getParameter("id");
        livro.setId(id);
        Livro livroReservar = livroService.getLivrosBy(livro).getFirst();
        livrosReservados.add(livroReservar);
        
        
        List<Livro> livros = livroService.getAllLivros();
        request.getSession().setAttribute("livrosReservados", livrosReservados);
        request.setAttribute("livros", livros);
        request.getRequestDispatcher("selecionarLivrosReserva.jsp").forward(request, response);
    }
}
