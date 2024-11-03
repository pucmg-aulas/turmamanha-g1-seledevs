/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.model;

/**
 *
 * @author viniciusgomesrodrigues
 */
public class ParqueSelecionado {
    private static Parque parqueSelecionado;

    public static Parque getParqueSelecionado() {
        return parqueSelecionado;
    }

    public static void setParqueSelecionado(Parque parque) {
        parqueSelecionado = parque;
    }
}
