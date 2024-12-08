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

class ConfiguracaoTest {

    @Test
    void testConstrutorValido() {
        Configuracao config = new Configuracao(10.0, 20.0, 30.0, 15, 50.0, 4.0);
        assertEquals(10.0, config.getPorcentagemMinimaIdosos());
        assertEquals(20.0, config.getPorcentagemMinimaPCD());
        assertEquals(30.0, config.getPorcentagemMinimaVIP());
        assertEquals(15, config.getIntervaloCobrancaMinutos());
        assertEquals(50.0, config.getValorMaximoDiaria());
        assertEquals(4.0, config.getValorPeriodoPorTempo());
    }

    @Test
    void testConstrutorInvalido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Configuracao(40.0, 40.0, 30.0, 15, 50.0, 4.0);
        });
        assertEquals("A soma das porcentagens não pode ultrapassar 100%", exception.getMessage());
    }

    @Test
    void testSetPorcentagemMinimaIdosos() {
        Configuracao config = new Configuracao(10.0, 20.0, 30.0, 15, 50.0, 4.0);
        config.setPorcentagemMinimaIdosos(15.0);
        assertEquals(15.0, config.getPorcentagemMinimaIdosos());
    }

    @Test
    void testSetPorcentagemMinimaIdososInvalido() {
        Configuracao config = new Configuracao(10.0, 20.0, 30.0, 15, 50.0, 4.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            config.setPorcentagemMinimaIdosos(50.0);
        });
        assertEquals("A soma das porcentagens não pode ultrapassar 100%", exception.getMessage());
    }

    @Test
    void testSetPorcentagemMinimaPCD() {
        Configuracao config = new Configuracao(10.0, 20.0, 30.0, 15, 50.0, 4.0);
        config.setPorcentagemMinimaPCD(25.0);
        assertEquals(25.0, config.getPorcentagemMinimaPCD());
    }

    @Test
    void testSetPorcentagemMinimaPCDInvalido() {
        Configuracao config = new Configuracao(10.0, 20.0, 30.0, 15, 50.0, 4.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            config.setPorcentagemMinimaPCD(60.0);
        });
        assertEquals("A soma das porcentagens não pode ultrapassar 100%", exception.getMessage());
    }

    @Test
    void testSetIntervaloCobrancaMinutos() {
        Configuracao config = new Configuracao(10.0, 20.0, 30.0, 15, 50.0, 4.0);
        config.setIntervaloCobrancaMinutos(20);
        assertEquals(20, config.getIntervaloCobrancaMinutos());
    }

    @Test
    void testSetValorMaximoDiaria() {
        Configuracao config = new Configuracao(10.0, 20.0, 30.0, 15, 50.0, 4.0);
        config.setValorMaximoDiaria(60.0);
        assertEquals(60.0, config.getValorMaximoDiaria());
    }

    @Test
    void testSetValorPeriodoPorTempo() {
        Configuracao config = new Configuracao(10.0, 20.0, 30.0, 15, 50.0, 4.0);
        config.setValorPeriodoPorTempo(5.0);
        assertEquals(5.0, config.getValorPeriodoPorTempo());
    }
}