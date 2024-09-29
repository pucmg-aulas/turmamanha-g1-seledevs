package br.com.javaParking.model;

import br.com.javaParking.model.Vaga;

public class VagaPCD extends Vaga {
    
    public VagaPCD(String identificador) {
        super(identificador);
    }

    @Override
    public double calcularPreco(double tempoEstacionado) {
        double precoTotal = super.calcularPreco(tempoEstacionado); // Chama o cálculo da classe base
        return precoTotal * 0.87; // Aplica 13% de desconto
    }
}