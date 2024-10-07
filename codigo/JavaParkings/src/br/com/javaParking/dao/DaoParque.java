/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.dao;

import br.com.javaParking.model.Parque;
import br.com.javaParking.model.Parque;
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

public class DaoParque{
    
    public final static String CAMINHOPARQUE;
    
    static {
        CAMINHOPARQUE = Util.CAMINHOPADRAO + "parques.txt";
    }
    
    public static boolean gravar(Parque parque) {

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
                w.write(parque.getIdentificador()+"&"+parque.getNumeroVagas()+"&"+ parque.getVagasPorFileira()+"&"+"\n");
                w.write("§\n");
            }
            
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

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
                    
                    parques.add(new Parque(identificador, numeroVagas, vagasPorFileira));
                    
                    parqueAtual.setLength(0);
                }
            }

        } catch (Exception e) {
            System.out.println("Erro durante a leitura do registro: " + e.getMessage());
        }

        return parques;
    }
}
