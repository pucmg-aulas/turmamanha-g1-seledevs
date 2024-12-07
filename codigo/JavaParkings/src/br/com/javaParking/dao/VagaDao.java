package br.com.javaParking.dao;

import br.com.javaParking.model.Parque;
import br.com.javaParking.model.Vaga;
import br.com.javaParking.model.tiposVaga.Comum;
import br.com.javaParking.model.tiposVaga.Idoso;
import br.com.javaParking.model.tiposVaga.PCD;
import br.com.javaParking.model.tiposVaga.VIP;
import br.com.javaParking.util.Comunicacao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VagaDao {

   
    public static String criarTabela() {
        try {
            Comunicacao.setSql("""
                CREATE TABLE IF NOT EXISTS 
                    interno.tbvagas (
                        identificador VARCHAR(50) PRIMARY KEY,
                        tipo VARCHAR(20) NOT NULL,
                        ocupada BOOLEAN NOT NULL,
                        parque_id INT NOT NULL,
                        FOREIGN KEY (parque_id) REFERENCES interno.tbparques(id)
                    );
                """);
            Comunicacao.prepararConexcao();
            Comunicacao.executar();
        } catch (Exception e) {
            return "Erro ao criar tabela de vagas: " + e;
        }
        return "Tabela de vagas criada com sucesso";
    }

 
    public static void addVaga(Vaga vaga) {
        try {
            String tipo = vaga.getClass().getSimpleName().toUpperCase(); 
            Comunicacao.setSql("""
                INSERT INTO
                    interno.tbvagas (identificador, tipo, ocupada, parque_id)
                VALUES
                    (?, ?, ?, ?);
                """);
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setString(1, vaga.getIdentificador());
            Comunicacao.getPst().setString(2, tipo);
            Comunicacao.getPst().setBoolean(3, vaga.isOcupada());
            Comunicacao.getPst().setInt(4, vaga.getParque().getId());
            Comunicacao.executar();
        } catch (Exception e) {
            System.out.println("Erro ao adicionar vaga: " + e);
        }
    }


    public static List<Vaga> getVagas(Parque parque) {
        List<Vaga> vagas = new ArrayList<>();
        try {
            Comunicacao.setSql("SELECT * FROM interno.tbvagas WHERE parque_id = ?;");
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setInt(1, parque.getId());
            ResultSet rs = Comunicacao.getPst().executeQuery();
            
            while (rs.next()) {
                String tipo = rs.getString("tipo");
                String identificador = rs.getString("identificador");
                boolean ocupada = rs.getBoolean("ocupada");

                Vaga vaga = switch (tipo) {
                    case "COMUM" -> new Comum(parque, identificador, ocupada);
                    case "IDOSO" -> new Idoso(parque, identificador, ocupada);
                    case "PCD" -> new PCD(parque, identificador, ocupada);
                    case "VIP" -> new VIP(parque, identificador, ocupada);
                    default -> throw new IllegalArgumentException("Tipo de vaga desconhecido: " + tipo);
                };
                vagas.add(vaga);
            }
        } catch (Exception e) {
            System.out.println("Erro ao recuperar vagas: " + e);
        }
        return vagas;
    }

   
    public static void excluirVaga(String identificador) {
        try {
            Comunicacao.setSql("DELETE FROM interno.tbvagas WHERE identificador = ?;");
            Comunicacao.prepararConexcao();
            Comunicacao.getPst().setString(1, identificador);
            Comunicacao.executar();
        } catch (Exception e) {
            System.out.println("Erro ao excluir vaga: " + e);
        }
    }
}