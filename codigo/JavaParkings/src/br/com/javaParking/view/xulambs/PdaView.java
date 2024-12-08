/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.javaParking.view.xulambs;

import br.com.javaParking.assets.swing.botaoArredondado;
import br.com.javaParking.model.Arrecadacao;
import br.com.javaParking.view.parque.ParqueView;
import br.com.javaParking.view.xulambs.ArrecadacaoView;
import br.com.javaParking.view.veiculo.VeiculosRegistradosView;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 *
 * @author Leandro Alencar
 */
public class PdaView extends javax.swing.JFrame {

    /**
     * Creates new form PdaView
     */
    public PdaView() {
        initComponents();
    }

    public botaoArredondado getBtnConcluir() {
        return btnConcluir;
    }

    public void setBtnConcluir(botaoArredondado btnConcluir) {
        this.btnConcluir = btnConcluir;
    }

    public JButton getBtnVoltar() {
        return btnVoltar1;
    }

    public void setBtnVoltar(JButton btnVoltar) {
        this.btnVoltar1 = btnVoltar;
    }

    public JLabel getLblPesquisa() {
        return lblPesquisa;
    }

    public void setLblPesquisa(JLabel lblPesquisa) {
        this.lblPesquisa = lblPesquisa;
    }

    public JLabel getLblPesquisarCliente() {
        return lblPesquisarCliente;
    }

    public void setLblPesquisarCliente(JLabel lblPesquisarCliente) {
        this.lblPesquisarCliente = lblPesquisarCliente;
    }

    public JTable getTbVagasDesocupadas() {
        return tbVagasDesocupadas;
    }

    public void setTbVagasDesocupadas(JTable tbVagasDesocupadas) {
        this.tbVagasDesocupadas = tbVagasDesocupadas;
    }

    public JTable getTbVagasOcupadas() {
        return tbVagasOcupadas;
    }

    public void setTbVagasOcupadas(JTable tbVagasOcupadas) {
        this.tbVagasOcupadas = tbVagasOcupadas;
    }

    public static JLabel getLblValorTotal() {
        return lblValorTotal;
    }

    public static void setLblValorTotal(JLabel lblValorTotal) {
        PdaView.lblValorTotal = lblValorTotal;
    }

    public static JLabel getLblMinutosPassados() {
        return lblMinutosPassados;
    }

