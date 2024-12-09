/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.controller;

import br.com.javaParking.dao.ArrecadacaoDAO;
import br.com.javaParking.dao.ClienteDao;
import br.com.javaParking.model.Arrecadacao;
import br.com.javaParking.model.Cliente;
import br.com.javaParking.view.cliente.ClienteHistoricoView;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author leand
 */
public class ClienteHistoricoController {

    private ClienteHistoricoView view;
    private Cliente clienteSelecionado;

    public ClienteHistoricoController(Cliente cliente) {
        
        this.view = new ClienteHistoricoView();
        clienteSelecionado = cliente;

        carregarTabela();

        this.view.setVisible(true);
    }

    public void carregarTabela() {
        List<Arrecadacao> arrecadacoes = ArrecadacaoDAO.tabelaPorCpf(clienteSelecionado.getCpf());
        DefaultTableModel tm = new DefaultTableModel(new Object[]{"Valores arrecadados"}, 0);

        for (Arrecadacao arrecadacao : arrecadacoes) {
            tm.addRow(new Object[]{arrecadacao.getValor_arrecadado()});
        }

        this.view.getTbHistorico().setModel(tm);
    }
}
