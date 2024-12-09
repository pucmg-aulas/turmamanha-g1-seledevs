package br.com.javaParking.dao;

import br.com.javaParking.util.Comunicacao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ConexaoDAO {

    private static String URL;
    private static String USER;
    private static String PASSWORD;
    private static Connection CON;
    private static Connection conexao = null;
    private static PreparedStatement pst = null;

    private static Connection ConexaoPadrao() {
        URL = "jdbc:postgresql://localhost:5432/";
        USER = "xumlambs";
        PASSWORD = "123";
        try {
            Class.forName("org.postgresql.Driver");
            CON = DriverManager.getConnection(URL, USER, PASSWORD);
            return CON;
        } catch (Exception e) {
            return null;
        }

    }

    public static Connection Conexao() {
        URL = "jdbc:postgresql://localhost:5432/dbxumlambs";
        USER = "xumlambs";
        PASSWORD = "123";
        try {
            Class.forName("org.postgresql.Driver");
            CON = DriverManager.getConnection(URL, USER, PASSWORD);
            return CON;
        } catch (Exception e) {
            CriarBanco();
            return null;
        }
    }

    public static String Tabelas() {
        try {
            Comunicacao.setSql("CREATE SCHEMA IF NOT EXISTS interno");
            Comunicacao.prepararConexcao();
            Comunicacao.executar();
        } catch (Exception e) {
            return "Erro ao Criar SchemaInterno: " + e;
        } finally {
            ConfiguracaoDAO.criarTabela();
            ClienteDao.criarTabela();
            VeiculoDao.criarTabela();
            VagaDao.criarTabela();
            OcupacaoDao.criarTabela();
            ArrecadacaoDAO.criarTabela();
            ParqueDAO.criarTabela();
        }
        return "";
    }

    private static void CriarBanco() {
        try {
            conexao = ConexaoPadrao();
            String sqo = "CREATE DATABASE dbxumlambs";
            pst = CON.prepareStatement(sqo);
            pst.execute();
        } catch (Exception e) {
        }
    }

}
