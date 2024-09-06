package com.umc.bibliotecav2web.servlet;

import com.umc.bibliotecav2web.service.AuthService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final AuthService authService = new AuthService();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        response.sendRedirect("login.jsp?error=true");
        /*
        if (authService.authenticate(username, password)) {
            // Credenciais válidas, redireciona para a página principal
            response.sendRedirect("index.jsp");
        } else {
            // Credenciais inválidas, redireciona de volta para a página de login
            response.sendRedirect("login.jsp?error=true");
        }
*/
    }
}
