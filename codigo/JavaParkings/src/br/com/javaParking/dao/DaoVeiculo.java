/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.dao;

import br.com.javaParking.model.Cliente;
import br.com.javaParking.model.Veiculo;
import br.com.javaParking.util.Util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro Alencar
 */

public class DaoVeiculo{
    
    public final static String CAMINHOVEICULO;
    
    static {
        CAMINHOVEICULO = Util.CAMINHOPADRAO + "veiculos.txt";
    }
    
    public static boolean gravar(Veiculo veiculo, Cliente cliente) {

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
                w.write(veiculo.getPlaca()+ "&"+ cliente.getId() +"&"+ "\n");
                w.write("§\n");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Veiculo> listar() {
        String placa;
        
        File registro = new File(CAMINHOVEICULO);        
        List<Veiculo> veiculos = new ArrayList<Veiculo>();

        try (BufferedReader r = new BufferedReader(new FileReader(registro))) {
            String linha;
            StringBuilder veiculoAtual = new StringBuilder();

            while ((linha = r.readLine()) != null) {
                veiculoAtual.append(linha).append("\n");

                if (linha.equals("§")) {
                    
                    placa = veiculoAtual.toString().split("&")[0].replace("\n", "");
                    veiculos.add(new Veiculo(placa));                 
                    
                    
                    veiculoAtual.setLength(0);
                }
            }

        } catch (Exception e) {
            System.out.println("Erro durante a leitura do registro: " + e.getMessage());
        }

        return veiculos;
    }
    
     public static List<Veiculo> listar(Cliente cliente) {
        String placa;
        
        File registro = new File(CAMINHOVEICULO);        
        List<Veiculo> veiculos = new ArrayList<Veiculo>();

        try (BufferedReader r = new BufferedReader(new FileReader(registro))) {
            String linha;
            StringBuilder veiculoAtual = new StringBuilder();

            while ((linha = r.readLine()) != null) {
                veiculoAtual.append(linha).append("\n");

                if (linha.equals("§")) {
                    
                    placa = veiculoAtual.toString().split("&")[0].replace("\n", "");
                    
                    if(cliente.getId().equals(veiculoAtual.toString().split("&")[1].replace("\n", ""))){
                        veiculos.add(new Veiculo(placa));
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
