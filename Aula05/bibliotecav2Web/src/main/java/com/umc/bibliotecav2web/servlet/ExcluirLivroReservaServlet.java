/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.umc.bibliotecav2web.servlet;

import com.umc.bibliotecav2web.model.Livro;
import com.umc.bibliotecav2web.service.LivroService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Akio
 */
@WebServlet(name = "ExcluirLivroReserva", urlPatterns = {"/excluirLivroReserva"})
public class ExcluirLivroReservaServlet extends HttpServlet {
    final LivroService livroService= new LivroService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Livro> livrosReservados = (List<Livro>)request.getSession().getAttribute("livrosReservados");
  
        String index = request.getParameter("index");
        System.out.println("index: " + index);
        livrosReservados.remove(Integer.parseInt(index));
        List<Livro> livros = livroService.getAllLivros();
        request.getSession().setAttribute("livrosReservados", livrosReservados);
        request.setAttribute("livros", livros);
        request.getRequestDispatcher("selecionarLivrosReserva.jsp").forward(request, response);
    }
}
