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
            this.nome = nome;
            this.identificador = identificador;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public String getIdentificador(){
        return this.identificador;
    }
    
    public String getId(){
        return (this.nome + this.identificador);
    }
    
    public List<Veiculo> listaVeiculos(){
        return this.veiculos;
    }
        
    public void addVeiculo(Veiculo veiculo){
        this.veiculos.add(veiculo);
    }
        
    public void delVeiculo(Veiculo veiculo){
        for(int i = 0; i < this.veiculos.size(); i++){
            if(this.veiculos.get(i).getPlaca().equals(veiculo.getPlaca())){
                this.veiculos.remove(i);
            }
        }
    }
    
    private boolean validarCliente(String nome, String identificador){  
        
        List<Cliente> clientes = Xumlabs.listaClientes();        
        
        for(int i = 0; i < clientes.size(); i++){
            if(clientes.get(i).equals(this)){
                return false;
            }
        }
        
        return true;
    }
}
