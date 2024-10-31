/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.controller;

import br.com.javaParking.dao.ParqueDAO;
import br.com.javaParking.model.Parque;
import br.com.javaParking.view.parque.ParqueView;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author viniciusgomesrodrigues
 */

public class ParqueController {
    
    private ParqueView view;    
    ParqueDAO parques;
    
    public ParqueController() {   
        
        this.parques = ParqueDAO.getInstance();
        this.view = new ParqueView();
        
        this.view.getBtnAdicionar().addActionListener((e) -> {
            AddParque();
        });        
        carregaTabela();
        this.view.setVisible(true);
    }
    
    private void carregaTabela(){
        Object colunas[] = {"Nome", "NºVagas"};
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);
       
        tm.setNumRows(0);
        Iterator<Parque> it = parques.getParques().iterator();
        while (it.hasNext()) {
            Parque x = it.next();
            tm.addRow(new Object[]{x.getNomeParque(), x.getNumeroVagas()});
        }
        view.getTbParques().setModel(tm);
    }
    
    public void AddParque() {
        try {
            carregaTabela();
            String nomeParque = view.getTxtNomeParque().getText();
            int numeroVagas = Integer.parseInt(view.getTxtNumeroVagas().getText());
            int vagasPorFileira = Integer.parseInt(view.getTxtVagasPorFileira().getText());

            // Gere um ID de forma adequada para o novo parque

            // Cria uma nova instância do modelo usando o construtor existente
            Parque parque = new Parque(gerarId(), nomeParque, numeroVagas, vagasPorFileira);
            
            // Grava o parque no arquivo usando o DAO
            if (parques.addParque(parque)) {
                // Atualiza a tabela da interface com o novo parque
                JOptionPane.showMessageDialog(view, "<html> <strong>Parque " + nomeParque + " salvo com sucesso! </strong> </html>");
                
                // Limpa os campos após adicionar o parque
                limparTela();
            } else {
                JOptionPane.showMessageDialog(view, "Erro ao salvar o parque!", "Erro", JOptionPane.ERROR_MESSAGE);
            }            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Por favor, insira valores válidos para Número de Vagas e Vagas por Fileira.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }finally{ // Finally: Responde ao Try, onde independente do erro que acontecer, oq for posto dentro do finally sera executado.
            carregaTabela();
        }
    }

    private int gerarId() {
        // Lógica para gerar um ID único para o novo parque
        return (int) (Math.random() * 1000); // Exemplo simples, altere conforme necessário
    }

    private void limparTela() {
        view.getTxtNomeParque().setText("");
        view.getTxtNumeroVagas().setText("");
        view.getTxtVagasPorFileira().setText("");
    }
}

