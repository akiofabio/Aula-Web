/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.aulaweb.aula04.servelet;

import com.aulaweb.aula04.model.ContatoModel;
import com.aulaweb.aula04.repository.ContatoRepository;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
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
        
    final ContatoRepository repository;
    
    public ContatoServlet() {
        this.repository = new ContatoRepository();
           
    }
    
    public ContatoServlet(ContatoRepository repository) {
            super();
            this.repository = repository;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getServletPath();
        System.out.println("Teste:");
        System.out.println(action);
        try {
            switch (action) {
                case "/new":
                        showNewForm(request, response);
                        break;
                case "/insert":
                        insertUser(request, response);
                        break;
                case "/delete":
                        deleteUser(request, response);
                        break;
                case "/edit":
                        showEditForm(request, response);
                        break;
                case "/update":
                        updateUser(request, response);
                        break;
                default:
                    //listUser(request, response);
                    insertUser(request, response);
                    break;
                }
        } catch (SQLException ex) {
                throw new ServletException(ex);
        }
    }
    private void listUser(HttpServletRequest request, HttpServletResponse response)
                throws SQLException, IOException, ServletException {
        List<ContatoModel> listContatos = repository.selectAll();
        System.out.println(listContatos);
        request.setAttribute("listContatos", listContatos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("contato_form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ContatoModel existingUser = repository.select(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("contato_form.jsp");
		request.setAttribute("contato", existingUser);
		dispatcher.forward(request, response);
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
                throws SQLException, IOException {
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            ContatoModel novoContato = new ContatoModel(nome, email);
            repository.insert(novoContato);
            response.sendRedirect("/index.jsp");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");

		ContatoModel contato = new ContatoModel(id, nome, email);
		repository.update(contato);
		response.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		repository.delete(id);
		response.sendRedirect("list");

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
