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
import java.util.ArrayList;


//Servlet que lida com request do caminho "/atualizarAluno" responsavel por atualizar os dados de um aluno
@WebServlet(name = "AtualizarAlunoServlet", urlPatterns = {"/atualizarAluno"})
public class AtualizarAlunoServlet extends HttpServlet {
    
    //Metodo que lida com request do tipo post para atualizar um aluno
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Variavel para saber se houver erro nos parametros do resquest
        boolean erro = false;
        
        //Atribui os parametros da request as variveis para instaciar o objeto Aluno
        int id = Integer.parseInt(request.getParameter("id")) ;
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String curso = request.getParameter("curso");
        String anoDeIngressoString = request.getParameter("anoDeIngresso");
        
        //Valida os parametros
        //Valida se a String nome é null(caso o parametro não for encontrado) ou se ela é uma String vazia
        if(nome==null || nome.isEmpty() || nome.isBlank() ){
            //Especifica a mensagem de erro
            request.setAttribute("nomeErroMensagem","Nome inválido");
            erro = true;
        }
        
        //Valida se a String email é null(caso o parametro não for encontrado) ou se ela é uma String vazia
        if(email==null || email.isEmpty() || email.isBlank() ){
            //Especifica a mensagem de erro
            request.setAttribute("emailErroMensagem","Email inválido");
            erro = true;
        }
        //Caso o email não for null e nem vazio, verifica se é um email valido( contem o @)
        else if(!email.contains("@")){
            //Especifica a mensagem de erro
            request.setAttribute("emailErroMensagem","Email inválido");
            erro = true;
        }
        
        //Valida se a String curso é null(caso o parametro não for encontrado) ou se ela é uma String vazia
        if(curso==null || curso.isEmpty()  || curso.isBlank() ){
            //Especifica a mensagem de erro
            request.setAttribute("cursoErroMensagem","Curso inválido");
            erro = true;
        }
        
        //Instancia o anoDeIngresso para ser atribido caso ele seja valido
        int anoDeIngresso = 0;
        //Valida se a String anoDeIngressoString é null(caso o parametro não for encontrado) ou se ela é uma String vazia
        if(anoDeIngressoString==null || anoDeIngressoString.isEmpty() || anoDeIngressoString.isBlank()){
            //Especifica a mensagem de erro
            request.setAttribute("anoDeIngressoErroMensagem","Ano de Ingresso inválido");
            erro = true;
        }
        else{
            //Artibui a somenteNumerico só os numeros contidos em anoDeIngressoString
            String somenteNumerico = anoDeIngressoString.replaceAll("[^\\d.]", "");
            //Compara o tamanho das duas Strings, se forem igual é porque anoDeIngressoString é numerico
            if(somenteNumerico.length() == anoDeIngressoString.length()){
                //Passa de String para int
                anoDeIngresso = Integer.parseInt(anoDeIngressoString);
            }
            //Valida se o anoDeIngresso tem 4 digitos
            if(anoDeIngresso/1000<1 || anoDeIngresso/1000>10){
                //Adiciona os valores dos atributos no request caso ocorra um erro, para não precisar prencher novamente
                request.setAttribute("anoDeIngressoErroMensagem","Ano de Ingresso inválido");
                erro = true;
            }
        }
        
        
        
        //Verifica se não tem erros
        if(!erro){
            //Instancia o objeto Aluno com todos os atributos
            Aluno aluno = new Aluno(id,nome, email, curso, anoDeIngresso);
            //Instancia o AlunoServece para poder salar as alterações
            AlunoService alunoService = new AlunoService();
            //Atualiza o objeto aluno no BD
            alunoService.atualizarAluno(aluno);
            //Adiciona a mensagem bem sucedido
            request.setAttribute("mensagem", "Aluno Atualizado com sucesso!!");
            //Redireciona ao Servlet ListarAlunosServlet que mostrara uma lista com todos os alunos salvos
            request.getRequestDispatcher("/listarAlunos").forward(request, response);
        }
        //Caso houver erros
        else{
            request.setAttribute("id",id);
            request.setAttribute("nome",nome);
            request.setAttribute("email", email);
            request.setAttribute("curso", curso);
            request.setAttribute("anoDeIngresso", anoDeIngressoString);
            //Envia o request e a response para a página adicionarAluno.jsp
            request.getRequestDispatcher("/editarAluno.jsp").forward(request, response);
        }
    }
}
