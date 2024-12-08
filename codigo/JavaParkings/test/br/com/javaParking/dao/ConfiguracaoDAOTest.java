/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.com.javaParking.dao;

import br.com.javaParking.model.Configuracao;
import br.com.javaParking.util.Comunicacao;
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

class ConfiguracaoDAOTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        // Configura o ambiente antes de todos os testes
        Comunicacao.setSql("DROP TABLE IF EXISTS interno.tbconfiguracao;");
        Comunicacao.executar();
    }

    @BeforeEach
    void setUp() throws Exception {
        // Garante que a tabela está criada antes de cada teste
        ConfiguracaoDAO.criarTabela();
    }

    @Test
    void testCriarTabela() {
        String resultado = ConfiguracaoDAO.criarTabela();
        assertEquals("tbconfiguracao criada com sucesso", resultado, "Deve retornar mensagem de sucesso ao criar tabela");
    }

    @Test
    void testAdicionarConfiguracaoPadrao() {
        Configuracao config = ConfiguracaoDAO.configuracao();
        assertNotNull(config, "A configuração padrão deve ser inserida automaticamente na tabela");

        // Verifica os valores padrões
        assertEquals(4.00, config.getValorPeriodoPorTempo(), "O valor por tempo deve ser 4.00");
        assertEquals(50.00, config.getValorMaximoDiaria(), "O valor máximo da diária deve ser 50.00");
        assertEquals(15, config.getIntervaloCobrancaMinutos(), "O intervalo de cobrança deve ser 15 minutos");
        assertEquals(5.00, config.getPorcentagemMinimaPCD(), "A porcentagem mínima PCD deve ser 5.00");
        assertEquals(10.00, config.getPorcentagemMinimaIdosos(), "A porcentagem mínima para idosos deve ser 10.00");
        assertEquals(20.00, config.getPorcentagemMinimaVIP(), "A porcentagem mínima VIP deve ser 20.00");
    }

    @Test
    void testSalvarConfiguracao() {
        Configuracao novaConfig = new Configuracao(7.00, 55.00, 20, (int) 6.00, 12.00, 25.00);
        ConfiguracaoDAO.salvar(novaConfig);

        Configuracao configSalva = ConfiguracaoDAO.configuracao();
        assertNotNull(configSalva, "A configuração salva deve existir na tabela");

        // Verifica os valores atualizados
        assertEquals(7.00, configSalva.getValorPeriodoPorTempo(), "O valor por tempo deve ser atualizado para 7.00");
        assertEquals(55.00, configSalva.getValorMaximoDiaria(), "O valor máximo da diária deve ser atualizado para 55.00");
        assertEquals(20, configSalva.getIntervaloCobrancaMinutos(), "O intervalo de cobrança deve ser atualizado para 20 minutos");
        assertEquals(6.00, configSalva.getPorcentagemMinimaPCD(), "A porcentagem mínima PCD deve ser atualizada para 6.00");
        assertEquals(12.00, configSalva.getPorcentagemMinimaIdosos(), "A porcentagem mínima para idosos deve ser atualizada para 12.00");
        assertEquals(25.00, configSalva.getPorcentagemMinimaVIP(), "A porcentagem mínima VIP deve ser atualizada para 25.00");
    }

    @AfterEach
    void tearDown() throws Exception {
        // Limpa a tabela após cada teste
        Comunicacao.setSql("DELETE FROM interno.tbconfiguracao;");
        Comunicacao.executar();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        // Remove a tabela do banco após todos os testes
        Comunicacao.setSql("DROP TABLE IF EXISTS interno.tbconfiguracao;");
        Comunicacao.executar();
    }
}