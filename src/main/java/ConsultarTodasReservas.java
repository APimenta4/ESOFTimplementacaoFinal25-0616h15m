import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultarTodasReservas extends JFrame {
    public ConsultarTodasReservas() {
        setTitle("Consultar todas as reservas - BIBLIOTECH");
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

        JLabel reservasLabel = new JLabel("Reservas/");
        reservasLabel.setFont(new Font("Serif", Font.BOLD, 25));
        reservasLabel.setForeground(new Color(51, 153, 255));
        reservasLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Add left margin
        navigationPanel.add(reservasLabel);

        JLabel consultarLabel = new JLabel("Consultar todas as reservas");
        consultarLabel.setFont(new Font("Serif", Font.BOLD, 25));
        consultarLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(consultarLabel);

        topPanel.add(navigationPanel);

        add(topPanel, BorderLayout.NORTH);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Table for displaying reservations
        String[] columnNames = {"Requisitado por", "Título do livro", "Data", "Autor"};
        Reservas reservasInstance = Reservas.getInstance();

        // Crie uma matriz de objetos para armazenar os dados da reserva
        Object[][] data = new Object[reservasInstance.getReservas().size()][4];

        // Itere sobre a lista de reservas
        for (int i = 0; i < reservasInstance.getReservas().size(); i++) {
            Reserva reserva = reservasInstance.getReservas().get(i);
            // Para cada reserva, crie um array de objetos e preencha-o com os detalhes da reserva
            data[i] = new Object[] {reserva.getNumeroDeReserva(), reserva.getTituloDoLivro(), reserva.getData(), reserva.getAutor()};
        }  //reserva.getRequisitadoPor()


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


        JButton consultButton = new JButton("Consultar reservas por título do livro");
        consultButton.setPreferredSize(new Dimension(300, 40)); // Set preferred size
        consultButton.setMaximumSize(new Dimension(300, 40)); // Set maximum size for the button
        consultButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        consultButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new ConsultarPorTituloDoLivro().setVisible(true);
        }
        });

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        mainPanel.add(consultButton);

        add(mainPanel, BorderLayout.CENTER);
    }

}
