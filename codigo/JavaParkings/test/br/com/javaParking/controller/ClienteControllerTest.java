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

        dao.criarTabela();
    }

    @Test
    void testAddCliente() throws Exception {
        view.getTxtCPF().setText("123.456.789-00");
        view.getTxtNome().setText("João Silva");

        Method addMethod = ClienteController.class.getDeclaredMethod("addCliente");
        addMethod.setAccessible(true); 
        addMethod.invoke(controller); 

        Cliente cliente = dao.buscarPorCpf("123.456.789-00");
        assertNotNull(cliente);
        assertEquals("João Silva", cliente.getNome());
    }

    @Test
    void testEditarCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCpf("987.654.321-00");
        cliente.setNome("Maria Oliveira");
        dao.addCliente(cliente);

        view.getTxtCPF().setText("987.654.321-00");
        view.getTxtNome().setText("Maria Silva");

        Method editMethod = ClienteController.class.getDeclaredMethod("editarCliente");
        editMethod.setAccessible(true);
        editMethod.invoke(controller);

        Cliente clienteAtualizado = dao.buscarPorCpf("987.654.321-00");
        assertNotNull(clienteAtualizado);
        assertEquals("Maria Silva", clienteAtualizado.getNome());
    }

    @Test
    void testDeleteCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCpf("111.222.333-44");
        cliente.setNome("Carlos Silva");
        dao.addCliente(cliente);

        DefaultTableModel tableModel = (DefaultTableModel) view.getTbClientes().getModel();
        tableModel.addRow(new Object[]{"Carlos Silva", "111.222.333-44"});

        view.getTbClientes().setRowSelectionInterval(0, 0);

        Method deleteMethod = ClienteController.class.getDeclaredMethod("deleteCliente");
        deleteMethod.setAccessible(true);
        deleteMethod.invoke(controller);

        Cliente clienteExcluido = dao.buscarPorCpf("111.222.333-44");
        assertNull(clienteExcluido);
    }

    @Test
    void testPesquisarCliente() throws Exception {
        Cliente cliente1 = new Cliente();
        cliente1.setCpf("444.555.666-77");
        cliente1.setNome("Roberto Lima");

        Cliente cliente2 = new Cliente();
        cliente2.setCpf("888.999.000-11");
        cliente2.setNome("Roberta Alves");

        dao.addCliente(cliente1);
        dao.addCliente(cliente2);

        view.getTxtPesquisar().setText("Roberto");

        Method searchMethod = ClienteController.class.getDeclaredMethod("pesquisarCliente");
        searchMethod.setAccessible(true);
        searchMethod.invoke(controller);

        DefaultTableModel tableModel = (DefaultTableModel) view.getTbClientes().getModel();
        assertEquals(1, tableModel.getRowCount());
        assertEquals("Roberto Lima", tableModel.getValueAt(0, 0));
    }
}
