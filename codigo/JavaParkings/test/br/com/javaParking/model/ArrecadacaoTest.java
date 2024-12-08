/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.com.javaParking.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Islayder
 */
public class ArrecadacaoTest {
    public static void main(String[] args) {
        Arrecadacao arrecadacao = new Arrecadacao();

        arrecadacao.setId(1);
        assert arrecadacao.getId() == 1 : "Erro: o ID não foi setado corretamente.";
        System.out.println("Teste de setId e getId realizado com sucesso!");

        arrecadacao.setFk_id_cliente(100);
        assert arrecadacao.getFk_id_cliente() == 100 : "Erro: o fk_id_cliente não foi setado corretamente.";
        System.out.println("Teste de setFk_id_cliente e getFk_id_cliente realizado com sucesso!");

        arrecadacao.setValor_arrecadado(250.75f);
        assert arrecadacao.getValor_arrecadado() == 250.75f : "Erro: o valor arrecadado não foi setado corretamente.";
        System.out.println("Teste de setValor_arrecadado e getValor_arrecadado realizado com sucesso!");
    }
}