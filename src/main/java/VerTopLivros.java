import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerTopLivros extends JFrame {
    private JTextField bookTitleField;
    private JTextField authorField;

    private JTextField genreField;

    public VerTopLivros() {
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
        gbc.weightx = 0.7;
        bookTitleField = new JTextField();
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
        authorField = new JTextField();
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
        genreField = new JTextField();
        genreField.setFont(textFieldFont);
        genreField.setPreferredSize(new Dimension(300, 40)); // Increased size
        formPanel.add(genreField, gbc);



        mainPanel.add(formPanel);

        JLabel instructionLabel = new JLabel("Preencher apenas um campo");
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        mainPanel.add(instructionLabel);

        // Search button
        JButton searchButton = new JButton("Pesquisar");
        searchButton.setPreferredSize(new Dimension(250, 50)); // Set the preferred width to 250 and height to 50
        searchButton.setMaximumSize(new Dimension(250, 50)); // Set maximum size for the button
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the text from the text fields
                String bookTitle = bookTitleField.getText();
                String author = authorField.getText();
                String genre = genreField.getText();

                int filledFields = 0;

                if (!bookTitle.isEmpty()) {
                    filledFields++;
                }

                if (!author.isEmpty()) {
                    filledFields++;
                }

                if (!genre.isEmpty()) {
                    filledFields++;
                }

                if (filledFields > 1) {
                    JOptionPane.showMessageDialog(null, "Apenas um campo pode ser preenchido.");
                } else if (filledFields == 0) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha um campo.");
                } else {

                    VerTopLivros2 verTopLivros2 = new VerTopLivros2(bookTitle, author, genre);
                    verTopLivros2.setVisible(true);
                    dispose();


                }

            }});
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        mainPanel.add(searchButton);



        add(mainPanel, BorderLayout.CENTER);
    }

}

