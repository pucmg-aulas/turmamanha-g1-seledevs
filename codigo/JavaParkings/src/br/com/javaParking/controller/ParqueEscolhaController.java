/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.controller;

import br.com.javaParking.dao.ParqueDAO;
import br.com.javaParking.model.Parque;
import br.com.javaParking.view.parque.ParqueEscolhaView;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author leand
 */
public class ParqueEscolhaController {
    
    private ParqueEscolhaView view;
    private Parque parqueSelecionado;

    public ParqueEscolhaController() {
        
        this.view = new ParqueEscolhaView();
        
        carregarTabela();

        this.view.getTbParques().getSelectionModel().addListSelectionListener(e -> {
            int linha = this.view.getTbParques().getSelectedRow();
            if (linha != -1) {
                String nomeParque = (String) this.view.getTbParques().getValueAt(linha, 0);
                parqueSelecionado = ParqueDAO.buscarPorNome(nomeParque);

                new PDAController(parqueSelecionado);
                this.view.dispose();
            }
        });

        this.view.setVisible(true);
    }
    
    public void carregarTabela() {
        List<Parque> parques = ParqueDAO.listarParques();
        DefaultTableModel tm = new DefaultTableModel(new Object[]{"Nome", "Nº Vagas"}, 0);

        for (Parque parque : parques) {
            tm.addRow(new Object[]{parque.getNomeParque(), parque.getNumeroVagas()});
        }

        this.view.getTbParques().setModel(tm);
    }
    
}
