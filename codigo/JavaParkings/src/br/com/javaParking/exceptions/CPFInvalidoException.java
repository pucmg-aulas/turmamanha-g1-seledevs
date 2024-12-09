/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.exceptions;

/**
 *
 * @author leand
 */
public class CPFInvalidoException extends Exception{
    public CPFInvalidoException() {
        super("CPF invalido.");
    }

    public CPFInvalidoException(String message) {
        super(message);

    }
}
