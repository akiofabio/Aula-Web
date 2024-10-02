/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.umc.bibliotecav2web.servlet.reserva;

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
import org.bson.Document;
import org.bson.types.ObjectId;

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
        String id = request.getParameter("id");
        Document query = new Document("_id", new ObjectId(id));
        Livro livroReservar = livroService.getBy(query).getFirst();
        livrosReservados.add(livroReservar);
        
        List<Livro> livros = livroService.getAllLivros();
        request.getSession().setAttribute("livrosReservados", livrosReservados);
        request.setAttribute("livros", livros);
        request.getRequestDispatcher("reserva/selecionarLivrosReserva.jsp").forward(request, response);
    }
}
