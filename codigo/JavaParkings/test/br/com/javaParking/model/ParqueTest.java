/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.com.javaParking.model;

import br.com.javaParking.dao.ConfiguracaoDAO;
import br.com.javaParking.model.tiposVaga.Comum;
import br.com.javaParking.model.tiposVaga.Idoso;
import br.com.javaParking.model.tiposVaga.PCD;
import br.com.javaParking.model.tiposVaga.VIP;
import br.com.javaParking.util.Util;
import java.util.List;
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
class ParqueTest {

    private Parque parque;

    @BeforeEach
    void setUp() {
        ConfiguracaoDAO.salvar(new Configuracao(50.00, 4.00, 20.00, (int) 5.00, 10.00, 15)); 
        parque = new Parque(1, "Parque Central", 10, 5);
    }

    @Test
    void testConstrutorParque() {
        assertNotNull(parque, "O objeto Parque deve ser criado");
        assertEquals(10, parque.getNumeroVagas(), "O número de vagas deve ser 10");
        assertEquals(5, parque.getVagasPorFileira(), "O número de vagas por fileira deve ser 5");
    }

    @Test
    void testValidarNumeroDeVagas() {
        assertTrue(parque.validarNumeroDeVagas(), "O número de vagas deve ser válido");

        parque.setNumeroVagas(Util.alfabeto().size() * parque.getVagasPorFileira() + 1);
        assertFalse(parque.validarNumeroDeVagas(), "O número de vagas deve ser inválido");
    }

    @Test
    void testMontarVagas() {
        parque.montarVagas();

        List<Vaga> vagas = parque.listarVagas();
        assertNotNull(vagas, "A lista de vagas não deve ser nula");
        assertEquals(10, vagas.size(), "A quantidade de vagas deve ser igual ao número de vagas especificado");

        assertTrue(vagas.get(0) instanceof Comum || vagas.get(0) instanceof Idoso || vagas.get(0) instanceof PCD || vagas.get(0) instanceof VIP,
            "A primeira vaga deve ser de um tipo válido");
    }

    @Test
    void testListarVagas() {
        parque.montarVagas();

        List<Vaga> vagas = parque.listarVagas();
        assertFalse(vagas.isEmpty(), "A lista de vagas não deve estar vazia após montar as vagas");
        assertEquals(10, vagas.size(), "A quantidade de vagas listadas deve ser 10");
    }
}