/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

package br.com.javaParking.model.tiposVaga;

import br.com.javaParking.model.Vaga;
import br.com.javaParking.model.Vaga;

/**
 *
 * @author viniciusgomesrodrigues
 */
public class Idoso extends Vaga {

    private static double modificadorPreco;
    
    static{
        modificadorPreco = 0.85;
    }
    
    public Idoso(String identificador) {
        super(identificador);
    }
    
    public double calcularPreco(int tempoEstacionado) {
        double precoTotal = super.calcularPreco(tempoEstacionado); // Chama o cálculo da classe base
        return precoTotal * modificadorPreco; // Aplica 15% de desconto
    }
    
}
