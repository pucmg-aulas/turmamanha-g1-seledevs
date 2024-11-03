package br.com.javaParking.model;

import java.io.Serializable;

/**
 * Configuração do sistema de estacionamento.
 * Contém as porcentagens mínimas e valores de cobrança.
 */
public class ConfiguracaoModel implements Serializable {
    private static final long serialVersionUID = 1L; 

    public static final double PORCENTAGEMMINIMAIDOSOS = 0.10; 
    public static final double PORCENTAGEMMINIMAPCD = 0.10; 
    public static final double PORCENTAGEMMINIMAVIP = 0.20; 

    private double porcentagemMinimaIdosos;
    private double porcentagemMinimaPCD;
    private double porcentagemMinimaVIP;
    private int intervaloCobrancaMinutos;
    private double valorMaximoDiaria;

    public ConfiguracaoModel(double porcentagemMinimaIdosos, double porcentagemMinimaPCD,
                             double porcentagemMinimaVIP, int intervaloCobrancaMinutos,
                             double valorMaximoDiaria) {
        this.porcentagemMinimaIdosos = porcentagemMinimaIdosos;
        this.porcentagemMinimaPCD = porcentagemMinimaPCD;
        this.porcentagemMinimaVIP = porcentagemMinimaVIP;
        this.intervaloCobrancaMinutos = intervaloCobrancaMinutos;
        this.valorMaximoDiaria = valorMaximoDiaria;

        if (!validarPorcentagens()) {
            throw new IllegalArgumentException("A soma das porcentagens não pode ultrapassar 100%");
        }
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

    private boolean validarPorcentagens() {
        double soma = porcentagemMinimaIdosos + porcentagemMinimaPCD + porcentagemMinimaVIP;
        return soma <= 1.0;
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
}
