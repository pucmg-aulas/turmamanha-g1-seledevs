/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.com.javaParking.dao;

import br.com.javaParking.model.Arrecadacao;
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
public class ArrecadacaoDAOTest {
    
    public ArrecadacaoDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of criarTabela method, of class ArrecadacaoDAO.
     */
    @Test
    public void testCriarTabela() {
        System.out.println("criarTabela");
        String expResult = "";
        String result = ArrecadacaoDAO.criarTabela();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tabela method, of class ArrecadacaoDAO.
     */
    @Test
    public void testTabela() {
        System.out.println("tabela");
        List<Arrecadacao> expResult = null;
        List<Arrecadacao> result = ArrecadacaoDAO.tabela();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addArrecadacao method, of class ArrecadacaoDAO.
     */
    @Test
    public void testAddArrecadacao() {
        System.out.println("addArrecadacao");
        Arrecadacao arrecadacao = null;
        ArrecadacaoDAO.addArrecadacao(arrecadacao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
