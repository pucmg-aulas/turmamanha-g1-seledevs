package br.com.javaParking.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Veiculo implements Serializable{

    private String placa;
    private String cpfCliente;

    public Veiculo(String placa, String cpfCliente) {
        this.placa = placa;
        this.cpfCliente = cpfCliente;
    }
    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }
    
    
}
