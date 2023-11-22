package rocket.tfpoo;
import javax.swing.JOptionPane;

/**
 *
 * @author yasmin
 */
public class JFrameInserirVagao extends javax.swing.JFrame {
    private GaragemCarros gc;
    private Patio patio;
    /**
     * Creates new form JFrameInserirVagao
     */
    public JFrameInserirVagao() {
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
        comboTrem = new javax.swing.JComboBox<>();
        comboVagao = new javax.swing.JComboBox<>();
        buttonAdicionarVagao = new javax.swing.JButton();
        labelVagao = new javax.swing.JLabel();
        labelTrem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(241, 215, 204));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        comboTrem.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 178;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(163, 12, 0, 130);
        jPanel1.add(comboTrem, gridBagConstraints);

        comboVagao.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        comboVagao.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 178;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 12, 0, 130);
        jPanel1.add(comboVagao, gridBagConstraints);

        buttonAdicionarVagao.setBackground(new java.awt.Color(255, 153, 153));
        buttonAdicionarVagao.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        buttonAdicionarVagao.setForeground(new java.awt.Color(255, 255, 249));
        buttonAdicionarVagao.setText("Adicionar");
        buttonAdicionarVagao.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonAdicionarVagao.setDefaultCapable(false);
        buttonAdicionarVagao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdicionarVagaoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 69;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 266, 168, 0);
        jPanel1.add(buttonAdicionarVagao, gridBagConstraints);

        labelVagao.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        labelVagao.setForeground(new java.awt.Color(255, 255, 249));
        labelVagao.setText("Escolha um vagão:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 134, 0, 0);
        jPanel1.add(labelVagao, gridBagConstraints);

        labelTrem.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        labelTrem.setForeground(new java.awt.Color(255, 255, 249));
        labelTrem.setText("Escolha um trem:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(161, 149, 0, 0);
        jPanel1.add(labelTrem, gridBagConstraints);

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

    private void buttonAdicionarVagaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdicionarVagaoActionPerformed
        if(comboVagao.getSelectedItem() == null){
            JOptionPane.showMessageDialog(JFrameInserirVagao.this, "Nenhum vagão disponível.", "Erro", JOptionPane.ERROR_MESSAGE);
        }else if(comboTrem.getSelectedItem() == null){
            JOptionPane.showMessageDialog(JFrameInserirVagao.this, "Nenhum trem disponível.", "Erro", JOptionPane.ERROR_MESSAGE);
        }else{
            String selected = comboVagao.getSelectedItem().toString();
            String id = selected.substring(7, selected.length());
            int idVagao = Integer.parseInt(id);
            selected = comboTrem.getSelectedItem().toString();
            id = selected.substring(6, selected.length());
            int idTrem = Integer.parseInt(id);
            Vagao vagaoadd = (Vagao)gc.getCarro(idVagao);
            Trem tremadd = patio.getTrem(idTrem);
            boolean engatado = tremadd.engataVagao(vagaoadd,gc);
            if(engatado){
                int index = comboVagao.getSelectedIndex();
                comboVagao.removeItemAt(index);
                JOptionPane.showMessageDialog(JFrameInserirVagao.this, "Vagão inserido com sucesso!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
            }else{
              JOptionPane.showMessageDialog(JFrameInserirVagao.this, "Vagão pesado demais para o trem selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_buttonAdicionarVagaoActionPerformed
    public void infoCollector(GaragemCarros gc, Patio patio){
        this.gc = gc;
        this.patio = patio;
        for(Carro c:gc.garagemCarro){
            if(c instanceof Vagao){
                String item = "Vagão: "+c.id;
                comboVagao.addItem(item);
            }
        }
        for(Trem t:patio.trens){
            String item = "Trem: "+t.getId();
            comboTrem.addItem(item);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdicionarVagao;
    private javax.swing.JComboBox<String> comboTrem;
    private javax.swing.JComboBox<String> comboVagao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelTrem;
    private javax.swing.JLabel labelVagao;
    // End of variables declaration//GEN-END:variables
}
