import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ConsultarAquisicoes extends JFrame {
    public ConsultarAquisicoes() {
        // Layout da página
        setTitle("Consultar Aquisições - BIBLIOTECH");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel bibliotechPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel bibliotechLabel = new JLabel("BIBLIOTECH");
        bibliotechLabel.setFont(new Font("Serif", Font.BOLD, 24));
        bibliotechLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bibliotechLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
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
        backLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                setVisible(false);
                new MenuAquisicoes().setVisible(true);
            }
        });
        navigationPanel.add(backLabel);

        JLabel aquisicoesLabel = new JLabel("Aquisições/");
        aquisicoesLabel.setFont(new Font("Serif", Font.BOLD, 25));
        aquisicoesLabel.setForeground(new Color(51, 153, 255));
        aquisicoesLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Add left margin
        navigationPanel.add(aquisicoesLabel);

        JLabel consultarLabel = new JLabel("Consultar aquisições");
        consultarLabel.setFont(new Font("Serif", Font.BOLD, 25));
        consultarLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(consultarLabel);

        topPanel.add(navigationPanel);

        add(topPanel, BorderLayout.NORTH);

        // Conteúdo
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));


        List<Aquisicao> aquisicoes = Aquisicoes.getInstance().getAquisicoes();

        // Criar e preencher a tabela com os dados das aquisições
        String[] columnNames = {"Título", "Código", "Distribuidor", "Autores", "Gêneros", "Editora", "Edição/Ano", "ISBN", "Prateleira/Estante"};
        Object[][] data = new Object[aquisicoes.size()][columnNames.length];
        for (int i = 0; i < aquisicoes.size(); i++) {
            Aquisicao aquisicao = aquisicoes.get(i);
            data[i][0] = aquisicao.getExemplar().getTitulo();
            data[i][1] = aquisicao.getExemplar().getCodigo();
            data[i][2] = aquisicao.getDistributor().getName();
            data[i][3] = aquisicao.getExemplar().getAutores();
            data[i][4] = aquisicao.getExemplar().getGeneros();
            data[i][5] = aquisicao.getExemplar().getEditora().getName();
            data[i][6] = aquisicao.getExemplar().getEdicaoAno();
            data[i][7] = aquisicao.getExemplar().getIsbn();
            data[i][8] = aquisicao.getExemplar().getPrateleiraEstante();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No cells are editable
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        table.setFont(new Font("Arial", Font.PLAIN, 20));
        table.setPreferredScrollableViewportSize(new Dimension(1000, 300));
        table.getTableHeader().setBackground(new Color(51, 153, 255));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
    }

    // Construtor secundário para quando o utilizador pesquisa os dados de uma aquisição específica
    public ConsultarAquisicoes(Aquisicao aquisicao) {
        setTitle("Consultar Aquisição - BIBLIOTECH");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel bibliotechPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel bibliotechLabel = new JLabel("BIBLIOTECH");
        bibliotechLabel.setFont(new Font("Serif", Font.BOLD, 24));
        bibliotechLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bibliotechLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
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
        backLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                setVisible(false);
                new MenuAquisicoes().setVisible(true);
            }
        });
        navigationPanel.add(backLabel);

        JLabel aquisicoesLabel = new JLabel("Aquisições/");
        aquisicoesLabel.setFont(new Font("Serif", Font.BOLD, 25));
        aquisicoesLabel.setForeground(new Color(51, 153, 255));
        aquisicoesLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Add left margin
        navigationPanel.add(aquisicoesLabel);

        JLabel consultarLabel = new JLabel("Consultar aquisições");
        consultarLabel.setFont(new Font("Serif", Font.BOLD, 25));
        consultarLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(consultarLabel);

        topPanel.add(navigationPanel);

        add(topPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        String[] columnNames = {"Título", "Código", "Distribuidor", "Autores", "Gêneros", "Editora", "Edição/Ano", "ISBN", "Prateleira/Estante"};
        Object[][] data = new Object[1][columnNames.length];

        data[0][0] = aquisicao.getExemplar().getTitulo();
        data[0][1] = aquisicao.getExemplar().getCodigo();
        data[0][2] = aquisicao.getDistributor().getName();
        data[0][3] = aquisicao.getExemplar().getAutores();
        data[0][4] = aquisicao.getExemplar().getGeneros();
        data[0][5] = aquisicao.getExemplar().getEditora().getName();
        data[0][6] = aquisicao.getExemplar().getEdicaoAno();
        data[0][7] = aquisicao.getExemplar().getIsbn();
        data[0][8] = aquisicao.getExemplar().getPrateleiraEstante();

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No cells are editable
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        table.setFont(new Font("Arial", Font.PLAIN, 20));
        table.setPreferredScrollableViewportSize(new Dimension(1000, 300));
        table.getTableHeader().setBackground(new Color(51, 153, 255));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
    }
}