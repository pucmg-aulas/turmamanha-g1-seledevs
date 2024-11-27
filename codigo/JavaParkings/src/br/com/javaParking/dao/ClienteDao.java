package br.com.javaParking.dao;

import br.com.javaParking.model.Cliente;
import br.com.javaParking.util.Comunicacao;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {

    // Cria a tabela de clientes no banco de dados
    public static String criarTabela() {
        try {
            Comunicacao.setSql("""
                CREATE TABLE IF NOT EXISTS
                    interno.tbcliente(
                                    id SERIAL,
                                    nome VARCHAR(255) NOT NULL,
                                    cpf VARCHAR(255) NOT NULL
                    );
                """);
            Comunicacao.prepararConexcao();
            Comunicacao.executar();
        } catch (Exception e) {
            return "Erro ao criar tabela de clientes: " + e;
        }
        return "Tabela de clientes criada com sucesso";
    }

    // Adiciona um novo cliente no banco de dados
    public static void addCliente(Cliente cliente) {
        try {
            Comunicacao.setSql("""
                INSERT INTO
                    interno.tbcliente (nome, cpf)
                VALUES
                    (?,?);
                """);
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setString(1, cliente.getNome());
            Comunicacao.getPst().setString(2, cliente.getCpf());
            Comunicacao.executar();
        } catch (Exception e) {
            System.out.println("Erro ao adicionar cliente: " + e);
        }
    }

    // Busca um cliente pelo CPF
    public static Cliente buscarPorCpf(String cpf) {
        try {
            Comunicacao.setSql("SELECT * FROM interno.tbcliente WHERE cpf = ?;");
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setString(1, cpf);
            Comunicacao.executarQuery();

            if (Comunicacao.getRs().next()) {
                
                Cliente x = new Cliente();
                x.setId(Comunicacao.getRs().getInt("id"));
                x.setCpf(Comunicacao.getRs().getString("cpf"));
                x.setNome(Comunicacao.getRs().getString("nome"));
                
                return x;
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar cliente por CPF: " + e);
        }
        return null;
    }

    // Lista todos os clientes
    public static List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            Comunicacao.setSql("SELECT * FROM interno.tbcliente;");
            Comunicacao.prepararConexcao();
            Comunicacao.executarQuery();

            while (Comunicacao.getRs().next()) {
                Cliente x = new Cliente();
                x.setId(Comunicacao.getRs().getInt("id"));
                x.setCpf(Comunicacao.getRs().getString("cpf"));
                x.setNome(Comunicacao.getRs().getString("nome"));
                
                clientes.add(x);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar clientes: " + e);
        }
        return clientes;
    }

    // Exclui um cliente pelo CPF
    public static void excluirCliente(int id) {
        try {
            Comunicacao.setSql("DELETE FROM interno.tbcliente WHERE id = ?;");
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setInt(1, id);
            Comunicacao.executar();
        } catch (Exception e) {
            System.out.println("Erro ao excluir cliente: " + e);
        }
    }

    // Altera um cliente no banco de dados
    public static void alterarCliente(Cliente cliente) {
        try {
            Comunicacao.setSql("""
                UPDATE
                    interno.tbcliente
                SET
                    nome = ?
                WHERE
                    id = ?;
                """);
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setString(1, cliente.getNome());
            Comunicacao.getPst().setInt(2, cliente.getId());
            Comunicacao.executar();
        } catch (Exception e) {
            System.out.println("Erro ao alterar cliente: " + e);
        }
    }

    // Método para validar se um cliente com o CPF já existe no banco
    public static boolean validarCliente(String cpf) {
        Cliente cliente = buscarPorCpf(cpf);
        return cliente == null; // Retorna true se o cliente não existir
    }

    // Método para pesquisar cliente por nome exato
    public static Cliente pesquisarPorNome(String nome) {
        try {
            Comunicacao.setSql("SELECT * FROM interno.tbcliente WHERE nome = ?;");
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setString(1, nome);
            Comunicacao.executarQuery();

            if (Comunicacao.getRs().next()) {
                Cliente x = new Cliente();
                x.setId(Comunicacao.getRs().getInt("id"));
                x.setCpf(Comunicacao.getRs().getString("cpf"));
                x.setNome(Comunicacao.getRs().getString("nome"));
                
                return x;
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar cliente por nome: " + e);
        }
        return null;
    }

    // Método para pesquisar clientes por nome parcial (ex: "Jo" retornará "João", "Joaquim")
    public static List<Cliente> pesquisarPorNomeParcial(String nomeParcial) {
        List<Cliente> clientes = new ArrayList<>();
        try {
            Comunicacao.setSql("SELECT * FROM interno.tbcliente WHERE nome LIKE ?;");
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setString(1, "%" + nomeParcial + "%");
            Comunicacao.executarQuery();

            while (Comunicacao.getRs().next()) {
                Cliente x = new Cliente();
                x.setId(Comunicacao.getRs().getInt("id"));
                x.setCpf(Comunicacao.getRs().getString("cpf"));
                x.setNome(Comunicacao.getRs().getString("nome"));
                
                clientes.add(x);
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar cliente por nome parcial: " + e);
        }
        return clientes;
    }
}
