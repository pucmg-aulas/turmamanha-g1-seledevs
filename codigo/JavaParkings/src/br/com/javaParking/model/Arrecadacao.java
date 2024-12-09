package br.com.javaParking.model;

import java.util.Date;

public class Arrecadacao {
    
    private int id;
    private String fk_cpf_cliente;
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
    
    
    
}
