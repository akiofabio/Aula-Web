
package com.umc.bibliotecav2web.model;

import java.util.ArrayList;

public class Usuario {
    private String id;
    private String nome;
    private String numeroIdentificacao;

    public Usuario(String id, String nome, String numeroIdentificacao) {
        this.id = id;
        this.nome = nome;
        this.numeroIdentificacao = numeroIdentificacao;
    }

    public Usuario(String nome, String numeroIdentificacao) {
        this.nome = nome;
        this.numeroIdentificacao = numeroIdentificacao;
    }

    public Usuario() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroIdentificacao() {
        return numeroIdentificacao;
    }

    public void setNumeroIdentificacao(String numeroIdentificacao) {
        this.numeroIdentificacao = numeroIdentificacao;
    }

}
