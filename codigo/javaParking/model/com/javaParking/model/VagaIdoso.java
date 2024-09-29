package com.javaParking.model;

public class VagaIdoso extends Vaga {
    
    public VagaIdoso(String identificador) {
        super(identificador);
    }

   
    public double calcularPreco(double tempoEstacionado) {
        double precoTotal = super.calcularPreco(tempoEstacionado); // Chama o cálculo da classe base
        return precoTotal * 0.85; // Aplica 15% de desconto
    }
}