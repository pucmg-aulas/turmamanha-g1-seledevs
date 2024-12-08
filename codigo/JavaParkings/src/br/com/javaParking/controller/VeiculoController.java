/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.controller;

import br.com.javaParking.dao.ConfiguracaoDAO;
import br.com.javaParking.dao.ParqueDAO;
import br.com.javaParking.dao.VeiculoDao;
import br.com.javaParking.model.Cliente;
import br.com.javaParking.model.Parque;
import br.com.javaParking.model.Veiculo;
import br.com.javaParking.view.veiculo.VeiculoView;
import br.com.javaParking.view.xulambs.ConfiguracaoView;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rafael
 */
public class VeiculoController {

    private VeiculoView view;
    private static String clienteEscolhidoCPF;
    private static Veiculo veiculoEscolhido;

    public VeiculoController(Cliente cliente) {

        clienteEscolhidoCPF = cliente.getCpf();

        this.view = new VeiculoView();

        this.view.getBtnAdicionar().setBorderPainted(false);
        this.view.getBtnAtualizar().setBorderPainted(false);
        this.view.getBtnRemover().setBorderPainted(false);
        
        this.view.getBtnAdicionar().addActionListener((e) -> {
            Veiculo x = new Veiculo(view.getTxtPlaca().getText(), clienteEscolhidoCPF);
            VeiculoDao.addVeiculo(x, clienteEscolhidoCPF);
            limpartela();
        });
        
        

        this.view.getBtnAtualizar().addActionListener((e) -> {
            limpartela();
        });

        this.view.getBtnRemover().addActionListener((e) -> {
            VeiculoDao.excluirVeiculo(veiculoEscolhido.getPlaca());
            limpartela();
        });

        this.view.getTbVeiculo().getSelectionModel().addListSelectionListener((e) -> {
            int linha = this.view.getTbVeiculo().getSelectedRow();
            if (linha != -1) {
                Veiculo x = new Veiculo(this.view.getTbVeiculo().getValueAt(linha, 0).toString(), clienteEscolhidoCPF);
                
                veiculoEscolhido = x;
                habilitarModoEdicao();
            }
        });

        this.view.setVisible(true);
        habilitarModoCriacao();
        carregarTabela();
    }

    private void carregarTabela() {
        List<Veiculo> veiculos = VeiculoDao.listarTodosVeiculos();
        DefaultTableModel tm = new DefaultTableModel(new Object[]{"placa", "cliente"}, 0);

        for (Veiculo x : veiculos) {
            tm.addRow(new Object[]{x.getPlaca(), x.getCpfCliente()});
        }

        this.view.getTbVeiculo().setModel(tm);
    }

    private void limpartela() {
        view.getTxtPlaca().setText("");
        habilitarModoCriacao();
        carregarTabela();
    }
    
     private void habilitarModoEdicao() {
        this.view.getBtnAdicionar().setEnabled(false);
        this.view.getBtnRemover().setEnabled(true);
        this.view.getTxtPlaca().setEnabled(false);
        
        this.view.getBtnAdicionar().setBorderPainted(false);
        this.view.getBtnAtualizar().setBorderPainted(false);
        this.view.getBtnRemover().setBorderPainted(false);
    }
    
    private void habilitarModoCriacao(){
        this.view.getBtnAdicionar().setEnabled(true);
        this.view.getBtnRemover().setEnabled(false);
        this.view.getTxtPlaca().setEnabled(true);
        
        this.view.getBtnAdicionar().setBorderPainted(false);
        this.view.getBtnAtualizar().setBorderPainted(false);
        this.view.getBtnRemover().setBorderPainted(false);
    }
    
}
