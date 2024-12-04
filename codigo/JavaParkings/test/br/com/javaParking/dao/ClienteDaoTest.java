/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package br.com.javaParking.dao;

import br.com.javaParking.model.Cliente;
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

public class ClienteDaoTest {

    private ClienteDao dao;

    @BeforeEach
    void setUp() {
        // Configura o banco de dados em memória H2 para os testes
        dao = new ClienteDao();
        dao.criarTabela(); // Cria a tabela de clientes no banco de dados
    }

    @AfterEach
    void tearDown() {
        // Limpa os dados da tabela após cada teste (opcional)
        dao.excluirCliente(1); // Exclui clientes com IDs específicos, se necessário
    }

    @Test
    void testAddCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("João Silva");
        cliente.setCpf("123.456.789-00");

        dao.addCliente(cliente);

        Cliente clienteBuscado = dao.buscarPorCpf("123.456.789-00");
        assertNotNull(clienteBuscado);
        assertEquals("João Silva", clienteBuscado.getNome());
    }

    @Test
    void testBuscarPorCpf() {
        Cliente cliente = new Cliente();
        cliente.setNome("Maria Oliveira");
        cliente.setCpf("987.654.321-00");

        dao.addCliente(cliente);

        Cliente clienteBuscado = dao.buscarPorCpf("987.654.321-00");
        assertNotNull(clienteBuscado);
        assertEquals("Maria Oliveira", clienteBuscado.getNome());

        Cliente clienteNaoExistente = dao.buscarPorCpf("111.222.333-44");
        assertNull(clienteNaoExistente);
    }

    @Test
    void testListarClientes() {
        Cliente cliente1 = new Cliente();
        cliente1.setNome("Roberto Lima");
        cliente1.setCpf("444.555.666-77");

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Roberta Alves");
        cliente2.setCpf("888.999.000-11");

        dao.addCliente(cliente1);
        dao.addCliente(cliente2);

        List<Cliente> clientes = dao.listarClientes();
        assertEquals(2, clientes.size());
    }

    @Test
    void testExcluirCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Carlos Silva");
        cliente.setCpf("111.222.333-44");

        dao.addCliente(cliente);

        Cliente clienteBuscado = dao.buscarPorCpf("111.222.333-44");
        assertNotNull(clienteBuscado);

        dao.excluirCliente(clienteBuscado.getId());

        Cliente clienteExcluido = dao.buscarPorCpf("111.222.333-44");
        assertNull(clienteExcluido);
    }

    @Test
    void testAlterarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Ana Souza");
        cliente.setCpf("222.333.444-55");

        dao.addCliente(cliente);

        cliente.setNome("Ana Costa");
        dao.alterarCliente(cliente);

        Cliente clienteAlterado = dao.buscarPorCpf("222.333.444-55");
        assertNotNull(clienteAlterado);
        assertEquals("Ana Costa", clienteAlterado.getNome());
    }

    @Test
    void testPesquisarPorNome() {
        Cliente cliente = new Cliente();
        cliente.setNome("Carlos Lima");
        cliente.setCpf("555.666.777-88");

        dao.addCliente(cliente);

        Cliente clienteBuscado = dao.pesquisarPorNome("Carlos Lima");
        assertNotNull(clienteBuscado);
        assertEquals("555.666.777-88", clienteBuscado.getCpf());
    }

    @Test
    void testPesquisarPorNomeParcial() {
        Cliente cliente1 = new Cliente();
        cliente1.setNome("João Silva");
        cliente1.setCpf("111.222.333-44");

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Joana Silva");
        cliente2.setCpf("555.666.777-88");

        dao.addCliente(cliente1);
        dao.addCliente(cliente2);

        List<Cliente> clientes = dao.pesquisarPorNomeParcial("João");
        assertEquals(2, clientes.size());
    }
}