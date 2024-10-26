/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro Alencar
 */
public class Util {
    
    public final static String CAMINHOPADRAO = "./src/br/com/javaParking/arquivos/";
    
    public static boolean ePositivo(int... x){
        for(int i = 0; i < x.length; i++){
           if (x[i] < 1)
                return false; 
        }        
        
        return true;
    }
    
    public static List<Character> alfabeto(){
        List<Character> lista = new ArrayList<Character>();
        char x = 'A';
        for(int i = 65; i <= 90; i++){
            lista.add(x);
            x++;
        }
        
        return lista;
    }
    
}
