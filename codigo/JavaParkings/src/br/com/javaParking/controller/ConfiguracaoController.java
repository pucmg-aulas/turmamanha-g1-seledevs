package br.com.javaParking.controller;

import br.com.javaParking.dao.ConfiguracaoDAO;
import br.com.javaParking.model.ConfiguracaoModel;
import br.com.javaParking.view.sistema.ConfiguracaoView;

import javax.swing.*;

public class ConfiguracaoController {
    private ConfiguracaoModel model;
    private ConfiguracaoView view;
    private ConfiguracaoDAO dao;

    public ConfiguracaoController(ConfiguracaoModel model, ConfiguracaoView view) {
        this.model = model;
        this.view = view;
        this.dao = new ConfiguracaoDAO();
        carregarConfiguracao(); // Carrega a configuração ao iniciar o controller
        initController();
    }

    private void initController() {
        view.getSalvarButton().addActionListener(e -> salvarConfiguracao());
        view.getCancelarButton().addActionListener(e -> cancelarConfiguracao());
    }

    private void salvarConfiguracao() {
        try {
            double porcentagemIdosos = Double.parseDouble(view.getPorcentagemMinimaIdososField().getText());
            double porcentagemPCD = Double.parseDouble(view.getPorcentagemMinimaPCDField().getText());
            double porcentagemVIP = Double.parseDouble(view.getPorcentagemMinimaVIPField().getText());
            int intervaloCobranca = Integer.parseInt(view.getIntervaloCobrancaField().getText());
            double valorMaximoDiaria = Double.parseDouble(view.getValorMaximoDiariaField().getText());

            model.setPorcentagemMinimaIdosos(porcentagemIdosos);
            model.setPorcentagemMinimaPCD(porcentagemPCD);
            model.setPorcentagemMinimaVIP(porcentagemVIP);
            model.setIntervaloCobrancaMinutos(intervaloCobranca);
            model.setValorMaximoDiaria(valorMaximoDiaria);

            if (model.validarPorcentagens()) {
                dao.salvarConfiguracao(model); 
                JOptionPane.showMessageDialog(view, "Configurações salvas com sucesso.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Por favor, insira valores válidos.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    private void carregarConfiguracao() {
        ConfiguracaoModel configuracaoCarregada = dao.carregarConfiguracao();
        if (configuracaoCarregada != null) {
            model.setPorcentagemMinimaIdosos(configuracaoCarregada.getPorcentagemMinimaIdosos());
            model.setPorcentagemMinimaPCD(configuracaoCarregada.getPorcentagemMinimaPCD());
            model.setPorcentagemMinimaVIP(configuracaoCarregada.getPorcentagemMinimaVIP());
            model.setIntervaloCobrancaMinutos(configuracaoCarregada.getIntervaloCobrancaMinutos());
            model.setValorMaximoDiaria(configuracaoCarregada.getValorMaximoDiaria());
        }
    }

    private void cancelarConfiguracao() {
        view.dispose();
    }
}
