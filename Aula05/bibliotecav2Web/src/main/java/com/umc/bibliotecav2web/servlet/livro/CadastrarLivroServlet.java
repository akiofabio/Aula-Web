package com.umc.bibliotecav2web.servlet.livro;

import com.umc.bibliotecav2web.model.Livro;
import com.umc.bibliotecav2web.service.LivroService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cadastrarLivro")
public class CadastrarLivroServlet extends HttpServlet {
    private final LivroService livroService = new LivroService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String  anoPublicacaoString = request.getParameter("anoPublicacao");
        String quantidadeDisponivelString = request.getParameter("quantidadeDisponivel");
        boolean erro = false;
        
        int anoPublicacao = 0;
        if(anoPublicacaoString!=null && !anoPublicacaoString.isBlank() && !anoPublicacaoString.isBlank()){
            anoPublicacao = Integer.parseInt(anoPublicacaoString);
        }
        else{
            request.setAttribute("anoPublicacaoErroMensagem","Ano de Publicacao inválido");
            erro = true;
        }
        
        int quantidadeDisponivel = 0;
        if(quantidadeDisponivelString!=null && !quantidadeDisponivelString.isBlank() && !quantidadeDisponivelString.isBlank()){
            quantidadeDisponivel = Integer.parseInt(quantidadeDisponivelString);
        }
        else{
            request.setAttribute("anoPublicacaoErroMensagem","Ano de Publicacao inválido");
            erro = true;
        }
        
        if(!erro){
            Livro livro = new Livro(titulo, autor, anoPublicacao, quantidadeDisponivel);
            livroService.newLivro(livro);
            request.setAttribute("mensagem", "Livro cadastrado com sucesso!!");
            request.getRequestDispatcher("/visualizarLivros").forward(request, response);
        }
        else{
            request.setAttribute("titulo", titulo);
            request.setAttribute("autor", autor);
            request.setAttribute("anoPublicacao", anoPublicacaoString);
            request.setAttribute("quantidadeDisponivel", quantidadeDisponivelString);
            request.getRequestDispatcher("/livro/cadastrarLivro.jsp").forward(request, response);
        }
    }
}
