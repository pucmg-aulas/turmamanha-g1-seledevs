/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

package br.com.javaParking.model;

import br.com.javaParking.model.Vaga;

/**
 *
 * @author viniciusgomesrodrigues
 */
public class VagaIdoso extends Vaga {

    public VagaIdoso(String identificador) {
        super(identificador);
    }
    
    public double calcularPreco(double tempoEstacionado) {
        double precoTotal = super.calcularPreco(tempoEstacionado); // Chama o cálculo da classe base
        return precoTotal * 0.85; // Aplica 15% de desconto
    }
    
}
