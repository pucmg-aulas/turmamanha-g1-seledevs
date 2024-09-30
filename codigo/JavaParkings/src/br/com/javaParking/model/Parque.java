/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.model;

import br.com.javaParking.util.Validador;
import java.util.List;

/**
 *
 * @author Leandro Alencar
 */
public class Parque {

    private int numeroVagas;
    private List<Vaga> vagas;
    private static List<String> letras;
    private int vagasPorFileira;

    static{
        //REFATORAR PARA DIMINUIR
        letras.add("A");
        letras.add("B");
        letras.add("C");
        letras.add("D");
        letras.add("E");
        letras.add("F");
        letras.add("G");
        letras.add("H");
        letras.add("I");
        letras.add("J");
        letras.add("K");
        letras.add("L");
        letras.add("M");
        letras.add("N");
        letras.add("O");
        letras.add("P");
        letras.add("Q");
        letras.add("R");
        letras.add("S");
        letras.add("T");
        letras.add("U");
        letras.add("V");
        letras.add("W");
        letras.add("X");
        letras.add("Y");
        letras.add("Z");
    }
            
    public Parque(int numeroVagas, int vagasPorFileira) {
        
        if (Validador.ePositivo(numeroVagas)){ 
            this.numeroVagas = numeroVagas;
        }else{
            throw new RuntimeException();
        }
        
        if (Validador.ePositivo(vagasPorFileira)) {
            this.vagasPorFileira = vagasPorFileira; 
        }else{
            throw new RuntimeException();
        }
        
        if(!validarNumeroDeVagas()) 
            throw new RuntimeException();       
                
        montarVagas();     
        
    }
    
    private boolean validarNumeroDeVagas(){
        int maximo = Parque.letras.size() * this.vagasPorFileira;
        
        if(maximo < this.numeroVagas )
            return false;
        
        return true;        
    }

    private void montarVagas() {
        
        int n = 0;
        
        for(int i = 0; i < Parque.letras.size(); i++){
            for(int j = 0; j < this.vagasPorFileira; j++){
                
                if(!(n>this.numeroVagas))
                    return;
                
                this.vagas.add(new Vaga(Parque.letras.get(i) + j));
                
                n++;
            }
        }
    }

    private boolean atualizarNumeroVagas(int numeroVagas) {
        if(Validador.ePositivo(numeroVagas)){
            
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
    
    public boolean vagaExiste(String identificador){
        for(int i = 0; i < this.vagas.size(); i++){
            if(this.vagas.get(i).getIdentificador().equals(identificador)){
                return true;
            }
        }
        return false;
    }
}
