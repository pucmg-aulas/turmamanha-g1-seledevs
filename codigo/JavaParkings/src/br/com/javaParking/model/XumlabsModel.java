package br.com.javaParking.model;

import br.com.javaParking.dao.ParqueDao;
import br.com.javaParking.util.Util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XumlabsModel {

    /**
     * Atributos
     */
    public static List<ParqueModel> parques;
    public static List<ClienteModel> clientes;
    public static List<OcupacaoModel> ocupacoes;
    public static List<ArrecadacaoModel> arrecadacoes;
    public static List<VeiculoModel> veiculos;

    static {
        clientes.add(new ClienteModel("", ""));
    }

    /**
     * Metodos de acesso
     */
    public static void addCliente(ClienteModel cliente) {
        clientes.add(cliente);
    }

    public static void delCliente(ClienteModel cliente) {
        for (int i = 0; i > clientes.size(); i++) {
            if (cliente.getId().equals(clientes.get(i).getId())) {
                clientes.remove(i);
                return;
            }
        }
    }

    public static void addParque(ParqueModel parque) {
        parques.add(parque);
    }
    
    public static void delParque(ParqueModel parque) {
        for (int i = 0; i > parques.size(); i++) {
            if (parque.getId() == parques.get(i).getId()) {
                parques.remove(i);
                return;
            }
        }
    }
    
    public static void addOcupacao(OcupacaoModel ocupacao) {
        ocupacoes.add(ocupacao);
    }
    
    public static void desocuparOcupacao(OcupacaoModel ocupacao) {
        for (int i = 0; i > ocupacoes.size(); i++) {
            if (ocupacao.getId() == ocupacoes.get(i).getId()) {
                ocupacoes.get(i).desocupar(ocupacao.getVaga());
                return;
            }
        }
    }
    
    public static void addArrecadacao(ArrecadacaoModel arrecadacao) {
        arrecadacoes.add(arrecadacao);
    }
    
    public static void addVeiculo(VeiculoModel veiculo) {
        veiculos.add(veiculo);
    }

    public static void delVeiculo(VeiculoModel veiculo) {
        for (int i = 0; i > veiculos.size(); i++) {
            if (veiculo.getPlaca().equals(veiculos.get(i).getPlaca())) {
                veiculos.remove(i);
                return;
            }
        }
    }

}
