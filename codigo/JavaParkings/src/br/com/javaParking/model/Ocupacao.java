/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.model;

import br.com.javaParking.model.tiposVaga.Comum;
import br.com.javaParking.model.tiposVaga.Idoso;
import br.com.javaParking.model.tiposVaga.PessoaComDeficiencia;
import br.com.javaParking.model.tiposVaga.Vip;
import java.util.Date;

/**
 *
 * @author Leandro Alencar
 */
public class Ocupacao {
    
    private Cliente cliente;
    private Veiculo veiculo;
    private Object vaga;
    private Date horaEntrada;
    private Date horaSaida;
    
    public Ocupacao(Cliente cliente, Veiculo veiculo, Vip vaga){       
        this.cliente = cliente;
        this.veiculo = veiculo;        
        vaga.setOcupada(true);        
        this.vaga = vaga;        
        this.horaEntrada = new Date();                
    }
    
    public Ocupacao(Cliente cliente, Veiculo veiculo, Idoso vaga){       
        this.cliente = cliente;
        this.veiculo = veiculo;        
        vaga.setOcupada(true);        
        this.vaga = vaga;        
        this.horaEntrada = new Date();                
    }
    
    public Ocupacao(Cliente cliente, Veiculo veiculo, Comum vaga){       
        this.cliente = cliente;
        this.veiculo = veiculo;        
        vaga.setOcupada(true);        
        this.vaga = vaga;        
        this.horaEntrada = new Date();                
    }
    
    public Ocupacao(Cliente cliente, Veiculo veiculo, PessoaComDeficiencia vaga){       
        this.cliente = cliente;
        this.veiculo = veiculo;        
        vaga.setOcupada(true);        
        this.vaga = vaga;        
        this.horaEntrada = new Date();                
    }
    
    public void desocuparVaga(Date horaSaida) {
      
    }
    
    private int tempoEstacionadoEmMinutos(Date horaEntrada, Date horaSaida) {
        return 0;
    }

}
