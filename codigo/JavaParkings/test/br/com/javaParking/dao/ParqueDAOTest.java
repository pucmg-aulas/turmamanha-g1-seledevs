/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.com.javaParking.dao;

import br.com.javaParking.model.Parque;
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
class ParqueDAOTest {

    @BeforeEach
    void setUp() {
        // Cria a tabela tbparques antes de cada teste
        String resultado = ParqueDAO.criarTabela();
        assertEquals("Tabela tbparques criada com sucesso", resultado, "A criação da tabela deve ser bem-sucedida");
    }

    @Test
    void testAddParque() {
        Parque parque = new Parque(1, "Parque Teste", 50, 5);
        assertTrue(ParqueDAO.addParque(parque), "O parque deve ser adicionado com sucesso");
        
        Parque parqueBuscado = ParqueDAO.buscarPorNome("Parque Teste");
        assertNotNull(parqueBuscado, "O parque deve ser encontrado após ser adicionado");
        assertEquals("Parque Teste", parqueBuscado.getNomeParque(), "O nome do parque deve coincidir");
    }

    @Test
    void testAlterarParque() {
        Parque parque = new Parque(1, "Parque Teste", 50, 5);
        ParqueDAO.addParque(parque);
        
        parque.setNomeParque("Parque Alterado");
        assertTrue(ParqueDAO.alterarParque(parque), "O parque deve ser alterado com sucesso");

        Parque parqueAlterado = ParqueDAO.buscarPorNome("Parque Alterado");
        assertNotNull(parqueAlterado, "O parque alterado deve ser encontrado");
        assertEquals("Parque Alterado", parqueAlterado.getNomeParque(), "O nome do parque alterado deve coincidir");
    }

    @Test
    void testExcluirParque() {
        Parque parque = new Parque(1, "Parque Teste", 50, 5);
        ParqueDAO.addParque(parque);

        assertTrue(ParqueDAO.excluirParque(parque.getId()), "O parque deve ser excluído com sucesso");

        Parque parqueExcluido = ParqueDAO.buscarPorNome("Parque Teste");
        assertNull(parqueExcluido, "O parque excluído não deve ser encontrado");
    }

    @Test
    void testBuscarPorNome() {
        Parque parque = new Parque(1, "Parque Teste", 50, 5);
        ParqueDAO.addParque(parque);

        Parque parqueBuscado = ParqueDAO.buscarPorNome("Parque Teste");
        assertNotNull(parqueBuscado, "O parque deve ser encontrado pelo nome");
        assertEquals("Parque Teste", parqueBuscado.getNomeParque(), "O nome do parque deve coincidir");
    }

    @Test
    void testBuscarPorNomeParcial() {
        Parque parque1 = new Parque(1, "Parque Teste", 50, 5);
        Parque parque2 = new Parque(2, "Parque Central", 100, 10);
        ParqueDAO.addParque(parque1);
        ParqueDAO.addParque(parque2);

        List<Parque> parquesEncontrados = ParqueDAO.buscarPorNomeParcial("Parque");
        assertNotNull(parquesEncontrados, "A lista de parques encontrados não deve ser nula");
        assertTrue(parquesEncontrados.size() >= 2, "A lista de parques encontrados deve conter pelo menos dois parques");
    }
}