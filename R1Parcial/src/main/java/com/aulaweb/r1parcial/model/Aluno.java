/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aulaweb.r1parcial.model;

/**
 *
 * @author Akio
 */
public class Aluno {
    
    //Atributos da Classe
    private int id;
    private String nome;
    private String email;
    private String curso;
    private int anoDeIngresso;
    
    //Construtores
    //Construtor com todas os atributos (geralmente usado para retonar os gets)
    public Aluno(int id, String nome, String email, String curso, int anoDeIngresso) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.curso = curso;
        this.anoDeIngresso = anoDeIngresso;
    }
    
    //Construtor sem id (geralmente usado para salvar um novo aluno pois o id é gerado pelo DB)
    public Aluno(String nome, String email, String curso, int anoDeIngresso) {
        this.nome = nome;
        this.email = email;
        this.curso = curso;
        this.anoDeIngresso = anoDeIngresso;
    }
    
    //Construtor sem atributos (geralmente usado para instanciar o Objeto aluno qunado não se tem todos os atributos)
    public Aluno() {
    }
    
    //Gets e Sets (Gets servem para pegar os atributos dos Objetos e os Sets para atribuir-los)
    //Retorna o atributo id
    public int getId() {
        return id;
    }
    
    //Atribui o atributo id
    public void setId(int id) {
        this.id = id;
    }
    
    //Retorna o atributo nome
    public String getNome() {
        return nome;
    }
    
     //Atribui o atributo nome
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    //Retorna o atributo email
    public String getEmail() {
        return email;
    }
    
    //Atribui o atributo email
    public void setEmail(String email) {
        this.email = email;
    }
    
    //Retorna o atributo curso
    public String getCurso() {
        return curso;
    }
    
    //Atribui o atributo curso
    public void setCurso(String curso) {
        this.curso = curso;
    }
    
    //Retorna o atributo anoDeIngresso
    public int getAnoDeIngresso() {
        return anoDeIngresso;
    }
    
    //Atribui o atributo anoDeIngresso
    public void setAnoDeIngresso(int anoDeIngresso) {
        this.anoDeIngresso = anoDeIngresso;
    }
}
