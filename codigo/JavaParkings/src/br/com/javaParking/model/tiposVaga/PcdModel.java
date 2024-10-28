package br.com.javaParking.model.tiposVaga;

import br.com.javaParking.model.ParqueModel;
import br.com.javaParking.model.VagaModel;
import br.com.javaParking.model.VagaModel;

public class PcdModel extends VagaModel {
    
    private final static double MODIFICADORPRECO = 0.87;
    
    public PcdModel(ParqueModel parque,String identificador, boolean ocupada) {
        super(parque,identificador,ocupada);
    }

    @Override
    public double calcularPreco(long minutosTotais) {
        double precoTotal = Math.floor(minutosTotais / this.getParque().getIntervaloDeCobrancaMinutos()) * this.getParque().getValorPorTempo();
        return super.aplicarLimite(precoTotal) * MODIFICADORPRECO;
    }
}