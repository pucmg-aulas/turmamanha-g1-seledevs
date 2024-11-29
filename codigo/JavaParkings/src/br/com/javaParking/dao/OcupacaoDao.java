package br.com.javaParking.dao;

import br.com.javaParking.model.Cliente;
import br.com.javaParking.model.Ocupacao;
import br.com.javaParking.model.Vaga;
import br.com.javaParking.model.Veiculo;
import br.com.javaParking.util.Comunicacao;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class OcupacaoDAO {

    // Criação da tabela de ocupações
    public static String criarTabela() {
        try {
            Comunicacao.setSql("""
                CREATE TABLE IF NOT EXISTS 
                    interno.tbocupacoes (
                        id SERIAL PRIMARY KEY,
                        cliente_id VARCHAR(50) NOT NULL,
                        veiculo_id VARCHAR(100) NOT NULL,
                        vaga_id VARCHAR(100) NOT NULL,
                        hora_entrada TIME NOT NULL,
                        hora_saida TIME,
                        FOREIGN KEY (cliente_id) REFERENCES interno.tbcliente(id),
                        FOREIGN KEY (veiculo_id) REFERENCES interno.tbveiculos(id),
                        FOREIGN KEY (vaga_id) REFERENCES interno.tbvagas(id)
                    );
                """);
            Comunicacao.prepararConexcao();
            Comunicacao.executar();
        } catch (Exception e) {
            return "Erro ao criar tabela de ocupações: " + e;
        }
        return "Tabela de ocupações criada com sucesso";
    }

    // Adiciona uma nova ocupação
    public static void addOcupacao(Ocupacao ocupacao) {
        try {
            Comunicacao.setSql("""
                INSERT INTO
                    interno.tbocupacoes (cliente_id, veiculo_id, vaga_id, hora_entrada)
                VALUES
                    (?, ?, ?, ?);
                """);
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setString(1, ocupacao.getCliente().getCpf()); // decidir id SERIAL ou CPF
            Comunicacao.getPst().setString(2, ocupacao.getVeiculo().getPlaca());
            Comunicacao.getPst().setString(3, ocupacao.getVaga().getIdentificador());
            Comunicacao.getPst().setTime(4, Time.valueOf(ocupacao.getEntrada()));
            Comunicacao.executar();
        } catch (Exception e) {
            System.out.println("Erro ao adicionar ocupação: " + e);
        }
    }

    // Atualiza a saída de uma ocupação
    public static void registrarSaida(int ocupacaoId, LocalTime horaSaida) {
        try {
            Comunicacao.setSql("""
                UPDATE
                    interno.tbocupacoes
                SET
                    hora_saida = ?
                WHERE
                    id = ?;
                """);
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setTime(1, Time.valueOf(horaSaida));
            Comunicacao.getPst().setInt(2, ocupacaoId);
            Comunicacao.executar();
        } catch (Exception e) {
            System.out.println("Erro ao registrar saída da ocupação: " + e);
        }
    }

    // Exclui uma ocupação pelo ID
    public static void excluirOcupacao(int id) {
        try {
            Comunicacao.setSql("DELETE FROM interno.tbocupacoes WHERE id = ?;");
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setInt(1, id);
            Comunicacao.executar();
        } catch (Exception e) {
            System.out.println("Erro ao excluir ocupação: " + e);
        }
    }
}
