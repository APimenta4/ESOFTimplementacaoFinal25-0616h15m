import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnularReserva extends JFrame {
    public AnularReserva() {
        setTitle("Anular Reserva - BIBLIOTECH");
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
                new MenuReservas().setVisible(true);
            }
        });
        navigationPanel.add(backLabel);

        JLabel reservaLabel = new JLabel("Reservas");
        reservaLabel.setFont(new Font("Serif", Font.BOLD, 25));
        reservaLabel.setForeground(new Color(51, 153, 255));
        reservaLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Add left margin
        navigationPanel.add(reservaLabel);

        JLabel anularLabel = new JLabel("/Anular reserva");
        anularLabel.setFont(new Font("Serif", Font.BOLD, 25));
        anularLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(anularLabel);

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
        JLabel reservaNumLabel = new JLabel("Número da reserva");
        reservaNumLabel.setFont(labelFont);
        formPanel.add(reservaNumLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField reservaNumField = new JTextField();
        reservaNumField.setFont(textFieldFont);
        reservaNumField.setPreferredSize(new Dimension(300, 40)); // Set size
        formPanel.add(reservaNumField, gbc);

        mainPanel.add(formPanel);

        // Confirm button
        JButton confirmButton = new JButton("Anular reserva");
        confirmButton.setPreferredSize(new Dimension(250, 50)); // Set the preferred width to 250 and height to 50
        confirmButton.setMaximumSize(new Dimension(250, 50)); // Set maximum size for the button
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the text from the text field
                String reservaNum = reservaNumField.getText();

                // Validate input and cancel the reservation
                if (reservaNum.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha o número da reserva.");
                } else {
                    try {
                        int reservaNumInt = Integer.parseInt(reservaNum);
                        try {
                            Reservas.getInstance().removeReserva(reservaNumInt);
                            JOptionPane.showMessageDialog(null, "Reserva anulada com sucesso.");
                            setVisible(false);
                            new JanelaPrincipal().setVisible(true);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Número da reserva inválido.");
                    }
                }
            }
});

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        mainPanel.add(confirmButton);

        add(mainPanel, BorderLayout.CENTER);
    }


}
