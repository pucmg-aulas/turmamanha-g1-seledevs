package br.com.javaParking.model.tiposVaga;

import br.com.javaParking.model.ParqueModel;
import br.com.javaParking.model.VagaModel;

public class ComumModel extends VagaModel {
    
    private final static double MODIFICADORPRECO = 1.0;
    
    public ComumModel(ParqueModel parque,String identificador, boolean ocupada) {
        super(parque,identificador,ocupada);
    }

    @Override
    public double calcularPreco(long minutosTotais) {
        double precoTotal = Math.floor(minutosTotais / this.getParque().getIntervaloDeCobrancaMinutos()) * this.getParque().getValorPorTempo();
        return super.aplicarLimite(precoTotal) * MODIFICADORPRECO;
    }
}