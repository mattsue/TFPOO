package rocket.tfpoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CriarTremWindow extends JFrame {
    GaragemCarros gc = new GaragemCarros();
    Patio yard = new Patio();

    public CriarTremWindow(GaragemCarros gc) {
        this.gc = gc;

        setTitle("Criar Trem");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        setSize(1000, 200); // Tamanho da janela
        setLocationRelativeTo(null); // Centraliza a janela
        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent windowEvent) {
                readLocomotives();
            }
        });
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel labelTremId = new JLabel("Identificador do Trem:");
        JTextField textFieldTremId = new JTextField();

        JLabel labelLocomotiva = new JLabel("Escolha a Locomotiva:");
        JComboBox<Locomotiva> comboBoxLocomotiva = new JComboBox<>();

        JButton buttonCriarTrem = new JButton("Criar Trem");
        
        buttonCriarTrem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Criação do trem
                    int tremId = Integer.parseInt(textFieldTremId.getText());
        
                    // Check if the train ID is already in use
                    if (yard.verificaIdTrem(tremId)) {
                        JOptionPane.showMessageDialog(CriarTremWindow.this, "Identificador do trem já está em uso. Escolha um identificador único.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return; // Exit the method to avoid creating the train
                    }
        
                    Locomotiva selectedLocomotiva = (Locomotiva) comboBoxLocomotiva.getSelectedItem();
                    yard.criaTrem(tremId, selectedLocomotiva, gc);
        
                    JOptionPane.showMessageDialog(CriarTremWindow.this, "Trem criado com sucesso!", "Trem Criado", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CriarTremWindow.this, "Identificador do trem deve ser um número inteiro válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(labelTremId);
        panel.add(textFieldTremId);
        panel.add(labelLocomotiva);
        panel.add(comboBoxLocomotiva);
        panel.add(new JLabel()); // Espaço vazio
        panel.add(buttonCriarTrem);

        add(panel, BorderLayout.CENTER);
    }

    private void readLocomotives() {
        Leitor leitor = new Leitor(gc, yard);
        leitor.function();
        JComboBox<Locomotiva> comboBoxLocomotiva = findLocomotivaComboBox();
        comboBoxLocomotiva.removeAllItems();
        for (Carro carro : gc.garagemCarro) {
            if (carro instanceof Locomotiva) {
                comboBoxLocomotiva.addItem((Locomotiva) carro);
            }
        }
    }

    private JComboBox<Locomotiva> findLocomotivaComboBox() {
        Container container = getContentPane();
        for (Component component : container.getComponents()) {
            if (component instanceof JPanel) {
                for (Component panelComponent : ((JPanel) component).getComponents()) {
                    if (panelComponent instanceof JComboBox) {
                        return (JComboBox<Locomotiva>) panelComponent;
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GaragemCarros gc = new GaragemCarros();
            new CriarTremWindow(gc);
        });
    }
}
