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

    private final static double MODIFICADORPRECO = 0.85;
    
    public Idoso(String parque,String identificador, boolean ocupada) {
        super(parque,identificador,ocupada);
    }
    
   @Override
    public double calcularPreco(long minutosTotais) {
        double precoTotal = Math.floor(minutosTotais / Parque.INTERVALODECOBRANCAEMMINUTOS) * Parque.VALORPORTEMPO;
        return super.aplicarLimite(precoTotal) * MODIFICADORPRECO;
    }
    
}
