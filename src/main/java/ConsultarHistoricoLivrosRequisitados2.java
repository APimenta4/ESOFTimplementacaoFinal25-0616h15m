import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ConsultarHistoricoLivrosRequisitados2 extends JFrame {
    private JTextField memberNumberField;
    private final int memberNumber;

    public ConsultarHistoricoLivrosRequisitados2(int memberNumber) {
        this.memberNumber = memberNumber;

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
        JTextField memberNumberField = new JTextField(String.valueOf(memberNumber));
        memberNumberField.setFont(textFieldFont);
        memberNumberField.setPreferredSize(new Dimension(300, 40)); // Increased size
        formPanel.add(memberNumberField, gbc);

        mainPanel.add(formPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        add(mainPanel, BorderLayout.CENTER);

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
        String[] columnNames = {"Nome do livro", "Data de Empréstimo"};
        List<Emprestimo> emprestimos = Emprestimos.getInstance().getEmprestimos();
        // filter emprestimos if numeroDeSocio is equal to memberNumber
        List<Emprestimo> emprestimosFiltered = emprestimos.stream().filter(emprestimo -> emprestimo.getSocio().getNumeroDeSocio() == memberNumber).toList();

        String[][] data = new String[emprestimosFiltered.size()][2];
        for (int i = 0; i < emprestimosFiltered.size(); i++) {
            data[i][0] = emprestimosFiltered.get(i).getExemplar().getTitulo();
            data[i][1] = String.valueOf(emprestimosFiltered.get(i).getDataDeEmprestimo());
        }

        JTable topBooksTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(topBooksTable);
        bottomPanel.add(scrollPane, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.EAST);
    }
}






