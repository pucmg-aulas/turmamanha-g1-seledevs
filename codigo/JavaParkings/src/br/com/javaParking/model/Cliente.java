package br.com.javaParking.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import br.com.javaParking.dao.ClienteDao;

public class Cliente implements Serializable {

    /**
     * Atributos
     */
    private int id;
    private String nome;
    private String cpf;
    
    /**
     * Métodos de acesso
     */
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

   
    /**
     * Método de validação
     */
    public boolean validarCliente() {
        return ClienteDao.validarCliente(this.cpf);  // Utiliza o DAO para verificar se o cliente já existe
    }

    /**
     * Método para adicionar cliente no banco de dados
     */
    public void addCliente() {
        ClienteDao.addCliente(this);
    }

    /**
     * Método para alterar cliente no banco de dados
     */
    public void alterarCliente() {
        ClienteDao.alterarCliente(this);
    }

    /**
     * Método para excluir cliente no banco de dados
     */
    public void excluirCliente() {
        ClienteDao.excluirCliente(this.id);
    }

    /**
     * Método para buscar um cliente por CPF
     */
    public static Cliente buscarPorCpf(String cpf) {
        return ClienteDao.buscarPorCpf(cpf);
    }

    /**
     * Método para listar todos os clientes
     */
    public static List<Cliente> listarClientes() {
        return ClienteDao.listarClientes();
    }

    /**
     * Método para pesquisar cliente por nome
     */
    public static Cliente pesquisarPorNome(String nome) {
        return ClienteDao.pesquisarPorNome(nome);
    }

    /**
     * Método para pesquisar clientes por nome parcial
     */
    public static List<Cliente> pesquisarPorNomeParcial(String nomeParcial) {
        return ClienteDao.pesquisarPorNomeParcial(nomeParcial);
    }

    @Override
    public String toString() {
        return id + "%" + nome;
    }
}
