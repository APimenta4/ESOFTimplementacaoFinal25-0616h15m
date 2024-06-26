import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelarAnuidade extends JFrame {
    public CancelarAnuidade() {
        setTitle("Cancelar Anuidade - BIBLIOTECH");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel superior com o título e navegação
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel bibliotechPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel bibliotechLabel = new JLabel("BIBLIOTECH");
        bibliotechLabel.setFont(new Font("Serif", Font.BOLD, 24));
        bibliotechLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bibliotechLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new JanelaPrincipal().setVisible(true);
                dispose();
            }
        });
        bibliotechPanel.add(bibliotechLabel);
        topPanel.add(bibliotechPanel);

        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel backLabel = new JLabel("← ");
        backLabel.setFont(new Font("Serif", Font.BOLD, 50));
        backLabel.setForeground(new Color(51, 153, 255)); // Cor azul
        backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backLabel.setBorder(new EmptyBorder(0, 25, 0, 0)); // Adicionar margem à esquerda
        backLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Handle back click
                setVisible(false);
                new MenuSocios().setVisible(true);
            }
        });
        navigationPanel.add(backLabel);

        JLabel sociosLabel = new JLabel("Sócios/");
        sociosLabel.setFont(new Font("Serif", Font.BOLD, 25));
        sociosLabel.setForeground(new Color(51, 153, 255));
        sociosLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Adicionar margem à esquerda
        navigationPanel.add(sociosLabel);

        JLabel cancelarLabel = new JLabel("Cancelar anuidade");
        cancelarLabel.setFont(new Font("Serif", Font.BOLD, 25));
        cancelarLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(cancelarLabel);

        topPanel.add(navigationPanel);

        add(topPanel, BorderLayout.NORTH);

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Substituir as declarações de fonte atuais
        Font labelFont = new Font("Arial", Font.PLAIN, 20);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 20);

        // Labels e campos de texto
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        JLabel numeroSocioLabel = new JLabel("Número de sócio");
        numeroSocioLabel.setFont(labelFont);
        formPanel.add(numeroSocioLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField numeroSocioField = new JTextField();
        numeroSocioField.setFont(textFieldFont);
        numeroSocioField.setPreferredSize(new Dimension(300, 40)); // Tamanho aumentado
        formPanel.add(numeroSocioField, gbc);

        mainPanel.add(formPanel);

        // Botão de cancelamento
        JButton cancelButton = new JButton("Cancelar anuidade");
        cancelButton.setPreferredSize(new Dimension(250, 50)); // Setar tamanho preferido
        cancelButton.setMaximumSize(new Dimension(250, 50)); // Definir tamanho máximo
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numeroSocio;
                try {
                    numeroSocio = Integer.parseInt(numeroSocioField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de número de sócio inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check if a member with this number exists
                if (!Socios.getInstance().existsSocio(numeroSocio)) {
                    JOptionPane.showMessageDialog(null, "Não existe sócio com este número.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(Socios.getInstance().getSocioByNumero(numeroSocio).getAnuidadePaga() != 2024){
                    JOptionPane.showMessageDialog(null, "O sócio não tem anuidade paga.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Implementar a lógica para cancelar a anuidade do sócio
                Socio socio = Socios.getInstance().getSocioByNumero(numeroSocio);
                JOptionPane.showMessageDialog(null, "Anuidade cancelada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                // Limpar o campo após o cancelamento
                numeroSocioField.setText("");
                socio.cancelarAnuidade();
            }
        });

        mainPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Espaçamento
        mainPanel.add(cancelButton);

        add(mainPanel, BorderLayout.CENTER);
    }

    protected String cancelarAnuidade(int numeroSocio){
        // Implementar a lógica para cancelar a anuidade do sócio
        Socio socio = Socios.getInstance().getSocioByNumero(numeroSocio);

        if (!Socios.getInstance().existsSocio(numeroSocio)) {
            return "Não existe sócio com este número.";
        }

        if(socio.getAnuidadePaga() != 2024){
            return "O sócio não tem anuidade paga.";
        }

        socio.cancelarAnuidade();
        return "Anuidade cancelada com sucesso.";
    }

}
