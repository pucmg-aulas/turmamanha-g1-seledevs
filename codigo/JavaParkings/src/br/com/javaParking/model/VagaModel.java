package br.com.javaParking.model;

public abstract class VagaModel {
    
    /**
     * Atributos
     */
    private ParqueModel parque;
    private String identificador;
    private boolean ocupada;
    private double precoBase = 4.0; // preço de 4 reais a cada 15 minutos

    /**
     * Construtores
     */ 
    public VagaModel(ParqueModel parque, String identificador, boolean ocupada) {
        this.parque = parque;
        this.identificador = identificador;
        this.ocupada = ocupada;
    }
    
    /**
     * Metodos de acesso 
     */
    public String getIdentificador() {
        return this.identificador;
    }
    
    public ParqueModel getParque() {
        return this.parque;
    }

    public boolean isOcupada() {
        return ocupada;
    }
   
    /**
     * Metodos de ação 
     */     
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    
    public void ocuparVaga() {
        this.ocupada = true;
    }
    
    public void desocuparVaga() {
        this.ocupada = false;
    }
    
    public abstract double calcularPreco(long minutosTotais);

    protected double aplicarLimite(double precoTotal) {
        return Math.min(precoTotal, this.getParque().getValorDeDiariaMaxima());
    }
    
}
