package br.com.javaParking.model.tiposVaga;

import br.com.javaParking.model.Vaga;
import br.com.javaParking.model.Vaga;

public class Vip extends Vaga {
    
    private static double modificadorPreco;
    
    static{
        modificadorPreco = 1.20;
    }
    
    public Vip(String identificador) {
        super(identificador);
    }

    @Override
    public double calcularPreco(int tempoEstacionado) {
        double precoTotal = super.calcularPreco(tempoEstacionado); // Chama o cálculo da classe base
        return precoTotal * modificadorPreco; // Aplica 20% de aumento
    }
}