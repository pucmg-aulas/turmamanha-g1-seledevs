package br.com.javaParking.model;

import java.time.Duration;
import java.time.LocalTime;

public class Ocupacao {
    
    /**
     * Atributos
     */
    private int id;
    private Cliente cliente;
    private Veiculo veiculo;
    private Vaga vaga;
    private LocalTime horaEntrada;
    private LocalTime horaSaida;

    /**
     * Construtores
     */    
    public Ocupacao(Cliente cliente, Veiculo veiculo, Vaga vaga, LocalTime horaEntrada) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.vaga = vaga;
        this.vaga.ocuparVaga();
        this.horaEntrada = horaEntrada;   
    }
    
    /**
     * Metodos de acesso 
     */
    public int getId(){
        return this.id;
    }
    
    public Cliente getCliente(){
        return this.cliente;
    }
    
    public Veiculo getVeiculo(){
        return this.veiculo;
    }
    
    public Vaga getVaga(){
        return this.vaga;
    }
    
    public LocalTime getEntrada(){
        return this.horaEntrada;
    }
    
    /**
     * Metodos de ação 
     */ 
    public void desocupar(Vaga vaga) {
        vaga.desocuparVaga();
    }    
    
    public double custoOcupacao(Vaga vaga, LocalTime saida) {       

        // Calcula a diferença total entre hora de entrada e saída em minutos
        Duration duracao = Duration.between(this.horaEntrada, saida);
        long minutosTotais = duracao.toMinutes();

        return vaga.calcularPreco(minutosTotais);
    }
    
}
