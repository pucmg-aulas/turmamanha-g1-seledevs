package br.com.javaParking.dao;

import br.com.javaParking.model.Veiculo;
import br.com.javaParking.util.Comunicacao;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDao {

    
   //Temos que decidir se vai criar um veiculo solto ou com a FK de cliente
    
    // Cria a tabela de veículos no banco de dados
    public static String criarTabela() {
        try {
            Comunicacao.setSql("""
                CREATE TABLE IF NOT EXISTS
                    interno.tbveiculo(
                                    placa VARCHAR(15) NOT NULL UNIQUE,
                                    cliente_cpf VARCHAR(255) NOT NULL,
                                    FOREIGN KEY (cliente_cpf) REFERENCES interno.tbcliente(cpf)
                    );
                """);
            Comunicacao.prepararConexcao();
            Comunicacao.executar();
        } catch (Exception e) {
            return "Erro ao criar tabela de veículos: " + e;
        }
        return "Tabela de veículos criada com sucesso";
    }

    // Adiciona um veículo ao banco de dados
    public static void addVeiculo(Veiculo veiculo, String clienteCpf) {
        try {
            Comunicacao.setSql("""
                INSERT INTO
                    interno.tbveiculo (placa, cliente_cpf)
                VALUES
                    (?,?);
                """);
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setString(1, veiculo.getPlaca());
            Comunicacao.getPst().setString(2, clienteCpf);
            Comunicacao.executar();
        } catch (Exception e) {
            System.out.println("Erro ao adicionar veículo: " + e);
        }
    }

    // Busca um veículo pela placa
    public static Veiculo buscarPorPlaca(String placa) {
        try {
            Comunicacao.setSql("SELECT * FROM interno.tbveiculo WHERE placa = ?;");
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setString(1, placa);
            Comunicacao.executarQuery();

            if (Comunicacao.getRs().next()) {
                return new Veiculo(Comunicacao.getRs().getString("placa"));
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar veículo por placa: " + e);
        }
        return null;
    }

    // Lista todos os veículos de um cliente
    public static List<Veiculo> listarVeiculosPorCliente(String clienteCpf) {
        List<Veiculo> veiculos = new ArrayList<>();
        try {
            Comunicacao.setSql("SELECT * FROM interno.tbveiculo WHERE cliente_cpf = ?;");
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setString(1, clienteCpf);
            Comunicacao.executarQuery();

            while (Comunicacao.getRs().next()) {
                veiculos.add(new Veiculo(Comunicacao.getRs().getString("placa")));
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar veículos por cliente: " + e);
        }
        return veiculos;
    }

    // Exclui um veículo pela placa
    public static void excluirVeiculo(String placa) {
        try {
            Comunicacao.setSql("DELETE FROM interno.tbveiculo WHERE placa = ?;");
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setString(1, placa);
            Comunicacao.executar();
        } catch (Exception e) {
            System.out.println("Erro ao excluir veículo: " + e);
        }
    }

    // Atualiza a placa de um veículo
    public static void alterarPlaca(String novaPlaca, String placaAntiga) {
        try {
            Comunicacao.setSql("""
                UPDATE
                    interno.tbveiculo
                SET
                    placa = ?
                WHERE
                    placa = ?;
                """);
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setString(1, novaPlaca);
            Comunicacao.getPst().setString(2, placaAntiga);
            Comunicacao.executar();
        } catch (Exception e) {
            System.out.println("Erro ao alterar placa do veículo: " + e);
        }
    }

   
    // Lista todos os veículos no banco de dados
    public static List<Veiculo> listarTodosVeiculos() {
        List<Veiculo> veiculos = new ArrayList<>();
        try {
            Comunicacao.setSql("SELECT * FROM interno.tbveiculo;");
            Comunicacao.prepararConexcao();
            Comunicacao.executarQuery();

            while (Comunicacao.getRs().next()) {
                veiculos.add(new Veiculo(Comunicacao.getRs().getString("placa")));
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar todos os veículos: " + e);
        }
        return veiculos;
    }
}
