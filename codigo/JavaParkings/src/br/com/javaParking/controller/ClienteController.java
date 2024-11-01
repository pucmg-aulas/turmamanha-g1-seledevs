/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.controller;

import br.com.javaParking.dao.ClienteDao;
import br.com.javaParking.model.Cliente;
import br.com.javaParking.view.cliente.ClienteView;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rafael
 */
public class ClienteController {

    private ClienteView view;
    private ClienteDao clientes;

    public ClienteController() {
        this.clientes = ClienteDao.getInstance();
        this.view = new ClienteView();

        carregarTabela();
        
        this.view.getBtnAdicionar().addActionListener((e) -> {
            addCliente();
        });

        this.view.getBtnAlterar().addActionListener((e) -> {
            if (this.view.getTbClientes().getSelectedRow() != -1) {
                editarCliente();
            }
        });

        this.view.getBtnRemover().addActionListener((e) -> {
            if (this.view.getTbClientes().getSelectedRow() != -1) {
                deleteCliente();
            }
        });

        this.view.getTbClientes().getSelectionModel().addListSelectionListener(e -> {
            boolean linhaSelecionada = this.view.getTbClientes().getSelectedRow() != -1;
            this.view.getBtnAlterar().setEnabled(linhaSelecionada);
            this.view.getBtnRemover().setEnabled(linhaSelecionada);
            this.view.getBtnAdicionar().setEnabled(!linhaSelecionada);
        });
    }

    private void addCliente() {
        String id = view.getTxtCPF().getText();
        String nome = view.getTxtNome().getText();

        Cliente c = new Cliente(id, nome);
        clientes.addCliente(c);

        JOptionPane.showMessageDialog(view, "Cliente salvo com sucesso!");
        limpartela();
        carregarTabela();
    }

    private void editarCliente() {
        int linha = this.view.getTbClientes().getSelectedRow();
        String nome = (String) this.view.getTbClientes().getValueAt(linha, 1);
        String cpf = (String) this.view.getTbClientes().getValueAt(linha, 0);

        int op = JOptionPane.showConfirmDialog(view, "Deseja editar " + nome + "?");
        if (op == JOptionPane.YES_OPTION) {
            Cliente cliente = clientes.pesquisarPorCpf(cpf);
            clientes.alterarCliente(cliente, nome);
            JOptionPane.showMessageDialog(view, nome + " editado com sucesso!");
            carregarTabela();
        }
    }

    private void deleteCliente() {
        int linha = this.view.getTbClientes().getSelectedRow();
        String nome = (String) this.view.getTbClientes().getValueAt(linha, 1);
        String cpf = (String) this.view.getTbClientes().getValueAt(linha, 0);

        int op = JOptionPane.showConfirmDialog(view, "Deseja excluir " + nome + "?");
        if (op == JOptionPane.YES_OPTION) {
            Cliente cliente = clientes.pesquisarPorCpf(cpf);
            clientes.excluirCliente(cliente);
            JOptionPane.showMessageDialog(view, nome + " excluído com sucesso!");
            carregarTabela();
        }
    }

    private void carregarTabela() {
        Object colunas[] = {"CPF", "Nome"};
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);
        tm.setNumRows(0);

        for (Cliente cliente : clientes.getClientes()) {
            String linha[] = {cliente.getId(), cliente.getNome()};
            tm.addRow(linha);
        }
        view.getTbClientes().setModel(tm);
    }

    private void limpartela() {
        this.view.getTxtCPF().setText("");
        this.view.getTxtNome().setText("");
    }
}
