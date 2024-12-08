package br.com.javaParking.model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;

public class Ocupacao implements Serializable{
    
    /**
     * Atributos
     */
    private int id;
    private Cliente cliente;
    private Veiculo veiculo;
    private Vaga vaga;
    private Parque parque;
    private LocalTime horaEntrada;
    private LocalTime horaSaida;

    /**
     * Construtores
     */    
    public Ocupacao(Cliente cliente, Veiculo veiculo, Vaga vaga, LocalTime horaEntrada, Parque parque) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.vaga = vaga;
        this.vaga.ocuparVaga();
        this.horaEntrada = horaEntrada; 
        this.parque = parque;
    }
    
    public Ocupacao(int id, LocalTime horaEntrada) {
        this.id = id;
        this.horaEntrada = horaEntrada;
    }
    
    public int getId() {
        return id;
    }    

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Parque getParque() {
        return parque;
    }

    public void setParque(Parque parque) {
        this.parque = parque;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSaida() {
        return horaSaida;
    }

    /**
     * Metodos de acesso 
     */
    public void setHoraSaida(LocalTime horaSaida) {
        this.horaSaida = horaSaida;
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
