/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.controller;

import br.com.javaParking.dao.ArrecadacaoDAO;
import br.com.javaParking.dao.ClienteDao;
import br.com.javaParking.view.cliente.ClienteView;
import br.com.javaParking.view.xulambs.ArrecadacaoView;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author leand
 */
public class ArrecadacaoController {
    
    private ArrecadacaoView view;
    private ArrecadacaoDAO arrecadacoes;
    
    public ArrecadacaoController() {
        this.arrecadacoes = new ArrecadacaoDAO(); 
        this.view = new ArrecadacaoView();
        
        this.view.getCbAno();
        this.view.getCbMes();
        this.view.getCbParque();
        
        this.view.getTbArrecadacoes();
        
        this.view.getMenSair();
        this.view.getMenPDA();
        this.view.getMenConfiguracao();
        this.view.getMenCadastroParque();
        this.view.getMenCadastroCliente();
        
        this.view.getLblValorMedio();
        this.view.getLblTotalArrecadado();

        carregarTabela();

        this.view.getMenSair().addActionListener((e) -> {
            this.view.dispose();
        });

        this.view.getMenCadastroCliente().addActionListener((e) -> {
            new ClienteController();
            this.view.dispose();
        });

        this.view.getMenConfiguracao().addActionListener((e) -> {
            new ConfiguracaoController();
        });
        
        this.view.getMenPDAItem().addActionListener((e) -> {
            new ParqueEscolhaController();
            this.view.dispose();
        });
        
        this.view.getMenCadastroParque().addActionListener((e) -> {
            new ParqueController();
            this.view.dispose();
        });

        this.view.getTbArrecadacoes().getSelectionModel().addListSelectionListener(e -> {
           
        });

       
        this.view.setVisible(true);
    }
    
    private void carregarTabela() {

    }
    
}
