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

//Servlet que lida com request do caminho "/editarAluno" responsavel por pagar os dados do aluno a ser editado
@WebServlet(name = "EditarAlunoServlet", urlPatterns = {"/editarAluno"})
public class EditarAlunoServlet extends HttpServlet {
    
    //Metodo que lida com request do tipo get pagar os dados do aluno a ser editado
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Instancia o AlunoService para pegar os dados do aluno a ser editado
        AlunoService alunoService = new AlunoService();
        //Pega o id do aluno a editar do parametro da request e passa de String para int
        int id = Integer.parseInt(request.getParameter("id")) ;
        //Pegar os dados do aluno a ser editado
        Aluno aluno = alunoService.buscarAluno(id);
        //Adiciona o objeto aluno ao request
        request.setAttribute("aluno", aluno);
        //Redireciona a pagina editarAluno que mostrara os dados do aluno a ser editado
        request.getRequestDispatcher("/editarAluno.jsp").forward(request, response);
    }
}
