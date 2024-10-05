/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.umc.bibliotecav2web.servlet.multa;

import com.umc.bibliotecav2web.model.Emprestimo;
import com.umc.bibliotecav2web.model.Usuario;
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
@WebServlet(name = "RetirarMultaServlet", urlPatterns = {"/retirarMulta"})
public class RetirarMultaServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        List<Emprestimo> emprestimosTemp = (List<Emprestimo>)request.getSession().getAttribute("emprestimos");
        List<Emprestimo> emprestimosRetirados = (List<Emprestimo>)request.getSession().getAttribute("emprestimosRetirados");
        List<Emprestimo> emprestimos = new ArrayList<>();
        double total = 0.0;
        
        for(Emprestimo emprestimo : emprestimosTemp){
            if(emprestimo.getId().equals(id)){
                emprestimosRetirados.add(emprestimo);
            }
            else{
                emprestimos.add(emprestimo);
                total+=emprestimo.getMulta().getValor();
            }
        }
        request.getSession().setAttribute("total", total);
        request.getSession().setAttribute("emprestimosRetirados", emprestimosRetirados);
        request.getSession().setAttribute("emprestimos", emprestimos);
        request.getRequestDispatcher("multa/pagarMulta.jsp").forward(request, response);
    }
    
    
}
