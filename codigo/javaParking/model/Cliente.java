/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.model;

import java.util.List;

/**
 *
 * @author Leandro Alencar
 */
public class Cliente {
    private String nome;
    private String identificador;
    private List<Veiculo> veiculos;
    
    public Cliente(String nome, String identificador){
        if(validarCliente(nome, identificador)){
            this.nome = nome;
            this.identificador = identificador;
        }else{
            throw new RuntimeException();
        }
    }
        
    public void addVeiculo(Veiculo veiculo){
        this.veiculos.add(veiculo);
        Xumlabs.addVeiculo(veiculo);
    }
        
    public void delVeiculo(Veiculo veiculo){
        for(int i = 0; i < this.veiculos.size(); i++){
            if(this.veiculos.get(i).getPlaca().equals(veiculo.getPlaca())){
                this.veiculos.remove(i);
                Xumlabs.delVeiculo(veiculo);
            }
        }
    }
    
    private boolean validarCliente(String nome, String identificador){  
        
        Cliente cliente = new Cliente(nome, identificador);
        
        List<Cliente> clientes = Xumlabs.listaClientes();        
        
        for(int i = 0; i < clientes.size(); i++){
            if(clientes.get(i).equals(cliente)){
                return false;
            }
        }
        
        return true;
    }
}
