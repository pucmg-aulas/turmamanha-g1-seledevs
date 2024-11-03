/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.controller;

import br.com.javaParking.dao.ParqueDao;
import br.com.javaParking.model.Parque;
import br.com.javaParking.view.parque.ParqueView;
import br.com.javaParking.model.Vaga;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author viniciusgomesrodrigues
 */

public class ParqueController {
    
    private ParqueView view;    
    private ParqueDao parques;
    private Parque parqueSelecionado;
    
    public ParqueController() {   
        
        this.parques = ParqueDao.getInstance();
        this.view = new ParqueView();
        carregaTabela();
        
        this.view.getBtnAdicionar().addActionListener((e) -> {
            AddParque();
        }); 
        this.view.getBtnExcluir().addActionListener((e) -> {
            excluirParque();
        });
        
        this.view.getBtnAlterar().addActionListener((e) -> {
            AlterarParque();
        });
        
        // EVENTO PARA CAPTURAR O CLICK DO MOUSE NA LINHA E PEGAR O NOME DO PARQUE SELECIONADO NA TABELA:
        this.view.getTbParques().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = view.getTbParques().getSelectedRow();
                if (linha != -1) {
                    String nomeParque = (String) view.getTbParques().getValueAt(linha, 0);
                    parqueSelecionado = parques.buscarPorNome(nomeParque);

                    if (parqueSelecionado != null) {
                        // Preenche os campos com os dados do parque selecionado
                        view.getTxtNomeParque().setText(parqueSelecionado.getNomeParque());
                        view.getTxtNumeroVagas().setText(String.valueOf(parqueSelecionado.getNumeroVagas()));
                        view.getTxtVagasPorFileira().setText(String.valueOf(parqueSelecionado.getVagasPorFileira()));
                    }
                }
            }
        });
        
        // Evento para capturar o click do mouse na linha e carregar as vagas do parque selecionado
        this.view.getTbParques().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = view.getTbParques().getSelectedRow();
                if (linha != -1) {
                    String nomeParque = (String) view.getTbParques().getValueAt(linha, 0);
                    parqueSelecionado = parques.buscarPorNome(nomeParque);

                    if (parqueSelecionado != null) {
                        // Preenche os campos com os dados do parque selecionado
                        view.getTxtNomeParque().setText(parqueSelecionado.getNomeParque());
                        view.getTxtNumeroVagas().setText(String.valueOf(parqueSelecionado.getNumeroVagas()));
                        view.getTxtVagasPorFileira().setText(String.valueOf(parqueSelecionado.getVagasPorFileira()));

                        // Gera as vagas do parque e exibe na tabela
                        parqueSelecionado.montarVagas(); // Gera as vagas
                        carregarVagasDoParque(parqueSelecionado.listarVagas());
                    }
                }
            }
        });
        
        this.view.getBtnAtualizar().addActionListener((e) -> {
            AtualizarTabela();
        });
        
        // Adiciona o DocumentListener para a pesquisa em tempo real
        this.view.getTxtPesquisarParque().getDocument().addDocumentListener(new DocumentListener() {
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
    
    public void carregarVagasDoParque(List<Vaga> vagas) {
        Object colunas[] = {"ID da Vaga", "Tipo"};
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);

        for (Vaga vaga : vagas) {
            String tipoVaga = vaga.getClass().getSimpleName(); // Obtém o tipo de vaga (IdosoModel, PcdModel, etc.)
            tm.addRow(new Object[]{vaga.getIdentificador(), tipoVaga});
        }

        view.getTbVagasDoParque().setModel(tm); // Define o modelo para a tabela
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

    public int gerarId() {
        int id;
        do {
            id = (int) (Math.random() * 1000); // Gera um ID aleatório
        } while (parques.buscarPorId(id) != null); // Verifica se o ID já existe
        return id;
    }
    
    public void excluirParque() {
        int linha = view.getTbParques().getSelectedRow(); // Obtém a linha selecionada
        if (linha == -1) {
            JOptionPane.showMessageDialog(view, "Por favor, selecione um parque para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nomeParque = (String) view.getTbParques().getValueAt(linha, 0); // Obtém o nome do parque na linha selecionada
        Parque parque = parques.buscarPorNome(nomeParque); // Busca o parque pelo nome

        if (parque != null) {
            int op = JOptionPane.showConfirmDialog(view, "Deseja excluir o parque " + parqueSelecionado.getNomeParque() + "?");
            if (op == JOptionPane.YES_OPTION) {
                parques.excluirParque(parque); // Exclui o parque do DAO
                JOptionPane.showMessageDialog(view, "Parque" + nomeParque + " excluído com sucesso!");
                carregaTabela(); // Atualiza a tabela"
            } else {
                JOptionPane.showMessageDialog(view, "Erro ao excluir o parque.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void AlterarParque () {
    
        if (parqueSelecionado == null) {
            JOptionPane.showMessageDialog(view, "Selecione um parque para alterar.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int op = JOptionPane.showConfirmDialog(view, "Deseja editar o parque " + parqueSelecionado.getNomeParque() + "?");
        if (op == JOptionPane.YES_OPTION) {
            try {
                // Atualiza o objeto Parque com os novos dados
                parqueSelecionado.setNomeParque(view.getTxtNomeParque().getText());
                parqueSelecionado.setNumeroVagas(Integer.parseInt(view.getTxtNumeroVagas().getText()));
                parqueSelecionado.setVagasPorFileira(Integer.parseInt(view.getTxtVagasPorFileira().getText()));

                // Salva as alterações no DAO
                parques.alterarParque(parqueSelecionado, parqueSelecionado.getId());
                
                JOptionPane.showMessageDialog(view, "Parque " + parqueSelecionado.getNomeParque() + " editado com sucesso!");

                // Limpa a seleção e os campos após a alteração
                limparTela();
                parqueSelecionado = null;

                // Atualiza a tabela
                carregaTabela();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, "Por favor, insira valores válidos para Número de Vagas e Vagas por Fileira.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
   
    public void AtualizarTabela() {
        carregaTabela();
    }
    
    public void pesquisarParque() {
        String nomeParque = view.getTxtPesquisarParque().getText().trim();

        // Se o campo estiver vazio, carrega todos os parques novamente
        if (nomeParque.isEmpty()) {
            carregaTabela();
            return;
        }

        // Pesquisa o parque pelo nome ou caracteres inseridos
        List<Parque> parquesEncontrados = parques.buscarPorNomeParcial(nomeParque);

        // Atualizar a tabela com os resultados encontrados
        DefaultTableModel tm = new DefaultTableModel(new Object[]{"Nome", "NºVagas"}, 0);
        for (Parque parque : parquesEncontrados) {
            tm.addRow(new Object[]{parque.getNomeParque(), parque.getNumeroVagas()});
        }
    view.getTbParques().setModel(tm);
    }
    
    public void limparTela() {
        view.getTxtNomeParque().setText("");
        view.getTxtNumeroVagas().setText("");
        view.getTxtVagasPorFileira().setText("");
    }
}

