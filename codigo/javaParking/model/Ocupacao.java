/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javaParking.model;

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
    
    public void desocuparVaga(byte horaSaida) {
        if (!ocupada) {
            throw new RuntimeException("A vaga já está desocupada.");
        }

        this.horaSaida = horaSaida;
        ocupada = false;

        // Calcular o tempo de permanência em minutos
        double tempoEstacionado = calcularTempoEstacionado(horaEntrada, horaSaida);

        // Obter a vaga correspondente e calcular o preço
        Vaga vaga = parque.getVagaById(identificadorVaga); // Método para obter a vaga pela ID
        double precoTotal = vaga.calcularPreco(tempoEstacionado);
        
        // Imprimir o preço total
        System.out.println("Preço total: R$" + precoTotal);

        // Desocupar a vaga
        vaga.desocupar();
    }
    
    private double calcularTempoEstacionado(byte horaEntrada, byte horaSaida) {
        // Calcula o tempo total em minutos
        int tempoEmMinutos = (horaSaida - horaEntrada);
        return Math.max(0, tempoEmMinutos); // Garante que não seja negativo
    }

}
