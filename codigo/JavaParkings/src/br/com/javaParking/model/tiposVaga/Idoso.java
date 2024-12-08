/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

package br.com.javaParking.model.tiposVaga;

import br.com.javaParking.dao.ConfiguracaoDAO;
import br.com.javaParking.model.Parque;
import br.com.javaParking.model.Vaga;
import br.com.javaParking.model.Vaga;
import javax.swing.JOptionPane;

/**
 *
 * @author viniciusgomesrodrigues
 */
public class Idoso extends Vaga {

    private final static double MODIFICADORPRECO = 0.85;
    
    public Idoso(Parque parque,String identificador, boolean ocupada) {
        super(parque,identificador,ocupada);
    }
    
   @Override
    public double calcularPreco(long minutosTotais) {
        
        double precoTotal = Math.floor(minutosTotais / ConfiguracaoDAO.configuracao().getIntervaloCobrancaMinutos()) * ConfiguracaoDAO.configuracao().getValorPeriodoPorTempo();
        return super.aplicarLimite(precoTotal) * MODIFICADORPRECO;
    }
    
}
