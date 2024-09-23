/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.model;

/**
 *
 * @author Leandro Alencar
 */
public class Ocupacao {
    
    private Cliente cliente;
    private Veiculo veiculo;
    private String identificadorVaga;
    private Parque parque;
    private byte horaEntrada;
    private byte horaSaida;
    private boolean ocupada;
    
    public Ocupacao(Cliente cliente, Veiculo veiculo, String identificadorVaga, Parque parque, byte horaEntrada){
       
        this.cliente = cliente;
        this.veiculo = veiculo;
        
        this.parque = parque;
        
        if(!parque.vagaExiste(identificadorVaga))
            throw new RuntimeException();
        
        this.identificadorVaga = identificadorVaga;
        
        this.horaEntrada = horaEntrada;
        this.ocupada = true;
               
    }
    
    public void desocuparVaga(Ocupacao ocupacao){
        
        
    }
    
    
    
}
