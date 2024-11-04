/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.controller;

import br.com.javaParking.dao.ClienteDao;
import br.com.javaParking.model.Cliente;
import br.com.javaParking.view.cliente.ClienteView;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rafael
 */
public class ClienteController {

    private ClienteView view;
    private ClienteDao clientes;
    private Cliente clienteSelecionado;

    public ClienteController() {
        this.clientes = ClienteDao.getInstance();
        this.view = new ClienteView();

        this.view.getBtnAlterar().setBorderPainted(false);
        this.view.getBtnRemover().setBorderPainted(false);
        this.view.getBtnAdicionar().setBorderPainted(false);
        this.view.getBtnHistorico().setBorderPainted(false);
        this.view.getBtnVeiculo().setBorderPainted(false);
        this.view.getBtnVoltar().setBorderPainted(false);

        carregarTabela();

        this.view.getBtnAdicionar().addActionListener((e) -> {
            addCliente();
        });

        this.view.getBtnAlterar().addActionListener((e) -> {

            editarCliente();

        });

        this.view.getBtnRemover().addActionListener((e) -> {
            deleteCliente();
        });

        this.view.getTbClientes().getSelectionModel().addListSelectionListener(e -> {
            int linha = this.view.getTbClientes().getSelectedRow();
            if (linha != -1) {
                String cpf = (String) view.getTbClientes().getValueAt(linha, 1);
                clienteSelecionado = clientes.pesquisarPorCpf(cpf);

                if (clienteSelecionado != null) {
                    view.getTxtCPF().setText(clienteSelecionado.getId());
                    view.getTxtNome().setText(clienteSelecionado.getNome());
                    
                    updateMode();
                }
            }
        });
        
         // Adiciona o DocumentListener para a pesquisa em tempo real
        this.view.getTxtPesquisar().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                pesquisarParque();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                pesquisarParque();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                pesquisarParque();
            }
        });

        createMode();
        this.view.setVisible(true);
    }

    public void pesquisarParque() {
        String nome = view.getTxtPesquisar().getText().trim();

        // Se o campo estiver vazio, carrega todos os parques novamente
        if (nome.isEmpty()) {
            carregarTabela();
            return;
        }

        // Pesquisa o parque pelo nome ou caracteres inseridos
        List<Cliente> clientesEncontrados = clientes.buscarPorNomeParcial(nome);

        // Atualizar a tabela com os resultados encontrados
        DefaultTableModel tm = new DefaultTableModel(new Object[]{"Nome", "CPF"}, 0);
        for (Cliente cliente : clientesEncontrados) {
            tm.addRow(new Object[]{cliente.getNome(), cliente.getId()});
        }
        
       view.getTbClientes().setModel(tm);
    }
    
    private void limparCampos() {
        this.view.getTxtCPF().setText("");
        this.view.getTxtNome().setText("");
    }

    private void createMode() {
        this.view.getBtnAlterar().setEnabled(false);
        this.view.getBtnRemover().setEnabled(false);
        this.view.getBtnAdicionar().setEnabled(true);
        this.view.getBtnHistorico().setEnabled(false);
        this.view.getBtnVeiculo().setEnabled(false);
        this.view.getTxtCPF().setEnabled(true);
    }

    private void updateMode() {
        this.view.getBtnAlterar().setEnabled(true);
        this.view.getBtnRemover().setEnabled(true);
        this.view.getBtnAdicionar().setEnabled(false);
        this.view.getBtnHistorico().setEnabled(true);
        this.view.getBtnVeiculo().setEnabled(true);
        this.view.getTxtCPF().setEnabled(false);
    }

    private void addCliente() {
        String id = view.getTxtCPF().getText();
        String nome = view.getTxtNome().getText();

        if (!id.isEmpty() && !nome.isEmpty()) {
            Cliente c = new Cliente(nome, id);
            clientes.addCliente(c);

            JOptionPane.showMessageDialog(view, "Cliente salvo com sucesso!");
            limparCampos();
            carregarTabela();
        }else{
            JOptionPane.showMessageDialog(view, "Campos nome e CPF são obrigatorios!");
        }
    }

    private void editarCliente() {
        String nome = view.getTxtNome().getText();
        String cpf = view.getTxtCPF().getText();

        int op = JOptionPane.showConfirmDialog(view, "Deseja editar " + nome + "?");
        if (op == JOptionPane.YES_OPTION) {
            Cliente cliente = clientes.pesquisarPorCpf(cpf);
            cliente.setNome(nome);
            clientes.alterarCliente(cliente, cpf);
            JOptionPane.showMessageDialog(view, nome + " editado com sucesso!");
            carregarTabela();
            createMode();
            limparCampos();
        }
    }

    private void deleteCliente() {
        int linha = this.view.getTbClientes().getSelectedRow();
        String nome = (String) this.view.getTbClientes().getValueAt(linha, 0);
        String cpf = (String) this.view.getTbClientes().getValueAt(linha, 1);

        int op = JOptionPane.showConfirmDialog(view, "Deseja excluir " + nome + "?");
        if (op == JOptionPane.YES_OPTION) {
            Cliente cliente = clientes.pesquisarPorCpf(cpf);
            clientes.excluirCliente(cliente);
            JOptionPane.showMessageDialog(view, nome + " excluído com sucesso!");
            carregarTabela();
            createMode();
            limparCampos();
        }
    }

    private void carregarTabela() {
        Object colunas[] = {"Nome", "CPF"};
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);
        tm.setNumRows(0);

        for (Cliente cliente : clientes.getClientes()) {
            String linha[] = {cliente.getNome(), cliente.getId()};
            tm.addRow(linha);
        }
        view.getTbClientes().setModel(tm);
    }
}
