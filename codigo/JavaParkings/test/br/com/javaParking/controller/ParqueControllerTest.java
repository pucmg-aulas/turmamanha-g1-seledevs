/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.com.javaParking.controller;

import br.com.javaParking.dao.ParqueDAO;
import br.com.javaParking.dao.VagaDao;
import br.com.javaParking.model.Parque;
import br.com.javaParking.view.parque.ParqueView;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Islayder
 */
public class ParqueControllerTest {

    public static void main(String[] args) {
        ParqueView view = new ParqueView();
        ParqueController controller = new ParqueController();

        testAdicionarParque(controller, view);

        testAlterarParque(controller, view);

        testExcluirParque(controller, view);

        testPesquisarParque(controller, view);
    }

    private static void testAdicionarParque(ParqueController controller, ParqueView view) {
        view.getTxtNomeParque().setText("Parque Teste");
        view.getTxtNumeroVagas().setText("50");
        view.getTxtVagasPorFileira().setText("5");

        controller.adicionarParque();

        JTable tabela = view.getTbParques();
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        assert model.getRowCount() > 0 : "Erro: Parque não foi adicionado corretamente.";
        System.out.println("Teste de adição de parque realizado com sucesso!");
    }

    private static void testAlterarParque(ParqueController controller, ParqueView view) {
        if (view.getTbParques().getRowCount() > 0) {
            view.getTbParques().setRowSelectionInterval(0, 0);
            view.getTxtNomeParque().setText("Parque Teste Alterado");
            view.getTxtNumeroVagas().setText("60");
            view.getTxtVagasPorFileira().setText("6");

            controller.alterarParque();

            JTable tabela = view.getTbParques();
            DefaultTableModel model = (DefaultTableModel) tabela.getModel();
            assert model.getValueAt(0, 0).equals("Parque Teste Alterado") : "Erro: Parque não foi alterado corretamente.";
            System.out.println("Teste de alteração de parque realizado com sucesso!");
        } else {
            System.out.println("Nenhum parque para alterar.");
        }
    }

    private static void testExcluirParque(ParqueController controller, ParqueView view) {
        if (view.getTbParques().getRowCount() > 0) {
            view.getTbParques().setRowSelectionInterval(0, 0);

            controller.excluirParque();

            JTable tabela = view.getTbParques();
            DefaultTableModel model = (DefaultTableModel) tabela.getModel();
            assert model.getRowCount() == 0 : "Erro: Parque não foi excluído corretamente.";
            System.out.println("Teste de exclusão de parque realizado com sucesso!");
        } else {
            System.out.println("Nenhum parque para excluir.");
        }
    }

    private static void testPesquisarParque(ParqueController controller, ParqueView view) {
        view.getTxtPesquisarParque().setText("Parque Teste");

        controller.pesquisarParque();

        JTable tabela = view.getTbParques();
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        assert model.getRowCount() > 0 : "Erro: Pesquisa não retornou resultados.";
        System.out.println("Teste de pesquisa de parque realizado com sucesso!");
    }
}
