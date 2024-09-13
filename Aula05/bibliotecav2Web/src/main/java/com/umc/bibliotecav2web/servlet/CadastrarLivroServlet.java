package com.umc.bibliotecav2web.servlet;

import com.umc.bibliotecav2web.model.Livro;
import com.umc.bibliotecav2web.service.LivroService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cadastrarLivro")
public class CadastrarLivroServlet extends HttpServlet {
    private final LivroService livroService = new LivroService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        int anoPublicacao = Integer.parseInt(request.getParameter("anoPublicacao"));
        int quantidadeDisponivel = Integer.parseInt(request.getParameter("quantidadeDisponivel"));
        Livro livro = new Livro(titulo, autor, anoPublicacao, quantidadeDisponivel);
        livroService.newLivro(livro);
        response.sendRedirect("visualizarLivros");
    }
}
