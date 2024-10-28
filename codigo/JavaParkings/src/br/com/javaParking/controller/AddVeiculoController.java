/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.controller;

import br.com.javaParking.dao.VeiculoDao;
import br.com.javaParking.model.VeiculoModel;
import br.com.javaParking.view.veiculo.VeiculoView;
import javax.swing.JOptionPane;

/**
 *
 * @author rafae
 */
public class AddVeiculoController {
    private VeiculoView view;
    private VeiculoDao veiculos;
    
    public void addVeiculo(){
        String placa = view.getPlaca().getText();
        
        
        VeiculoModel v = new VeiculoModel(placa);
        
        // [CONCERTAR] - Mudei o id do cliente, criar de acordo com o CPF
        veiculos.gravar(v,"");
        
        JOptionPane.showMessageDialog(view, "Carro salvo com sucesso!");
        
        limpartela();
    }
    
    private void limpartela() {
        this.view.getPlaca().setText("");
    }
}
