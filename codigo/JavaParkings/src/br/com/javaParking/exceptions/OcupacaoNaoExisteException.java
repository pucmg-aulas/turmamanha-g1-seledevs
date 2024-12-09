/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.exceptions;

/**
 *
 * @author leand
 */
public class OcupacaoNaoExisteException extends Exception {

    public OcupacaoNaoExisteException() {
        super("Ocupação não encontrada.");
    }

    public OcupacaoNaoExisteException(String message) {
        super(message);

    }
}
