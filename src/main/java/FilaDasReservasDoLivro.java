import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilaDasReservasDoLivro extends JFrame {

    private JTable resultsTable;
    private JScrollPane scrollPane;

    private String bookTitle;
    private String author;

    public FilaDasReservasDoLivro(String bookTitle, String author) {
        this.bookTitle = bookTitle;
        this.author = author;

        setTitle("Filas das Reservas - BIBLIOTECH");
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

        JLabel consultarLabel = new JLabel("Fila das reservas por título do livro");
        consultarLabel.setFont(new Font("Serif", Font.BOLD, 25));
        consultarLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(consultarLabel);

        topPanel.add(navigationPanel);

        add(topPanel, BorderLayout.NORTH);

        // Main panel


        // Replace the current font declarations
        Font labelFont = new Font("Arial", Font.PLAIN, 20);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 20);

        // Labels and text fields
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Table for displaying reservations
        String[] columnNames = { "Data", "Requisitado por"};
        Reservas reservasInstance = Reservas.getInstance();

        // Crie uma matriz de objetos para armazenar os dados da reserva
        Object[][] data = new Object[reservasInstance.getReservas().size()][4];

        // Itere sobre a lista de reservas
        for (int i = 0; i < reservasInstance.getReservas().size(); i++) {
            Reserva reserva = reservasInstance.getReservas().get(i);
            // Para cada reserva, crie um array de objetos e preencha-o com os detalhes da reserva
            //filter by book title and author
            if (reserva.getTituloDoLivro().equals(bookTitle) && reserva.getAutor().equals(author)){

                data[i] = new Object[] {reserva.getData(), reserva.getRequisitadoPor()};
            }else{
                data[i] = new Object[] { , };
            }
        }


        // Crie um novo DefaultTableModel com a matriz de objetos e os nomes das colunas
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Defina este modelo para a tabela


        JTable table = new JTable(model);
        table.setModel(model);
        table.setRowHeight(30); // Set row height
        table.setFont(new Font("Arial", Font.PLAIN, 18)); // Set font for table data
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18)); // Set font for table header

        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane);

        mainPanel.add(formPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer


        // Table for displaying search results
        resultsTable = new JTable();
        resultsTable.setFillsViewportHeight(true);
        resultsTable.setFont(new Font("Arial", Font.PLAIN, 20));
        resultsTable.setRowHeight(30);

        // Scroll pane for the table
        scrollPane = new JScrollPane(resultsTable);
        scrollPane.setVisible(false); // Initially hidden

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        mainPanel.add(scrollPane);

        add(mainPanel, BorderLayout.CENTER);
    }


}


