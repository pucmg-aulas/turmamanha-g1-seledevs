/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.com.javaParking.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.function.Executable;

/**
 *
 * @author Islayder Jackson
 */

public class ConfiguracaoTest {

    @Test
    public void testConstrutorValido() {
        System.out.println("testConstrutorValido");
        // Testa se o construtor cria uma instância de Configuracao com valores válidos
        Configuracao configuracao = new Configuracao(0.10, 0.10, 0.20, 30, 50.0);
        
        // Verifica se os valores foram definidos corretamente
        assertEquals(0.10, configuracao.getPorcentagemMinimaIdosos());
        assertEquals(0.10, configuracao.getPorcentagemMinimaPCD());
        assertEquals(0.20, configuracao.getPorcentagemMinimaVIP());
        assertEquals(30, configuracao.getIntervaloCobrancaMinutos());
        assertEquals(50.0, configuracao.getValorMaximoDiaria());
    }

    @Test
    public void testConstrutorInvalidoPorcentagemExcedida() {
        System.out.println("testConstrutorInvalidoPorcentagemExcedida");
        // Testa se uma IllegalArgumentException é lançada quando a soma das porcentagens excede 100%
        Exception exception;
        exception = assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new Configuracao(0.10, 0.10, 0.90, 30, 50.0);
            }
        });

        // Mensagem esperada ao lançar a exceção
        String expectedMessage = "A soma das porcentagens não pode ultrapassar 100%";
        String actualMessage = exception.getMessage();

        // Verifica se a mensagem da exceção é a esperada
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testSetPorcentagensValidas() {
        System.out.println("testSetPorcentagensValidas");
        // Testa se as porcentagens podem ser atualizadas para valores válidos
        Configuracao configuracao = new Configuracao(0.10, 0.10, 0.20, 30, 50.0);
        
        configuracao.setPorcentagemMinimaIdosos(0.15);
        configuracao.setPorcentagemMinimaPCD(0.15);
        configuracao.setPorcentagemMinimaVIP(0.20);
        
        // Verifica se os novos valores foram definidos corretamente
        assertEquals(0.15, configuracao.getPorcentagemMinimaIdosos());
        assertEquals(0.15, configuracao.getPorcentagemMinimaPCD());
        assertEquals(0.20, configuracao.getPorcentagemMinimaVIP());
    }

    @Test
    public void testSetPorcentagensInvalida() {
        System.out.println("testSetPorcentagensInvalida");
        // Testa se uma IllegalArgumentException é lançada ao tentar definir porcentagens que excedem 100%
        Configuracao configuracao = new Configuracao(0.10, 0.10, 0.20, 30, 50.0);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            configuracao.setPorcentagemMinimaVIP(0.80); // Soma total vai para 1.0
        });

        // Mensagem esperada ao lançar a exceção
        String expectedMessage = "A soma das porcentagens não pode ultrapassar 100%";
        String actualMessage = exception.getMessage();

        // Verifica se a mensagem da exceção é a esperada
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testSetIntervaloCobrancaMinutos() {
        System.out.println("testSetIntervaloCobrancaMinutos");
        // Testa se o intervalo de cobrança pode ser atualizado corretamente
        Configuracao configuracao = new Configuracao(0.10, 0.10, 0.20, 30, 50.0);
        
        configuracao.setIntervaloCobrancaMinutos(45);
        // Verifica se o novo intervalo foi definido corretamente
        assertEquals(45, configuracao.getIntervaloCobrancaMinutos());
    }

    @Test
    public void testSetValorMaximoDiaria() {
        System.out.println("testSetValorMaximoDiaria");
        // Testa se o valor máximo da diária pode ser atualizado corretamente
        Configuracao configuracao = new Configuracao(0.10, 0.10, 0.20, 30, 50.0);
        
        configuracao.setValorMaximoDiaria(75.0);
        // Verifica se o novo valor máximo foi definido corretamente
        assertEquals(75.0, configuracao.getValorMaximoDiaria());
    }
}
