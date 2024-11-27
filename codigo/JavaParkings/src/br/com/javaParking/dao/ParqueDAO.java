package br.com.javaParking.dao;

// IMPORTS DE CLASSES:
import br.com.javaParking.model.Parque;
import br.com.javaParking.model.Veiculo;
import br.com.javaParking.util.Comunicacao;
import br.com.javaParking.controller.ParqueController;
import br.com.javaParking.model.Cliente;
import br.com.javaParking.model.Configuracao;
import br.com.javaParking.dao.ConexaoDAO;


// IMPORT DE BIBLIOTECAS DO JAVA:
import br.com.javaParking.util.Util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParqueDAO extends ConexaoDAO {
    

    public static String criarTabela() {
        try {
            Comunicacao.setSql  ("""
                                    CREATE TABLE IF NOT EXISTS 
                                        interno.tbparques(
                                            id SERIAL,
                                            nomeParque VARCHAR(100),
                                            numeroDeVagas INT NOT NULL,
                                            vagasPorFileira INT NOT NULL,
                                            PRIMARY KEY (id)
                                 );
                                """);
            Comunicacao.prepararConexcao();
            Comunicacao.executar();
            //adicionarParqueInicial();

        } catch (Exception e) {
            return "Erro ao criar tabela tbparques: " + e;
        }
        return "Tabela tbparques criada com sucesso";
    }
    
    public static Parque parque() {
        try {

            Comunicacao.setSql("SELECT * from interno.tbparques where interno.tbparques.id = 1;");
            Comunicacao.prepararConexcao();
            Comunicacao.executarQuery();

            while (Comunicacao.getRs().next()) {
                Parque x = new Parque(
                        Comunicacao.getRs().getInt("id"),
                        Comunicacao.getRs().getString("nomeParque"),
                        Comunicacao.getRs().getInt("numeroDeVagas"),
                        Comunicacao.getRs().getInt("vagasPorFileira")
                );

                return x;
            }

            } catch (Exception e) {
        }

        return null;

    }

    
    public static boolean addParque(Parque parque) {
        
        try {
            Comunicacao.setSql("""
                                INSERT INTO 
                               interno.tbparques(
                                    id,
                                    nomeParque, 
                                    numeroVagas, 
                                    vagasPorFileira)
                                VALUES (?, ?, ?);
                                """);
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setString(1, parque.getNomeParque());
            Comunicacao.getPst().setInt(2, parque.getNumeroVagas());
            Comunicacao.getPst().setInt(3, parque.getVagasPorFileira());
            Comunicacao.executar();
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao adicionar parque: " + e);
            return false;
        }
    }
    
    public static boolean alterarParque(Parque parque) {
        try {
            Comunicacao.setSql("""
                UPDATE
                    interno.tbparques
                SET
                    nomeParque = ?,
                    numeroVagas = ?,
                    vagasPorFileira = ?
                WHERE
                    id = ?;
                """);
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setString(1, parque.getNomeParque());
            Comunicacao.getPst().setInt(2, parque.getNumeroVagas());
            Comunicacao.getPst().setInt(3, parque.getVagasPorFileira());
            Comunicacao.getPst().setInt(4, parque.getId());
            Comunicacao.executar();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao alterar parque: " + e);
            return false;
        }
    }    
        
    public static boolean excluirParque(int id) {
        try {
            Comunicacao.setSql("""
                                    DELETE
                                    FROM interno.tbparques
                                    WHERE id = ?;
                                """);
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setInt(1, id);
            Comunicacao.executar();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excluir parque: " + e);
            return false;
        }
    }
    
    public static Parque buscarPorNome(String nomeParque) {
        
        try {
            Comunicacao.setSql("""
                                    SELECT *
                                    FROM interno.tbparques
                                    WHERE nomeParque = ?;
                                 );
                                """);
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setString(1, nomeParque);
            Comunicacao.executarQuery();

            if (Comunicacao.getRs().next()) {
                Parque parqueEncontrado = new Parque(
                        Comunicacao.getRs().getInt("id"),
                        Comunicacao.getRs().getString("nomeParque"),
                        Comunicacao.getRs().getInt("numeroVagas"),
                        Comunicacao.getRs().getInt("vagasPorFileira")
                );
                return parqueEncontrado;
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar parque por nome: " + e);
        }
        return null;
    }
    
    
    public static List<Parque> listarParques() {
        List<Parque> parques = new ArrayList<>();
        try {
            Comunicacao.setSql("SELECT nomeParque, numeroVagas FROM interno.tbparques;");
            Comunicacao.prepararConexcao();
            Comunicacao.executarQuery();

            while (Comunicacao.getRs().next()) {
                Parque parque = new Parque(
                        Comunicacao.getRs().getInt("id"),
                        Comunicacao.getRs().getString("nomeParque"),
                        Comunicacao.getRs().getInt("numeroVagas"),
                        Comunicacao.getRs().getInt("vagasPorFileira")

                );
                parques.add(parque);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar clientes: " + e);
        }
        return parques;
    }
    
    /*
    private static void adicionarParqueInicial() {
        try {
            if (getInstance().buscarTodosParques().isEmpty()) {
                Comunicacao.setSql  ("""
                                        INSERT INTO interno.tbparques
                                            (nomeParque, numeroDeVagas, vagasPorFileira)
                                        VALUES ('Parque Central', 100, 20);
                                    """);
                Comunicacao.prepararConexcao();
                Comunicacao.executar();
            }
        } catch (Exception e) {
            System.err.println("Erro ao adicionar parque inicial: " + e);
        }
    }
    
    public List<Parque> buscarTodosParques() {
        List<Parque> listaParques = new ArrayList<>();
        try {
            Comunicacao.setSql  ("  SELECT * FROM interno.tbparques;");
            Comunicacao.prepararConexcao();
            Comunicacao.executarQuery();

            while (Comunicacao.getRs().next()) {
                Parque parque = new Parque(
                    Comunicacao.getRs().getInt("id"),
                    Comunicacao.getRs().getString("nomeParque"),
                    Comunicacao.getRs().getInt("numeroDeVagas"),
                    Comunicacao.getRs().getInt("vagasPorFileira")
                );
                listaParques.add(parque);
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar parques: " + e);
        }
        return listaParques;
    }
    
    
    // INICIO DOS MÉTODOS AUXILIARES DO CRUD:
    
/*
    public Parque buscarPorNome(String nomeParque) {
        for (Parque parque : parques) {
            if (parque.getNomeParque().equals(nomeParque)) {
                return parque;
            }
        }
        return null;
    }
    
    public Parque buscarPorId(int id) {
        for (Parque parque : parques) {
            if (parque.getId() == id) {
                    return parque;
                }
            }
        return null;
    }
    */
    /*
    public List<Parque> buscarPorNomeParcial(String nomeParcial) {
        List<Parque> resultado = new ArrayList<>();
            for (Parque parque : parques) { // Supondo que listaDeParques é a lista de todos os parques
                if (parque.getNomeParque().toLowerCase().contains(nomeParcial.toLowerCase())) {
                    resultado.add(parque);
                }
            }
        return resultado;
    }
    
    // FIM DOS MÉTODOS AUXILIARES DO CRUD.
    
    /* Nos parametros temos dois: 
            veiculoExistente -> Em termos de codigo ele seria o novo Veiculo/Objeto que vc vai colocar no lugar de um antigo 
                                (Observação: o novo objeto tem que ter um campo em comum com o antigo)
            placaAnterior -> Seria o campo em comum ultilizado para localizar e substituir
    */
    
    // INICIO DOS MÉTODOS ESPECIAIS DE CRUD DO PARQUE:
    
    
        
        /*
        try {
            this.parques.add(parque);
            grava();
            return true;
        } catch(Exception e) {
            return false;
        }
    */
    
    /*
    public boolean alterarParque(Parque parqueSelecionado, int parqueIdentificador) {
        try {
        // Localiza e substitui o parque com o identificador correto
        for (int i = 0; i < parques.size(); i++) {
            if (parques.get(i).getId() == parqueIdentificador) {
                    parques.set(i, parqueSelecionado); // Substitui o parque existente pelo atualizado
                    grava(); // Grava a lista atualizada
                    return true;
                }
            }
            return false; // Retorna false se o parque com o identificador não foi encontrado
        } catch (Exception e) {
            e.printStackTrace(); // Imprime a pilha de erro para facilitar a depuração
            return false;
        }
    }
    
    public void excluirParque(Parque parque) {
        parques.remove(parque);
        grava();
    }
    
    // FIM DOS MÉTODOS ESPECIAIS DE CRUD DO PARQUE:
    */
}
