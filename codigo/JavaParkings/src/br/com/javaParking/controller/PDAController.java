/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.controller;

import br.com.javaParking.dao.VeiculoDao;
import br.com.javaParking.model.Parque;
import br.com.javaParking.model.Veiculo;
import br.com.javaParking.view.parque.ParqueEscolhaView;
import br.com.javaParking.view.veiculo.VeiculoView;
import br.com.javaParking.view.xulambs.PdaView;

/**
 *
 * @author leand
 */
public class PDAController {
    
    private PdaView view;
    
    public PDAController(Parque parque) {

        this.view = new PdaView();
        this.view.setVisible(true);
    }
    
}
