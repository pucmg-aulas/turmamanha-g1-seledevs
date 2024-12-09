package br.com.javaParking.model;

import java.util.Date;

public class Arrecadacao {
    
    private int id;
    private String fk_cpf_cliente;
    private String fk_nome_parque;
    private float valor_arrecadado;
    private Date data_arrecadacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFk_cpf_cliente() {
        return fk_cpf_cliente;
    }

    public void setFk_cpf_cliente(String fk_cpf_cliente) {
        this.fk_cpf_cliente = fk_cpf_cliente;
    }
    public float getValor_arrecadado() {
        return valor_arrecadado;
    }

    public void setValor_arrecadado(float valor_arrecadado) {
        this.valor_arrecadado = valor_arrecadado;
    }

    public Date getData_arrecadacao() {
        return data_arrecadacao;
    }

    public void setData_arrecadacao(Date data_arrecadacao) {
        this.data_arrecadacao = data_arrecadacao;
    }

    public String getFk_nome_parque() {
        return fk_nome_parque;
    }

    public void setFk_nome_parque(String fk_nome_parque) {
        this.fk_nome_parque = fk_nome_parque;
    }
    
    
    
}
