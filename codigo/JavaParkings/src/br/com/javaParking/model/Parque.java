package br.com.javaParking.model;

import br.com.javaParking.model.tiposVaga.ComumModel;
import br.com.javaParking.model.tiposVaga.IdosoModel;
import br.com.javaParking.model.tiposVaga.PcdModel;
import br.com.javaParking.model.tiposVaga.VipModel;
import br.com.javaParking.model.Configuracao;
import br.com.javaParking.dao.ConfiguracaoDao;
import br.com.javaParking.dao.VagaDao;

import br.com.javaParking.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Parque implements Serializable{

    // ATRIBUTOS:
    
    private double valorPorTempo; 
    private int intervaloDeCobrancaMinutos; 
    private double valorDeDiariaMaxima; 
            
    private int id;
    private String nomeParque;
    private int numeroVagas; 
    private List<Vaga> vagas;   
    private int vagasPorFileira;
     
    // MÉTODO DO CONSTRUTOR DA CLASSE: 
    
    public Parque(int id, String nomeParque, int numeroVagas, int vagasPorFileira) {
        this.id = id;
        this.nomeParque = nomeParque;
        this.vagas = new ArrayList<>();
        if (Util.ePositivo(numeroVagas, vagasPorFileira)){             
            this.numeroVagas = numeroVagas;
            this.vagasPorFileira = vagasPorFileira; 
        }else{
            throw new RuntimeException();
        }
        
        if(!validarNumeroDeVagas()) 
            throw new RuntimeException();    
    }
    
    // MÉTODOS BÁSICOS DE GET E SET:
    
    public int getId(){
        return this.id;
    }
    
    public String getNomeParque() { 
        return this.nomeParque;
    }
    
    public int getNumeroVagas(){
        return this.numeroVagas;
    }
    
    public int getVagasPorFileira(){
        return this.vagasPorFileira;
    }
    
    public double getValorPorTempo(){
        return this.valorPorTempo;
    }
    
    public int getIntervaloDeCobrancaMinutos(){
        return this.intervaloDeCobrancaMinutos;
    }
    
    public double getValorDeDiariaMaxima(){
        return this.valorDeDiariaMaxima;
    }
    
    public void setNomeParque(String nomeParque) {
        this.nomeParque = nomeParque;
    }
    
    public void setNumeroVagas(int numeroVagas) {
        this.numeroVagas = numeroVagas;
    }
    
    public void setVagasPorFileira(int vagasPorFileira) {
        this.vagasPorFileira = vagasPorFileira;
    }
    
    // MÉTODOS ESPECIAIS:
    
    public boolean validarNumeroDeVagas(){
        int maximo = Util.alfabeto().size() * this.vagasPorFileira;
        
        if(maximo < this.numeroVagas )
            return false;
            
        return true;        
    }

    public void montarVagas() {   
        
        int n = 0;
        
        int nIdoso = (int) Math.floor(this.numeroVagas * Configuracao.PORCENTAGEMMINIMAIDOSOS);
        int nPCD = (int) Math.floor(this.numeroVagas * Configuracao.PORCENTAGEMMINIMAPCD);
        int nVIP = (int) Math.floor(this.numeroVagas * Configuracao.PORCENTAGEMMINIMAVIP); 
        
        for(int i = 0; i < Util.alfabeto().size(); i++){
            for(int j = 0; j < this.vagasPorFileira; j++){
                
                if(n>this.numeroVagas)
                    return;
                
                if(nIdoso != 0){
                    this.vagas.add(new IdosoModel(this,Util.alfabeto().get(i).toString() + j,false)); 
                    VagaDao.gravar(new IdosoModel(this,Util.alfabeto().get(i).toString() + j,false));
                    nIdoso--;
                } else if(nPCD != 0) {
                    this.vagas.add(new PcdModel(this ,Util.alfabeto().get(i).toString() + j,false)); 
                    VagaDao.gravar(new PcdModel(this,Util.alfabeto().get(i).toString() + j,false));
                    nPCD--;
                } else if(nVIP != 0) {
                    this.vagas.add(new VipModel(this,Util.alfabeto().get(i).toString() + j,false));
                    VagaDao.gravar(new VipModel(this,Util.alfabeto().get(i).toString() + j,false));
                    nVIP--;
                } else {
                    this.vagas.add(new ComumModel(this,Util.alfabeto().get(i).toString() + j,false)); 
                    VagaDao.gravar(new ComumModel(this,Util.alfabeto().get(i).toString() + j,false));
                }                
                
                n++;
            }
        }
    }
    
    public boolean atualizarNumeroVagas(int numeroVagas) {
        if(Util.ePositivo(numeroVagas)){
            
            if(!validarNumeroDeVagas()) 
                throw new RuntimeException(); 
            
            this.numeroVagas = numeroVagas;
            montarVagas();
            return true;
        }
        
        return false;
    }
    
    public List<Vaga> listarVagas() {
        return this.vagas;
    }

}
