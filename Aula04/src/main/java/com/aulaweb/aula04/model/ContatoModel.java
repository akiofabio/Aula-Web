/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aulaweb.aula04.model;
    
/**
 *
 * @author Akio
 */
public class ContatoModel {
    private int id;
    private String nome;
    private String email;

    public ContatoModel(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public ContatoModel(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
