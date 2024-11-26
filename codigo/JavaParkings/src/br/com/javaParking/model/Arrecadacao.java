package br.com.javaParking.model;

public class Arrecadacao {
    
    private int id;
    private int fk_id_cliente;
    private float valor_arrecadado;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_id_cliente() {
        return fk_id_cliente;
    }

    public void setFk_id_cliente(int fk_id_cliente) {
        this.fk_id_cliente = fk_id_cliente;
    }

    public float getValor_arrecadado() {
        return valor_arrecadado;
    }

    public void setValor_arrecadado(float valor_arrecadado) {
        this.valor_arrecadado = valor_arrecadado;
    }
    
}
