/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.com.javaParking.dao;

import java.sql.Connection;
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
public class ConexaoDAOTest {

    @Test
    void testConexao() {
        Connection conn = ConexaoDAO.Conexao();
        assertNotNull(conn, "A conexão não deveria ser nula");

        try {
            if (conn != null) {
                assertFalse(conn.isClosed(), "A conexão deveria estar aberta");
                conn.close();
            }
        } catch (Exception e) {
            fail("Erro ao verificar ou fechar a conexão: " + e.getMessage());
        }
    }

    @Test
    void testTabelas() {
        String resultado = ConexaoDAO.Tabelas();
        assertEquals("", resultado, "A criação de tabelas deveria ser concluída sem erros");
    }

    @Test
    void testCriarBanco() {
        Connection conn = ConexaoDAO.Conexao();
        assertNotNull(conn, "A conexão não deveria ser nula");

        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            fail("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}
