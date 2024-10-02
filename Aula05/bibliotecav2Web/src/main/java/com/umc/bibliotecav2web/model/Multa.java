/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umc.bibliotecav2web.model;

import java.util.Date;

/**
 *
 * @author Akio
 */
public class Multa {
    private String id;
    private String status;
    private double valor;
    private Date data;
    private Emprestimo emprestimo;

    public Multa(String id, String status, double valor, Date data, Emprestimo emprestimo) {
        this.id = id;
        this.status = status;
        this.valor = valor;
        this.data = data;
        this.emprestimo = emprestimo;
    }

    public Multa(String status, double valor, Date data, Emprestimo emprestimo) {
        this.status = status;
        this.valor = valor;
        this.data = data;
        this.emprestimo = emprestimo;
    }
    
    public Multa() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }
    
}
