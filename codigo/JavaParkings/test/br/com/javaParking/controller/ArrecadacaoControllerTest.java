/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.com.javaParking.controller;

import javax.swing.JOptionPane;
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
public class ArrecadacaoControllerTest {

    public static void main(String[] args) {
        ArrecadacaoController controller = new ArrecadacaoController();

        if (controller != null) {
            System.out.println("ArrecadacaoController inicializada com sucesso.");

            if (controller.getView().getCbAno() != null
                    && controller.getView().getCbMes() != null
                    && controller.getView().getCbParque() != null
                    && controller.getView().getTbArrecadacoes() != null) {
                System.out.println("Componentes da interface inicializados corretamente.");
            } else {
                System.out.println("Erro ao inicializar componentes da interface.");
            }

            controller.getView().getMenSair().doClick();
            System.out.println("Ação 'Sair' acionada com sucesso.");
        } else {
            System.out.println("Falha na inicialização do ArrecadacaoController.");
        }

        // Aguardando a interação do usuário para testes manuais adicionais
        JOptionPane.showMessageDialog(null, "Teste de ArrecadacaoController concluído. Verifique a interface.");
    }
}
