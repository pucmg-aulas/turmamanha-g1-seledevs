package br.com.javaParking.controller;

import br.com.javaParking.dao.ClienteDao;
import br.com.javaParking.model.Cliente;
import br.com.javaParking.util.validadores.CPFValidator;
import br.com.javaParking.view.cliente.ClienteView;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rafael
 */
public class ClienteController {

    private ClienteView view;
    private ClienteDao clientes;  // Alterado para usar ClienteDao
    private Cliente clienteSelecionado;

    public ClienteController() {
        this.clientes = new ClienteDao();  // Alterado para instanciar ClienteDao
        this.view = new ClienteView();

        this.view.getBtnAlterar().setBorderPainted(false);
        this.view.getBtnRemover().setBorderPainted(false);
        this.view.getBtnAdicionar().setBorderPainted(false);
        this.view.getBtnHistorico().setBorderPainted(false);
        this.view.getBtnVeiculo().setBorderPainted(false);
        this.view.getBtnVoltar().setBorderPainted(false);

        carregarTabela();

        this.view.getBtnVoltar().addActionListener((e) -> {
            new ArrecadacaoController();
            this.view.dispose();
        });
        
        this.view.getBtnAdicionar().addActionListener((e) -> {
            addCliente();
        });

        this.view.getBtnAlterar().addActionListener((e) -> {
            editarCliente();
        });

        this.view.getBtnRemover().addActionListener((e) -> {
            deleteCliente();
        });
        
        this.view.getBtnVeiculo().addActionListener((e) -> {
             new VeiculoController(clienteSelecionado);
        });

        this.view.getTbClientes().getSelectionModel().addListSelectionListener(e -> {
            int linha = this.view.getTbClientes().getSelectedRow();
            if (linha != -1) {
                String cpf = (String) view.getTbClientes().getValueAt(linha, 1);
                clienteSelecionado = clientes.buscarPorCpf(cpf);  // Alterado para usar buscarPorCpf

                if (clienteSelecionado != null) {
                    view.getTxtCPF().setText(clienteSelecionado.getCpf());
                    view.getTxtNome().setText(clienteSelecionado.getNome());

                    updateMode();
                }
            }
        });

        // Adiciona o DocumentListener para a pesquisa em tempo real
        this.view.getTxtPesquisar().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                pesquisarCliente();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                pesquisarCliente();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                pesquisarCliente();
            }
        });

        createMode();
        this.view.setVisible(true);
    }

    ClienteController(ClienteView clienteViewMock, ClienteDao clienteDaoMock) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Método para pesquisar cliente por nome parcial
    public void pesquisarCliente() {
        String nome = view.getTxtPesquisar().getText().trim();

        // Se o campo estiver vazio, carrega todos os clientes novamente
        if (nome.isEmpty()) {
            carregarTabela();
            return;
        }

        // Pesquisa os clientes pelo nome ou caracteres inseridos
        List<Cliente> clientesEncontrados = clientes.pesquisarPorNomeParcial(nome);

        // Atualiza a tabela com os resultados encontrados
        DefaultTableModel tm = new DefaultTableModel(new Object[]{"Nome", "CPF"}, 0);
        for (Cliente cliente : clientesEncontrados) {
            tm.addRow(new Object[]{cliente.getNome(), cliente.getCpf()});
        }

        view.getTbClientes().setModel(tm);
    }

    private void limparCampos() {
        this.view.getTxtCPF().setText("");
        this.view.getTxtNome().setText("");
    }

    private void createMode() {
        this.view.getBtnAlterar().setEnabled(false);
        this.view.getBtnRemover().setEnabled(false);
        this.view.getBtnAdicionar().setEnabled(true);
        this.view.getBtnHistorico().setEnabled(false);
        this.view.getBtnVeiculo().setEnabled(false);
        this.view.getTxtCPF().setEnabled(true);
    }

    private void updateMode() {
        this.view.getBtnAlterar().setEnabled(true);
        this.view.getBtnRemover().setEnabled(true);
        this.view.getBtnAdicionar().setEnabled(false);
        this.view.getBtnHistorico().setEnabled(true);
        this.view.getBtnVeiculo().setEnabled(true);
        this.view.getTxtCPF().setEnabled(false);
    }

    private void addCliente() {
        try {
            String cpf = view.getTxtCPF().getText();
            String nome = view.getTxtNome().getText();

            if (!cpf.isEmpty() && !nome.isEmpty()) {
                Cliente c = new Cliente();
                c.setNome(nome);

                if (CPFValidator.isCPF(cpf)) {
                    c.setCpf(cpf);
                } else {
                    JOptionPane.showMessageDialog(view, "CPF invalido!");
                    return;
                }

                clientes.addCliente(c);

                JOptionPane.showMessageDialog(view, "Cliente salvo com sucesso!");
                limparCampos();
                carregarTabela();
            } else {
                JOptionPane.showMessageDialog(view, "Campos nome e CPF são obrigatórios!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void editarCliente() {
        String nome = view.getTxtNome().getText();
        String cpf = view.getTxtCPF().getText();

        if (!CPFValidator.isCPF(cpf)) { 
            JOptionPane.showMessageDialog(view, "CPF invalido!");
            return;
        } 

        if (!cpf.isEmpty() && !nome.isEmpty()) {
            int op = JOptionPane.showConfirmDialog(view, "Deseja editar " + nome + "?");
            if (op == JOptionPane.YES_OPTION) {
                Cliente cliente = clientes.buscarPorCpf(cpf);
                cliente.setNome(nome);
                clientes.alterarCliente(cliente);
                JOptionPane.showMessageDialog(view, nome + " editado com sucesso!");
                carregarTabela();
                createMode();
                limparCampos();
            }
        }
    }

    private void deleteCliente() {
        int linha = this.view.getTbClientes().getSelectedRow();
        String nome = (String) this.view.getTbClientes().getValueAt(linha, 0);
        String cpf = (String) this.view.getTbClientes().getValueAt(linha, 1);

        int op = JOptionPane.showConfirmDialog(view, "Deseja excluir " + nome + "?");
        if (op == JOptionPane.YES_OPTION) {
            Cliente cliente = ClienteDao.buscarPorCpf(cpf);
            if (cliente == null) {
                JOptionPane.showMessageDialog(view, "Cliente não encontrado.");
                return;
            }

            // Exclui o cliente pelo CPF
            ClienteDao.excluirCliente(ClienteDao.buscarPorCpf(cpf).getId());
            JOptionPane.showMessageDialog(view, nome + " excluído com sucesso!");
            carregarTabela();
            createMode();
            limparCampos();
        }
    }

    private void carregarTabela() {
        Object colunas[] = {"Nome", "CPF"};
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);
        tm.setNumRows(0);

        for (Cliente cliente : clientes.listarClientes()) {  // Alterado para listarClientes
            String linha[] = {cliente.getNome(), cliente.getCpf()};
            tm.addRow(linha);
        }
        view.getTbClientes().setModel(tm);
    }
}
