/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.controller;

import br.com.javaParking.dao.ClienteDao;
import br.com.javaParking.model.ClienteModel;
import br.com.javaParking.view.cliente.ClienteView;
import javax.swing.JOptionPane;

/**
 *
 * @author rafae
 */
public class AddClienteController {

    private ClienteView view;
    private ClienteDao clientes;

    public AddClienteController() {
        
    }

    public void addCliente() {
        String id = view.getTxtCPF().getText();
        String nome = view.getTxtNome().getText();

        ClienteModel c = new ClienteModel(id, nome);

        clientes.gravar(c);

        JOptionPane.showMessageDialog(view, "Cliente salvo com sucesso!");

        limpartela();
    }
    
    public void deleteCliente(){
        
    }

    public void cancelar() {
        this.view.dispose();
    }

    private void limpartela() {
        this.view.getTxtCPF().setText("");
        this.view.getTxtNome().setText("");
    }
}
