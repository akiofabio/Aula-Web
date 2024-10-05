/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umc.bibliotecav2web.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//Classe para aplicar mascaras nos dados de saida
public class Mask {
    //Mascara que converte Date em String no formato dd/MM/uuuu
    public static String DataMask(Date data){
        LocalDate localDate = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String texto = localDate.format(formatters);
        return texto;
    }
    
    //Mascara que converte double em String com duas casas decimais
    public static String DinheiroMask(double valor){
        //Escolhe o formato do decimal para duas casas apos a virgula
        DecimalFormat df = new DecimalFormat("0.00");
        //Configura para o aredondamento para baixo
        df.setRoundingMode(RoundingMode.DOWN);
        String texto = df.format(valor);
        return texto;
    }
}
