package br.com.javaParking.dao;

import br.com.javaParking.model.ParqueModel;
import br.com.javaParking.model.VagaModel;
import br.com.javaParking.model.VagaModel;
import br.com.javaParking.model.tiposVaga.ComumModel;
import br.com.javaParking.model.tiposVaga.IdosoModel;
import br.com.javaParking.model.tiposVaga.PcdModel;
import br.com.javaParking.model.tiposVaga.VipModel;
import br.com.javaParking.util.Util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class VagaDao {

    public final static String CAMINHOVAGA;

    static {
        CAMINHOVAGA = Util.CAMINHOPADRAO + "vagas.txt";
    }

    public static boolean gravar(VagaModel vaga) {

        File registro = new File(CAMINHOVAGA);

        try {

            File dir = registro.getParentFile();
            if (dir != null && !dir.exists()) {
                dir.mkdirs();
            }

            if (!registro.exists()) {
                registro.createNewFile();
            }

            try (BufferedWriter w = new BufferedWriter(new FileWriter(registro, true))) {
                if (vaga instanceof IdosoModel) {
                    w.write(vaga.getParque() + "&" + vaga.getIdentificador() + "&" + vaga.isOcupada() + "&" + "Idoso" + "&" + "\n");
                } else if (vaga instanceof PcdModel) {
                    w.write(vaga.getParque() + "&" + vaga.getIdentificador() + "&" + vaga.isOcupada() + "&" + "Pcd" + "&" + "\n");
                } else if (vaga instanceof VipModel) {
                    w.write(vaga.getParque() + "&" + vaga.getIdentificador() + "&" + vaga.isOcupada() + "&" + "Vip" + "&" + "\n");
                } else if (vaga instanceof ComumModel) {
                    w.write(vaga.getParque() + "&" + vaga.getIdentificador() + "&" + vaga.isOcupada() + "&" + "Comum" + "&" + "\n");
                }

                w.write("§\n");
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<VagaModel> listar(ParqueModel parque) {
        String identificador;
        String ocupada;
        String tipo;

        File registro = new File(CAMINHOVAGA);
        List<VagaModel> vagas = new ArrayList<VagaModel>();

        try (BufferedReader r = new BufferedReader(new FileReader(registro))) {
            String linha;
            StringBuilder vagaAtual = new StringBuilder();
            VagaModel vaga = null;

            while ((linha = r.readLine()) != null) {
                vagaAtual.append(linha).append("\n");

                if (linha.equals("§")) {

                    identificador = vagaAtual.toString().split("&")[1].replace("\n", "");
                    ocupada = vagaAtual.toString().split("&")[2].replace("\n", "");
                    tipo = vagaAtual.toString().split("&")[3].replace("\n", "");

                    if (String.valueOf(parque.getId()).equals(vagaAtual.toString().split("&")[0].replace("\n", ""))) {

                        if (tipo.equals("Idoso")) {
                            vaga = new IdosoModel(parque, identificador,Boolean.parseBoolean(ocupada));
                        } else if (tipo.equals("Pcd")) {
                            vaga = new PcdModel(parque, identificador,Boolean.parseBoolean(ocupada));
                        } else if (tipo.equals("Vip")) {
                            vaga = new VipModel(parque, identificador,Boolean.parseBoolean(ocupada));
                        } else if (tipo.equals("Comum")) {
                            vaga = new ComumModel(parque, identificador,Boolean.parseBoolean(ocupada));
                        }
                        
                        vagas.add(vaga); 
                        
                    }

                    vagaAtual.setLength(0);
                }
            }

        } catch (Exception e) {
            System.out.println("Erro durante a leitura do registro: " + e.getMessage());
        }

        return vagas;
    }
}
