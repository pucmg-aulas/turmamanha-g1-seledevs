package br.com.javaParking.model;

import br.com.javaParking.dao.DaoVaga;
import br.com.javaParking.model.tiposVaga.Comum;
import br.com.javaParking.model.tiposVaga.Idoso;
import br.com.javaParking.model.tiposVaga.Pcd;
import br.com.javaParking.model.tiposVaga.Vip;
import br.com.javaParking.util.Util;
import java.util.ArrayList;
import java.util.List;

public class Parque {

    /**
     * Atributos
     */
    public final static double VALORPORTEMPO = 4; 
    public final static int INTERVALODECOBRANCAEMMINUTOS = 15; 
    public final static double VALORDEDIARIAMAXIMA = 50; 
    
    private final static double PORCENTAGEMMINIMAIDOSOS = 0.10; 
    private final static double PORCENTAGEMMINIMAPCD = 0.10; 
    private final static double PORCENTAGEMMINIMAVIP = 0.20; 
        
    private int id;
    private int numeroVagas; 
    private List<Vaga> vagas;   
    private int vagasPorFileira;
     
    /**
     * Construtores
     */  
    public Parque(int id, int numeroVagas, int vagasPorFileira) {
        this.id = id;
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
    
    
    /**
     * Metodos de acesso 
     */    
    public int getId(){
        return this.id;
    }
    
    public int getNumeroVagas(){
        return this.numeroVagas;
    }
    
    public int getVagasPorFileira(){
        return this.vagasPorFileira;
    }
    
    /**
     * Metodos de ação 
     */     
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
                    this.vagas.add(new Idoso(String.valueOf(this.id),Util.alfabeto().get(i).toString() + j,false)); 
                    DaoVaga.gravar(new Idoso(String.valueOf(this.id),Util.alfabeto().get(i).toString() + j,false));
                    nIdoso--;
                }else if(nPCD != 0){
                    this.vagas.add(new Pcd(String.valueOf(this.id),Util.alfabeto().get(i).toString() + j,false)); 
                    DaoVaga.gravar(new Pcd(String.valueOf(this.id),Util.alfabeto().get(i).toString() + j,false));
                    nPCD--;
                }else if(nVIP != 0){
                    this.vagas.add(new Vip(String.valueOf(this.id),Util.alfabeto().get(i).toString() + j,false));
                    DaoVaga.gravar(new Vip(String.valueOf(this.id),Util.alfabeto().get(i).toString() + j,false));
                    nVIP--;
                }else{
                    this.vagas.add(new Comum(String.valueOf(this.id),Util.alfabeto().get(i).toString() + j,false)); 
                    DaoVaga.gravar(new Comum(String.valueOf(this.id),Util.alfabeto().get(i).toString() + j,false));
                }                
                
                n++;
            }
        }
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
