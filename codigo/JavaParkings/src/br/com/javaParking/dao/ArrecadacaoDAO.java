/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.dao;

import br.com.javaParking.model.Arrecadacao;
import br.com.javaParking.util.Comunicacao;
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
                                    fk_id_cliente INT,
                                    valor_arrecadado NUMERIC(1000,2),
                                    FOREIGN KEY (fk_id_cliente) REFERENCES interno.tbcliente(id),
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
                x.setFk_id_cliente(Comunicacao.getRs().getInt("fk_id_cliente"));
                x.setValor_arrecadado(Comunicacao.getRs().getFloat("valor_arrecadado"));

                Arrecadacoes.add(x);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar modelo tbArrecadacao");
        }

        return Arrecadacoes;

    }

    public static void addArrecadacao(Arrecadacao arrecadacao) {
        try {
            Comunicacao.setSql("""
                INSERT INTO
                    interno.tbarrecadacao (fk_id_cliente, valor_arrecadado)
                VALUES
                    (?,?);
                """);
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setInt(1, arrecadacao.getId());
            Comunicacao.getPst().setFloat(2, arrecadacao.getValor_arrecadado());
            Comunicacao.executar();
        } catch (Exception e) {
            System.out.println("Erro ao adicionar arrecadacao: " + e);
        }
    }

}
