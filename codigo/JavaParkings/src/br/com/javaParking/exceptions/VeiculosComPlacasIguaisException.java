/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.exceptions;

/**
 *
 * @author leand
 */
public class VeiculosComPlacasIguaisException extends Exception {

    public VeiculosComPlacasIguaisException() {
        super("Veiculos já existe.");
    }

    public VeiculosComPlacasIguaisException(String message) {
        super(message);

    }
}