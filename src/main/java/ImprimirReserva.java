import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImprimirReserva extends JFrame {
    private final String bookTitle;
    private final String author;
    private final String date;
    private final Reserva reserva;

    private final String requestedBy;
    private JTextField bookTitleField;
    private JTextField authorField;
    private JTextField genreField;

    public ImprimirReserva(String bookTitle, String author, String date, String requestedBy, Reserva reserva) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.date = date;
        this.requestedBy = requestedBy;
        this.reserva = reserva;

        setTitle("Top livros mais requisitados - BIBLIOTECH");
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

        JLabel reservaLabel = new JLabel("Reservas/");
        reservaLabel.setFont(new Font("Serif", Font.BOLD, 25));
        reservaLabel.setForeground(new Color(51, 153, 255));
        reservaLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Add left margin
        navigationPanel.add(reservaLabel);

        JLabel consultarLabel = new JLabel("Registar nova reserva");
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
        JLabel requestedByLabel = new JLabel("Requisitado Por:");
        requestedByLabel.setFont(labelFont);
        formPanel.add(requestedByLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.2;
        JTextField requestedByField = new JTextField(requestedBy);
        requestedByField.setFont(textFieldFont);
        requestedByField.setPreferredSize(new Dimension(300, 40)); // Increased size
        formPanel.add(requestedByField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        JLabel bookTitleLabel = new JLabel("Título do livro");
        bookTitleLabel.setFont(labelFont);
        formPanel.add(bookTitleLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.2;
        JTextField bookTitleField = new JTextField(bookTitle);
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
        gbc.weightx = 0.2;
        JTextField authorField = new JTextField(author);
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
        gbc.weightx = 0.2;
        JTextField dateField = new JTextField(date);
        dateField.setFont(textFieldFont);
        dateField.setPreferredSize(new Dimension(300, 40)); // Increased size
        formPanel.add(dateField, gbc);

        mainPanel.add(formPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer


        add(mainPanel, BorderLayout.WEST);
        JButton imprimirReserva = new JButton("Imprimir Reserva");
        imprimirReserva.setFont(new Font("Arial", Font.PLAIN, 20));
        imprimirReserva.setPreferredSize(new Dimension(300, 40));
        imprimirReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Reserva impressa com sucesso! Número da reserva: " + reserva.getNumeroDeReserva());
            }
        });

        mainPanel.add(imprimirReserva);


    }
}


