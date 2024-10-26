
package com.umc.bibliotecav2web.servlet.livro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.umc.bibliotecav2web.model.Livro;
import com.umc.bibliotecav2web.service.LivroService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Akio
 */
@WebServlet(name = "LivroSerlet", urlPatterns = {"/livro"})
public class LivroServlet extends HttpServlet {
    
    private final LivroService livroService = new LivroService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String parametro = request.getParameter("parametro");
        String valorParametro = request.getParameter("valorParametro");
        String caminho = request.getParameter("caminho");
        Document query = new Document();
        if(parametro != null && !parametro.isEmpty()){
           if(parametro.equals("id")){
               query = new Document("_id",new ObjectId(valorParametro));
           }
           else{
               query = new Document(parametro,valorParametro);
           }
           
        }
        List<Livro> listaDeLivros = livroService.getBy(query);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(listaDeLivros);
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        request.setAttribute("livros",  json);
        if(caminho==null || caminho.isEmpty()){
            request.getRequestDispatcher("vizualizarLivrosRestful.jsp").forward(request, response);
        }
        else{
            request.getRequestDispatcher(caminho).forward(request, response);
        }
        
    }

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
            response.setStatus(HttpServletResponse.SC_CREATED);
            request.setAttribute("mensagem", "Livro cadastrado com sucesso!!");
            doGet( request,  response);
        }
        else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("titulo", titulo);
            request.setAttribute("autor", autor);
            request.setAttribute("anoPublicacao", anoPublicacaoString);
            request.setAttribute("quantidadeDisponivel", quantidadeDisponivelString);
            request.getRequestDispatcher("/livro/cadastrarLivro.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id =  request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        int anoPublicacao = Integer.parseInt(request.getParameter("anoPublicacao"));
        int quantidadeDisponivel = Integer.parseInt(request.getParameter("quantidadeDisponivel"));
        Livro livro = new Livro(id,titulo, autor, anoPublicacao, quantidadeDisponivel);
        livroService.updateLivro(livro);
        
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("vizualizarLivrosRestful");
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id =  request.getParameter("id");
        livroService.deleteLivro(id);
        
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("vizualizarLivrosRestful");
        
    }
}
