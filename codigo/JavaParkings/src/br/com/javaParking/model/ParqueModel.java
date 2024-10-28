package br.com.javaParking.model;

import br.com.javaParking.dao.VagaDao;
import br.com.javaParking.model.tiposVaga.ComumModel;
import br.com.javaParking.model.tiposVaga.IdosoModel;
import br.com.javaParking.model.tiposVaga.PcdModel;
import br.com.javaParking.model.tiposVaga.VipModel;
import br.com.javaParking.util.Util;
import java.util.ArrayList;
import java.util.List;

public class ParqueModel {

    /**
     * Atributos
     */
    private double valorPorTempo; 
    private int intervaloDeCobrancaMinutos; 
    private double valorDeDiariaMaxima; 
            
    private int id;
    private int numeroVagas; 
    private List<VagaModel> vagas;   
    private int vagasPorFileira;
     
    /**
     * Construtores
     */  
    public ParqueModel(int id, int numeroVagas, int vagasPorFileira, double valorPorTempo, int intervaloDeCobrancaMinutos, double valorDeDiariaMaxima) {
        this.id = id;
        this.vagas = new ArrayList<>();
        if (Util.ePositivo(numeroVagas, vagasPorFileira)){             
            this.numeroVagas = numeroVagas;
            this.vagasPorFileira = vagasPorFileira; 
            this.valorPorTempo = valorPorTempo;
            this.intervaloDeCobrancaMinutos = intervaloDeCobrancaMinutos;
            this.valorDeDiariaMaxima = valorDeDiariaMaxima;
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
    
    public double getValorPorTempo(){
        return this.valorPorTempo;
    }
    
    public int getIntervaloDeCobrancaMinutos(){
        return this.intervaloDeCobrancaMinutos;
    }
    
    public double getValorDeDiariaMaxima(){
        return this.valorDeDiariaMaxima;
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
        
        int nIdoso = (int) Math.floor(this.numeroVagas  * ConfiguracaoModel.PORCENTAGEMMINIMAIDOSOS);
        int nPCD = (int) Math.floor(this.numeroVagas  * ConfiguracaoModel.PORCENTAGEMMINIMAPCD);
        int nVIP = (int) Math.floor(this.numeroVagas  * ConfiguracaoModel.PORCENTAGEMMINIMAVIP); 
        
        for(int i = 0; i < Util.alfabeto().size(); i++){
            for(int j = 0; j < this.vagasPorFileira; j++){
                
                if(n>this.numeroVagas)
                    return;
                
                if(nIdoso != 0){
                    this.vagas.add(new IdosoModel(this,Util.alfabeto().get(i).toString() + j,false)); 
                    VagaDao.gravar(new IdosoModel(this,Util.alfabeto().get(i).toString() + j,false));
                    nIdoso--;
                }else if(nPCD != 0){
                    this.vagas.add(new PcdModel(this ,Util.alfabeto().get(i).toString() + j,false)); 
                    VagaDao.gravar(new PcdModel(this,Util.alfabeto().get(i).toString() + j,false));
                    nPCD--;
                }else if(nVIP != 0){
                    this.vagas.add(new VipModel(this,Util.alfabeto().get(i).toString() + j,false));
                    VagaDao.gravar(new VipModel(this,Util.alfabeto().get(i).toString() + j,false));
                    nVIP--;
                }else{
                    this.vagas.add(new ComumModel(this,Util.alfabeto().get(i).toString() + j,false)); 
                    VagaDao.gravar(new ComumModel(this,Util.alfabeto().get(i).toString() + j,false));
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
    
    private List<VagaModel> listarVagas() {
        return this.vagas;
    }
}
