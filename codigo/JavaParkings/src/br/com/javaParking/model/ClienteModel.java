package br.com.javaParking.model;

import java.util.List;

public class ClienteModel {

    /**
     * Atributos
     */
    private String nome;
    private String id;
    private List<VeiculoModel> veiculos;

    /**
     * Construtores
     */
    public ClienteModel(String nome, String identificador) {
        this.nome = nome;
        this.id = identificador;
    }

    /**
     * Metodos de acesso
     */
    public String getNome() {
        return this.nome;
    }

    public String getId() {
        return this.id;
    }

    public List<VeiculoModel> listaVeiculos() {
        return this.veiculos;
    }

    /**
     * Metodos de ação 
     */
    public void addVeiculo(VeiculoModel veiculo) {
        this.veiculos.add(veiculo);
    }

    public void delVeiculo(VeiculoModel veiculo) {
        for (int i = 0; i < this.veiculos.size(); i++) {
            if (this.veiculos.get(i).getPlaca().equals(veiculo.getPlaca())) {
                this.veiculos.remove(i);
            }
        }
    }

    /**
     * Metodos de validação
     */
    private boolean validarCliente(String id) {
        /* [IMPLEMENTAÇÃO PENDENTE] -> Validar se o CPF ja foi registrada antes usando os dados persistidos nos arquivos,
         * caso ja exista retorne falso, caso contrario retorne verdadeiro.
         */
        return false;
    }
}
