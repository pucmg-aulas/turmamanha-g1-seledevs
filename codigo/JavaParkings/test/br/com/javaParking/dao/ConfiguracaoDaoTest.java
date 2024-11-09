/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.com.javaParking.dao;

import br.com.javaParking.model.Configuracao;
import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Islayder Jackson
 */

public class ConfiguracaoDaoTest {
    
    private ConfiguracaoDAO configuracaoDao;
    private static final String CAMINHOCONFIGURACAO = ConfiguracaoDAO.CAMINHOCONFIGURACAO;
    
    @BeforeEach
    public void setUp() {
        configuracaoDao = new ConfiguracaoDAO();
    }
    
    @AfterEach
    public void tearDown() {
        // Limpa o arquivo de configuração após cada teste
        File arquivoConfiguracao = new File(CAMINHOCONFIGURACAO);
        if (arquivoConfiguracao.exists()) {
            arquivoConfiguracao.delete();
        }
    }

    @Test
    public void testSalvarConfiguracao() {
        System.out.println("salvarConfiguracao");
        Configuracao configuracao = new Configuracao(10.0, 15.0, 20.0, 30, 50.0);
        configuracaoDao.salvarConfiguracao(configuracao);
        
        // Verifica se o arquivo foi criado
        File arquivoConfiguracao = new File(CAMINHOCONFIGURACAO);
        assertTrue(arquivoConfiguracao.exists(), "O arquivo de configuração não foi criado.");

        // Carrega a configuração e verifica se os valores estão corretos
        Configuracao configuracaoCarregada = configuracaoDao.carregarConfiguracao();
        assertNotNull(configuracaoCarregada, "A configuração carregada não pode ser nula.");
        assertEquals(configuracao.getPorcentagemMinimaIdosos(), configuracaoCarregada.getPorcentagemMinimaIdosos());
        assertEquals(configuracao.getPorcentagemMinimaPCD(), configuracaoCarregada.getPorcentagemMinimaPCD());
        assertEquals(configuracao.getPorcentagemMinimaVIP(), configuracaoCarregada.getPorcentagemMinimaVIP());
        assertEquals(configuracao.getIntervaloCobrancaMinutos(), configuracaoCarregada.getIntervaloCobrancaMinutos());
        assertEquals(configuracao.getValorMaximoDiaria(), configuracaoCarregada.getValorMaximoDiaria());
    }

    @Test
    public void testCarregarConfiguracao() {
        System.out.println("carregarConfiguracao");
        
        // Tentar carregar a configuração sem salvar nada deve retornar nulo
        Configuracao configuracaoCarregada = configuracaoDao.carregarConfiguracao();
        assertNull(configuracaoCarregada, "A configuração carregada deve ser nula se não houver arquivo existente.");
        
        // Salvar uma configuração e depois carregar
        Configuracao configuracao = new Configuracao(10.0, 15.0, 20.0, 30, 50.0);
        configuracaoDao.salvarConfiguracao(configuracao);
        
        // Carregar a configuração e verificar se os valores estão corretos
        Configuracao configuracaoResult = configuracaoDao.carregarConfiguracao();
        assertNotNull(configuracaoResult, "A configuração carregada não pode ser nula.");
        assertEquals(configuracao.getPorcentagemMinimaIdosos(), configuracaoResult.getPorcentagemMinimaIdosos());
        assertEquals(configuracao.getPorcentagemMinimaPCD(), configuracaoResult.getPorcentagemMinimaPCD());
        assertEquals(configuracao.getPorcentagemMinimaVIP(), configuracaoResult.getPorcentagemMinimaVIP());
        assertEquals(configuracao.getIntervaloCobrancaMinutos(), configuracaoResult.getIntervaloCobrancaMinutos());
        assertEquals(configuracao.getValorMaximoDiaria(), configuracaoResult.getValorMaximoDiaria());
    }
}
