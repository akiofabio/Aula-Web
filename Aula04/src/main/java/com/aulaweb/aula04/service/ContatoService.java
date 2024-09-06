/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aulaweb.aula04.service;

import com.aulaweb.aula04.model.Contato;
import com.aulaweb.aula04.repository.ContatoRepository;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Akio
 */
public class ContatoService {
    final ContatoRepository repository = new ContatoRepository();

    public Boolean savar(Contato contato)
            throws ServletException, IOException, SQLException {
        return repository.salvar(contato);
    }
    
    public Boolean deletar(int id)
            throws ServletException, IOException, SQLException {
        return repository.deletar(id);
    }
    
    public Boolean alterar(Contato contato)
            throws ServletException, IOException, SQLException {
        return repository.alterar(contato);
    }
    
    public Contato selecionarPorId(int id)
            throws ServletException, IOException, SQLException {
        return repository.selecionarPorId(id);
    }
    
    public List<Contato> selecionarTodos()
            throws ServletException, IOException, SQLException {
        return repository.selecionarTodos();
    }
}
