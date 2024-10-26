package br.com.javaParking.model.tiposVaga;

import br.com.javaParking.model.Parque;
import br.com.javaParking.model.Vaga;
import br.com.javaParking.model.Vaga;

public class Pcd extends Vaga {
    
    private final static double MODIFICADORPRECO = 0.87;
    
    public Pcd(String parque,String identificador, boolean ocupada) {
        super(parque,identificador,ocupada);
    }

    @Override
    public double calcularPreco(long minutosTotais) {
        double precoTotal = Math.floor(minutosTotais / Parque.INTERVALODECOBRANCAEMMINUTOS) * Parque.VALORPORTEMPO;
        return super.aplicarLimite(precoTotal) * MODIFICADORPRECO;
    }
}