package com.umc.bibliotecav2web.servlet;

import com.umc.bibliotecav2web.model.Livro;
import com.umc.bibliotecav2web.service.LivroService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/visualizarLivros")
public class VisualizarLivrosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LivroService livroService = new LivroService();
        List<Livro> listaDeLivros = livroService.getAllLivros();
        request.setAttribute("livros", listaDeLivros);
        request.getRequestDispatcher("visualizarLivros.jsp").forward(request, response);
    }
}
