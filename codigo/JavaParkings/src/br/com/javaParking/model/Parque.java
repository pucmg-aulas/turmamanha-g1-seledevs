/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.model;

import br.com.javaParking.dao.DaoVaga;
import br.com.javaParking.model.tiposVaga.Comum;
import br.com.javaParking.model.tiposVaga.Idoso;
import br.com.javaParking.model.tiposVaga.Pcd;
import br.com.javaParking.model.tiposVaga.Vip;
import br.com.javaParking.util.Util;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro Alencar
 */
public class Parque {

    public final static double VALORPORTEMPO; 
    public final static int INTERVALODECOBRANCAEMMINUTOS; 
    public final static double VALORDEDIARIAMAXIMA; 
    
    private final static double PORCENTAGEMMINIMAIDOSOS; 
    private final static double PORCENTAGEMMINIMAPCD; 
    private final static double PORCENTAGEMMINIMAVIP; 
        
    private String identificador;
    private int numeroVagas; 
    private List<Vaga> vagas;   
    private int vagasPorFileira;

    static{
        VALORPORTEMPO=4;
        INTERVALODECOBRANCAEMMINUTOS=15;
        VALORDEDIARIAMAXIMA=50;
        
        PORCENTAGEMMINIMAIDOSOS=0.10;
        PORCENTAGEMMINIMAPCD=0.10;
        PORCENTAGEMMINIMAVIP=0.20;
    }
            
    public Parque(String identificador, int numeroVagas, int vagasPorFileira) {
        this.identificador = identificador;
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
    
    private boolean validarNumeroDeVagas(){
        int maximo = Util.alfabeto().size() * this.vagasPorFileira;
        
        if(maximo < this.numeroVagas )
            return false;
            
        return true;        
    }

    public void montarVagas() {   
        
        int n = 0;
        
        int nIdoso = (int) Math.floor(this.numeroVagas  * PORCENTAGEMMINIMAIDOSOS);
        int nPCD = (int) Math.floor(this.numeroVagas  * PORCENTAGEMMINIMAPCD);
        int nVIP = (int) Math.floor(this.numeroVagas  * PORCENTAGEMMINIMAVIP); 
        
        for(int i = 0; i < Util.alfabeto().size(); i++){
            for(int j = 0; j < this.vagasPorFileira; j++){
                
                if(n>this.numeroVagas)
                    return;
                
                if(nIdoso != 0){
                    this.vagas.add(new Idoso(this.identificador,Util.alfabeto().get(i).toString() + j)); 
                    DaoVaga.gravar(new Idoso(this.identificador,Util.alfabeto().get(i).toString() + j));
                    nIdoso--;
                }else if(nPCD != 0){
                    this.vagas.add(new Pcd(this.identificador,Util.alfabeto().get(i).toString() + j)); 
                    DaoVaga.gravar(new Pcd(this.identificador,Util.alfabeto().get(i).toString() + j));
                    nPCD--;
                }else if(nVIP != 0){
                    this.vagas.add(new Vip(this.identificador,Util.alfabeto().get(i).toString() + j));
                    DaoVaga.gravar(new Vip(this.identificador,Util.alfabeto().get(i).toString() + j));
                    nVIP--;
                }else{
                    this.vagas.add(new Comum(this.identificador,Util.alfabeto().get(i).toString() + j)); 
                    DaoVaga.gravar(new Comum(this.identificador,Util.alfabeto().get(i).toString() + j));
                }                
                
                n++;
            }
        }
    }
    
     public String getIdentificador(){
        return this.identificador;
    }
    
    public int getNumeroVagas(){
        return this.numeroVagas;
    }
    
    public int getVagasPorFileira(){
        return this.vagasPorFileira;
    }
    
    private boolean atualizarNumeroVagas(int numeroVagas) {
        if(Util.ePositivo(numeroVagas)){
            
            if(!validarNumeroDeVagas()) 
                throw new RuntimeException(); 
            
            this.numeroVagas = numeroVagas;
            montarVagas();
            return true;
        }
        
        return false;
    }
    
    private List<Vaga> listarVagas() {
        return this.vagas;
    }
}