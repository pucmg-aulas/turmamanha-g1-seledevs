/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking;

import br.com.javaParking.dao.DaoCliente;
import br.com.javaParking.dao.DaoOcupacao;
import br.com.javaParking.dao.DaoParque;
import br.com.javaParking.dao.DaoVaga;
import br.com.javaParking.dao.DaoVeiculo;
import br.com.javaParking.model.Cliente;
import br.com.javaParking.model.Ocupacao;
import br.com.javaParking.model.Parque;
import br.com.javaParking.model.Vaga;
import br.com.javaParking.model.Veiculo;
import br.com.javaParking.model.tiposVaga.Comum;
import br.com.javaParking.model.tiposVaga.Idoso;
import br.com.javaParking.model.tiposVaga.Pcd;
import br.com.javaParking.model.tiposVaga.Vip;
import br.com.javaParking.util.Util;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Leandro Alencar
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int x, y, z;
        String a, b, c, d, e;

        Parque auxParque;
        Cliente auxCliente;
        Veiculo auxVeiculo;
        Vaga auxVaga;
        Ocupacao auxOcupacao;

        List<Vaga> auxListaVaga = new ArrayList<Vaga>();
        List<Ocupacao> auxListaOcupacao = new ArrayList<Ocupacao>();

        while (true) {
            System.out.println("\n=== Xumlambs Parkink ===");
            System.out.println("(1) -> Cadastrar Parque");
            System.out.println("(2) -> Cadastrar Cliente");
            System.out.println("(3) -> Cadastrar Veiculo");
            System.out.println("(4) -> Ocupar um Parque");
            System.out.println("(5) -> Desocupar Vaga");
            System.out.println("(6) -> Sair");
            System.out.print("Escolha: ");

            x = scanner.nextInt();
            scanner.nextLine();

            if (x == 6) {
                System.out.println("Ate mais...");
                break;
            }

            switch (x) {
                case 1:
                    System.out.println();
                    System.out.print("Escolha um nome para o parque: ");
                    a = scanner.nextLine();
                    System.out.print("Escolha um numero de vagas totais para o parque: ");
                    x = scanner.nextInt();
                    System.out.print("Escolha um numero de vagas por fileira para o parque: ");
                    y = scanner.nextInt();
                    System.out.println();

                    auxParque = new Parque(a, x, y);
                    auxParque.montarVagas();
                    DaoParque.gravar(auxParque);

                    break;
                case 2:
                    System.out.println();
                    System.out.print("Insira o nome do cliente: ");
                    a = scanner.nextLine();
                    System.out.print("Insira o identificador do cliente: ");
                    b = scanner.nextLine();

                    auxCliente = new Cliente(a, b);
                    DaoCliente.gravar(auxCliente);
                    break;
                case 3:
                    System.out.println();
                    System.out.print("Insira a placa do veiculo: ");
                    a = scanner.nextLine();
                    auxVeiculo = new Veiculo(a);

                    System.out.println();
                    System.out.println("Escolha um dos clientes registrados para vincular o veiculo: ");
                    System.out.println("(-1) -> Anonimo");

                    for (int i = 0; i < DaoCliente.listar().size(); i++) {
                        System.out.println("(" + i + ") -> Nome:" + DaoCliente.listar().get(i).getNome() + " Id:" + DaoCliente.listar().get(i).getIdentificador());
                    }
                    System.out.print("Escolha: ");
                    x = scanner.nextInt();

                    if (x == -1) {
                        auxCliente = new Cliente("", "");
                    } else {
                        auxCliente = DaoCliente.listar().get(x);
                    }

                    DaoVeiculo.gravar(auxVeiculo, auxCliente);
                    break;
                case 4:
                    if (!DaoParque.listar().isEmpty()) {
                        System.out.println();
                        System.out.println("Escolha um dos clientes registrados para estacionar no Parque: ");
                        System.out.println("(-1) -> Anonimo");

                        for (int i = 0; i < DaoCliente.listar().size(); i++) {
                            System.out.println("(" + i + ") -> Nome:" + DaoCliente.listar().get(i).getNome() + " Id:" + DaoCliente.listar().get(i).getIdentificador());
                        }
                        System.out.print("Escolha: ");
                        x = scanner.nextInt();

                        if (x == -1) {
                            auxCliente = new Cliente("", "");
                        } else {
                            auxCliente = DaoCliente.listar().get(x);
                        }

                        if (DaoVeiculo.listar(auxCliente).isEmpty()) {
                            System.out.println();
                            System.out.print("Este cliente nao tem veiculos");
                            System.out.println();
                            break;
                        }

                        System.out.println();
                        System.out.println("Escolha um dos veiculos desse cliente para estacionar no Parque: ");

                        for (int i = 0; i < DaoVeiculo.listar(auxCliente).size(); i++) {
                            System.out.println("(" + i + ") -> Placa:" + DaoVeiculo.listar(auxCliente).get(i).getPlaca());
                        }
                        System.out.print("Escolha: ");
                        x = scanner.nextInt();

                        auxVeiculo = DaoVeiculo.listar(auxCliente).get(x);

                        System.out.println();
                        System.out.println("Escolha um dos parques registrados para ocupar uma vaga: ");
                        for (int i = 0; i < DaoParque.listar().size(); i++) {
                            System.out.println("(" + i + ") -> Identificador:" + DaoParque.listar().get(i).getIdentificador());
                        }
                        System.out.print("Escolha: ");
                        x = scanner.nextInt();
                        auxParque = DaoParque.listar().get(x);

                        auxListaVaga = DaoVaga.listar(auxParque);

                        System.out.println();
                        System.out.println("Escolha uma das vagas desocupadas no parque para estacionar: ");
                        for (int i = 0; i < auxListaVaga.size(); i++) {
                            if (!auxListaVaga.get(i).isOcupada()) {
                                if (auxListaVaga.get(i) instanceof Idoso) {
                                    System.out.println("(" + i + ") -> Identificador:" + auxListaVaga.get(i).getIdentificador() + " Tipo:Idoso");
                                } else if (auxListaVaga.get(i) instanceof Pcd) {
                                    System.out.println("(" + i + ") -> Identificador:" + auxListaVaga.get(i).getIdentificador() + " Tipo:Pcd");
                                } else if (auxListaVaga.get(i) instanceof Vip) {
                                    System.out.println("(" + i + ") -> Identificador:" + auxListaVaga.get(i).getIdentificador() + " Tipo:Vip");
                                } else if (auxListaVaga.get(i) instanceof Comum) {
                                    System.out.println("(" + i + ") -> Identificador:" + auxListaVaga.get(i).getIdentificador() + " Tipo:Comum");
                                }
                            }
                        }
                        System.out.print("Escolha: ");
                        y = scanner.nextInt();

                        auxVaga = auxListaVaga.get(y);

                        auxOcupacao = new Ocupacao(auxCliente.getId(), auxVeiculo.getPlaca(), auxVaga.getIdentificador());

                        auxListaVaga.get(y).ocuparVaga();

                        File file = new File(DaoVaga.CAMINHOVAGA);
                        file.delete();

                        for (int i = 0; i < auxListaVaga.size(); i++) {
                            DaoVaga.gravar(auxListaVaga.get(i));
                        }

                        DaoOcupacao.gravar(auxOcupacao);
                        System.out.println("Vaga ocupada com sucesso!");

                    } else {
                        System.out.println("Nenhum parque cadastrado...");
                    }
                    break;
                case 5:
                    System.out.println();
                    System.out.println("Qual a data de hoje? (dd/MM/yyyy-HH-mm)");
                    a = scanner.nextLine();

                    DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH-mm");
                    LocalDateTime dataSaida = LocalDate.parse(a, parser).atStartOfDay();

                    System.out.println();
                    System.out.println("Escolha um dos parques registrados para desocupar uma vaga: ");
                    for (int i = 0; i < DaoParque.listar().size(); i++) {
                        System.out.println("(" + i + ") -> Identificador:" + DaoParque.listar().get(i).getIdentificador());
                    }

                    System.out.print("Escolha: ");
                    x = scanner.nextInt();
                    auxParque = DaoParque.listar().get(x);

                    auxListaVaga = DaoVaga.listar(auxParque);
                    System.out.println();
                    System.out.println("Escolha uma das vagas ocupadas no parque para desocupar: ");
                    for (int i = 0; i < auxListaVaga.size(); i++) {
                        if (auxListaVaga.get(i).isOcupada()) {
                            if (auxListaVaga.get(i) instanceof Idoso) {
                                System.out.println("(" + i + ") -> Identificador:" + auxListaVaga.get(i).getIdentificador() + " Tipo:Idoso");
                            } else if (auxListaVaga.get(i) instanceof Pcd) {
                                System.out.println("(" + i + ") -> Identificador:" + auxListaVaga.get(i).getIdentificador() + " Tipo:Pcd");
                            } else if (auxListaVaga.get(i) instanceof Vip) {
                                System.out.println("(" + i + ") -> Identificador:" + auxListaVaga.get(i).getIdentificador() + " Tipo:Vip");
                            } else if (auxListaVaga.get(i) instanceof Comum) {
                                System.out.println("(" + i + ") -> Identificador:" + auxListaVaga.get(i).getIdentificador() + " Tipo:Comum");
                            }
                        }
                    }
                    System.out.print("Escolha: ");
                    y = scanner.nextInt();

                    auxVaga = auxListaVaga.get(y);

                    auxListaOcupacao = DaoOcupacao.listar();

                    for (int i = 0; i < auxListaOcupacao.size(); i++) {
                        if (auxVaga.getIdentificador().equals(auxListaOcupacao.get(i).getVaga())) {
                            System.out.println("Para o Cliente:" + auxListaOcupacao.get(i).getCliente() + " retirar o veiculo de placa: " 
                                    + auxListaOcupacao.get(i).getVeiculo() + " da vaga:" + auxListaOcupacao.get(i).getVaga() + " precisara pagar uma taxa de:" 
                                    + auxListaOcupacao.get(i).custoOcupacao(auxVaga, dataSaida));

                            while (true) {
                                System.out.println("Deseja pagar?\n(1) -> Sim\n(2) -> Nao");
                                b = scanner.nextLine();

                                if (b.equals("1")) {
                                    auxListaVaga.get(y).desocuparVaga();

                                    File file = new File(DaoVaga.CAMINHOVAGA);
                                    file.delete();

                                    for (int j = 0; j < auxListaVaga.size(); j++) {
                                        DaoVaga.gravar(auxListaVaga.get(j));
                                    }
                                    
                                    auxListaOcupacao.remove(i);

                                    file = new File(DaoOcupacao.CAMINHOOCUPACAO);
                                    file.delete();

                                    for (int j = 0; j < auxListaOcupacao.size(); j++) {
                                        DaoOcupacao.gravar(auxListaOcupacao.get(j));
                                    }
                                } else if (b.equals("2")) {
                                    break;
                                }

                            }
                        }
                    }

                    break;
                case 6:
                    break;
            }
        }
        scanner.close();

    }

}

