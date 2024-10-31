package br.com.javaParking.model;

import java.time.Duration;
import java.time.LocalTime;

public class OcupacaoModel {
    
    /**
     * Atributos
     */
    private int id;
    private ClienteModel cliente;
    private VeiculoModel veiculo;
    private VagaModel vaga;
    private LocalTime horaEntrada;
    private LocalTime horaSaida;

    /**
     * Construtores
     */    
    public OcupacaoModel(ClienteModel cliente, VeiculoModel veiculo, VagaModel vaga, LocalTime horaEntrada) {
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
    
    public ClienteModel getCliente(){
        return this.cliente;
    }
    
    public VeiculoModel getVeiculo(){
        return this.veiculo;
    }
    
    public VagaModel getVaga(){
        return this.vaga;
    }
    
    public LocalTime getEntrada(){
        return this.horaEntrada;
    }
    
    /**
     * Metodos de ação 
     */ 
    public void desocupar(VagaModel vaga) {
        vaga.desocuparVaga();
    }    
    
    public double custoOcupacao(VagaModel vaga, LocalTime saida) {       

        // Calcula a diferença total entre hora de entrada e saída em minutos
        Duration duracao = Duration.between(this.horaEntrada, saida);
        long minutosTotais = duracao.toMinutes();

        return vaga.calcularPreco(minutosTotais);
    }
    
}
