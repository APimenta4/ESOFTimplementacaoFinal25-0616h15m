import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PesquisarLivro2 extends JFrame {
    private JTextField bookTitleField;
    private JTextField authorField;

    private JTextField genreField;
    private final String bookTitle;
    private final String author;
    private final String genre;

    public PesquisarLivro2(String bookTitle, String author, String genre) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.genre = genre;

        setTitle("Pesquisar livro - BIBLIOTECH");
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

        JLabel reservaLabel = new JLabel("Pesquisas e estatisticas/");
        reservaLabel.setFont(new Font("Serif", Font.BOLD, 25));
        reservaLabel.setForeground(new Color(51, 153, 255));
        reservaLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Add left margin
        navigationPanel.add(reservaLabel);

        JLabel consultarLabel = new JLabel("Pesquisar Livro");
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

        JLabel instructionLabel1 = new JLabel("LIVRO ENCONTRADO");
        instructionLabel1.setFont(new Font("Arial", Font.PLAIN, 16));
        instructionLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        mainPanel.add(instructionLabel1);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        JLabel bookTitleLabel = new JLabel("Título do livro");
        bookTitleLabel.setFont(labelFont);
        formPanel.add(bookTitleLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField bookTitleField = new JTextField(bookTitle);
        bookTitleField.setFont(textFieldFont);
        bookTitleField.setPreferredSize(new Dimension(300, 40)); // Increased size
        formPanel.add(bookTitleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        JLabel authorLabel = new JLabel("Autor");
        authorLabel.setFont(labelFont);
        formPanel.add(authorLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField authorField = new JTextField(author);
        authorField.setFont(textFieldFont);
        authorField.setPreferredSize(new Dimension(300, 40)); // Increased size
        formPanel.add(authorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        JLabel genreLabel = new JLabel("Género");
        genreLabel.setFont(labelFont);
        formPanel.add(genreLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField genreField = new JTextField(genre);
        genreField.setFont(textFieldFont);
        genreField.setPreferredSize(new Dimension(300, 40)); // Increased size
        formPanel.add(genreField, gbc);

        mainPanel.add(formPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        add(mainPanel, BorderLayout.CENTER);
    }

}

