package br.com.javaParking.controller;

import br.com.javaParking.dao.ParqueDAO;
import br.com.javaParking.model.Cliente;
import br.com.javaParking.model.Parque;
import br.com.javaParking.view.parque.ParqueView;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ParqueController {

    private ParqueView view;
    private Parque parqueSelecionado;

    public ParqueController() {
        this.view = new ParqueView();
        createMode();

        carregarTabela();

        this.view.getBtnAdicionar().addActionListener(e -> adicionarParque());
        this.view.getBtnAlterar().addActionListener(e -> alterarParque());
        this.view.getBtnExcluir().addActionListener(e -> excluirParque());

        this.view.getTbParques().getSelectionModel().addListSelectionListener(e -> {
            int linha = this.view.getTbParques().getSelectedRow();
            if (linha != -1) {
                String nomeParque = (String) this.view.getTbParques().getValueAt(linha, 0);
                parqueSelecionado = (Parque) ParqueDAO.buscarPorNomeParcial(nomeParque);

                if (parqueSelecionado != null) {
                    this.view.getTxtNomeParque().setText(parqueSelecionado.getNomeParque());
                    this.view.getTxtNumeroVagas().setText(String.valueOf(parqueSelecionado.getNumeroVagas()));
                    this.view.getTxtVagasPorFileira().setText(String.valueOf(parqueSelecionado.getVagasPorFileira()));
                    habilitarModoEdicao();
                }
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

    public void adicionarParque() {
        try {
            String nome = this.view.getTxtNomeParque().getText();
            int numeroVagas = Integer.parseInt(this.view.getTxtNumeroVagas().getText());
            int vagasPorFileira = Integer.parseInt(this.view.getTxtVagasPorFileira().getText());

            Parque novoParque = new Parque(0, nome, numeroVagas, vagasPorFileira);

            if (ParqueDAO.addParque(novoParque)) {
                JOptionPane.showMessageDialog(view, "Parque adicionado com sucesso!");
                carregarTabela();
                limparCampos();
                habilitarModoEdicao();
                
            } else {
                JOptionPane.showMessageDialog(view, "Erro ao adicionar o parque.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Insira valores válidos para número de vagas e vagas por fileira.", "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            carregarTabela();
        }
        
    }

    public void alterarParque() {
        
        if (parqueSelecionado == null) {
            JOptionPane.showMessageDialog(view, "Selecione um parque para alterar.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            parqueSelecionado.setNomeParque(this.view.getTxtNomeParque().getText());
            parqueSelecionado.setNumeroVagas(Integer.parseInt(this.view.getTxtNumeroVagas().getText()));
            parqueSelecionado.setVagasPorFileira(Integer.parseInt(this.view.getTxtVagasPorFileira().getText()));

            if (ParqueDAO.alterarParque(parqueSelecionado)) {
                JOptionPane.showMessageDialog(view, "Parque alterado com sucesso!");
                carregarTabela();
                habilitarModoEdicao();
                limparCampos();
     
            } else {
                JOptionPane.showMessageDialog(view, "Erro ao alterar o parque.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Insira valores válidos para número de vagas e vagas por fileira.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void excluirParque() {
        
        if (parqueSelecionado == null) {
            JOptionPane.showMessageDialog(view, "Selecione um parque para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(view, "Deseja realmente excluir o parque " + parqueSelecionado.getNomeParque() + "?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            if (ParqueDAO.excluirParque(parqueSelecionado.getId())) {
                JOptionPane.showMessageDialog(view, "Parque excluído com sucesso!");
                habilitarModoEdicao();
                limparCampos();
                carregarTabela();
            } else {
                JOptionPane.showMessageDialog(view, "Erro ao excluir o parque.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void pesquisarParque() {
        carregarTabela();
        habilitarModoEdicao();
        String nomeParque = view.getTxtPesquisarParque().getText().trim();

        // Se o campo estiver vazio, carrega todos os parques novamente
        if (nomeParque.isEmpty()) {
            carregarTabela();
            return;
        }

        // Pesquisa o parque pelo nome ou caracteres inseridos
        List<Parque> parquesEncontrados = ParqueDAO.buscarPorNomeParcial(nomeParque);

        // Atualizar a tabela com os resultados encontrados
        DefaultTableModel tm = new DefaultTableModel(new Object[]{"Nome", "NºVagas"}, 0);
        for (Parque parque : parquesEncontrados) {
            tm.addRow(new Object[]{parque.getNomeParque(), parque.getNumeroVagas()});
        }
        view.getTbParques().setModel(tm);
    }
    
    private void limparCampos() {
        this.view.getTxtNomeParque().setText("");
        this.view.getTxtNumeroVagas().setText("");
        this.view.getTxtVagasPorFileira().setText("");
        parqueSelecionado = null;
    }

    private void habilitarModoEdicao() {
        this.view.getBtnAdicionar().setEnabled(false);
        this.view.getBtnAlterar().setEnabled(true);
        this.view.getBtnExcluir().setEnabled(true);
    }
    
    private void createMode(){
        this.view.getBtnAdicionar().setEnabled(true);
        this.view.getBtnAlterar().setEnabled(false);
        this.view.getBtnAtualizar().setEnabled(true);
        this.view.getBtnExcluir().setEnabled(false);
    }
    
    
}
