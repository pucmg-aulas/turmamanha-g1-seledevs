package br.com.javaParking.dao;

import br.com.javaParking.model.Cliente;
import br.com.javaParking.model.Ocupacao;
import br.com.javaParking.model.Parque;
import br.com.javaParking.model.Vaga;
import br.com.javaParking.model.Veiculo;
import br.com.javaParking.util.Comunicacao;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class OcupacaoDao {

    // Criação da tabela de ocupações
    public static String criarTabela() {
        try {
            Comunicacao.setSql("""
                CREATE TABLE IF NOT EXISTS 
                    interno.tbocupacoes (
                        id SERIAL,
                        cliente_id VARCHAR(50) NOT NULL,
                        veiculo_id VARCHAR(100) NOT NULL,
                        vaga_id VARCHAR(100) NOT NULL,
                        parque_id INT,
                        hora_entrada TIME,
                        hora_saida TIME,
                        PRIMARY KEY (id)
                    );
                """);
            Comunicacao.prepararConexcao();
            Comunicacao.executar();
        } catch (Exception e) {
            return "Erro ao criar tabela de ocupações: " + e;
        }
        return "Tabela de ocupações criada com sucesso";
    }

    public static Ocupacao getOcupacaoHoraEntrada(Parque parque, String identificadorVaga) {
        try {
            Comunicacao.setSql("SELECT * FROM interno.tbocupacoes WHERE parque_id = ? and vaga_id = ?;");
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setInt(1, parque.getId());
            Comunicacao.getPst().setString(2, identificadorVaga);
            Comunicacao.executarQuery();

            
            while (Comunicacao.getRs().next()) {
                Time z = Comunicacao.getRs().getTime("hora_entrada");
                
                Ocupacao x = new Ocupacao(Comunicacao.getRs().getInt("id"), z.toLocalTime());              
                
                return x;
            }
        } catch (Exception e) {
            System.out.println("Erro ao recuperar ocupacao: " + e);
        }

        throw new RuntimeException("Ocupacao não existe.");
    }
    
    // Adiciona uma nova ocupação
    public static void addOcupacao(Ocupacao ocupacao) {
        try {
            Comunicacao.setSql("""
                INSERT INTO
                    interno.tbocupacoes 
                                (cliente_id, veiculo_id, vaga_id, parque_id, hora_entrada)
                VALUES
                    (?,?,?,?,?);
                """);
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setString(1, ocupacao.getCliente().getCpf());
            Comunicacao.getPst().setString(2, ocupacao.getVeiculo().getPlaca());
            Comunicacao.getPst().setString(3, ocupacao.getVaga().getIdentificador());
            Comunicacao.getPst().setInt(4, ocupacao.getParque().getId());
            Comunicacao.getPst().setTime(5, Time.valueOf(ocupacao.getHoraEntrada()));
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
