package br.com.javaParking.dao;

import br.com.javaParking.model.Cliente;
import br.com.javaParking.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClienteDAO extends ConexaoDAO implements Serializable {

    private List<Cliente> clientes;
    // Atributo da própria classe, estático, para implementar o Singleton
    private static ClienteDAO instance;

    // Endereço do arquivo serializado que contém a coleção de clientes
    private final String localArquivo = Util.CAMINHOPADRAO + "Clientes.dat";

    // Construtor privado para implementar o padrão Singleton
    private ClienteDAO() {
        this.clientes = new ArrayList<>();
        carregaClientes();
    }

    // Método para recuperar a única instância de clientes
    public static ClienteDAO getInstance() {
        if (instance == null) {
            instance = new ClienteDAO();
        }
        return instance;
    }

    public void addCliente(Cliente cliente) {
        this.clientes.add(cliente);
        grava();
    }

    private void carregaClientes() {
        this.clientes = super.leitura(localArquivo);
    }

    private void grava() {
        super.grava(localArquivo, clientes);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void excluirCliente(Cliente cliente) {
        clientes.remove(cliente);
        grava();
    }

    public Cliente pesquisarPorCpf(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }
    
    public Cliente buscarPorNome(String nome) {
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equals(nome)) {
                return cliente;
            }
        }
        return null;
    }

    public List<Cliente> buscarPorNomeParcial(String nomeParcial) {
        List<Cliente> resultado = new ArrayList<>();
            for (Cliente cliente : clientes) { // Supondo que listaDeParques é a lista de todos os parques
                if (cliente.getNome().toLowerCase().contains(nomeParcial.toLowerCase())) {
                    resultado.add(cliente);
                }
            }
        return resultado;
    }
    
    public boolean alterarCliente(Cliente clienteExistente, String cpfAnterior) {
        try {
            if (clienteExistente.getId().equals(cpfAnterior)) {
                
                ArrayList<Cliente> listaTemp = new ArrayList<>();

                // Loop para criar lista temporária
                for (Iterator<Cliente> it = clientes.iterator(); it.hasNext();) {
                    Cliente cliente = it.next();
                    if (!cliente.getId().equals(cpfAnterior)) {
                        listaTemp.add(cliente);
                    } else {
                        listaTemp.add(clienteExistente);
                    }
                }

                // Zerar lista
                clientes.clear();

                // Preencher lista com novos dados
                clientes.addAll(listaTemp);

                // Sobreescrever dados antigos
                grava();

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
