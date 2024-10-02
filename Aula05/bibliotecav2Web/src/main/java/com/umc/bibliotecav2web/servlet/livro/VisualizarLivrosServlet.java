package com.umc.bibliotecav2web.servlet.livro;

import com.umc.bibliotecav2web.model.Livro;
import com.umc.bibliotecav2web.service.LivroService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/visualizarLivros")
public class VisualizarLivrosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LivroService livroService = new LivroService();
        List<Livro> listaDeLivros = livroService.getAllLivros();
        request.setAttribute("livros", listaDeLivros);
        request.getRequestDispatcher("livro/visualizarLivros.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
