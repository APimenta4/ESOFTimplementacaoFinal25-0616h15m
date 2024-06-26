import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultarHistoricoLivrosRequisitados extends JFrame {
    private JTextField memberNumberField;

    public ConsultarHistoricoLivrosRequisitados() {
        setTitle("Consultar histórico dos livros requisitados - BIBLIOTECH");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top panel with "BIBLIOTECH" label and navigation
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
        backLabel.setForeground(new Color(51, 153, 255)); // Blue color
        backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backLabel.setBorder(new EmptyBorder(0, 25, 0, 0)); // Add left margin
        backLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Handle back click
                setVisible(false);
                new MenuPesquisasEstatisticas().setVisible(true);
            }
        });
        navigationPanel.add(backLabel);

        JLabel reservaLabel = new JLabel("Pesquisas e estatísticas/");
        reservaLabel.setFont(new Font("Serif", Font.BOLD, 25));
        reservaLabel.setForeground(new Color(51, 153, 255));
        reservaLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Add left margin
        navigationPanel.add(reservaLabel);

        JLabel consultarLabel = new JLabel("Consultar histórico dos livros requisitados");
        consultarLabel.setFont(new Font("Serif", Font.BOLD, 25));
        consultarLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(consultarLabel);

        topPanel.add(navigationPanel);

        add(topPanel, BorderLayout.NORTH);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Labels and text fields
        Font labelFont = new Font("Arial", Font.PLAIN, 20);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 20);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        JLabel memberNumberLabel = new JLabel("Número do sócio");
        memberNumberLabel.setFont(labelFont);
        formPanel.add(memberNumberLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        memberNumberField = new JTextField();
        memberNumberField.setFont(textFieldFont);
        memberNumberField.setPreferredSize(new Dimension(300, 40)); // Increased size
        formPanel.add(memberNumberField, gbc);

        mainPanel.add(formPanel);

        // Search button
        JButton searchButton = new JButton("Ver histórico");
        searchButton.setPreferredSize(new Dimension(250, 50)); // Set the preferred width to 250 and height to 50
        searchButton.setMaximumSize(new Dimension(250, 50)); // Set maximum size for the button
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the text from the text field
                String memberNumber = memberNumberField.getText();

                try {
                    int requestedByInt = Integer.parseInt(memberNumber);
                    if (!Socios.getInstance().existsSocio(requestedByInt)) {
                        JOptionPane.showMessageDialog(null, "O número do sócio não existe.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Número do sócio inválido.");
                    return;
                }

                if (memberNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha o número do sócio.");
                } else {
                    // Continue with the search logic
                    ConsultarHistoricoLivrosRequisitados2 consultarHistoricoLivrosRequisitados2 = new ConsultarHistoricoLivrosRequisitados2(Integer.parseInt(memberNumber));
                    consultarHistoricoLivrosRequisitados2.setVisible(true);
                    setVisible(false);


                }
            }
        });
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        mainPanel.add(searchButton);

        add(mainPanel, BorderLayout.CENTER);
    }


}



