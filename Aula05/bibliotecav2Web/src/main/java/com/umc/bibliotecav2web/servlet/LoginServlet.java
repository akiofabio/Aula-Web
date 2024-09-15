package com.umc.bibliotecav2web.servlet;

import com.umc.bibliotecav2web.model.Usuario;
import com.umc.bibliotecav2web.service.AuthService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "login",urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private final AuthService authService = new AuthService();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (authService.authenticate(username, password)) {
            response.sendRedirect("index.jsp");
        } else {
            // Credenciais inválidas, redireciona de volta para a página de login
            response.sendRedirect("login.jsp?error=true");
        }
    }
}
