package rocket.tfpoo;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduarda
 */
public class JFrameCriarTrem extends javax.swing.JFrame {
    private GaragemCarros gc;
    private Patio patio;
    private Leitor leitor;
    /**
     * Creates new form JFrameCriarTrem
     */
    public JFrameCriarTrem() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboLocomotiva = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        caixaIdTrem = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(241, 215, 204));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Escolha a locomotiva:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(39, 190, 0, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 175;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 190, 0, 0);
        jPanel1.add(comboLocomotiva, gridBagConstraints);

        jButton1.setBackground(new java.awt.Color(255, 153, 153));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Criar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 280, 63, 0);
        jPanel1.add(jButton1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Identificador do trem:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(93, 190, 0, 221);
        jPanel1.add(jLabel2, gridBagConstraints);

        caixaIdTrem.setColumns(5);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 54;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 274, 0, 0);
        jPanel1.add(caixaIdTrem, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // pegar id do trem através da caixa de texto
        caixaIdTrem.setVisible(true);
        if(comboLocomotiva.getSelectedItem() == null){
            JOptionPane.showMessageDialog(JFrameCriarTrem.this, "Nenhuma locomotiva disponível.", "Mensagem", JOptionPane.ERROR_MESSAGE);
        }else{
            if (caixaIdTrem.getText().isEmpty()) {
                JOptionPane.showMessageDialog(JFrameCriarTrem.this, "Por favor, informe o identificador do trem.", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                caixaIdTrem.requestFocus();
            } else {
                String idTrem = caixaIdTrem.getText();
                String id = idTrem.substring(0, idTrem.length());

                // tratamento de erro para entrada de dados (id do trem)
                try {
                    int tremId = Integer.parseInt(id);
                    // pegar id da lococmotiva e transformar em objeto
                    String locoSelecionada = comboLocomotiva.getSelectedItem().toString();
                    String idLoco = locoSelecionada .substring(12, locoSelecionada .length());
                    int idLocomotiva = Integer.parseInt(idLoco);
                    Locomotiva locoadd = (Locomotiva) gc.getCarro(idLocomotiva);
                    // Checa se o identificador do trem já está em uso
                    if (patio.verificaIdTrem(tremId)) {
                        JOptionPane.showMessageDialog(JFrameCriarTrem.this, "Identificador do trem já está em uso. Escolha um identificador único.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return; // Sai do método
                    }    
                    patio.criaTrem(tremId, locoadd, gc);
                    leitor.reescreverComposicao();
                    int index = comboLocomotiva.getSelectedIndex();
                    comboLocomotiva.removeItemAt(index);
                    JOptionPane.showMessageDialog(JFrameCriarTrem.this, "Trem criado com sucesso!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                }catch (java.lang.NumberFormatException ex){
                    JOptionPane.showMessageDialog(JFrameCriarTrem.this, "Identificador do trem deve ser um número inteiro válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }  
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void infoCollector(GaragemCarros gc, Patio patio){
        this.gc = gc;
        this.patio = patio;
        leitor = new Leitor(gc, patio);
        for(Carro c : gc.garagemCarro){
            if(c instanceof Locomotiva){
                String item = "Locomotiva: "+c.id;
                comboLocomotiva.addItem(item);
            }
        }
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField caixaIdTrem;
    private javax.swing.JComboBox<String> comboLocomotiva;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
