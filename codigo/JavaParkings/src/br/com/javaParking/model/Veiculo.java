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
public class Veiculo {
    private String placa;
    
    public Veiculo(String placa){        
        if(validarPlaca(placa)){
            this.placa = placa;
        }else{
            throw new RuntimeException();
        }
    }
    
    public String getPlaca(){
        return this.placa;
    }
    
    private boolean validarPlaca(String placa){
        
        List<Veiculo> veiculos = Xumlabs.listaVeiculos();        
        
        for(int i = 0; i < veiculos.size(); i++){
            if(veiculos.get(i).getPlaca().equals(placa)){
                return false;
            }
        }
        
        return true;
    }
}
