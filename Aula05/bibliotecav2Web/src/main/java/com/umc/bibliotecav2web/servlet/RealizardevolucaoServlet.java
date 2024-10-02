/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.umc.bibliotecav2web.servlet;

import com.umc.bibliotecav2web.model.Emprestimo;
import com.umc.bibliotecav2web.model.Livro;
import com.umc.bibliotecav2web.model.Multa;
import com.umc.bibliotecav2web.model.Usuario;
import com.umc.bibliotecav2web.service.EmprestimoService;
import com.umc.bibliotecav2web.service.LivroService;
import com.umc.bibliotecav2web.service.MultaService;
import com.umc.bibliotecav2web.service.UsuarioService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Akio
 */
@WebServlet(name = "RealizardevolucaoServlet", urlPatterns = {"/realizarDevolucao"})
public class RealizardevolucaoServlet extends HttpServlet {
    EmprestimoService emprestimoService = new EmprestimoService();
    LivroService livroService = new LivroService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numeroIdentificacao = request.getParameter("numeroIdentificacao");
        Document query = new Document("numeroIdentificacao",numeroIdentificacao);
        UsuarioService usuarioService = new UsuarioService();       
        List<Usuario> usuarios = usuarioService.getBy(query);
        if(usuarios.isEmpty()){
            response.sendRedirect("acharUsuarioDevolucao.jsp?error=true");
        }
        else{
            Usuario usuario = usuarios.getFirst();
            Document emprestimoQuery = new Document("usuario",new ObjectId(usuario.getId()))
                    .append("statusDevolucao", "Aberto");
            List<Emprestimo> emprestimos = emprestimoService.getBy(emprestimoQuery);
            request.setAttribute("usuario", usuario);
            request.setAttribute("emprestimos", emprestimos);
            request.getRequestDispatcher("devolucao/realizarDevolucao.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Document emprestimoQuery = new Document("_id",new ObjectId(id));
        Emprestimo emprestimo = emprestimoService.getBy(emprestimoQuery).getFirst();
        
        Document livroQuery = new Document("_id",new ObjectId(emprestimo.getLivro().getId()));
        Livro livro = livroService.getBy(livroQuery).getFirst();
        livro.setNumeroCopiasDisponiveis(livro.getNumeroCopiasDisponiveis()+1);
        livroService.updateLivro(livro);
        
        long tempo = new Date().getTime() - emprestimo.getDataDevolucao().getTime();
        long dias = TimeUnit.DAYS.convert(tempo, TimeUnit.MILLISECONDS);
        
        if(dias>0){
            double valor = 5 + 1.5*dias;
            Multa multa = new Multa("Gerada", valor, new Date(), emprestimo);
            MultaService multaService = new MultaService();
            multaService.newMulta(multa);
            
            Document multaQuery = new Document("emprestimo",new ObjectId(emprestimo.getId()));
            System.out.println(multaService.getBy(multaQuery));
            multa = multaService.getBy(multaQuery).getFirst();
            request.setAttribute("multa", multa);
            emprestimo.setStatusDevolucao("Delvovido com Atraso");
            emprestimoService.update(emprestimo);
            request.getRequestDispatcher("multa/pagarConta.jsp").forward(request, response);
        }
        else{
            emprestimo.setStatusDevolucao("Delvovido");
            emprestimoService.update(emprestimo);
            
            emprestimoQuery = new Document("usuario",new ObjectId(emprestimo.getUsuario().getId()))
                    .append("statusDevolucao", "Aberto");
            List<Emprestimo> emprestimos = emprestimoService.getBy(emprestimoQuery);
            request.setAttribute("mensagem", "Devolução concluida com sucesso");
            request.setAttribute("usuario", emprestimo.getUsuario());
            request.setAttribute("emprestimos", emprestimos);
            request.getRequestDispatcher("devolucao/realizarDevolucao.jsp").forward(request, response);
        }
    }

    
}
