/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.aulaweb.aula04.servelet;

import com.aulaweb.aula04.model.Contato;
import com.aulaweb.aula04.model.Teste;
import com.aulaweb.aula04.repository.FormRepository;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Akio
 */
@WebServlet(name = "FormServlet", urlPatterns = {"/FormServlet"})
public class FormServlet extends HttpServlet {
    
    final FormRepository repository;
    
    public FormServlet() {
        this.repository = new FormRepository();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        boolean create = false; 
        
        System.out.println("nome: " + nome);
        System.out.println("email: " + email);
                
        HttpSession session = request.getSession(create);
        session.setAttribute ("nome", nome);
        
        try {
            create = repository.salvar(nome,email);
        } catch (SQLException ex) {
            Logger.getLogger(FormServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(create){
            response.sendRedirect("response.jsp?nome="+nome);
        }
        else{
            response.sendRedirect("response.jsp?nome="+nome);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = (String) request.getSession().getAttribute("nome");
        List<Teste> lista = repository.selecionarTodos();
        request.setAttribute("testes", lista);
        RequestDispatcher dispatcher = request.getRequestDispatcher("response.jsp?nome="+nome);
        dispatcher.forward(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
