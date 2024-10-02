/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.umc.bibliotecav2web.servlet.livro;

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
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Akio
 */
@WebServlet(name = "AlterarLivroServlet", urlPatterns = {"/alterarLivro"})
public class AlterarLivroServlet extends HttpServlet {
    private final LivroService livroService = new LivroService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id =  request.getParameter("id");
        Document livroQuery = new Document("_id",new ObjectId(id));
        Livro livro = livroService.getBy(livroQuery).getFirst();
        request.setAttribute("livro", livro);
        request.getRequestDispatcher("livro/alterarLivro.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id =  request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        int anoPublicacao = Integer.parseInt(request.getParameter("anoPublicacao"));
        int quantidadeDisponivel = Integer.parseInt(request.getParameter("quantidadeDisponivel"));
        Livro livro = new Livro(id,titulo, autor, anoPublicacao, quantidadeDisponivel);
        livroService.updateLivro(livro);
        response.sendRedirect("visualizarLivros");
    }
}
