/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.controller;

import br.com.javaParking.dao.VeiculoDAO;
import br.com.javaParking.model.Veiculo;
import br.com.javaParking.view.veiculo.VeiculoView;
import javax.swing.JOptionPane;

/**
 *
 * @author rafae
 */
public class VeiculoController {
    private VeiculoView view;
    private VeiculoDAO veiculos;
    
    public void addVeiculo(){
        String placa = view.getPlaca().getText();
        
        
        Veiculo v = new Veiculo(placa);
        
        // [CONCERTAR] - Mudei o id do cliente, criar de acordo com o CPF
        veiculos.gravar(v,"");
        
        JOptionPane.showMessageDialog(view, "Carro salvo com sucesso!");
        
        limpartela();
    }
    
    private void limpartela() {
        this.view.getPlaca().setText("");
    }
}
