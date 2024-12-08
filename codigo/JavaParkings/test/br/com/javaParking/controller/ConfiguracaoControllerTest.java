/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.com.javaParking.controller;

import br.com.javaParking.dao.ConfiguracaoDAO;
import br.com.javaParking.model.Configuracao;
import br.com.javaParking.view.xulambs.ConfiguracaoView;
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


class ConfiguracaoControllerTest {

    private ConfiguracaoController controller;
    private ConfiguracaoView view;

    @BeforeEach
    void setUp() {
        // Instancia o controller e a view
        controller = new ConfiguracaoController();
        view = controller.getView();

        // Configura valores iniciais na view diretamente
        view.getTxtValorMaximoDiaria().setText("50.00");
        view.getTxtPeriodoDeTempo().setText("4.00");
        view.getTxtPorcentagemVIP().setText("20.00");
        view.getTxtPorcentagemPCD().setText("5.00");
        view.getTxtPorcentagemIdoso().setText("10.00");
        view.getTxtIntervaloEmMinutos().setText("15");
    }

    @Test
    void testMontarConfiguracao() {
        // Simula o clique no botão de salvar
        view.getBtnSalvar().doClick();

        // Recupera a configuração montada pelo método interno
        Configuracao configSalva = ConfiguracaoDAO.configuracao();
        assertNotNull(configSalva, "A configuração deve ser salva no banco de dados");

        assertEquals(50.00, configSalva.getValorMaximoDiaria(), "O valor máximo da diária deve ser 50.00");
        assertEquals(4.00, configSalva.getValorPeriodoPorTempo(), "O valor por período deve ser 4.00");
        assertEquals(20.00, configSalva.getPorcentagemMinimaVIP(), "A porcentagem VIP deve ser 20.00");
        assertEquals(5.00, configSalva.getPorcentagemMinimaPCD(), "A porcentagem PCD deve ser 5.00");
        assertEquals(10.00, configSalva.getPorcentagemMinimaIdosos(), "A porcentagem de idosos deve ser 10.00");
        assertEquals(15, configSalva.getIntervaloCobrancaMinutos(), "O intervalo de cobrança deve ser 15 minutos");
    }

    @AfterEach
    void tearDown() {
        // Limpa o banco de dados após cada teste
        ConfiguracaoDAO.criarTabela();
    }
}