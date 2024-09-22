/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.aulaweb.r1parcial.servlet;

import com.aulaweb.r1parcial.model.Aluno;
import com.aulaweb.r1parcial.service.AlunoService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Servlet que lida com request do caminho "/deletarAluno" responsavel por deletar os dados de um aluno
@WebServlet(name = "DeletarAlunoServlet", urlPatterns = {"/deletarAluno"})
public class DeletarAlunoServlet extends HttpServlet {
    
    //Metodo que lida com request do tipo post para deletar um aluno
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Instancia o AlunoService para poder deletar
        AlunoService alunoService = new AlunoService();
        //Pega o id do aluno a deletar do parametro da request e passa de String para int
        int id = Integer.parseInt(request.getParameter("id")) ;
        //Deleta o aluno
        alunoService.deletarAluno(id);
        //Adiciona a mensagem bem sucedido
        request.setAttribute("mensagem", "Aluno Deletado com sucesso!!");
        //Redireciona ao Servlet ListarAlunosServlet que mostrara uma lista com todos os alunos salvos
        request.getRequestDispatcher("/listarAlunos").forward(request, response);
    }
}
