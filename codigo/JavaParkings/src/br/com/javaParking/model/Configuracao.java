package br.com.javaParking.model;

import java.io.Serializable;
import br.com.javaParking.model.Parque;
import javax.swing.JOptionPane;


public class Configuracao implements Serializable {

    private double porcentagemMinimaIdosos;
    private double porcentagemMinimaPCD;
    private double porcentagemMinimaVIP;

    private double valorPeriodoPorTempo;
    private int intervaloCobrancaMinutos;
    private double valorMaximoDiaria;

    public Configuracao(double porcentagemMinimaIdosos,
            double porcentagemMinimaPCD,
            double porcentagemMinimaVIP,
            int intervaloCobrancaMinutos,
            double valorMaximoDiaria,
            double valorPeriodoPorTempo) {
        try {
            this.porcentagemMinimaIdosos = porcentagemMinimaIdosos;
            this.porcentagemMinimaPCD = porcentagemMinimaPCD;
            this.porcentagemMinimaVIP = porcentagemMinimaVIP;
            this.intervaloCobrancaMinutos = intervaloCobrancaMinutos;
            this.valorMaximoDiaria = valorMaximoDiaria;
            this.valorPeriodoPorTempo = valorPeriodoPorTempo;

            if (!validarPorcentagens()) {
                throw new IllegalArgumentException("A soma das porcentagens não pode ultrapassar 100%");
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    private boolean validarPorcentagens() {
        double soma = porcentagemMinimaIdosos + porcentagemMinimaPCD + porcentagemMinimaVIP;
        return soma <= 100;
    }

    public double getPorcentagemMinimaIdosos() {
        return porcentagemMinimaIdosos;
    }

    public double getPorcentagemMinimaPCD() {
        return porcentagemMinimaPCD;
    }

    public double getPorcentagemMinimaVIP() {
        return porcentagemMinimaVIP;
    }

    public int getIntervaloCobrancaMinutos() {
        return intervaloCobrancaMinutos;
    }

    public double getValorMaximoDiaria() {
        return valorMaximoDiaria;
    }

    public double getValorPeriodoPorTempo() {
        return valorPeriodoPorTempo;
    }

    public void setPorcentagemMinimaIdosos(double porcentagemMinimaIdosos) {
        this.porcentagemMinimaIdosos = porcentagemMinimaIdosos;
        if (!validarPorcentagens()) {
            throw new IllegalArgumentException("A soma das porcentagens não pode ultrapassar 100%");
        }
    }

    public void setPorcentagemMinimaPCD(double porcentagemMinimaPCD) {
        this.porcentagemMinimaPCD = porcentagemMinimaPCD;
        if (!validarPorcentagens()) {
            throw new IllegalArgumentException("A soma das porcentagens não pode ultrapassar 100%");
        }
    }

    public void setPorcentagemMinimaVIP(double porcentagemMinimaVIP) {
        this.porcentagemMinimaVIP = porcentagemMinimaVIP;
        if (!validarPorcentagens()) {
            throw new IllegalArgumentException("A soma das porcentagens não pode ultrapassar 100%");
        }
    }

    public void setIntervaloCobrancaMinutos(int intervaloCobrancaMinutos) {
        this.intervaloCobrancaMinutos = intervaloCobrancaMinutos;
    }

    public void setValorMaximoDiaria(double valorMaximoDiaria) {
        this.valorMaximoDiaria = valorMaximoDiaria;
    }

    public void setValorPeriodoPorTempo(double valorPeriodoPorTempo) {
        this.valorPeriodoPorTempo = valorPeriodoPorTempo;
    }

}
