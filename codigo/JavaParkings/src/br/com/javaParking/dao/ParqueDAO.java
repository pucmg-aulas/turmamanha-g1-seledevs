package br.com.javaParking.dao;

// IMPORTS DE CLASSES:
import br.com.javaParking.model.Parque;
import br.com.javaParking.model.Veiculo;
import br.com.javaParking.util.Comunicacao;
import br.com.javaParking.controller.ParqueController;
import br.com.javaParking.model.Configuracao;


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

public class ParqueDAO extends ConexaoDAO implements Serializable{
    
    private List<Parque> parques;
    
    // Atributo da própria classe, estático, para implementar o Singleton
    private static ParqueDAO instance;

    //Endereço do arquivo serializado que contém a coleção de veiculos
    private final String localArquivo = Util.CAMINHOPADRAO + "Parques.dat";

    /*  Construtor privado, pois não podemos permitir mais de uma instância desta classe
        que controla a coleção de carros do sistema (Singleton)
    */ 
    
    private ParqueDAO() {
        this.parques = new ArrayList<>();
        carregaParques();
    }

    public static String criarTabelaParques () {
        try {
            Comunicacao.setSql  ("""
                                    CREATE TABLE IF NOT EXISTS 
                                        interno.tbparques(
                                            id SERIAL PRIMARY KEY IS NOT NULL,
                                            nomeParque VARCHAR(100),
                                            numeroDeVagas INT NOT NULL,
                                            vagasPorFileira INT NOT NULL,
                                """);
            Comunicacao.prepararConexcao();
            Comunicacao.executar();
            adicionarParqueInicial();

        } catch (Exception e) {
            return "Erro ao criar tabela tbparques: " + e;
        }
        return "Tabela tbparques criada com sucesso";
    }
    
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
    
    public static ParqueDAO getInstance() { //Método para recuperar a única instância de veiculos
        if (instance == null) {
            instance = new ParqueDAO();
        }
        return instance;
    }

    public void carregaParques() {
        this.parques = super.leitura(localArquivo);
    }

    public void grava() {
        super.grava(localArquivo, parques);
    }

    public List<Parque> getParques() {
        return parques;
    }

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
    
    public boolean addParque(Parque parque) {
        
        try {
            Comunicacao.setSql("""
                                INSERT INTO 
                               interno.tbparques(
                                    nomeParque, 
                                    numeroVagas, 
                                    vagasPorFileira,)
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
        
        /*
        try {
            this.parques.add(parque);
            grava();
            return true;
        } catch(Exception e) {
            return false;
        }
    */
    }
    
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
    
}
