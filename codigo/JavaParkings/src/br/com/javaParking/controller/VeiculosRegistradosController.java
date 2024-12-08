/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.controller;

import br.com.javaParking.dao.OcupacaoDao;
import br.com.javaParking.dao.VagaDao;
import br.com.javaParking.dao.VeiculoDao;
import br.com.javaParking.model.Cliente;
import br.com.javaParking.model.Ocupacao;
import br.com.javaParking.model.Parque;
import br.com.javaParking.model.Vaga;
import br.com.javaParking.model.Veiculo;
import br.com.javaParking.view.veiculo.VeiculosRegistradosView;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author leand
 */
public class VeiculosRegistradosController {

    private VeiculosRegistradosView view;
    private Veiculo veiculoSelecionado;
    private Cliente clienteSelecionado;

    public VeiculosRegistradosController(Parque parque, Vaga vaga, Cliente cliente, PDAController pda) {
        this.view = new VeiculosRegistradosView();
        clienteSelecionado = cliente;

        carregarTabela();

        this.view.getTbListaVeiculos().getSelectionModel().addListSelectionListener(e -> {
            int linha = this.view.getTbListaVeiculos().getSelectedRow();
            if (linha != -1) {
                veiculoSelecionado = VeiculoDao.buscarPorPlaca(this.view.getTbListaVeiculos().getValueAt(linha, 0).toString());
                VagaDao.ocupar(parque, vaga.getIdentificador());
                pda.carregarTabelaVagaOcupadas();
                pda.carregarTabelaVagasDesocupadas();
                LocalTime lt = LocalTime.now();

                Ocupacao x = new Ocupacao(cliente, veiculoSelecionado, vaga, lt, parque);

                OcupacaoDao.addOcupacao(x);

                this.view.dispose();
            }
        });

        this.view.setVisible(true);
    }

    public void carregarTabela() {
        List<Veiculo> veiculos = VeiculoDao.listarVeiculosPorCliente(clienteSelecionado.getCpf());
        DefaultTableModel tm = new DefaultTableModel(new Object[]{"Placa"}, 0);

        for (Veiculo veiculo : veiculos) {
            tm.addRow(new Object[]{veiculo.getPlaca()});
        }

        this.view.getTbListaVeiculos().setModel(tm);
    }
}
