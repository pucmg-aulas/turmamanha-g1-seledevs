package br.com.javaParking.dao;

import br.com.javaParking.model.ParqueModel;
import br.com.javaParking.util.Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ParqueDao {
    
    public final static String CAMINHOPARQUE;
    
    static {
        CAMINHOPARQUE = Util.CAMINHOPADRAO + "parques.txt";
    }
    
    public static boolean gravar(ParqueModel parque) {
        File registro = new File(CAMINHOPARQUE);

        try {
            File dir = registro.getParentFile();
            if (dir != null && !dir.exists()) {
                dir.mkdirs(); 
            }

            if (!registro.exists()) {
                registro.createNewFile(); 
            }
                 
            try (BufferedWriter w = new BufferedWriter(new FileWriter(registro, true))) {
                // Grava todos os atributos necessários, incluindo nomeParque
                w.write(parque.getId() + "&" + parque.getNomeParque() + "&" +
                        parque.getNumeroVagas() + "&" + 
                        parque.getVagasPorFileira() + "\n");
                w.write("§\n");
            }
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static List<ParqueModel> listar() {
        List<ParqueModel> lista = new ArrayList<>();
        File registro = new File(CAMINHOPARQUE);
        
        try (BufferedReader r = new BufferedReader(new FileReader(registro))) {
            String linha;
            while ((linha = r.readLine()) != null) {
                if (linha.equals("§")) continue; // Ignora separador
                String[] dados = linha.split("&");
                
                // Certifique-se de que o array tenha o número correto de elementos
                if (dados.length >= 4) {
                    int id = Integer.parseInt(dados[0]);
                    String nomeParque = dados[1];  // Usando nomeParque aqui
                    int numeroVagas = Integer.parseInt(dados[2]);
                    int vagasPorFileira = Integer.parseInt(dados[3]);
                    
                    // Cria o modelo e adiciona à lista
                    ParqueModel parque = new ParqueModel(id, nomeParque, numeroVagas, vagasPorFileira, 0.0, 0, 0.0); // Ajuste os parâmetros conforme necessário
                    parque.setNomeParque(nomeParque); // Definindo o nome do parque
                    lista.add(parque);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    /*
    
    [CORRIGIR]
    
    public static List<Parque> listar() {
        String identificador;
        int numeroVagas;
        int vagasPorFileira;
        
        File registro = new File(CAMINHOPARQUE);        
        List<Parque> parques = new ArrayList<Parque>();

        try (BufferedReader r = new BufferedReader(new FileReader(registro))) {
            String linha;
            StringBuilder parqueAtual = new StringBuilder();

            while ((linha = r.readLine()) != null) {
                parqueAtual.append(linha).append("\n");

                if (linha.equals("§")) {
                    
                    identificador = parqueAtual.toString().split("&")[0].replace("\n", "");
                    numeroVagas = Integer.parseInt(parqueAtual.toString().split("&")[1].replace("\n", ""));
                    vagasPorFileira = Integer.parseInt(parqueAtual.toString().split("&")[2].replace("\n", ""));
                    
                    parques.add(new Parque(Integer.parseInt(identificador), numeroVagas, vagasPorFileira));
                    
                    parqueAtual.setLength(0);
                }
            }

        } catch (Exception e) {
            System.out.println("Erro durante a leitura do registro: " + e.getMessage());
        }

        return parques;
    }
    
    */
}
