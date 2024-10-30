import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.*;

public class calculoVaga extends JFrame {
    private JTextField txtEntrada;
    private JTextField txtSaida;
    private JLabel lblResultado;
    private final double VALOR_POR_HORA = 10.0; // valor que se precisar, mudamos depois
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public calculoVaga() {
        super("Valor a Pagar");

        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel tablePanel = new JPanel(new GridLayout(3, 2, 10, 10));
        tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblEntrada = new JLabel("Horário de Entrada (HH:mm):");
        txtEntrada = new JTextField(5);
        
        JLabel lblSaida = new JLabel("Horário de Saída (HH:mm):");
        txtSaida = new JTextField(5);

        lblResultado = new JLabel("Total a pagar: R$ 0.00");

        JButton btnCalcular = new JButton("Calcular");

        // Adicionar elementos na tabela
        tablePanel.add(lblEntrada);
        tablePanel.add(txtEntrada);
        tablePanel.add(lblSaida);
        tablePanel.add(txtSaida);
        tablePanel.add(new JLabel("")); 
        tablePanel.add(btnCalcular);

        add(tablePanel, BorderLayout.CENTER);
        add(lblResultado, BorderLayout.SOUTH);

        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularValor();
            }
        });
    }

    private void calcularValor() {
        try {
            // Converter as entradas de texto em horários
            LocalTime horarioEntrada = LocalTime.parse(txtEntrada.getText(), timeFormatter);
            LocalTime horarioSaida = LocalTime.parse(txtSaida.getText(), timeFormatter);

            // Calcular a duração em horas
            Duration duracao = Duration.between(horarioEntrada, horarioSaida);
            double horas = duracao.toMinutes() / 60.0;

            if (horas < 0) {
                JOptionPane.showMessageDialog(this, "O horário de saída deve ser depois do horário de entrada.");
                return;
            }

            // Calcular o valor total
            double total = horas * VALOR_POR_HORA;
            lblResultado.setText("Total a pagar: R$ " + String.format("%.2f", total));

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira os horários no formato HH:mm.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
           calculoVaga calculadora = new calculoVaga();
            calculadora.setVisible(true);
        });
    }
}
