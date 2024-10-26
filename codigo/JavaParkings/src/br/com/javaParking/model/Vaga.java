/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.model;

public abstract class Vaga {
    
    private String parque;
    private String identificador;
    private boolean ocupada;
    private double precoBase = 4.0; // preço de 4 reais a cada 15 minutos

    public Vaga(String parque, String identificador, boolean ocupada) {
        this.parque = parque;
        this.identificador = identificador;
        this.ocupada = ocupada;
    }
    
    // MÉTODOS PÚBLICOS GET E SET
    public String getIdentificador() {
        return this.identificador;
    }
    
    public String getParque() {
        return this.parque;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    
    public void ocuparVaga() {
        this.ocupada = true;
    }
    
    public void desocuparVaga() {
        this.ocupada = false;
    }
    
    // MÉTODOS ESPECIAIS    
    public abstract double calcularPreco(long minutosTotais);

    protected double aplicarLimite(double precoTotal) {
        return Math.min(precoTotal, Parque.VALORDEDIARIAMAXIMA);
    }
    
}
