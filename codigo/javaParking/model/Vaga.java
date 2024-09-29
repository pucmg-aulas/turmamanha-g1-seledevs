/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.model;

/**
 *
 * @author Leandro Alencar
 */
public class Vaga {
    
    private String identificador;
    private boolean ocupada;
    private double precoBase = 4.0; // preço de 4 reais a cada 15 minutos

    public Vaga(String identificador) {
        this.identificador = identificador;
        this.ocupada = false;
    }
    
    // MÉTODOS PÚBLICOS GET E SET
    public String getIdentificador() {
        return identificador;
    }

    public boolean getOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    
    public void ocuparVaga(boolean ocupada) {
        this.ocupada = true;
    }
    
    public void desocuparVaga(boolean ocupada) {
        this.ocupada = false;
    }
    
    // MÉTODOS ESPECIAIS
    
    public double calcularPreco(double tempoEstacionado) {
        double precoTotal = (tempoEstacionado / 15) * precoBase; // Preço por 15 minutos
        return aplicarLimite(precoTotal);
    }
    
    private double aplicarLimite(double precoTotal) {
        return Math.min(precoTotal, 50.0); // Limite de 50 reais 
    }
    
}
