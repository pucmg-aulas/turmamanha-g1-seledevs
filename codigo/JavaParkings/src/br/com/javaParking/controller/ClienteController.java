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
        
         this.clientes = clientes.getInstance();
         this.view = new ClienteView();
         
         carregarTabela();
        
        if(this.view.getTbClientes().getSelectedRow() != -1){
            
            this.view.getBtnAlterar().setEnabled(true);
            this.view.getBtnRemover().setEnabled(true);
            this.view.getBtnAdicionar().setEnabled(false);
            
            this.view.getBtnAlterar().addActionListener((e)->{
                editarCliente();
            });
            
            this.view.getBtnRemover().addActionListener((e)->{
                deleteCliente();
            });
            
        }
        
        this.view.getBtnAdicionar().addActionListener((e)->{
            addCliente();
        });
    }

    private void addCliente() {
        String id = view.getTxtCPF().getText();
        String nome = view.getTxtNome().getText();

        Cliente c = new Cliente(id, nome);

        clientes.addCliente(c);

        JOptionPane.showMessageDialog(view, "Cliente salvo com sucesso!");

        limpartela();
    }

    private void editarCliente() {

        //ta errado mas a logica ta certa arrumar       
            int linha = this.view.getTbClientes().getSelectedRow();
            String nome = (String) this.view.getTbClientes().getValueAt(linha, 1);
            String cpf = (String) this.view.getTbClientes().getValueAt(linha, 0);

            int op = JOptionPane.showConfirmDialog(view, "Deseja editar " + nome + "?");
            if (op == JOptionPane.YES_OPTION) {
                Cliente cliente = clientes.pesquisarPorCpf(cpf);
                clientes.alterarCliente(cliente, nome);
                JOptionPane.showMessageDialog(view, nome + " Editado com Sucesso!");
                //chamar função para recarregar tabelas
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
            JOptionPane.showMessageDialog(view, nome + " Excluído com Sucesso!");
            //chamar função para recarregar tabelas
        }

    }

    private void carregarTabela() {
        Object colunas[] = {"Nome", "Marca"};
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);

        tm.setNumRows(0);

        for (Cliente cliente : clientes.getClientes()) {
            String c = cliente.toString();
            String linha[] = c.split("%");
            tm.addRow(new Object[]{linha[0], linha[1]});
        }
        view.getTbClientes().setModel(tm);
    }

    private void limpartela() {
        this.view.getTxtCPF().setText("");
        this.view.getTxtNome().setText("");
    }
}
