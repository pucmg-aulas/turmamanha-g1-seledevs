package br.com.javaParking.dao;

import br.com.javaParking.model.Configuracao;
import br.com.javaParking.util.Comunicacao;
import javax.swing.JOptionPane;

public class ConfiguracaoDAO {

    public static String criarTabela() {
        try {
            Comunicacao.setSql("""
			CREATE TABLE IF NOT EXISTS 
                               interno.tbconfiguracao(
                                        id SERIAL,
					valorPorTempo NUMERIC(1000,2),
                                        valorMaximoDiaria NUMERIC(1000,2),
                                        intervaloCobrancaMinutos INT,  
                                        porcentagemPDC NUMERIC(1000,2),
                                        porcentagemIdoso NUMERIC(1000,2),
                                        porcentagemVIP NUMERIC(1000,2),
                                        PRIMARY KEY (id)
                               );
					""");
            Comunicacao.prepararConexcao();
            Comunicacao.executar();
            adicionar();

        } catch (Exception e) {
            return "Erro ao criar tbconfiguracao: " + e;
        }
        return "tbconfiguracao criada com sucesso";
    }

    public static Configuracao configuracao() {
        try {

            Comunicacao.setSql("SELECT * from interno.tbconfiguracao where interno.tbconfiguracao.id = 1;");
            Comunicacao.prepararConexcao();
            Comunicacao.executarQuery();

            while (Comunicacao.getRs().next()) {
                Configuracao x = new Configuracao(
                        Comunicacao.getRs().getDouble("porcentagemIdoso"),
                        Comunicacao.getRs().getDouble("porcentagemPDC"),
                        Comunicacao.getRs().getDouble("porcentagemVIP"),
                        Comunicacao.getRs().getInt("intervaloCobrancaMinutos"),
                        Comunicacao.getRs().getDouble("valorMaximoDiaria"),
                        Comunicacao.getRs().getDouble("valorPorTempo")
                );

                return x;
            }

        } catch (Exception e) {
        }

        return null;

    }

    private static void adicionar() {
        try {
            if(configuracao() == null){
             Comunicacao.setSql("""
				INSERT INTO
                                        interno.tbconfiguracao
                                            (valorPorTempo,
                                             valorMaximoDiaria,
                                             intervaloCobrancaMinutos,  
                                             porcentagemPDC,
                                             porcentagemIdoso,
                                             porcentagemVIP)
					VALUES
                                            (4.00,
                                             50.00,
                                             15,
                                             5,
                                             10,
                                             20);
					""");
            Comunicacao.prepararConexcao();
            Comunicacao.executar();
            }
           

        } catch (Exception e) {
        }
    }

    public static void salvar(Configuracao x) {
        try {
            Comunicacao.setSql(""" 
                               UPDATE
                                   interno.tbconfiguracao
            			SET
            			   valorPorTempo=?,
                                   valorMaximoDiaria=?,
                                   intervaloCobrancaMinutos=?,  
                                   porcentagemPDC=?,
                                   porcentagemIdoso=?,
                                   porcentagemVIP=?
            			WHERE
            			   id=1
                                """);
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setDouble(1, x.getValorPeriodoPorTempo());
            Comunicacao.getPst().setDouble(2, x.getValorMaximoDiaria());
            Comunicacao.getPst().setInt(3, x.getIntervaloCobrancaMinutos());
            Comunicacao.getPst().setDouble(4, x.getPorcentagemMinimaPCD());
            Comunicacao.getPst().setDouble(5, x.getPorcentagemMinimaIdosos());
            Comunicacao.getPst().setDouble(6, x.getPorcentagemMinimaVIP());
            Comunicacao.executar();

        } catch (Exception e) {
        }
    }

}
