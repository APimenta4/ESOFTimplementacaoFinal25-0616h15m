import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsultarEmprestimosSocio extends JFrame {
    public ConsultarEmprestimosSocio() {
        setTitle("Consultar Empréstimos - BIBLIOTECH");
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
                new MenuEmprestimos().setVisible(true);
            }
        });
        navigationPanel.add(backLabel);

        JLabel emprestimosLabel = new JLabel("Empréstimos/");
        emprestimosLabel.setFont(new Font("Serif", Font.BOLD, 25));
        emprestimosLabel.setForeground(new Color(51, 153, 255));
        emprestimosLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Add left margin
        navigationPanel.add(emprestimosLabel);

        JLabel consultarLabel = new JLabel("Consultar empréstimo");
        consultarLabel.setFont(new Font("Serif", Font.BOLD, 25));
        consultarLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(consultarLabel);

        topPanel.add(navigationPanel);

        add(topPanel, BorderLayout.NORTH);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Replace the current font declarations
        Font labelFont = new Font("Arial", Font.PLAIN, 20);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 20);

        // Labels and text fields
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
        numeroSocioField.setPreferredSize(new Dimension(300, 40)); // Increased size
        formPanel.add(numeroSocioField, gbc);

        mainPanel.add(formPanel);

        // Search button
        JButton searchButton = new JButton("Pesquisar empréstimos");
        searchButton.setPreferredSize(new Dimension(250, 50)); // Set the preferred width to 250 and height to 50
        searchButton.setMaximumSize(new Dimension(250, 50)); // Set maximum size for the button
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        List<Socio> socios = Socios.getInstance().getSocios();
        List<Emprestimo> emprestimos = Emprestimos.getInstance().getEmprestimos();
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the text from the text box
                String numeroSocioText = numeroSocioField.getText();

                // Convert the text to an integer
                int numeroSocio;
                try {
                    numeroSocio = Integer.parseInt(numeroSocioText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "O número de sócio especificado é inválido.");
                    return;
                }

                // Find the Socio with the given number
                Socio socio = null;
                for (Socio s : socios) { // Assuming 'socios' is the list of all Socio objects
                    if (s.getNumeroDeSocio() == numeroSocio) {
                        socio = s;
                        break;
                    }
                }

                // If no Socio was found, show an error message
                if (socio == null) {
                    JOptionPane.showMessageDialog(null, "O sócio especificado não existe");
                    return;
                }

                // Check if the Socio has any Emprestimos.Emprestimo
                boolean hasEmprestimo = false;
                for (Emprestimo emprestimo : emprestimos) { // Assuming 'emprestimos' is the list of all Emprestimos.Emprestimo objects
                    if (emprestimo.getSocio().equals(socio)) {
                        hasEmprestimo = true;
                        break;
                    }
                }

                // If the Socio doesn't have any Emprestimos.Emprestimo, show an error message
                if (!hasEmprestimo) {
                    JOptionPane.showMessageDialog(null, "O sócio especificado não possui empréstimos");
                    return;
                }

                // Show the ConsultarEmprestimos page with the found Socio as a parameter
                setVisible(false);
                new ConsultarEmprestimos(socio).setVisible(true);
            }
        });

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        mainPanel.add(searchButton);

        add(mainPanel, BorderLayout.CENTER);
    }


}
