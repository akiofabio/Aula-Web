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


public class Mask {
    public static String DataMask(Date data){
        LocalDate localDate = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
        String texto = localDate.format(formatters);
        return texto;
    }
    
    public static String DinheiroMask(double valor){
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.DOWN);
        String texto = df.format(valor);
        return texto;
    }
}
