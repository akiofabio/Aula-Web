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
public class Pagamento {
    private String id;
    private double valor;
    private Date data;
    private String meio;
    private Multa multa;

    public Pagamento(String id, double valor, Date data, String meio, Multa multa) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.meio = meio;
        this.multa = multa;
    }

    public Pagamento(double valor, Date data, String meio, Multa multa) {
        this.valor = valor;
        this.data = data;
        this.meio = meio;
        this.multa = multa;
    }

    public Pagamento() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMeio() {
        return meio;
    }

    public void setMeio(String meio) {
        this.meio = meio;
    }

    public Multa getMulta() {
        return multa;
    }

    public void setMulta(Multa multa) {
        this.multa = multa;
    }
    
}
