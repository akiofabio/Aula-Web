/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.umc.bibliotecav2web.servlet.reserva;

import com.umc.bibliotecav2web.model.Livro;
import com.umc.bibliotecav2web.model.Reserva;
import com.umc.bibliotecav2web.model.Usuario;
import com.umc.bibliotecav2web.service.LivroService;
import com.umc.bibliotecav2web.service.ReservaService;
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
@WebServlet(name = "CriarReservaServlet", urlPatterns = {"/CriarReservaServlet"})
public class CriarReservaServlet extends HttpServlet {
    final ReservaService reservaService = new ReservaService();
    final LivroService livroService = new LivroService();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idUsuario = request.getParameter("idUsuario");
        Usuario usuario= new Usuario();
        usuario.setId(idUsuario);
        
        List<Livro> livros = new ArrayList<>();
        
        for(int i = 1;i>0 ; i++ ){
            String livroId = request.getParameter("livroId" + i);
            if(livroId == null){
                break;
            }
            Livro livro = new Livro();
            livro.setId(livroId);
            livros.add(livro);
        }
        Reserva reserva = new Reserva(usuario,livros ,new Date(), "Aberta");
        if(reservaService.newReserva(reserva)){
            response.sendRedirect("visualizarReservas");
        }
        else{
            List<Livro> todosLivros = livroService.getAllLivros();
            request.setAttribute("livros", todosLivros);
            request.getRequestDispatcher("reserva/selecionarLivrosReserva.jsp?error=true").forward(request, response);
        }
    }  
}
