/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.umc.bibliotecav2web.servlet.multa;

import com.umc.bibliotecav2web.model.Emprestimo;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Akio
 */
@WebServlet(name = "AdicionarMulatServlet", urlPatterns = {"/adicionarMulta"})
public class AdicionarMultaServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        List<Emprestimo> emprestimos = (List<Emprestimo>)request.getSession().getAttribute("emprestimos");
        List<Emprestimo> emprestimosRetiradosTemp = (List<Emprestimo>)request.getSession().getAttribute("emprestimosRetirados");
        List<Emprestimo> emprestimosRetirados = new ArrayList<>();
        double total = (double) request.getSession().getAttribute("total"); 
        
        for(Emprestimo emprestimo : emprestimosRetiradosTemp){
            if(emprestimo.getId().equals(id)){
                emprestimos.add(emprestimo);
                total+=emprestimo.getMulta().getValor();
            }
            else{
                emprestimosRetirados.add(emprestimo);
            }
        }
        
        request.getSession().setAttribute("total", total);
        request.getSession().setAttribute("emprestimosRetirados", emprestimosRetirados);
        request.getSession().setAttribute("emprestimos", emprestimos);
        request.getRequestDispatcher("multa/pagarMulta.jsp").forward(request, response);
    }
}
