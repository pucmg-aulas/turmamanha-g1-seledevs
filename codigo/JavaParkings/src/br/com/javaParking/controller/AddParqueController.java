/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.controller;

import br.com.javaParking.dao.ParqueDao;
import br.com.javaParking.model.ParqueModel;
import br.com.javaParking.view.parque.ParqueView;
import javax.swing.JOptionPane;

/**
 *
 * @author viniciusgomesrodrigues
 */

public class AddParqueController {
    
    private ParqueView view;
    
    public AddParqueController(ParqueView view) {
        this.view = view;
    }
    
    public void AddParque() {
        try {
            String nomeParque = view.getTxtnomeParque().getText();
            int numeroVagas = Integer.parseInt(view.getTxtnumeroVagas().getText());
            int vagasPorFileira = Integer.parseInt(view.getTxtVagasPorFileira().getText());
            double valorPorTempo = 4.0; // Exemplo fixo, ajuste conforme sua lógica
            int intervaloDeCobrancaMinutos = 15; // Exemplo fixo, ajuste conforme sua lógica
            double valorDeDiariaMaxima = 50.0; // Exemplo fixo, ajuste conforme sua lógica

            // Gere um ID de forma adequada para o novo parque
            int id = gerarId(); // Utilize sua lógica de geração de ID

            // Cria uma nova instância do modelo usando o construtor existente
            ParqueModel parque = new ParqueModel(id, nomeParque, numeroVagas, vagasPorFileira, valorPorTempo, intervaloDeCobrancaMinutos, valorDeDiariaMaxima);

            // Grava o parque no arquivo usando o DAO
            if (ParqueDao.gravar(parque)) {
                // Atualiza a tabela da interface com o novo parque
                view.addParqueToTable(parque.getId(), parque.getNomeParque(), numeroVagas, vagasPorFileira);

                JOptionPane.showMessageDialog(view, "<html> <strong>Parque " + nomeParque + " salvo com sucesso! </strong> </html>");
                
                // Limpa os campos após adicionar o parque
                limparTela();
            } else {
                JOptionPane.showMessageDialog(view, "Erro ao salvar o parque!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Por favor, insira valores válidos para Número de Vagas e Vagas por Fileira.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int gerarId() {
        // Lógica para gerar um ID único para o novo parque
        return (int) (Math.random() * 1000); // Exemplo simples, altere conforme necessário
    }

    private void limparTela() {
        view.getTxtnomeParque().setText("");
        view.getTxtnumeroVagas().setText("");
        view.getTxtVagasPorFileira().setText("");
    }
}

