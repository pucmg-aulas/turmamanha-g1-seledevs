/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.com.javaParking.util.validadores;

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

public class CPFValidatorTest {
    public static void main(String[] args) {
        // Testes de CPF válidos
        String cpfValido1 = "12345678909"; // CPF fictício válido
        String cpfValido2 = "39053344703"; // CPF fictício válido

        // Testes de CPF inválidos
        String cpfInvalido1 = "00000000000"; // CPF com todos os dígitos iguais
        String cpfInvalido2 = "11111111111"; // CPF com todos os dígitos iguais
        String cpfInvalido3 = "12345678900"; // CPF com dígitos verificadores incorretos
        String cpfInvalido4 = "12345678"; // CPF com menos de 11 dígitos

        // Verificando CPF válidos
        System.out.println("Testando CPF válido: " + cpfValido1);
        if (CPFValidator.isCPF(cpfValido1)) {
            System.out.println("CPF válido detectado corretamente.\n");
        } else {
            System.out.println("Erro: CPF válido não foi detectado.\n");
        }

        System.out.println("Testando CPF válido: " + cpfValido2);
        if (CPFValidator.isCPF(cpfValido2)) {
            System.out.println("CPF válido detectado corretamente.\n");
        } else {
            System.out.println("Erro: CPF válido não foi detectado.\n");
        }

        // Verificando CPF inválidos
        System.out.println("Testando CPF inválido: " + cpfInvalido1);
        if (!CPFValidator.isCPF(cpfInvalido1)) {
            System.out.println("CPF inválido detectado corretamente.\n");
        } else {
            System.out.println("Erro: CPF inválido foi detectado como válido.\n");
        }

        System.out.println("Testando CPF inválido: " + cpfInvalido2);
        if (!CPFValidator.isCPF(cpfInvalido2)) {
            System.out.println("CPF inválido detectado corretamente.\n");
        } else {
            System.out.println("Erro: CPF inválido foi detectado como válido.\n");
        }

        System.out.println("Testando CPF inválido: " + cpfInvalido3);
        if (!CPFValidator.isCPF(cpfInvalido3)) {
            System.out.println("CPF inválido detectado corretamente.\n");
        } else {
            System.out.println("Erro: CPF inválido foi detectado como válido.\n");
        }

        System.out.println("Testando CPF inválido: " + cpfInvalido4);
        if (!CPFValidator.isCPF(cpfInvalido4)) {
            System.out.println("CPF inválido detectado corretamente.\n");
        } else {
            System.out.println("Erro: CPF inválido foi detectado como válido.\n");
        }
    }
}