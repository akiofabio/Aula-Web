/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umc.bibliotecav2web.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Akio
 */
public class Reserva {
    private String id;
    private Usuario usuario;
    private List<Livro> livros;
    private Date dataReserva;
    private String status;

    public Reserva() {
    }

    public Reserva(Usuario usuario, List<Livro> livros, Date dataReserva, String status) {
        this.usuario = usuario;
        this.livros = livros;
        this.dataReserva = dataReserva;
        this.status = status;
    }

    public Reserva(String id, Usuario usuario, List<Livro> livros, Date dataReserva, String status) {
        this.id = id;
        this.usuario = usuario;
        this.livros = livros;
        this.dataReserva = dataReserva;
        this.status = status;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   
}