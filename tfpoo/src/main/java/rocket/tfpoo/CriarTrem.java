package rocket.tfpoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CriarTrem extends JPanel {
    private GaragemCarros gc;
    private Patio yard;

    public CriarTrem(GaragemCarros gc, Patio yard) {
        this.gc = gc;
        this.yard = yard;

        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEtchedBorder());

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

                    // Checa se o identificador do trem já está em uso
                    if (yard.verificaIdTrem(tremId)) {
                        JOptionPane.showMessageDialog(CriarTrem.this, "Identificador do trem já está em uso. Escolha um identificador único.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return; // Sai do método
                    }

                    Locomotiva selectedLocomotiva = (Locomotiva) comboBoxLocomotiva.getSelectedItem();
                    yard.criaTrem(tremId, selectedLocomotiva, gc);

                    JOptionPane.showMessageDialog(CriarTrem.this, "Trem criado com sucesso!", "Trem Criado", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CriarTrem.this, "Identificador do trem deve ser um número inteiro válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        formPanel.add(labelTremId);
        formPanel.add(textFieldTremId);
        formPanel.add(labelLocomotiva);
        formPanel.add(comboBoxLocomotiva);
        formPanel.add(new JLabel()); // Espaço vazio
        formPanel.add(buttonCriarTrem);

        add(formPanel, BorderLayout.CENTER);
    }

    public void readLocomotives() {
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
        Component[] components = getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                Component[] subComponents = ((JPanel) component).getComponents();
                for (Component subComponent : subComponents) {
                    if (subComponent instanceof JComboBox) {
                        return (JComboBox<Locomotiva>) subComponent;
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GaragemCarros gc = new GaragemCarros();
            Patio yard = new Patio();
            JFrame frame = new JFrame("Criar Trem");
            CriarTrem criarTremPanel = new CriarTrem(gc, yard);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 200);
            frame.add(criarTremPanel);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);

            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowOpened(java.awt.event.WindowEvent windowEvent) {
                    criarTremPanel.readLocomotives();
                }
            });
        });
    }
}
