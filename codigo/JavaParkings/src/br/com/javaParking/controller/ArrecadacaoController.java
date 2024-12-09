/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.controller;

import br.com.javaParking.dao.ArrecadacaoDAO;
import br.com.javaParking.dao.ClienteDao;
import br.com.javaParking.model.Arrecadacao;
import br.com.javaParking.view.cliente.ClienteView;
import br.com.javaParking.view.xulambs.ArrecadacaoView;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author leand
 */
public class ArrecadacaoController {

    private ArrecadacaoView view;
    private ArrecadacaoDAO arrecadacoes;
    private boolean aux;
    private String auxS;

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

        this.view.getCbParque().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbParqueActionPerformed(evt);
            }
        });
        
        this.view.getCbMes().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbParqueActionPerformed(evt);
            }
        });
        
        this.view.getCbAno().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbParqueActionPerformed(evt);
            }
        });

        this.view.getCbMes().addItem("01");
        this.view.getCbMes().addItem("02");
        this.view.getCbMes().addItem("03");
        this.view.getCbMes().addItem("04");
        this.view.getCbMes().addItem("05");
        this.view.getCbMes().addItem("06");
        this.view.getCbMes().addItem("07");
        this.view.getCbMes().addItem("08");
        this.view.getCbMes().addItem("09");
        this.view.getCbMes().addItem("10");
        this.view.getCbMes().addItem("11");
        this.view.getCbMes().addItem("12");

        for (int i = 0; i < ArrecadacaoDAO.tabela().size(); i++) {

            aux = true;

            for (int j = 0; j < this.view.getCbParque().getItemCount(); j++) {
                if (this.view.getCbParque().getItemAt(j).equals(ArrecadacaoDAO.tabela().get(i).getFk_nome_parque())) {
                    aux = false;
                }
            }

            if (aux) {
                this.view.getCbParque().addItem(ArrecadacaoDAO.tabela().get(i).getFk_nome_parque());
            }

        }

        for (int i = 0; i < ArrecadacaoDAO.tabela().size(); i++) {

            aux = true;
            auxS = String.valueOf(ArrecadacaoDAO.tabela().get(i).getData_arrecadacao()).split("-")[0];

            for (int j = 0; j < this.view.getCbAno().getItemCount(); j++) {
                if (this.view.getCbAno().getItemAt(j).equals(auxS)) {
                    aux = false;
                }
            }

            if (aux) {
                this.view.getCbAno().addItem(auxS);
            }
        }

        this.view.setVisible(true);
    }

    private void carregarTabela() {
        Object colunas[] = {"Cliente", "Parque", "Valor", "Data"};
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);
        tm.setNumRows(0);

        for (Arrecadacao arrecadacao : ArrecadacaoDAO.tabela()) {  // Alterado para listarClientes

            if (ClienteDao.buscarPorCpf(arrecadacao.getFk_cpf_cliente()) == null) {
                String linha[] = {"Anonimo", arrecadacao.getFk_nome_parque(), String.valueOf(arrecadacao.getValor_arrecadado()), String.valueOf(arrecadacao.getData_arrecadacao())};
                tm.addRow(linha);
            } else {
                String linha[] = {ClienteDao.buscarPorCpf(arrecadacao.getFk_cpf_cliente()).getNome(), arrecadacao.getFk_nome_parque(), String.valueOf(arrecadacao.getValor_arrecadado()), String.valueOf(arrecadacao.getData_arrecadacao())};
                tm.addRow(linha);
            }

        }
        view.getTbArrecadacoes().setModel(tm);
        calcularValores();
    }

    private void calcularValores() {

        double valorTotal = 0;

        for (int i = 0; i < view.getTbArrecadacoes().getModel().getRowCount(); i++) {
            valorTotal += Double.parseDouble(view.getTbArrecadacoes().getModel().getValueAt(i, 2).toString());
        }

        view.getLblTotalArrecadado().setText(new DecimalFormat("#,##0.00").format(valorTotal).replace(",", "."));
        if(valorTotal > 0){
                    view.getLblValorMedio().setText(new DecimalFormat("#,##0.00").format(valorTotal / view.getTbArrecadacoes().getModel().getRowCount()).replace(",", "."));

        }else{
            view.getLblValorMedio().setText(new DecimalFormat("#,##0.00").format(0).replace(",", "."));
        }
    }

    private void filtros() {

        carregarTabela();

        if (this.view.getCbParque().getSelectedItem() != "TODOS") {
            for (int i = 0; i < view.getTbArrecadacoes().getModel().getRowCount(); i++) {

                if (!this.view.getCbParque().getSelectedItem().equals(view.getTbArrecadacoes().getModel().getValueAt(i, 1).toString())) {

                    DefaultTableModel model = (DefaultTableModel) this.view.getTbArrecadacoes().getModel();

                    if (i != -1) {
                        model.removeRow(i);
                        i--;
                    }

                }
            }
        }

        if (this.view.getCbAno().getSelectedItem() != "TODOS") {
            for (int i = 0; i < view.getTbArrecadacoes().getModel().getRowCount(); i++) {

                auxS = view.getTbArrecadacoes().getModel().getValueAt(i, 3).toString().split("-")[0];

                if (!this.view.getCbAno().getSelectedItem().equals(auxS)) {

                    DefaultTableModel model = (DefaultTableModel) this.view.getTbArrecadacoes().getModel();

                    if (i != -1) {
                        model.removeRow(i);
                        i--;
                    }

                }
            }
        }

        if (this.view.getCbMes().getSelectedItem() != "TODOS") {
            for (int i = 0; i < view.getTbArrecadacoes().getModel().getRowCount(); i++) {

                auxS = view.getTbArrecadacoes().getModel().getValueAt(i, 3).toString().split("-")[1];

                if (!this.view.getCbMes().getSelectedItem().equals(auxS)) {

                    DefaultTableModel model = (DefaultTableModel) this.view.getTbArrecadacoes().getModel();

                    if (i != -1) {
                        model.removeRow(i);
                        i--;
                    }

                }
            }
        }
        
        calcularValores();
    }

    private void cbParqueActionPerformed(java.awt.event.ActionEvent evt) {
        filtros();
    }

    public ArrecadacaoView getView() {
        return view;
    }

    public void setView(ArrecadacaoView view) {
        this.view = view;
    }

    
          
}
