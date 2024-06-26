import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VerTopLivros2 extends JFrame {
    private final String bookTitle;
    private final String author;
    private final String genre;
    private JTextField bookTitleField;
    private JTextField authorField;
    private JTextField genreField;

    public VerTopLivros2(String bookTitle, String author, String genre) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.genre = genre;

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
                new MenuPesquisasEstatisticas().setVisible(true);
            }
        });
        navigationPanel.add(backLabel);

        JLabel reservaLabel = new JLabel("Pesquisas e estatisticas/");
        reservaLabel.setFont(new Font("Serif", Font.BOLD, 25));
        reservaLabel.setForeground(new Color(51, 153, 255));
        reservaLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Add left margin
        navigationPanel.add(reservaLabel);

        JLabel consultarLabel = new JLabel("Top livros mais requisitados");
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
        gbc.gridy = 1;
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
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        JLabel genreLabel = new JLabel("Género");
        genreLabel.setFont(labelFont);
        formPanel.add(genreLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.2;
        JTextField genreField = new JTextField(genre);
        genreField.setFont(textFieldFont);
        genreField.setPreferredSize(new Dimension(300, 40)); // Increased size
        formPanel.add(genreField, gbc);

        mainPanel.add(formPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer


        add(mainPanel, BorderLayout.WEST);

        //aqui
        // Bottom panel for the top books table
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(20, 300, 20, 100));

        JLabel topBooksLabel = new JLabel("Nome do livro", SwingConstants.CENTER);
        topBooksLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topBooksLabel.setOpaque(true);
        topBooksLabel.setBackground(new Color(51, 153, 255));
        topBooksLabel.setForeground(Color.WHITE);
        bottomPanel.add(topBooksLabel, BorderLayout.NORTH);

        // Table with top books
        String[] columnNames = {"Nome do livro"};
        List<Emprestimo> emprestimos = Emprestimos.getInstance().getEmprestimos();

        String [][] data = null;
        if (bookTitle != null && !bookTitle.isEmpty()) {
            data = new String[emprestimos.size()][1];
            int index = 0;
            for (Emprestimo emprestimo : emprestimos) {
                if (emprestimo.getExemplar().getTitulo().equals(bookTitle)) {
                    data[index][0] = emprestimo.getExemplar().getTitulo();
                    index++;
                }
            }
        }

        if (author != null && !author.isEmpty()) {
            data = new String[emprestimos.size()][1];
            int index = 0;
            for (Emprestimo emprestimo : emprestimos) {
                if (emprestimo.getExemplar().getAutores().equals(author)) {
                    data[index][0] = emprestimo.getExemplar().getTitulo();
                    index++;
                }
            }
        }

        if (genre != null && !genre.isEmpty()) {
            data = new String[emprestimos.size()][1];
            int index = 0;
            for (Emprestimo emprestimo : emprestimos) {
                if (emprestimo.getExemplar().getGeneros().equals(genre)) {
                    data[index][0] = emprestimo.getExemplar().getTitulo();
                    index++;
                }
            }

        }


        JTable topBooksTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(topBooksTable);
        bottomPanel.add(scrollPane, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.EAST);
    }


}




