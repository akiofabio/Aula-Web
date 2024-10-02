/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.umc.bibliotecav2web.servlet.livro;

import com.umc.bibliotecav2web.service.LivroService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;

/**
 *
 * @author Akio
 */
@WebServlet(name = "DeletarLivroServlet", urlPatterns = {"/deletarLivro"})
public class DeletarLivroServlet extends HttpServlet {
    private final LivroService livroService = new LivroService();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id =  request.getParameter("id");
        System.out.println("id: " + id);
        livroService.deleteLivro(id);
        response.sendRedirect("visualizarLivros");
    }

    
}
