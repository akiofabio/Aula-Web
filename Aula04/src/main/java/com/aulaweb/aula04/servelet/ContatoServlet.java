/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.aulaweb.aula04.servelet;

import com.aulaweb.aula04.model.Contato;
import com.aulaweb.aula04.service.ContatoService;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Akio
 */
@WebServlet(name = "ContatoServlet", urlPatterns = {"/ContatoServlet/*"})
public class ContatoServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
        
    private static final String URL = "/Aula04/ContatoServlet";
    private static final String URLbase = "/Aula04";
    final ContatoService service = new ContatoService();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getRequestURI().replace(URL,"");
        System.out.println(action);
        try {
            switch (action) {
                case "/contatos":
                    mostrarContatos(request, response);
                    break;
                case "/novo":
                    mostrarNovoContatoForm(request, response);
                    break;
                case "/salvar":
                    salvar(request, response);
                    break;
                case "/deletar":
                    deletar(request, response);
                    break;
                case "/alterar":
                    mostrarAlterarContatoForm(request, response);
                    break;
                case "/salvarAlteracao":
                    salvarAlteracao(request, response);
                    break;
                default:
                    //mostrarContatos(request, response);
                    break;
                }
        } catch (SQLException ex) {
                throw new ServletException(ex);
        }
    }
    
    private void mostrarContatos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Contato> listContatos = service.selecionarTodos();
        request.setAttribute("contatos", listContatos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/contatos.jsp");
        dispatcher.forward(request, response);
    }
    
    private void mostrarNovoContatoForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/contato_form.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarAlterarContatoForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("id: " + id);
        Contato contato = service.selecionarPorId(id);
        System.out.println(contato);
        System.out.println(contato.getNome());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/contato_form.jsp");
        request.setAttribute("contato", contato);
        dispatcher.forward(request, response);
    }

    private void salvar(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        Contato novoContato = new Contato(nome, email);
        System.out.println(novoContato.getId());
        System.out.println(novoContato.getNome());
        System.out.println(novoContato.getEmail());
        service.savar(novoContato);
        //response.sendRedirect(URLbase+"/index.jsp");
        mostrarContatos(request, response);
    }

    private void salvarAlteracao(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        
        Contato contato = new Contato(id, nome, email);
        System.out.println(contato.getId());
        System.out.println(contato.getNome());
        System.out.println(contato.getEmail());
        service.alterar(contato);
        //response.sendRedirect(URLbase+"/index.jsp");
        mostrarContatos(request, response);
    }

    private void deletar(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        service.deletar(id);
        mostrarContatos(request, response);
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
