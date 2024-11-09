/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.javaParking.util;

import br.com.javaParking.dao.ConexaoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author leand
 */
public class Comunicacao {

    private static Connection conexao = ConexaoDAO.Conexao();
    private static PreparedStatement pst = null;
    private static ResultSet rs = null;
    private static String sql;

    public static String getSql() {
        return sql;
    }

    public static void setSql(String sql) {
        Comunicacao.sql = sql;
    }

    public static Connection getConexao() {
        return conexao;
    }

    public static void setConexao(Connection conexao) {
        Comunicacao.conexao = conexao;
    }

    public static PreparedStatement getPst() {
        return pst;
    }

    public static void setPst(PreparedStatement pst) {
        Comunicacao.pst = pst;
    }

    public static ResultSet getRs() {
        return rs;
    }

    public static void setRs(ResultSet rs) {
        Comunicacao.rs = rs;
    }

    public static String prepararConexcao() {
        try {
            Comunicacao.setPst(Comunicacao.getConexao().prepareStatement(Comunicacao.getSql()));
        } catch (Exception e) {
            return String.valueOf(e);
        }
        return "";
    }

    public static String executar() {
        try {
            Comunicacao.getPst().execute();
        } catch (Exception e) {
            return String.valueOf(e);
        }
        return "";
    }

    public static String executarQuery() {
        try {
            setRs(Comunicacao.getPst().executeQuery());
        } catch (Exception e) {
            return String.valueOf(e);
        }
        return "";
    }

    public static String atualizarQuery() {
        try {
            Comunicacao.getPst().executeUpdate();
        } catch (Exception e) {
            return String.valueOf(e);
        }
        return "";
    }

}
