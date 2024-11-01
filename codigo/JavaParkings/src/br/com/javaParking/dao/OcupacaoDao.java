package br.com.javaParking.dao;

import br.com.javaParking.model.Ocupacao;
import br.com.javaParking.util.Util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class OcupacaoDao {

    public final static String CAMINHOOCUPACAO;

    static {
        CAMINHOOCUPACAO = Util.CAMINHOPADRAO + "ocupacaos.txt";
    }

    public static boolean gravar(Ocupacao ocupacao) {

        File registro = new File(CAMINHOOCUPACAO);

        try {

            File dir = registro.getParentFile();
            if (dir != null && !dir.exists()) {
                dir.mkdirs();
            }

            if (!registro.exists()) {
                registro.createNewFile();
            }

            try (BufferedWriter w = new BufferedWriter(new FileWriter(registro, true))) {
                
                w.write(ocupacao.getVaga() + "&" + ocupacao.getCliente() + "&" + ocupacao.getVeiculo() + "&" + ocupacao.getEntrada() + "&" + "\n");
                w.write("§\n");
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
    
    [CORRIGIR]
    
    public static List<Ocupacao> listar() {
        String vaga;
        String cliente;
        String veiculo;
        String LocalDateTime;

        File registro = new File(CAMINHOOCUPACAO);
        List<Ocupacao> ocupacaos = new ArrayList<Ocupacao>();

        try (BufferedReader r = new BufferedReader(new FileReader(registro))) {
            String linha;
            StringBuilder ocupacaoAtual = new StringBuilder();
            Ocupacao ocupacao = null;

            while ((linha = r.readLine()) != null) {
                ocupacaoAtual.append(linha).append("\n");

                if (linha.equals("§")) {

                    vaga = ocupacaoAtual.toString().split("&")[0].replace("\n", "");
                    cliente = ocupacaoAtual.toString().split("&")[1].replace("\n", "");
                    veiculo = ocupacaoAtual.toString().split("&")[2].replace("\n", "");
                    LocalDateTime = ocupacaoAtual.toString().split("&")[3].replace("\n", "");


                        ocupacao = new Ocupacao(cliente, veiculo, vaga);
                       
                        
                        ocupacaos.add(ocupacao); 
                        

                    ocupacaoAtual.setLength(0);
                }
            }

        } catch (Exception e) {
            System.out.println("Erro durante a leitura do registro: " + e.getMessage());
        }

        return ocupacaos;
    }*/
}
