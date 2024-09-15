
package com.umc.bibliotecav2web.servlet;

import com.umc.bibliotecav2web.model.Reserva;
import com.umc.bibliotecav2web.model.Usuario;
import com.umc.bibliotecav2web.service.ReservaService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;


@WebServlet(name = "VisualizarReservas", urlPatterns = {"/visualizarReservas"})
public class VisualizarReservasServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReservaService reservaService = new ReservaService();
        
        String id = (String) request.getSession().getAttribute("idUsuario");
        Usuario usario = new Usuario();
        usario.setId(id);
        
        Reserva reserva = new Reserva();
        reserva.setUsuario(usario);
        
        List<Reserva> listaReservas = reservaService.getBy(reserva);
        request.setAttribute("revervas", listaReservas);
        request.getRequestDispatcher("visualizarRevervas.jsp").forward(request, response);
    }
    
}
