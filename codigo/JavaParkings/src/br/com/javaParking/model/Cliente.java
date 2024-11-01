package br.com.javaParking.model;

import java.io.Serializable;
import java.util.List;

public class Cliente implements Serializable{

    /**
     * Atributos
     */
    private String nome;
    private String id;
    private List<Veiculo> veiculos;

    /**
     * Construtores
     */
    public Cliente(String nome, String identificador) {
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

    public List<Veiculo> listaVeiculos() {
        return this.veiculos;
    }

    /**
     * Metodos de ação 
     */
    public void addVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
    }

    public void delVeiculo(Veiculo veiculo) {
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
    
    
     @Override
    public String toString() {
        return id + "%" + nome;
    }
}
