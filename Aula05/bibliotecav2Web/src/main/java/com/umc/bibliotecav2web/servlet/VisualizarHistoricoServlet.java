/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.umc.bibliotecav2web.servlet;

import com.umc.bibliotecav2web.model.Emprestimo;
import com.umc.bibliotecav2web.model.Reserva;
import com.umc.bibliotecav2web.model.Usuario;
import com.umc.bibliotecav2web.service.EmprestimoService;
import com.umc.bibliotecav2web.service.ReservaService;
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
import org.bson.types.ObjectId;

/**
 *
 * @author Akio
 */
@WebServlet(name = "VisualizarHistoricoServlet", urlPatterns = {"/visualizarHistorico"})
public class VisualizarHistoricoServlet extends HttpServlet {
    EmprestimoService emprestimoService = new EmprestimoService();
    ReservaService reservaService = new ReservaService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numeroIdentificacao = request.getParameter("numeroIdentificacao");
        Document query = new Document("numeroIdentificacao",numeroIdentificacao);
        UsuarioService usuarioService = new UsuarioService();       
        List<Usuario> usuarios = usuarioService.getBy(query);
        if(usuarios.isEmpty()){
            response.sendRedirect("acharUsuarioDevolucao.jsp?error=true");
        }
        else{
            Usuario usuario = usuarios.getFirst();
            Document userQuery = new Document("usuario",new ObjectId(usuario.getId()));
            List<Emprestimo> emprestimos = emprestimoService.getBy(userQuery);
            List<Reserva> reserva = reservaService.getBy(userQuery);
            
            request.setAttribute("usuario", usuario);
            request.setAttribute("emprestimos", emprestimos);
            request.setAttribute("reserva", reserva);
            
            request.getRequestDispatcher("historico/historico.jsp").forward(request, response);
        }
    }
}
