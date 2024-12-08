/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.controller;

import br.com.javaParking.dao.ClienteDao;
import br.com.javaParking.model.Cliente;
import br.com.javaParking.model.Parque;
import br.com.javaParking.model.Vaga;
import br.com.javaParking.view.cliente.ClientesRegistradosView;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author leand
 */
public class ClientesRegistradosController {

    private ClientesRegistradosView view;
    private Cliente clienteSelecionado;

    public ClientesRegistradosController(Parque parque, Vaga vaga, PDAController pda) {
        this.view = new ClientesRegistradosView();

        carregarTabela();

        this.view.getTbListaClientes().getSelectionModel().addListSelectionListener(e -> {
            int linha = this.view.getTbListaClientes().getSelectedRow();
            if (linha != -1) {
                clienteSelecionado = ClienteDao.buscarPorCpf(this.view.getTbListaClientes().getValueAt(linha, 1).toString());
                int confirmacao2 = JOptionPane.showConfirmDialog(view, "O veiculo existe?", "Duvida", JOptionPane.YES_NO_OPTION);

                if (confirmacao2 == JOptionPane.YES_OPTION) {
                    new VeiculosRegistradosController(parque, vaga, clienteSelecionado, pda);
                } else if (confirmacao2 == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "Registre o veiculo e reselecione ele nesta tela.");
                    new VeiculoController(clienteSelecionado);
                }
                this.view.dispose();
            }
        });

        this.view.setVisible(true);
    }

    public void carregarTabela() {
        List<Cliente> clientes = ClienteDao.listarClientes();
        DefaultTableModel tm = new DefaultTableModel(new Object[]{"Nome", "CPF"}, 0);

        for (Cliente cliente : clientes) {
            tm.addRow(new Object[]{cliente.getNome(), cliente.getCpf()});
        }

        this.view.getTbListaClientes().setModel(tm);
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

}
