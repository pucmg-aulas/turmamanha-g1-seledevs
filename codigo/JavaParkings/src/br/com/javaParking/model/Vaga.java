/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.model;

/**
 *
 * @author Leandro Alencar
 */
public abstract class Vaga {
    
    private String parque;
    private String identificador;
    private boolean ocupada;
    private double precoBase = 4.0; // preço de 4 reais a cada 15 minutos

    public Vaga(String parque, String identificador) {
        this.parque = parque;
        this.identificador = identificador;
        this.ocupada = false;
    }
    
    // MÉTODOS PÚBLICOS GET E SET
    public String getIdentificador() {
        return identificador;
    }
    
    public String getParque() {
        return this.parque;
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
    public abstract double calcularPreco(int dias,int minutos);
    
    protected double aplicarLimite(double precoTotal) {
        return Math.min(precoTotal, Parque.VALORDEDIARIAMAXIMA);
    }
    
}
