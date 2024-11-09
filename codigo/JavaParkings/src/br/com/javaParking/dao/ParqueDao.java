package br.com.javaParking.dao;

import br.com.javaParking.model.Parque;
import br.com.javaParking.model.Veiculo;
import br.com.javaParking.util.Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParqueDAO extends ConexaoDAO implements Serializable{
    
    private List<Parque> parques;
    // Atributo da própria classe, estático, para implementar o Singleton
    private static ParqueDAO instance;

    //Endereço do arquivo serializado que contém a coleção de veiculos
    private final String localArquivo = Util.CAMINHOPADRAO + "Parques.dat";

    //Construtor privado, pois não podemos permitir mais de uma instância desta classe
    //que controla a coleção de carros do sistema (Singleton)
    private ParqueDAO() {
        this.parques = new ArrayList<>();
        carregaParques();
    }

    //Método para recuperar a única instância de veiculos
    public static ParqueDAO getInstance() {
        if (instance == null) {
            instance = new ParqueDAO();
        }
        return instance;
    }

    public boolean addParque(Parque parque) {
        try {
            this.parques.add(parque);
            grava();
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    private void carregaParques() {
        this.parques = super.leitura(localArquivo);
    }

    private void grava() {
        super.grava(localArquivo, parques);
    }

    public List<Parque> getParques() {
        return parques;
    }

    public void excluirParque(Parque parque) {
        parques.remove(parque);
        grava();
    }

    public Parque buscarPorNome(String nomeParque) {
        for (Parque parque : parques) {
            if (parque.getNomeParque().equals(nomeParque)) {
                return parque;
            }
        }
        return null;
    }
    
    public Parque buscarPorId(int id) {
        for (Parque parque : parques) {
            if (parque.getId() == id) {
                    return parque;
                }
            }
        return null;
    }
    
    public List<Parque> buscarPorNomeParcial(String nomeParcial) {
        List<Parque> resultado = new ArrayList<>();
            for (Parque parque : parques) { // Supondo que listaDeParques é a lista de todos os parques
                if (parque.getNomeParque().toLowerCase().contains(nomeParcial.toLowerCase())) {
                    resultado.add(parque);
                }
            }
        return resultado;
    }
    
    /* Nos parametros temos dois: 
            veiculoExistente -> Em termos de codigo ele seria o novo Veiculo/Objeto que vc vai colocar no lugar de um antigo 
                                (Observação: o novo objeto tem que ter um campo em comum com o antigo)
            placaAnterior -> Seria o campo em comum ultilizado para localizar e substituir
    */
    public boolean alterarParque(Parque parqueSelecionado, int parqueIdentificador) {
        try {
        // Localiza e substitui o parque com o identificador correto
        for (int i = 0; i < parques.size(); i++) {
            if (parques.get(i).getId() == parqueIdentificador) {
                parques.set(i, parqueSelecionado); // Substitui o parque existente pelo atualizado
                grava(); // Grava a lista atualizada
                return true;
            }
        }
        return false; // Retorna false se o parque com o identificador não foi encontrado
    } catch (Exception e) {
        e.printStackTrace(); // Imprime a pilha de erro para facilitar a depuração
        return false;
    }
}
}
