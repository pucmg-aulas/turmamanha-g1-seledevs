package br.com.javaParking.model.tiposVaga;

import br.com.javaParking.model.Vaga;
import br.com.javaParking.model.Vaga;

public class PessoaComDeficiencia extends Vaga {
    
    private static double modificadorPreco;
    
    static{
        modificadorPreco = 0.87;
    }
    
    public PessoaComDeficiencia(String identificador) {
        super(identificador);
    }

    @Override
    public double calcularPreco(int tempoEstacionado) {
        double precoTotal = super.calcularPreco(tempoEstacionado); // Chama o cálculo da classe base
        return precoTotal * modificadorPreco; // Aplica 13% de desconto
    }
}