package br.com.javaParking.controller;

import br.com.javaParking.dao.ClienteDao;
import br.com.javaParking.model.Cliente;
import br.com.javaParking.view.cliente.ClienteView;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Method;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteControllerTest {

    private ClienteController controller;
    private ClienteView view;
    private ClienteDao dao;

    @BeforeEach
    void setUp() {
        dao = new ClienteDao(); // Instancia real do DAO
        view = new ClienteView();
        controller = new ClienteController(view, dao);

        // Inicializa a tabela no banco de dados (ou prepara o estado de teste)
        dao.criarTabela();
    }

    @Test
    void testAddCliente() throws Exception {
        // Preenche os campos da view com dados de teste
        view.getTxtCPF().setText("123.456.789-00");
        view.getTxtNome().setText("João Silva");

        // Usa reflection para acessar o método privado addCliente()
        Method addMethod = ClienteController.class.getDeclaredMethod("addCliente");
        addMethod.setAccessible(true); // Torna o método acessível
        addMethod.invoke(controller); // Invoca o método

        // Verifica se o cliente foi adicionado com sucesso
        Cliente cliente = dao.buscarPorCpf("123.456.789-00");
        assertNotNull(cliente);
        assertEquals("João Silva", cliente.getNome());
    }

    @Test
    void testEditarCliente() throws Exception {
        // Cria um cliente inicial e o adiciona ao DAO
        Cliente cliente = new Cliente();
        cliente.setCpf("987.654.321-00");
        cliente.setNome("Maria Oliveira");
        dao.addCliente(cliente);

        // Preenche a view com os dados modificados
        view.getTxtCPF().setText("987.654.321-00");
        view.getTxtNome().setText("Maria Silva");

        // Usa reflection para acessar o método privado editarCliente()
        Method editMethod = ClienteController.class.getDeclaredMethod("editarCliente");
        editMethod.setAccessible(true);
        editMethod.invoke(controller);

        // Verifica se o cliente foi atualizado corretamente
        Cliente clienteAtualizado = dao.buscarPorCpf("987.654.321-00");
        assertNotNull(clienteAtualizado);
        assertEquals("Maria Silva", clienteAtualizado.getNome());
    }

    @Test
    void testDeleteCliente() throws Exception {
        // Cria um cliente e o adiciona ao DAO
        Cliente cliente = new Cliente();
        cliente.setCpf("111.222.333-44");
        cliente.setNome("Carlos Silva");
        dao.addCliente(cliente);

        // Adiciona o cliente à tabela da view
        DefaultTableModel tableModel = (DefaultTableModel) view.getTbClientes().getModel();
        tableModel.addRow(new Object[]{"Carlos Silva", "111.222.333-44"});

        // Seleciona a linha do cliente na tabela
        view.getTbClientes().setRowSelectionInterval(0, 0);

        // Usa reflection para acessar o método privado deleteCliente()
        Method deleteMethod = ClienteController.class.getDeclaredMethod("deleteCliente");
        deleteMethod.setAccessible(true);
        deleteMethod.invoke(controller);

        // Verifica se o cliente foi excluído com sucesso
        Cliente clienteExcluido = dao.buscarPorCpf("111.222.333-44");
        assertNull(clienteExcluido);
    }

    @Test
    void testPesquisarCliente() throws Exception {
        // Cria clientes para serem adicionados ao DAO
        Cliente cliente1 = new Cliente();
        cliente1.setCpf("444.555.666-77");
        cliente1.setNome("Roberto Lima");

        Cliente cliente2 = new Cliente();
        cliente2.setCpf("888.999.000-11");
        cliente2.setNome("Roberta Alves");

        dao.addCliente(cliente1);
        dao.addCliente(cliente2);

        // Preenche o campo de pesquisa na view
        view.getTxtPesquisar().setText("Roberto");

        // Usa reflection para acessar o método privado pesquisarCliente()
        Method searchMethod = ClienteController.class.getDeclaredMethod("pesquisarCliente");
        searchMethod.setAccessible(true);
        searchMethod.invoke(controller);

        // Verifica se a pesquisa retornou o resultado esperado
        DefaultTableModel tableModel = (DefaultTableModel) view.getTbClientes().getModel();
        assertEquals(1, tableModel.getRowCount());
        assertEquals("Roberto Lima", tableModel.getValueAt(0, 0));
    }
}
