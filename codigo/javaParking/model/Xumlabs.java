/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.model;

import java.util.List;

/**
 *
 * @author Leandro Alencar
 */
public class Xumlabs {
    
    private static List<Parque> parques = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Veiculo> veiculos = new ArrayList<>();
    private static List<Ocupacao> ocupacoes = new ArrayList<>();
    
    static {
        clientes.add(new Cliente("", ""));
    }
    
    public static void addVeiculo(Veiculo veiculo) {
        // Adiciona um veículo à lista, caso não exista
        if (veiculo != null && validarVeiculo(veiculo.getPlaca())) {
            veiculos.add(veiculo);
        } else {
            throw new RuntimeException("Veículo inválido ou já cadastrado.");
        }
    }
    
    public static void delVeiculo(Veiculo veiculo) {
        // Remove um veículo da lista
        if (veiculo != null) {
            veiculos.remove(veiculo);
        } else {
            throw new RuntimeException("Veículo inválido.");
        }
    }
    
    public static List<Cliente> listaClientes() {
        return clientes;
    }
    
    public static List<Veiculo> listaVeiculos() {
        return veiculos;
    }

    private static boolean validarVeiculo(String placa) {
        // Verifica se o veículo já está cadastrado
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equals(placa)) {
                return false; // Veículo já existe
            }
        }
        return true; // Veículo é válido para cadastro
    }

}
