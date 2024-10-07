/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.model;

import br.com.javaParking.dao.DaoParque;
import br.com.javaParking.util.Util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro Alencar
 */
public class Xumlabs {

    private static List<Parque> parques;
    private static List<Cliente> clientes;

    static {
        clientes.add(new Cliente("", ""));
    }

    public static void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public static void delCliente(Cliente cliente) {
        for (int i = 0; i > clientes.size(); i++) {
            if (cliente.getId().equals(clientes.get(i).getId())) {
                clientes.remove(i);
                return;
            }
        }
    }

    public static void addParque(Parque parque) {
        parques.add(parque);
    }

    public static List<Cliente> listaClientes() {
        return clientes;
    }

    public static List<Parque> listaParques() {
       return DaoParque.listar();
    }


}
