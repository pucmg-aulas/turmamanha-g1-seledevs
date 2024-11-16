package br.com.javaParking.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import br.com.javaParking.dao.ClienteDao;

public class Cliente implements Serializable {

    /**
     * Atributos
     */
    private String nome;
    private String id;
    private List<Veiculo> veiculos;

    /**
     * Construtores
     */
    public Cliente(String nome, String identificador) {
        this.nome = nome;
        this.id = identificador;
        this.veiculos = new ArrayList<>();  // Inicializa a lista de veículos
    }

    /**
     * Métodos de acesso
     */
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return this.id;
    }

    public List<Veiculo> listaVeiculos() {
        return this.veiculos;
    }

    /**
     * Métodos de ação
     */
    public void addVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
    }

    public void delVeiculo(Veiculo veiculo) {
        for (int i = 0; i < this.veiculos.size(); i++) {
            if (this.veiculos.get(i).getPlaca().equals(veiculo.getPlaca())) {
                this.veiculos.remove(i);
            }
        }
    }

    /**
     * Método de validação
     */
    public boolean validarCliente() {
        return ClienteDao.validarCliente(this.id);  // Utiliza o DAO para verificar se o cliente já existe
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
