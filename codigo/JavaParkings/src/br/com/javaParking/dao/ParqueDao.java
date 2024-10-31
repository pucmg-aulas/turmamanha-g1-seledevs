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

public class ParqueDAO extends AbstractDAO implements Serializable{
    
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
        try{
            this.parques.add(parque);
            grava();
            return true;
        }catch(Exception e){
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

    public Parque buscarPorNome(String nome) {
        for (Parque parque : parques) {
            if (parque.getNomeParque().equals(nome)) {
                return parque;
            }
        }
        return null;
    }
    
    /* Nos parametros temos dois: 
            veiculoExistente -> Em termos de codigo ele seria o novo Veiculo/Objeto que vc vai colocar no lugar de um antigo 
                                (Observação: o novo objeto tem que ter um campo em comum com o antigo)
            placaAnterior -> Seria o campo em comum ultilizado para localizar e substituir
    */
    public boolean altera(Parque parqueAlterado, int parqueIdentificador) {

        try {
            if (parqueAlterado.getId() == parqueIdentificador) {
                // Lista temporaria
                ArrayList<Parque> listaTemp = new ArrayList<Parque>();

                // Loop para criar lista temporaria
                for (Iterator<Parque> it = parques.iterator(); it.hasNext();) {
                    Parque parque = it.next();
                    if (parqueAlterado.getId() != parqueIdentificador) {
                        listaTemp.add(parque);
                    } else {
                        listaTemp.add(parqueAlterado);
                    }
                }

                // Zerar lista 
                parques.removeAll(parques);
                
                // Preencher lista com novos dados
                parques.addAll(listaTemp);
                
                // Sobreescrever dados antigos
                grava();

                return true;
            }else{                
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
