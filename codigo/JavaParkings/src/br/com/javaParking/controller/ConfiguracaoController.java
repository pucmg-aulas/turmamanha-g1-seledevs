package br.com.javaParking.controller;

import br.com.javaParking.model.ConfiguracaoModel;
import br.com.javaParking.view.xulambs.ConfiguracaoView;

import javax.swing.JOptionPane;

public class ConfiguracaoController {
    private ConfiguracaoModel model;
    private ConfiguracaoView view;

    public ConfiguracaoController(ConfiguracaoModel model, ConfiguracaoView view) {
        this.model = model;
        this.view = view;
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
                JOptionPane.showMessageDialog(view, "Configurações salvas com sucesso.");
            } else {
                JOptionPane.showMessageDialog(view, "A soma das porcentagens não pode ultrapassar 100%.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Por favor, insira valores válidos.");
        }
    }

    private void cancelarConfiguracao() {
        view.dispose();
    }
}
