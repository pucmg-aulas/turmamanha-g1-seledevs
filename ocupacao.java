import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ocupacao extends JFrame {

    private JComboBox<String> parqueSelect;
    private JComboBox<String> tipoVagaSelect;
    private JButton confirmarButton;
    private JButton liberarButton; 
    private JButton voltarButton;
    private JButton selecionarVagaButton; // Novo botão para selecionar a vaga
    private JTable vagasTable;
    private DefaultTableModel tableModel;
    private JPanel selectionPanel, vagasPanel, summaryPanel;
    private CardLayout cardLayout;
    private EstacionamentoController controller; // Controlador do estacionamento

    public ocupacao() {
        setTitle("Sistema de Estacionamento");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new CardLayout());

        controller = new EstacionamentoController(); // Inicializa o controlador

        // Inicializar painéis
        initSelectionPanel();
        initSummaryPanel();
        initVagasPanel();

        // Adicionar painéis ao CardLayout
        add(selectionPanel, "selectionPanel");
        add(vagasPanel, "vagasPanel");

        cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "selectionPanel");
    }
    
    private void initSelectionPanel() {
        selectionPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        selectionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        selectionPanel.setBackground(new Color(242, 242, 242));

        parqueSelect = new JComboBox<>(new String[]{"Chume Park", "Parque Guanabara", "Parque Festa"});
        Dimension comboBoxSize = new Dimension(15, 7); // Tamanho ainda menor
        parqueSelect.setPreferredSize(comboBoxSize);
        parqueSelect.setMinimumSize(comboBoxSize);
        parqueSelect.setMaximumSize(comboBoxSize);
        
        JButton selecionarParqueButton = new JButton("Selecionar Parque");
        selecionarParqueButton.setPreferredSize(comboBoxSize);
        selecionarParqueButton.setMinimumSize(comboBoxSize);
        selecionarParqueButton.setMaximumSize(comboBoxSize);

        selecionarParqueButton.addActionListener(e -> {
            cardLayout.show(getContentPane(), "vagasPanel");
            updateSummaryPanel();
            updateTable(controller.getVagas((String) tipoVagaSelect.getSelectedItem())); // Atualiza as vagas ao selecionar o parque
        });

        selectionPanel.add(new JLabel("Selecione o Parque:"));
        selectionPanel.add(parqueSelect);
        selectionPanel.add(new JLabel());  // Placeholder
        selectionPanel.add(selecionarParqueButton);
    }

    private void initVagasPanel() {
        vagasPanel = new JPanel(new BorderLayout());
        vagasPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        tipoVagaSelect = new JComboBox<>(new String[]{"Comum", "VIP", "Idoso"});
        selecionarVagaButton = new JButton("Selecionar Vaga");
        confirmarButton = new JButton("Confirmar Seleção");
        confirmarButton.setBackground(new Color(76, 175, 80));
        confirmarButton.setForeground(Color.WHITE);
        confirmarButton.addActionListener(e -> reservarVaga());

        liberarButton = new JButton("Liberar Vaga"); // Inicialização do botão de liberar
        liberarButton.setBackground(new Color(244, 67, 54));
        liberarButton.setForeground(Color.WHITE);
        liberarButton.addActionListener(e -> liberarVaga());

        voltarButton = new JButton("Voltar");
        voltarButton.setBackground(new Color(51, 51, 51));
        voltarButton.setForeground(Color.WHITE);
        voltarButton.addActionListener(e -> cardLayout.show(getContentPane(), "selectionPanel"));

        JPanel topPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        topPanel.add(tipoVagaSelect);
        topPanel.add(selecionarVagaButton); // Adiciona o botão de selecionar vaga
        topPanel.add(confirmarButton);
        topPanel.add(liberarButton); 
        topPanel.add(voltarButton);

        tableModel = new DefaultTableModel(new Object[]{"Identificador", "Status"}, 0);
        vagasTable = new JTable(tableModel);
        vagasTable.setRowHeight(25);
        vagasTable.setFont(new Font("Arial", Font.PLAIN, 14));
        vagasTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        vagasTable.getTableHeader().setBackground(new Color(51, 51, 51));
        vagasTable.getTableHeader().setForeground(Color.WHITE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        vagasTable.setDefaultRenderer(Object.class, centerRenderer);

        JPanel tableContainer = new JPanel(new BorderLayout());
        tableContainer.add(new JScrollPane(vagasTable), BorderLayout.CENTER);

        vagasPanel.add(topPanel, BorderLayout.NORTH);
        vagasPanel.add(tableContainer, BorderLayout.CENTER);
        vagasPanel.add(summaryPanel, BorderLayout.SOUTH);  

        // Ação do botão de selecionar vaga
        selecionarVagaButton.addActionListener(e -> selecionarVaga());
    }

    private void initSummaryPanel() {
        summaryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        summaryPanel.setBackground(new Color(51, 51, 51));
    }

    private void updateSummaryPanel() {
        summaryPanel.removeAll();

        int totalVagas = controller.getTotalVagas();
        int ocupadas = controller.getVagasOcupadas();
        int livres = totalVagas - ocupadas;

        JLabel totalLabel = new JLabel("Total de Vagas: " + totalVagas);
        JLabel ocupadasLabel = new JLabel("Ocupadas: " + ocupadas);
        JLabel livresLabel = new JLabel("Livres: " + livres);

        totalLabel.setForeground(Color.WHITE);
        ocupadasLabel.setForeground(Color.RED);
        livresLabel.setForeground(new Color(76, 175, 80));

        summaryPanel.add(totalLabel);
        summaryPanel.add(ocupadasLabel);
        summaryPanel.add(livresLabel);
        summaryPanel.revalidate();
        summaryPanel.repaint();
    }

    private void reservarVaga() {
        String tipoVaga = (String) tipoVagaSelect.getSelectedItem();
        int selectedRow = vagasTable.getSelectedRow(); // Obtém a linha selecionada

        if (selectedRow != -1) {
            Vaga vagaSelecionada = controller.getVagas(tipoVaga).get(selectedRow);
            try {
                controller.reservarVaga(vagaSelecionada);
                updateTable(controller.getVagas(tipoVaga)); // Atualiza a tabela com as vagas do controlador
                updateSummaryPanel();
                JOptionPane.showMessageDialog(this, "Vaga reservada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalStateException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma vaga na tabela.", "Erro", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void liberarVaga() {
        String tipoVaga = (String) tipoVagaSelect.getSelectedItem();
        int selectedRow = vagasTable.getSelectedRow(); // Obtém a linha selecionada

        if (selectedRow != -1) {
            Vaga vagaSelecionada = controller.getVagas(tipoVaga).get(selectedRow);
            try {
                controller.liberarVaga(vagaSelecionada);
                updateTable(controller.getVagas(tipoVaga)); // Atualiza a tabela com as vagas do controlador
                updateSummaryPanel();
                JOptionPane.showMessageDialog(this, "Vaga liberada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalStateException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma vaga na tabela.", "Erro", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void selecionarVaga() {
        String tipoVaga = (String) tipoVagaSelect.getSelectedItem();
        updateTable(controller.getVagas(tipoVaga)); // Atualiza a tabela com as vagas do controlador
    }

    private void updateTable(ArrayList<Vaga> vagas) {
        tableModel.setRowCount(0);
        for (Vaga vaga : vagas) {
            String statusColor = vaga.getStatus().equals("Livre") ? "<font color='green'>" : "<font color='red'>";
            tableModel.addRow(new Object[]{vaga.getIdentificador(), "<html>" + statusColor + vaga.getStatus() + "</font></html>"});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ocupacao().setVisible(true));
    }
}

class Vaga {
    private final String identificador;
    private String status;

    public Vaga(String identificador, String status) {
        this.identificador = identificador;
        this.status = status;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

class EstacionamentoController {
    private final ArrayList<Vaga> vagasComum;
    private final ArrayList<Vaga> vagasVIP;
    private final ArrayList<Vaga> vagasIdosos;

    public EstacionamentoController() {
        vagasComum = new ArrayList<>();
        vagasVIP = new ArrayList<>();
        vagasIdosos = new ArrayList<>();

        // Inicializa as vagas
        for (int i = 1; i <= 10; i++) {
            vagasComum.add(new Vaga("Comum " + i, "Livre"));
            vagasVIP.add(new Vaga("VIP " + i, "Livre"));
            vagasIdosos.add(new Vaga("Idoso " + i, "Livre"));
        }
    }

    public ArrayList<Vaga> getVagas(String tipo) {
        switch (tipo) {
            case "Comum":
                return vagasComum;
            case "VIP":
                return vagasVIP;
            case "Idoso":
                return vagasIdosos;
            default:
                throw new IllegalArgumentException("Tipo de vaga inválido.");
        }
    }

    public void reservarVaga(Vaga vaga) {
        if (vaga.getStatus().equals("Livre")) {
            vaga.setStatus("Ocupado");
        } else {
            throw new IllegalStateException("Vaga já ocupada.");
        }
    }

    public void liberarVaga(Vaga vaga) {
        if (vaga.getStatus().equals("Ocupado")) {
            vaga.setStatus("Livre");
        } else {
            throw new IllegalStateException("Vaga já livre.");
        }
    }

    public int getTotalVagas() {
        return vagasComum.size() + vagasVIP.size() + vagasIdosos.size();
    }

    public int getVagasOcupadas() {
        return (int) (vagasComum.stream().filter(vaga -> vaga.getStatus().equals("Ocupado")).count() +
                      vagasVIP.stream().filter(vaga -> vaga.getStatus().equals("Ocupado")).count() +
                      vagasIdosos.stream().filter(vaga -> vaga.getStatus().equals("Ocupado")).count());
    }
}

