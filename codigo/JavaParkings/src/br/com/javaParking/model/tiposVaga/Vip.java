package br.com.javaParking.model.tiposVaga;

import br.com.javaParking.model.Parque;
import br.com.javaParking.model.Vaga;
import br.com.javaParking.model.Vaga;

public class Vip extends Vaga {
    
    private final static double MODIFICADORPRECO = 1.20;
    
    public Vip(String parque,String identificador, boolean ocupada) {
        super(parque,identificador,ocupada);
    }

   @Override
    public double calcularPreco(long minutosTotais) {
        double precoTotal = Math.floor(minutosTotais / Parque.INTERVALODECOBRANCAEMMINUTOS) * Parque.VALORPORTEMPO;
        return super.aplicarLimite(precoTotal) * MODIFICADORPRECO;
    }
    
}