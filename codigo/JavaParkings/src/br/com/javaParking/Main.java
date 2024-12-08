/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking;

import br.com.javaParking.controller.ArrecadacaoController;
import br.com.javaParking.controller.ClienteController;
import br.com.javaParking.controller.ConfiguracaoController;
import br.com.javaParking.controller.ParqueController;
import br.com.javaParking.controller.VeiculoController;
import br.com.javaParking.dao.ConexaoDAO;

/**
 *
 * @author Leandro Alencar
 */

public class Main {

    public static void main(String[] args) {
        ConexaoDAO.Conexao();
        ConexaoDAO.Tabelas();
        
         new ClienteController();
    }

}

