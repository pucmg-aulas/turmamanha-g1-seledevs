/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

package br.com.javaParking.model.tiposVaga;

import br.com.javaParking.model.Parque;
import br.com.javaParking.model.Vaga;
import br.com.javaParking.model.Vaga;

/**
 *
 * @author viniciusgomesrodrigues
 */
public class Idoso extends Vaga {

    private final static double MODIFICADORPRECO;
    
    static{
        MODIFICADORPRECO = 0.85;
    }
    
    public Idoso(String parque,String identificador) {
        super(parque,identificador);
    }
    
   @Override
    public double calcularPreco(int dias,int minutos) {        
        double precoTotal = Math.floor(minutos / Parque.INTERVALODECOBRANCAEMMINUTOS) * Parque.VALORPORTEMPO;         
        return (super.aplicarLimite(precoTotal) + (Parque.VALORDEDIARIAMAXIMA * dias)) * MODIFICADORPRECO; 
    }
    
}
