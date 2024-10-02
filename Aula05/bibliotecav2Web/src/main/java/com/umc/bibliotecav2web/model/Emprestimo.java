package com.umc.bibliotecav2web.model;

import java.util.Date;

public class Emprestimo {
    private String id;
    private Date dataInicio;
    private Date dataDevolucao;
    private Livro livro;
    private Usuario usuario;
    private String statusDevolucao;
    
    // Construtor
    public Emprestimo(String id, Date dataInicio, Date dataDevolucao, Livro livro, Usuario usuario, String statusDevolucao) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataDevolucao = dataDevolucao;
        this.livro = livro;
        this.usuario = usuario;
        this.statusDevolucao = statusDevolucao;
    }

    public Emprestimo(Date dataInicio, Date dataDevolucao, Livro livro, Usuario usuario, String statusDevolucao) {
        this.dataInicio = dataInicio;
        this.dataDevolucao = dataDevolucao;
        this.livro = livro;
        this.usuario = usuario;
        this.statusDevolucao = statusDevolucao;
    }
    
    public Emprestimo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getStatusDevolucao() {
        return statusDevolucao;
    }

    public void setStatusDevolucao(String statusDevolucao) {
        this.statusDevolucao = statusDevolucao;
    }

}
