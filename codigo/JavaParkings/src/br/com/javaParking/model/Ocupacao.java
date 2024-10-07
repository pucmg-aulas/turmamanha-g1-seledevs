/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.model;

import br.com.javaParking.model.tiposVaga.Comum;
import br.com.javaParking.model.tiposVaga.Idoso;
import br.com.javaParking.model.tiposVaga.Pcd;
import br.com.javaParking.model.tiposVaga.Vip;
import br.com.javaParking.util.Util;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Leandro Alencar
 */
public class Ocupacao {

    private Cliente cliente;
    private Veiculo veiculo;
    private Vaga vaga;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;

    public void ocupar(Cliente cliente, Veiculo veiculo, Vaga vaga) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        vaga.setOcupada(true);
        this.vaga = vaga;
        this.horaEntrada = LocalDateTime.now();
    }

    public void desocuparVaga(Vaga vaga) {
           vaga.setOcupada(false);
    }

    public double custoOcupacao(Vaga vaga) {
       
        this.horaSaida = LocalDateTime.now();
        
        int dias = diferencaDias(this.horaEntrada, this.horaSaida);
        int minutos = diferencaMinutos(this.horaEntrada, this.horaSaida);
        
        double preco = 0;
        
        if (vaga instanceof Idoso) {
            preco = vaga.calcularPreco(dias, minutos);
        } else if (vaga instanceof Pcd) {
            preco = vaga.calcularPreco(dias, minutos);
        } else if (vaga instanceof Vip) {
            preco = vaga.calcularPreco(dias, minutos);
        } else if (vaga instanceof Comum) {
            preco = vaga.calcularPreco(dias, minutos);
        }
        
        return preco;
    }
    
    private int diferencaDias(LocalDateTime dIn, LocalDateTime dOut){
        int ano = dOut.getYear() - dIn.getYear();
        int mes = dOut.getMonthValue() - dIn.getMonthValue();
        int dia = dOut.getDayOfMonth() - dIn.getDayOfMonth();
        
        if(ano > 0 || mes > 0 || dia > 7){
            return -1;
        }else {
            return dia;
        }
    }
    
    private int diferencaMinutos(LocalDateTime dIn, LocalDateTime dOut){
        int diferencaHoras = dOut.getHour() - dIn.getHour();
        int diferencaMinutos = dOut.getMinute() - dIn.getMinute(); 
        
        int minutos = 0;
        
        if (diferencaHoras > 0){
            minutos += (60 - dIn.getMinute());
            
            if (diferencaHoras > 1){
                minutos += ((diferencaHoras-1)*60);
            }
            
            minutos += dOut.getMinute();            
            
            return minutos; 
        } else {
            return diferencaMinutos;
        }
    }

}
