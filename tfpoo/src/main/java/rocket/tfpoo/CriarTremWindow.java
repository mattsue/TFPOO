package rocket.tfpoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CriarTremWindow extends JFrame {
    private GaragemCarros garagemCarros;
    Patio patio = new Patio();

    public CriarTremWindow(GaragemCarros garagemCarros) {
        this.garagemCarros = garagemCarros;

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
                // Criação do trem
                int tremId = Integer.parseInt(textFieldTremId.getText());
                Locomotiva selectedLocomotiva = (Locomotiva) comboBoxLocomotiva.getSelectedItem();
                patio.criaTrem(tremId,selectedLocomotiva,garagemCarros);
                //TODO: Verificar se o trem foi criado com sucesso!

                JOptionPane.showMessageDialog(CriarTremWindow.this, "Trem criado com sucesso!", "Trem Criado", JOptionPane.INFORMATION_MESSAGE);
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
        Leitor leitor = new Leitor(garagemCarros, null);
        leitor.function();
        JComboBox<Locomotiva> comboBoxLocomotiva = findLocomotivaComboBox();
        comboBoxLocomotiva.removeAllItems();
        for (Carro carro : garagemCarros.garagemCarro) {
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
            GaragemCarros garagemCarros = new GaragemCarros();
            new CriarTremWindow(garagemCarros);
        });
    }
}
