package br.com.javaParking.model;

import br.com.javaParking.model.Vaga;

public class VagaVIP extends Vaga {
    
    public VagaVIP(String identificador) {
        super(identificador);
    }

    @Override
    public double calcularPreco(double tempoEstacionado) {
        double precoTotal = super.calcularPreco(tempoEstacionado); // Chama o cálculo da classe base
        return precoTotal * 1.20; // Aplica 20% de aumento
    }
}