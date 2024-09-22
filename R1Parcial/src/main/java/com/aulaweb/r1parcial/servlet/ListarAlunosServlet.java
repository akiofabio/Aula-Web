/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.aulaweb.r1parcial.servlet;

import com.aulaweb.r1parcial.model.Aluno;
import com.aulaweb.r1parcial.service.AlunoService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

//Servlet que lida com request do caminho "/listarAlunos" responsavel por mostra uma lista com todos os alunos
@WebServlet(name = "ListarAlunosServlet", urlPatterns = {"/listarAlunos"})
public class ListarAlunosServlet extends HttpServlet {
    
    //Metodo que lida com request do tipo get para mostrar todos os alunos
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Pega a mensagem de sucesso do request 
        String mensagem = (String) request.getAttribute("mensagem");
        //Adiciona a mensagem ao request
        request.setAttribute("mensagem", mensagem);
        
        //Instancia o AlunoServece para poder consultar os alunos
        AlunoService alunoService = new AlunoService();
        //Consulta todos os alunos e atribui a listaAlunos
        ArrayList<Aluno> listaAlunos = alunoService.listarAlunos();
        //Adiciona a lista ao request
        request.setAttribute("alunos", listaAlunos);
        //Envia o request e a response para a página listarAlunos.jsp
        request.getRequestDispatcher("listarAlunos.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost (HttpServletRequest request,HttpServletResponse response)  
            throws ServletException, IOException{
        doGet(request,response);
    }
}
