import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RegistarNovaReserva extends JFrame {
    public RegistarNovaReserva() {
        setTitle("Registar Nova Reserva - BIBLIOTECH");
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

        JLabel reservaLabel = new JLabel("Reserva");
        reservaLabel.setFont(new Font("Serif", Font.BOLD, 25));
        reservaLabel.setForeground(new Color(51, 153, 255));
        reservaLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Add left margin
        navigationPanel.add(reservaLabel);

        JLabel registarLabel = new JLabel("Registar nova reserva");
        registarLabel.setFont(new Font("Serif", Font.BOLD, 25));
        registarLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(registarLabel);

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
        JLabel requestedByLabel = new JLabel("Requisitado por");
        requestedByLabel.setFont(labelFont);
        formPanel.add(requestedByLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField requestedByField = new JTextField();
        requestedByField.setFont(textFieldFont);
        requestedByField.setPreferredSize(new Dimension(300, 40)); // Decreased size
        formPanel.add(requestedByField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        JLabel bookTitleLabel = new JLabel("Título do livro");
        bookTitleLabel.setFont(labelFont);
        formPanel.add(bookTitleLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField bookTitleField = new JTextField();
        bookTitleField.setFont(textFieldFont);
        bookTitleField.setPreferredSize(new Dimension(300, 40)); // Increased size
        formPanel.add(bookTitleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        JLabel authorLabel = new JLabel("Autor");
        authorLabel.setFont(labelFont);
        formPanel.add(authorLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField authorField = new JTextField();
        authorField.setFont(textFieldFont);
        authorField.setPreferredSize(new Dimension(300, 40)); // Increased size
        formPanel.add(authorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        JLabel dateLabel = new JLabel("Data");
        dateLabel.setFont(labelFont);
        formPanel.add(dateLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField dateField = new JTextField();
        dateField.setFont(textFieldFont);
        dateField.setPreferredSize(new Dimension(300, 40)); // Increased size
        formPanel.add(dateField, gbc);

        mainPanel.add(formPanel);

        // Confirm button
        JButton confirmButton = new JButton("Confirmar reserva");
        confirmButton.setPreferredSize(new Dimension(250, 50)); // Set the preferred width to 250 and height to 50
        confirmButton.setMaximumSize(new Dimension(250, 50)); // Set maximum size for the button
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the text from the text fields
                String requestedBy = requestedByField.getText();
                String bookTitle = bookTitleField.getText();
                String author = authorField.getText();
                String date = dateField.getText();

                // Check if the member exists

                java.util.List<Emprestimo> emprestimos = Emprestimos.getInstance().getEmprestimos();
                List<Emprestimo> emprestimosFiltered = emprestimos.stream().filter(emprestimo -> emprestimo.getSocio().getNumeroDeSocio() == Integer.parseInt(requestedBy)).toList();
                for (Emprestimo emprestimo : emprestimosFiltered) {
                    if(emprestimo.isMultaPaga() == false){
                        JOptionPane.showMessageDialog(null, "O socio tem multas para pagar.");
                        return;
                    }
                }
                try {
                    int requestedByInt = Integer.parseInt(requestedBy);
                    if (!Socios.getInstance().existsSocio(requestedByInt)) {
                        JOptionPane.showMessageDialog(null, "O número do sócio não existe.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Número do sócio inválido.");
                    return;
                }

                // Validate inputs and save the reservation data
                if (requestedBy.isEmpty() || bookTitle.isEmpty() || author.isEmpty() || date.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                } else {
                    // Add the reservation to the list of reservations
                    Reservas.getInstance().addReserva(new Reserva(requestedBy, bookTitle, author, date));

                    JOptionPane.showMessageDialog(null, "Reserva registada com sucesso.");
                    // Return to the main window or perform other actions as needed
                    setVisible(false);
                    new ImprimirReserva(bookTitle, author, date, requestedBy, new Reserva(requestedBy, bookTitle, author, date)).setVisible(true);
                }
            }
        });

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        mainPanel.add(confirmButton);

        add(mainPanel, BorderLayout.CENTER);
    }


    protected String registarNovaReserva(String numeroSocio, String tituloLivro, String autorLivro, String dataReserva) {
        // Check if the member exists
        java.util.List<Emprestimo> emprestimos = Emprestimos.getInstance().getEmprestimos();
        List<Emprestimo> emprestimosFiltered = emprestimos.stream().filter(emprestimo -> emprestimo.getSocio().getNumeroDeSocio() == Integer.parseInt(numeroSocio)).toList();
        for (Emprestimo emprestimo : emprestimosFiltered) {
            if(emprestimo.isMultaPaga() == false){
                return "O sócio tem multa por pagar.";
            }
        }
        try {
            int numeroSocioInt = Integer.parseInt(numeroSocio);
            if (!Socios.getInstance().existsSocio(numeroSocioInt)) {
                return "O número do sócio não existe.";
            }
        } catch (NumberFormatException ex) {
            return "Número do sócio inválido.";
        }

        // Validate inputs and save the reservation data
        if (numeroSocio.isEmpty() || tituloLivro.isEmpty() || autorLivro.isEmpty() || dataReserva.isEmpty()) {
            return "Por favor, preencha todos os campos.";
        } else {
            // Add the reservation to the list of reservations
            Reservas.getInstance().addReserva(new Reserva(numeroSocio, tituloLivro, autorLivro, dataReserva));
            return "Reserva registada com sucesso.";
    }
}
}
