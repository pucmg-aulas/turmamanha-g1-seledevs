/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.controller;

import br.com.javaParking.dao.ConfiguracaoDAO;
import br.com.javaParking.model.Configuracao;
import br.com.javaParking.view.xulambs.ConfiguracaoView;
import javax.swing.JOptionPane;

/**
 *
 * @author leand
 */
public class ConfiguracaoController {

    private ConfiguracaoView view;

    public ConfiguracaoController() {
        this.view = new ConfiguracaoView();
        
        
       
            this.view.getTxtValorMaximoDiaria().setText(String.valueOf(ConfiguracaoDAO.configuracao().getValorMaximoDiaria()));
        this.view.getTxtPorcentagemVIP().setText(String.valueOf(ConfiguracaoDAO.configuracao().getPorcentagemMinimaVIP()));
        this.view.getTxtPorcentagemPCD().setText(String.valueOf(ConfiguracaoDAO.configuracao().getPorcentagemMinimaPCD()));
        this.view.getTxtPorcentagemIdoso().setText(String.valueOf(ConfiguracaoDAO.configuracao().getPorcentagemMinimaIdosos()));
        this.view.getTxtPeriodoDeTempo().setText(String.valueOf(ConfiguracaoDAO.configuracao().getValorPeriodoPorTempo()));
        this.view.getTxtIntervaloEmMinutos().setText(String.valueOf(ConfiguracaoDAO.configuracao().getIntervaloCobrancaMinutos()));
       
        
        this.view.getBtnSalvar().addActionListener((e) -> {
            salvarConfiguracao();
        });

        this.view.setVisible(true);
    }

    private Configuracao montarConfiguracao() {
        Configuracao x = new Configuracao(0, 0, 0, 0, 0, 0);
        try {
            x.setValorMaximoDiaria(Double.parseDouble(this.view.getTxtValorMaximoDiaria().getText()));
            x.setValorPeriodoPorTempo(Double.parseDouble(this.view.getTxtPeriodoDeTempo().getText()));
            
            x.setPorcentagemMinimaVIP(Double.parseDouble(this.view.getTxtPorcentagemVIP().getText()));
            x.setPorcentagemMinimaPCD(Double.parseDouble(this.view.getTxtPorcentagemPCD().getText()));
            x.setPorcentagemMinimaIdosos(Double.parseDouble(this.view.getTxtPorcentagemIdoso().getText()));
            
            x.setIntervaloCobrancaMinutos(Integer.parseInt(this.view.getTxtIntervaloEmMinutos().getText()));
            
            return x;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e);
        }

        return null;
    }

    private void salvarConfiguracao() {
        try {
            if (montarConfiguracao() != null) {
                ConfiguracaoDAO.salvar(montarConfiguracao());
                JOptionPane.showMessageDialog(view, "Configuração salva com sucesso!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.toString());
        }

    }

}
