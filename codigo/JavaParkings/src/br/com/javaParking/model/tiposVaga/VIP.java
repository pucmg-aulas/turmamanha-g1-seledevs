package br.com.javaParking.model.tiposVaga;

import br.com.javaParking.dao.ConfiguracaoDAO;
import br.com.javaParking.model.Parque;
import br.com.javaParking.model.Vaga;
import br.com.javaParking.model.Vaga;

public class VIP extends Vaga {
    
    private final static double MODIFICADORPRECO = 1.20;
    
    public VIP(Parque parque,String identificador, boolean ocupada) {
        super(parque,identificador,ocupada);
    }

   @Override
    public double calcularPreco(long minutosTotais) {
        double precoTotal = Math.floor(minutosTotais / ConfiguracaoDAO.configuracao().getIntervaloCobrancaMinutos()) * ConfiguracaoDAO.configuracao().getValorPeriodoPorTempo();
        return super.aplicarLimite(precoTotal) * MODIFICADORPRECO;
    }
    
}