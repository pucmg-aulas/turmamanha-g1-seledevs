/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.controller;

import br.com.javaParking.dao.OcupacaoDao;
import br.com.javaParking.dao.ParqueDAO;
import br.com.javaParking.dao.VagaDao;
import br.com.javaParking.dao.VeiculoDao;
import br.com.javaParking.model.Cliente;
import br.com.javaParking.model.Ocupacao;
import br.com.javaParking.model.Parque;
import br.com.javaParking.model.Vaga;
import br.com.javaParking.model.Veiculo;
import br.com.javaParking.view.parque.ParqueEscolhaView;
import br.com.javaParking.view.veiculo.VeiculoView;
import br.com.javaParking.view.veiculo.VeiculosRegistradosView;
import br.com.javaParking.view.xulambs.PdaView;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author leand
 */
public class PDAController {

    private PdaView view;
    private Parque parqueEscolhido;
    private Cliente clienteEscolhido;
    private Vaga VagaEscolhida;
    VeiculosRegistradosController veiculo;
    ClientesRegistradosController clienteDoRegistro;

    public PDAController(Parque parque) {

        parqueEscolhido = parque;

        this.view = new PdaView();
        carregarTabelaVagasDesocupadas();
        carregarTabelaVagaOcupadas();

        this.view.getTbVagasDesocupadas().getSelectionModel().addListSelectionListener(e -> {
            int linha = this.view.getTbVagasDesocupadas().getSelectedRow();
            if (linha != -1) {
                int confirmacao = JOptionPane.showConfirmDialog(view, "É um cliente anonimo?", "Duvida", JOptionPane.YES_NO_OPTION);
                VagaEscolhida = VagaDao.getVaga(parqueEscolhido, this.view.getTbVagasDesocupadas().getValueAt(linha, 0).toString());

                if (confirmacao == JOptionPane.YES_OPTION) {
                    clienteEscolhido = new Cliente();
                    clienteEscolhido.setCpf("");
                    clienteEscolhido.setNome("");

                    new VeiculosRegistradosController(parqueEscolhido, VagaEscolhida, clienteEscolhido, this);

                } else if (confirmacao == JOptionPane.NO_OPTION) {
                    clienteDoRegistro = new ClientesRegistradosController(parqueEscolhido, VagaEscolhida, this);
                    clienteEscolhido = clienteDoRegistro.getClienteSelecionado();
                }
            }
        });

        this.view.getBtnVoltar().addActionListener((e) -> {
            new ArrecadacaoController();
            this.view.dispose();
        });

        this.view.getTbVagasOcupadas().getSelectionModel().addListSelectionListener(e -> {
            int linha = this.view.getTbVagasOcupadas().getSelectedRow();
            if (linha != -1) {
                VagaEscolhida = VagaDao.getVaga(parqueEscolhido, this.view.getTbVagasOcupadas().getValueAt(linha, 0).toString());
                Ocupacao x = OcupacaoDao.getOcupacaoHoraEntrada(parqueEscolhido, VagaEscolhida.getIdentificador());
                LocalTime lt = LocalTime.now();

                Duration duracao = Duration.between(x.getHoraEntrada(), lt);
                long minutosTotais = duracao.toMinutes();

                this.view.getLblMinutosPassados().setText(String.valueOf(minutosTotais));
                this.view.getLblValorTotal().setText(String.valueOf(x.custoOcupacao(VagaEscolhida, lt)));
            }
        });

        this.view.setVisible(true);

    }

    public void carregarTabelaVagasDesocupadas() {
        List<Vaga> vagas = VagaDao.getVagasDesocupadas(parqueEscolhido);
        DefaultTableModel tm = new DefaultTableModel(new Object[]{"identificador", "tipo"}, 0);

        for (Vaga vaga : vagas) {
            tm.addRow(new Object[]{vaga.getIdentificador(), vaga.getClass().getSimpleName().toUpperCase()});
        }

        this.view.getTbVagasDesocupadas().setModel(tm);
    }

    public void carregarTabelaVagaOcupadas() {
        List<Vaga> vagas = VagaDao.getVagasOcupadas(parqueEscolhido);
        DefaultTableModel tm = new DefaultTableModel(new Object[]{"identificador", "tipo"}, 0);

        for (Vaga vaga : vagas) {
            tm.addRow(new Object[]{vaga.getIdentificador(), vaga.getClass().getSimpleName().toUpperCase()});
        }

        this.view.getTbVagasOcupadas().setModel(tm);
    }
}
