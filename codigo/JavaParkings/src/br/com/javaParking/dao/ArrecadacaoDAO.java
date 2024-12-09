/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.dao;

import br.com.javaParking.model.Arrecadacao;
import br.com.javaParking.util.Comunicacao;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro Alencar
 */
public class ArrecadacaoDAO {

    public static String criarTabela() {
        try {
            Comunicacao.setSql("""
                CREATE TABLE IF NOT EXISTS
                    interno.tbarrecadacao(
                                    id SERIAL,
                                    fk_cpf_cliente VARCHAR(255),
                                    fk_nome_parque VARCHAR(255),
                                    valor_arrecadado NUMERIC(1000,2),
                                    data_arrecadacao DATE,
                                    PRIMARY KEY (id)
                    );
                """);
            Comunicacao.prepararConexcao();
            Comunicacao.executar();
        } catch (Exception e) {
            return "Erro ao criar tabela de arrecadacao: " + e;
        }
        return "Tabela de arrecadacoes criada com sucesso";
    }

    public static List<Arrecadacao> tabela() {
        List<Arrecadacao> Arrecadacoes = new ArrayList<Arrecadacao>();
        try {

            Comunicacao.setSql("SELECT * from interno.tbarrecadacao;");
            Comunicacao.prepararConexcao();
            Comunicacao.executarQuery();

            while (Comunicacao.getRs().next()) {
                Arrecadacao x = new Arrecadacao();

                x.setId(Comunicacao.getRs().getInt("id"));
                x.setFk_cpf_cliente(Comunicacao.getRs().getString("fk_cpf_cliente"));
                x.setValor_arrecadado(Comunicacao.getRs().getFloat("valor_arrecadado"));
                x.setData_arrecadacao(Comunicacao.getRs().getDate("data_arrecadacao"));
                x.setFk_nome_parque(Comunicacao.getRs().getString("fk_nome_parque"));

                Arrecadacoes.add(x);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar modelo tbArrecadacao");
        }

        return Arrecadacoes;

    }

    public static List<Arrecadacao> tabelaPorCpf(String cpf) {
        List<Arrecadacao> Arrecadacoes = new ArrayList<Arrecadacao>();
        try {

            Comunicacao.setSql("SELECT * from interno.tbarrecadacao WHERE fk_cpf_cliente = ?;");
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setString(1, cpf);
            Comunicacao.executarQuery();

            while (Comunicacao.getRs().next()) {
                Arrecadacao x = new Arrecadacao();

                x.setId(Comunicacao.getRs().getInt("id"));
                x.setFk_cpf_cliente(Comunicacao.getRs().getString("fk_cpf_cliente"));
                x.setValor_arrecadado(Comunicacao.getRs().getFloat("valor_arrecadado"));
                x.setData_arrecadacao(Comunicacao.getRs().getDate("data_arrecadacao"));
                x.setFk_nome_parque(Comunicacao.getRs().getString("fk_nome_parque"));

                Arrecadacoes.add(x);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar modelo tbArrecadacao");
        }

        return Arrecadacoes;

    }
    
    public static List<Arrecadacao> tabelaRanking() {
        List<Arrecadacao> Arrecadacoes = new ArrayList<Arrecadacao>();
        try {

            Comunicacao.setSql("SELECT * from interno.tbarrecadacao ORDER BY valor_arrecadado DESC;");
            Comunicacao.prepararConexcao();
            Comunicacao.executarQuery();

            while (Comunicacao.getRs().next()) {
                Arrecadacao x = new Arrecadacao();

                x.setId(Comunicacao.getRs().getInt("id"));
                x.setFk_cpf_cliente(Comunicacao.getRs().getString("fk_cpf_cliente"));
                x.setValor_arrecadado(Comunicacao.getRs().getFloat("valor_arrecadado"));
                x.setData_arrecadacao(Comunicacao.getRs().getDate("data_arrecadacao"));
                x.setFk_nome_parque(Comunicacao.getRs().getString("fk_nome_parque"));

                Arrecadacoes.add(x);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar modelo tbArrecadacao");
        }

        return Arrecadacoes;

    }

    public static void addArrecadacao(Arrecadacao arrecadacao) {
        try {

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            java.sql.Date dSqo = new java.sql.Date(arrecadacao.getData_arrecadacao().getTime());
            df.format(dSqo);

            Comunicacao.setSql("""
                INSERT INTO
                    interno.tbarrecadacao (fk_cpf_cliente, valor_arrecadado, data_arrecadacao,fk_nome_parque)
                VALUES
                    (?,?,?,?);
                """);
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setString(1, arrecadacao.getFk_cpf_cliente());
            Comunicacao.getPst().setFloat(2, arrecadacao.getValor_arrecadado());
            Comunicacao.getPst().setDate(3, dSqo);
            Comunicacao.getPst().setString(4, arrecadacao.getFk_nome_parque());
            Comunicacao.executar();
        } catch (Exception e) {
            System.out.println("Erro ao adicionar arrecadacao: " + e);
        }
    }

}
