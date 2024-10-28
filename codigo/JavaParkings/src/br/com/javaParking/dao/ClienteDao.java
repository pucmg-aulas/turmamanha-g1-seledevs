package br.com.javaParking.dao;

import br.com.javaParking.model.ClienteModel;
import br.com.javaParking.util.Util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao{
    
    public final static String CAMINHOCLIENTE;
    
    static {
        CAMINHOCLIENTE = Util.CAMINHOPADRAO + "clientes.txt";
    }
    
    public static boolean gravar(ClienteModel cliente) {

        File registro = new File(CAMINHOCLIENTE);

        try {

            File dir = registro.getParentFile();
            if (dir != null && !dir.exists()) {
                dir.mkdirs(); 
            }

            if (!registro.exists()) {
                registro.createNewFile(); 
            }

            try (BufferedWriter w = new BufferedWriter(new FileWriter(registro, true))) {
                w.write(cliente.getNome()+ "&"+ cliente.getId()+ "&" + "\n");
                w.write("§\n");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<ClienteModel> listar() {
        String nome;
        String identificador;
        
        File registro = new File(CAMINHOCLIENTE);        
        List<ClienteModel> clientes = new ArrayList<ClienteModel>();

        try (BufferedReader r = new BufferedReader(new FileReader(registro))) {
            String linha;
            StringBuilder clienteAtual = new StringBuilder();

            while ((linha = r.readLine()) != null) {
                clienteAtual.append(linha).append("\n");

                if (linha.equals("§")) {
                    
                    nome = clienteAtual.toString().split("&")[0].replace("\n", "");
                    identificador = clienteAtual.toString().split("&")[1].replace("\n", "");
                    
                    clientes.add(new ClienteModel(nome, identificador));
                    
                    clienteAtual.setLength(0);
                }
            }

        } catch (Exception e) {
            System.out.println("Erro durante a leitura do registro: " + e.getMessage());
        }

        return clientes;
    }
}
