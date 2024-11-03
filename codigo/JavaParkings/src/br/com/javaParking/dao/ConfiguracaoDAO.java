package br.com.javaParking.dao;

import br.com.javaParking.model.ConfiguracaoModel;
import br.com.javaParking.util.Util;

import java.io.*;

public class ConfiguracaoDAO {
    public static final String CAMINHOCONFIGURACAO = Util.CAMINHOPADRAO + "configuracao.txt";

    public void salvarConfiguracao(ConfiguracaoModel configuracao) {
        File registro = new File(CAMINHOCONFIGURACAO);

        try {
            File dir = registro.getParentFile();
            if (dir != null && !dir.exists()) {
                dir.mkdirs();
            }

            if (!registro.exists()) {
                registro.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(registro))) {
                writer.write(configuracao.getPorcentagemMinimaIdosos() + "&" +
                             configuracao.getPorcentagemMinimaPCD() + "&" +
                             configuracao.getPorcentagemMinimaVIP() + "&" +
                             configuracao.getIntervaloCobrancaMinutos() + "&" +
                             configuracao.getValorMaximoDiaria());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar configurações: " + e.getMessage());
        }
    }

    public ConfiguracaoModel carregarConfiguracao() {
        File registro = new File(CAMINHOCONFIGURACAO);
        ConfiguracaoModel configuracao = null;

        if (registro.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(registro))) {
                String linha = reader.readLine();
                if (linha != null) {
                    String[] dados = linha.split("&");
                    double porcentagemIdosos = Double.parseDouble(dados[0]);
                    double porcentagemPCD = Double.parseDouble(dados[1]);
                    double porcentagemVIP = Double.parseDouble(dados[2]);
                    int intervaloCobranca = Integer.parseInt(dados[3]);
                    double valorMaximoDiaria = Double.parseDouble(dados[4]);

                    configuracao = new ConfiguracaoModel(porcentagemIdosos, porcentagemPCD,
                                                         porcentagemVIP, intervaloCobranca,
                                                         valorMaximoDiaria);
                }
            } catch (IOException | NumberFormatException e) {
                System.err.println("Erro ao carregar configurações: " + e.getMessage());
            }
        } else {
            System.out.println("Arquivo de configurações não encontrado, usando valores padrão.");
        }

        return configuracao;
    }
}
