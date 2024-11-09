/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.com.javaParking.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Islayder Jackson
 */

public class AbstractDAOTest {

    private ConexaoDAO abstractDAO;
    private final String filePath = "test.dat"; // Caminho para o arquivo de teste
    private List<String> testList;

    @BeforeEach
    public void setUp() {
        abstractDAO = new ConexaoDAO();
        testList = new ArrayList<>();
        testList.add("Item 1");
        testList.add("Item 2");
        testList.add("Item 3");
    }

    @AfterEach
    public void tearDown() {
        // Remove o arquivo após os testes
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testGravaELeitura() {
        System.out.println("testGravaELeitura");
        
        // Grava a lista no arquivo
        abstractDAO.grava(filePath, testList);
        
        // Lê a lista de volta do arquivo
        List<String> resultList = abstractDAO.leitura(filePath);
        
        // Verifica se a lista lida é igual à lista original
        assertEquals(testList.size(), resultList.size());
        assertTrue(resultList.containsAll(testList));
        assertTrue(testList.containsAll(resultList));
    }

    @Test
    public void testLeituraComArquivoInexistente() {
        System.out.println("testLeituraComArquivoInexistente");
        
        // Tenta ler um arquivo que não existe
        List<String> resultList = abstractDAO.leitura("inexistente.dat");
        
        // Verifica se a lista retornada está vazia
        assertTrue(resultList.isEmpty());
    }
}
