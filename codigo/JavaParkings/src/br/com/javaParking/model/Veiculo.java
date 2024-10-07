/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro Alencar
 */
public class Veiculo {
    private String placa;
    
    public Veiculo(String placa){  
        this.placa = placa;
    }
    
    public String getPlaca(){
        return this.placa;
    }
}
