package br.com.javaParking.model.tiposVaga;

import br.com.javaParking.model.Parque;
import br.com.javaParking.model.Vaga;

public class Comum extends Vaga {
    
    private final static double MODIFICADORPRECO;
    
    static{
        MODIFICADORPRECO = 1;
    }
    
    public Comum(String parque,String identificador, boolean ocupada) {
        super(parque,identificador,ocupada);
    }

    @Override
    public double calcularPreco(int dias,int minutos) {        
        double precoTotal = Math.floor(minutos / Parque.INTERVALODECOBRANCAEMMINUTOS) * Parque.VALORPORTEMPO;         
        return (super.aplicarLimite(precoTotal) + (Parque.VALORDEDIARIAMAXIMA * dias)) * MODIFICADORPRECO; 
    }
}