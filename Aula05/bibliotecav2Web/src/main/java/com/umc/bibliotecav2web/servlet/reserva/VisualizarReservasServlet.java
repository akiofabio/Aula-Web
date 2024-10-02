
package com.umc.bibliotecav2web.servlet.reserva;

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
        List<Reserva> listaReservas = reservaService.getAll();
        request.setAttribute("reservas", listaReservas);
        request.getRequestDispatcher("reserva/visualisarReservas.jsp").forward(request, response);
    }
    
}
