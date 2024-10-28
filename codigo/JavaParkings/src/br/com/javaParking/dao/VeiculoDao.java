package br.com.javaParking.dao;

import br.com.javaParking.model.ClienteModel;
import br.com.javaParking.model.VeiculoModel;
import br.com.javaParking.util.Util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDao{
    
    public final static String CAMINHOVEICULO;
    
    static {
        CAMINHOVEICULO = Util.CAMINHOPADRAO + "veiculos.txt";
    }
    
    public static boolean gravar(VeiculoModel veiculo, String id) {

        File registro = new File(CAMINHOVEICULO);

        try {

            File dir = registro.getParentFile();
            if (dir != null && !dir.exists()) {
                dir.mkdirs(); 
            }

            if (!registro.exists()) {
                registro.createNewFile(); 
            }

            try (BufferedWriter w = new BufferedWriter(new FileWriter(registro, true))) {
                w.write(veiculo.getPlaca()+ "&"+ id +"&"+ "\n");
                w.write("§\n");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<VeiculoModel> listar() {
        String placa;
        
        File registro = new File(CAMINHOVEICULO);        
        List<VeiculoModel> veiculos = new ArrayList<VeiculoModel>();

        try (BufferedReader r = new BufferedReader(new FileReader(registro))) {
            String linha;
            StringBuilder veiculoAtual = new StringBuilder();

            while ((linha = r.readLine()) != null) {
                veiculoAtual.append(linha).append("\n");

                if (linha.equals("§")) {
                    
                    placa = veiculoAtual.toString().split("&")[0].replace("\n", "");
                    veiculos.add(new VeiculoModel(placa));                 
                    
                    
                    veiculoAtual.setLength(0);
                }
            }

        } catch (Exception e) {
            System.out.println("Erro durante a leitura do registro: " + e.getMessage());
        }

        return veiculos;
    }
    
     public static List<VeiculoModel> listar(ClienteModel cliente) {
        String placa;
        
        File registro = new File(CAMINHOVEICULO);        
        List<VeiculoModel> veiculos = new ArrayList<VeiculoModel>();

        try (BufferedReader r = new BufferedReader(new FileReader(registro))) {
            String linha;
            StringBuilder veiculoAtual = new StringBuilder();

            while ((linha = r.readLine()) != null) {
                veiculoAtual.append(linha).append("\n");

                if (linha.equals("§")) {
                    
                    placa = veiculoAtual.toString().split("&")[0].replace("\n", "");
                    
                    if(cliente.getId().equals(veiculoAtual.toString().split("&")[1].replace("\n", ""))){
                        veiculos.add(new VeiculoModel(placa));
                    }
                    
                    veiculoAtual.setLength(0);
                }
            }

        } catch (Exception e) {
            System.out.println("Erro durante a leitura do registro: " + e.getMessage());
        }

        return veiculos;
    }
    
}
