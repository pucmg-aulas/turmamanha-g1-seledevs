/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.controller;

import br.com.javaParking.dao.ParqueDao;
import br.com.javaParking.model.ParqueModel;
import br.com.javaParking.view.parque.ParqueView;
import javax.swing.JOptionPane;

/**
 *
 * @author viniciusgomesrodrigues
 */

public class AddParqueController {
    
    private ParqueView view;
    private ParqueDao parques;
    
    public AddParqueController(ParqueView view) {
        this.view = view;
    }
    
    public void AddParque() {
        
        String nomeParque = view.getTxtnomeParque().getText();
        int numeroVagas = Integer.parseInt(view.getTxtnumeroVagas().getText());
        int VagasPorFileira = Integer.parseInt(view.getTxtVagasPorFileira().getText());

        JOptionPane.showMessageDialog(view, "<html> <strong>Parque " + nomeParque + " salvo com sucesso! </strong> </html>");

        limpartela();
        
    }

    private void limpartela() {
        view.getTxtnomeParque().setText("");
        view.getTxtnumeroVagas().setText("");
        view.getTxtVagasPorFileira().setText("");
    }
    
    public void cancelar() {
        this.view.dispose();
    }
    
}
