package br.com.javaParking.model;

import java.util.ArrayList;
import java.util.List;

public class VeiculoModel {

    /**
     * Atributos
     */
    private String placa;

    /**
     * Construtores
     */
    public VeiculoModel(String placa) {
        this.placa = placa;
    }

    /**
     * Metodos de acesso
     */
    public String getPlaca() {
        return this.placa;
    }

    /**
     * Metodos de validação
     */
    public boolean validarPlaca() {
        /* [IMPLEMENTAÇÃO PENDENTE] -> Validar se a placa ja foi registrada antes usando os dados persistidos nos arquivos,
         * caso ja exista retorne falso, caso contrario retorne verdadeiro.
         */
        return false;
    }
}