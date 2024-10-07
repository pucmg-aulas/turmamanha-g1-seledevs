/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking;

import br.com.javaParking.dao.DaoCliente;
import br.com.javaParking.dao.DaoParque;
import br.com.javaParking.model.Cliente;
import br.com.javaParking.model.Parque;
import br.com.javaParking.util.Util;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Leandro Alencar
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int x,y,z;
        String a,b,c,d,e;
        Parque auxParque;
        Cliente auxCliente;

        while (true) {
            System.out.println("\n=== Xumlambs Parkink ===");
            System.out.println("(1) -> Cadastrar Parque");
            System.out.println("(2) -> Cadastrar Cliente");
            System.out.println("(3) -> Cadastrar Cliente");
            System.out.println("(4) -> Ocupar um Parque");
            System.out.println("(5) -> Sair");
            System.out.print("Escolha: ");

            x = scanner.nextInt();
            scanner.nextLine();

            if (x == 4) {
                System.out.println("Ate mais...");
                break;
            }

            switch (x) {
                case 1:
                    System.out.println();
                    System.out.println("Escolha um nome para o parque: ");
                    a = scanner.nextLine();
                    System.out.println("Escolha um numero de vagas totais para o parque: ");
                    x = scanner.nextInt();
                    System.out.println("Escolha um numero de vagas por fileira para o parque: ");
                    y = scanner.nextInt();
                    auxParque = new Parque(a,x,y);
                    auxParque.montarVagas();
                    DaoParque.gravar(auxParque);
                    break;
                case 2:
                    
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        }
        scanner.close();

    }

}
