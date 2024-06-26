import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.Year;
import java.util.List;

public class ConsultarDadosSocios extends JFrame {
    public ConsultarDadosSocios() {
        setTitle("Consultar dados dos sócios - BIBLIOTECH");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel superior com o título e navegação
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
        backLabel.setForeground(new Color(51, 153, 255)); // Cor azul
        backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backLabel.setBorder(new EmptyBorder(0, 25, 0, 0)); // Adicionar margem à esquerda
        backLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Handle back click
                setVisible(false);
                new MenuSocios().setVisible(true);
            }
        });
        navigationPanel.add(backLabel);

        JLabel sociosLabel = new JLabel("Sócios/");
        sociosLabel.setFont(new Font("Serif", Font.BOLD, 25));
        sociosLabel.setForeground(new Color(51, 153, 255));
        sociosLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Adicionar margem à esquerda
        navigationPanel.add(sociosLabel);

        JLabel consultarLabel = new JLabel("Consultar dados dos sócios");
        consultarLabel.setFont(new Font("Serif", Font.BOLD, 25));
        consultarLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(consultarLabel);

        topPanel.add(navigationPanel);

        add(topPanel, BorderLayout.NORTH);

        // Painel principal com a tabela
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Modelo da tabela
        String[] columnNames = {"Número de Sócio", "Nome", "NIF", "Morada", "Preferência de contacto", "Tipo de sócio", "Telefone", "Email", "Anuidade paga"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);

        // Preencher a tabela com dados dos sócios
        List<Socio> socios = Socios.getInstance().getSocios();
        for (Socio socio : socios) {
            Object[] row = {
                    socio.getNumeroDeSocio(),
                    socio.getName(),
                    socio.getNifCC(),
                    socio.getMorada(),
                    socio.getContactType(),
                    socio.getMembershipType(),
                    socio.getTelefone(),
                    socio.getEmail(),
                    socio.getAnuidadePaga()==Year.now().getValue() ? "Sim" : "Não"
            };
            tableModel.addRow(row);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
    }

}