    public static void setLblMinutosPassados(JLabel lblMinutosPassados) {
        PdaView.lblMinutosPassados = lblMinutosPassados;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        pnTbPrincipal = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        scPdv = new javax.swing.JScrollPane();
        tbVagasDesocupadas = new javax.swing.JTable();
        lblPesquisa = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        pnNota = new javax.swing.JPanel();
        pnTbClientes = new javax.swing.JPanel();
        lblPesquisarCliente = new javax.swing.JLabel();
        txtPesquisarCliente = new javax.swing.JTextField();
        pnCliente = new javax.swing.JPanel();
        scCliente = new javax.swing.JScrollPane();
        tbVagasOcupadas = new javax.swing.JTable();
        btnVoltar = new javax.swing.JButton();
        btnVoltar1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();
        lblValorTotal = new javax.swing.JLabel();
        btnConcluir = new br.com.javaParking.assets.swing.botaoArredondado();
        lblMinutosPassados = new javax.swing.JLabel();
        lblTotal1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Xumlabs Parking");

        jPanel4.setBackground(java.awt.SystemColor.control);
        jPanel4.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(204, 204, 204))));

        pnTbPrincipal.setBackground(java.awt.SystemColor.control);
        pnTbPrincipal.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));

        jPanel9.setBackground(java.awt.SystemColor.control);
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(153, 153, 153)), "Vagas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 3, 12))); // NOI18N

        scPdv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scPdvMouseClicked(evt);
            }
        });

        tbVagasDesocupadas = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbVagasDesocupadas.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tbVagasDesocupadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbVagasDesocupadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVagasDesocupadasMouseClicked(evt);
            }
        });
        scPdv.setViewportView(tbVagasDesocupadas);

        lblPesquisa.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        lblPesquisa.setText("Pesquisa:");
        lblPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblPesquisaKeyPressed(evt);
            }
        });

        txtPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisaActionPerformed(evt);
            }
        });
        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scPdv, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lblPesquisa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPesquisa))
                .addGap(16, 16, 16)
                .addComponent(scPdv, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pnNota.setBackground(java.awt.SystemColor.control);
        pnNota.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(153, 153, 153)));

        pnTbClientes.setBackground(java.awt.SystemColor.control);
        pnTbClientes.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(153, 153, 153)));

        lblPesquisarCliente.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        lblPesquisarCliente.setText("Pesquisar:");

        txtPesquisarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarClienteActionPerformed(evt);
            }
        });
        txtPesquisarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarClienteKeyReleased(evt);
            }
        });

        pnCliente.setBackground(java.awt.SystemColor.control);
        pnCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(153, 153, 153)), "Ocupações", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 3, 12))); // NOI18N

        tbVagasOcupadas = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbVagasOcupadas.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tbVagasOcupadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbVagasOcupadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVagasOcupadasMouseClicked(evt);
            }
        });
        scCliente.setViewportView(tbVagasOcupadas);

        javax.swing.GroupLayout pnClienteLayout = new javax.swing.GroupLayout(pnCliente);
        pnCliente.setLayout(pnClienteLayout);
        pnClienteLayout.setHorizontalGroup(
            pnClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
        );
        pnClienteLayout.setVerticalGroup(
            pnClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
        );

        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/javaParking/assets/img/iconeSetaEsquerda-00.png"))); // NOI18N
        btnVoltar.setToolTipText("");
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnVoltar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        btnVoltar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/javaParking/assets/img/iconeSetaEsquerda-00.png"))); // NOI18N
        btnVoltar1.setToolTipText("");
        btnVoltar1.setContentAreaFilled(false);
        btnVoltar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnVoltar1.setPreferredSize(new java.awt.Dimension(80, 80));
        btnVoltar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnTbClientesLayout = new javax.swing.GroupLayout(pnTbClientes);
        pnTbClientes.setLayout(pnTbClientesLayout);
        pnTbClientesLayout.setHorizontalGroup(
            pnTbClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTbClientesLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblPesquisarCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPesquisarCliente)
                .addGap(18, 18, 18)
                .addComponent(btnVoltar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(pnCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnTbClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnTbClientesLayout.createSequentialGroup()
                    .addGap(210, 210, 210)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(211, Short.MAX_VALUE)))
        );
        pnTbClientesLayout.setVerticalGroup(
            pnTbClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTbClientesLayout.createSequentialGroup()
                .addGroup(pnTbClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTbClientesLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(pnTbClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPesquisarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPesquisarCliente))
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTbClientesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnVoltar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(pnCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
            .addGroup(pnTbClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnTbClientesLayout.createSequentialGroup()
                    .addGap(182, 182, 182)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(183, Short.MAX_VALUE)))
        );

        jPanel8.setBackground(java.awt.SystemColor.control);
        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(153, 153, 153)));

        lblTotal.setFont(new java.awt.Font("Arial", 3, 36)); // NOI18N
        lblTotal.setText("Total: R$");

        lblValorTotal.setFont(new java.awt.Font("Arial", 3, 36)); // NOI18N
        lblValorTotal.setForeground(new java.awt.Color(0, 153, 0));
        lblValorTotal.setText("0.00");

        btnConcluir.setBackground(new java.awt.Color(51, 153, 0));
        btnConcluir.setForeground(new java.awt.Color(0, 0, 153));
        btnConcluir.setText("Concluir");
        btnConcluir.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N

        lblMinutosPassados.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        lblMinutosPassados.setForeground(new java.awt.Color(0, 51, 204));
        lblMinutosPassados.setText("0.00");

        lblTotal1.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        lblTotal1.setText("Minutos passados: ");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lblTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblValorTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addComponent(btnConcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTotal1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMinutosPassados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(lblValorTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal1)
                    .addComponent(lblMinutosPassados))
                .addGap(16, 16, 16)
                .addComponent(btnConcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout pnNotaLayout = new javax.swing.GroupLayout(pnNota);
        pnNota.setLayout(pnNotaLayout);
        pnNotaLayout.setHorizontalGroup(
            pnNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnTbClientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnNotaLayout.setVerticalGroup(
            pnNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnNotaLayout.createSequentialGroup()
                .addComponent(pnTbClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnTbPrincipalLayout = new javax.swing.GroupLayout(pnTbPrincipal);
        pnTbPrincipal.setLayout(pnTbPrincipalLayout);
        pnTbPrincipalLayout.setHorizontalGroup(
            pnTbPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTbPrincipalLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(pnNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        pnTbPrincipalLayout.setVerticalGroup(
            pnTbPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnNota, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnTbPrincipalLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(pnTbPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnTbPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(816, 609));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarClienteActionPerformed

    private void txtPesquisarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarClienteKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtPesquisarClienteKeyReleased

    private void tbVagasOcupadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVagasOcupadasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbVagasOcupadasMouseClicked

    private void btnConcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConcluirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConcluirActionPerformed

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisaKeyReleased

    private void txtPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisaActionPerformed

    private void scPdvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scPdvMouseClicked

    }//GEN-LAST:event_scPdvMouseClicked

    private void tbVagasDesocupadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVagasDesocupadasMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tbVagasDesocupadasMouseClicked

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnVoltar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVoltar1ActionPerformed

    private void lblPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblPesquisaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblPesquisaKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private br.com.javaParking.assets.swing.botaoArredondado btnConcluir;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JButton btnVoltar1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    public static javax.swing.JLabel lblMinutosPassados;
    private javax.swing.JLabel lblPesquisa;
    private javax.swing.JLabel lblPesquisarCliente;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotal1;
    public static javax.swing.JLabel lblValorTotal;
    private javax.swing.JPanel pnCliente;
    private javax.swing.JPanel pnNota;
    private javax.swing.JPanel pnTbClientes;
    private javax.swing.JPanel pnTbPrincipal;
    private javax.swing.JScrollPane scCliente;
    private javax.swing.JScrollPane scPdv;
    private javax.swing.JTable tbVagasDesocupadas;
    private javax.swing.JTable tbVagasOcupadas;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JTextField txtPesquisarCliente;
    // End of variables declaration//GEN-END:variables
}
