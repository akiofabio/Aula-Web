/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umc.bibliotecav2web.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Akio
 */
public class CalculoMulta {
    static public double calcularMulta(Date dataDevolucao, Date dataPrevista){
        double total = 0.0;
        long dias = calcularDiasAtrasos(dataDevolucao,dataPrevista);
        if(dias>0){
            total = 5 + 1.5*dias;
        }
        return total;
    }
    
    static public long calcularDiasAtrasos(Date dataDevolucao, Date dataPrevista){
        long tempo = dataDevolucao.getTime() - dataPrevista.getTime();
        long dias = TimeUnit.DAYS.convert(tempo, TimeUnit.MILLISECONDS);
        return dias;
    }
}
