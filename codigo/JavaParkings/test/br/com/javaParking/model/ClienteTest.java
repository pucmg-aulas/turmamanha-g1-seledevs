/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.com.javaParking.model;

import br.com.javaParking.dao.ClienteDao;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Islayder
 */

class ClienteTest {

    private Cliente cliente;

    private static class FakeClienteDao extends ClienteDao {
        private static boolean validarRetorno;
        private static Cliente clienteRetornado;
        private static List<Cliente> listaClientes = new ArrayList<>();

        public static void configurarValidarCliente(boolean retorno) {
            validarRetorno = retorno;
        }

        public static void configurarBuscarPorCpf(Cliente cliente) {
            clienteRetornado = cliente;
        }

        public static void configurarListaClientes(List<Cliente> clientes) {
            listaClientes = clientes;
        }

        public static boolean validarCliente(String cpf) {
            return validarRetorno;
        }

        public static void addCliente(Cliente cliente) {
            listaClientes.add(cliente);
        }

        public static void alterarCliente(Cliente cliente) {
            // Simula alteração do cliente
        }

        public static void excluirCliente(int id) {
            listaClientes.removeIf(c -> c.getId() == id);
        }

        public static Cliente buscarPorCpf(String cpf) {
            return clienteRetornado;
        }

        public static List<Cliente> listarClientes() {
            return listaClientes;
        }

        public static Cliente pesquisarPorNome(String nome) {
            return listaClientes.stream().filter(c -> c.getNome().equals(nome)).findFirst().orElse(null);
        }

        public static List<Cliente> pesquisarPorNomeParcial(String nomeParcial) {
            List<Cliente> resultados = new ArrayList<>();
            for (Cliente c : listaClientes) {
                if (c.getNome().contains(nomeParcial)) {
                    resultados.add(c);
                }
            }
            return resultados;
        }
    }

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1);
        cliente.setNome("João Silva");
        cliente.setCpf("123.456.789-00");
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(1, cliente.getId());
        assertEquals("João Silva", cliente.getNome());
        assertEquals("123.456.789-00", cliente.getCpf());
    }

    @Test
    void testValidarCliente() {
        FakeClienteDao.configurarValidarCliente(true);
        assertTrue(cliente.validarCliente());

        FakeClienteDao.configurarValidarCliente(false);
        assertFalse(cliente.validarCliente());
    }

    @Test
    void testAddCliente() {
        List<Cliente> clientes = new ArrayList<>();
        FakeClienteDao.configurarListaClientes(clientes);

        cliente.addCliente();

        assertEquals(1, FakeClienteDao.listarClientes().size());
        assertEquals(cliente, FakeClienteDao.listarClientes().get(0));
    }

    @Test
    void testAlterarCliente() {
        assertDoesNotThrow(() -> cliente.alterarCliente());
    }

    @Test
    void testExcluirCliente() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente);
        FakeClienteDao.configurarListaClientes(clientes);

        cliente.excluirCliente();

        assertTrue(FakeClienteDao.listarClientes().isEmpty());
    }

    @Test
    void testBuscarPorCpf() {
        FakeClienteDao.configurarBuscarPorCpf(cliente);
        Cliente result = Cliente.buscarPorCpf("123.456.789-00");
        assertNotNull(result);
        assertEquals(cliente, result);
    }

    @Test
    void testListarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente);
        FakeClienteDao.configurarListaClientes(clientes);

        List<Cliente> result = Cliente.listarClientes();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(cliente, result.get(0));
    }

    @Test
    void testPesquisarPorNome() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente);
        FakeClienteDao.configurarListaClientes(clientes);

        Cliente result = Cliente.pesquisarPorNome("João Silva");
        assertNotNull(result);
        assertEquals(cliente, result);
    }

    @Test
    void testPesquisarPorNomeParcial() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente);
        FakeClienteDao.configurarListaClientes(clientes);

        List<Cliente> result = Cliente.pesquisarPorNomeParcial("João");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(cliente, result.get(0));
    }

    @Test
    void testToString() {
        assertEquals("1%João Silva", cliente.toString());
    }
}
