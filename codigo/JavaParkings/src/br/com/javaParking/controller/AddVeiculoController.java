/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.controller;

import br.com.javaParking.dao.DaoVeiculo;
import br.com.javaParking.model.Veiculo;
import br.com.javaParking.view.AddVeiculoView;
import javax.swing.JOptionPane;

/**
 *
 * @author rafae
 */
public class AddVeiculoController {
    private AddVeiculoView view;
    private DaoVeiculo veiculos;
    
    
    
    
    
    public void addVeiculo(){
        String placa = view.getPlacaTextField();
        String idCliente = view.getIDTextField();
        
        
        Veiculo v = new Veiculo(placa);
        
        veiculos.gravar(v,idCliente);
        
        JOptionPane.showMessageDialog(view, "Carro salvo com sucesso!");
        
        limpartela();
    }
    
    private void limpartela() {
        this.view.getPlacaTextField().setText("");
        this.view.getIDTextField().setText("");
    }
}
