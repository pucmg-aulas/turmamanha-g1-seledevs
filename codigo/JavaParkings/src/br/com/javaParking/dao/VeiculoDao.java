package br.com.javaParking.dao;

import br.com.javaParking.model.Veiculo;
import br.com.javaParking.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VeiculoDao extends AbstractDAO implements Serializable {

    private List<Veiculo> veiculos;
    // Atributo da própria classe, estático, para implementar o Singleton
    private static VeiculoDao instance;

    //Endereço do arquivo serializado que contém a coleção de veiculos
    private final String localArquivo = Util.CAMINHOPADRAO + "Veiculos.txt";

    //Construtor privado, pois não podemos permitir mais de uma instância desta classe
    //que controla a coleção de carros do sistema (Singleton)
    private VeiculoDao() {
        this.veiculos = new ArrayList<>();
        carregaVeiculos();
    }

    //Método para recuperar a única instância de veiculos
    public static VeiculoDao getInstance() {
        if (instance == null) {
            instance = new VeiculoDao();
        }
        return instance;
    }

    public void addVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
        grava();
    }

    private void carregaVeiculos() {
        this.veiculos = super.leitura(localArquivo);
    }

    private void grava() {
        super.grava(localArquivo, veiculos);
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void excluirVeiculo(Veiculo veiculo) {
        veiculos.remove(veiculo);
        grava();
    }

    public Veiculo buscarCarroPorPlaca(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }
    
    /* Nos parametros temos dois: 
            veiculoExistente -> Em termos de codigo ele seria o novo Veiculo/Objeto que vc vai colocar no lugar de um antigo 
                                (Observação: o novo objeto tem que ter um campo em comum com o antigo)
            placaAnterior -> Seria o campo em comum ultilizado para localizar e substituir
    */
    public boolean altera(Veiculo veiculoExistente, String placaAnterior) {

        try {
            if (veiculoExistente.getPlaca().equals(placaAnterior)) {
                // Lista temporaria
                ArrayList<Veiculo> listaTemp = new ArrayList<Veiculo>();

                // Loop para criar lista temporaria
                for (Iterator<Veiculo> it = veiculos.iterator(); it.hasNext();) {
                    Veiculo veiculo = it.next();
                    if (!veiculo.getPlaca().equals(placaAnterior)) {
                        listaTemp.add(veiculo);
                    } else {
                        listaTemp.add(veiculoExistente);
                    }
                }

                // Zerar lista 
                veiculos.removeAll(veiculos);
                
                // Preencher lista com novos dados
                veiculos.addAll(listaTemp);
                
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