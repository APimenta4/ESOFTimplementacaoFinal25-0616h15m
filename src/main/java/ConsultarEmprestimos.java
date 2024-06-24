import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

public class ConsultarEmprestimos extends JFrame {
    public ConsultarEmprestimos() {
        setTitle("Consultar Empréstimos - BIBLIOTECH");
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
                // Handle back click
                setVisible(false);
                new MenuEmprestimos().setVisible(true);
            }
        });
        navigationPanel.add(backLabel);

        JLabel emprestimosLabel = new JLabel("Empréstimos/");
        emprestimosLabel.setFont(new Font("Serif", Font.BOLD, 25));
        emprestimosLabel.setForeground(new Color(51, 153, 255));
        emprestimosLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Add left margin
        navigationPanel.add(emprestimosLabel);

        JLabel consultarLabel = new JLabel("Consultar empréstimos");
        consultarLabel.setFont(new Font("Serif", Font.BOLD, 25));
        consultarLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(consultarLabel);

        topPanel.add(navigationPanel);

        add(topPanel, BorderLayout.NORTH);

        // Main panel with table
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Table
        String[] columnNames = {"Exemplar", "Título", "Número de Sócio", "Data Empréstimo", "Data Devolução", "Devolvido"};
        // Fetch the list of Emprestimos
        List<Emprestimo> emprestimos = Emprestimos.getInstance().getEmprestimos();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // Convert the list of Emprestimos into a 2D Object array
        Object[][] data = new Object[emprestimos.size()][6];
        for (int i = 0; i < emprestimos.size(); i++) {
            Emprestimo emprestimo = emprestimos.get(i);
            data[i][0] = emprestimo.getExemplar().getCodigo();
            data[i][1] = emprestimo.getExemplar().getTitulo();
            data[i][2] = emprestimo.getSocio().getNumeroDeSocio();
            data[i][3] = dateFormat.format(emprestimo.getDataDeEmprestimo());
            data[i][4] = emprestimo.getDataDeDevolucao() != null ? dateFormat.format(emprestimo.getDataDeDevolucao()) : null;
            data[i][5] = emprestimo.isDevolvido();
        }


        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                return (column == 5) ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                // Only allow editing in the "check" column
                if (column == 5) {
                    Boolean isChecked = (Boolean) getValueAt(row, column);
                    return !isChecked; // If the checkbox is already checked, don't allow further interaction
                }
                return false;
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                if (column == 5 && aValue instanceof Boolean && (Boolean) aValue == true) {
                    // If the checkbox is not checked, show a confirmation dialog before allowing to check it
                    int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to check this box?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        super.setValueAt(aValue, row, column);
                        // Get the selected Emprestimos.Emprestimo and call the devolver() method
                        Emprestimo selectedEmprestimo = emprestimos.get(row);
                        selectedEmprestimo.devolver();
                        // Update the "Data Devolucao" column in the table
                        super.setValueAt(dateFormat.format(selectedEmprestimo.getDataDeDevolucao()), row, 4);
                    }
                } else {
                    super.setValueAt(aValue, row, column);
                }
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



    public ConsultarEmprestimos(Socio socio){
        setTitle("Consultar Empréstimos - BIBLIOTECH");
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
                // Handle back click
                setVisible(false);
                new MenuEmprestimos().setVisible(true);
            }
        });
        navigationPanel.add(backLabel);

        JLabel emprestimosLabel = new JLabel("Empréstimos/");
        emprestimosLabel.setFont(new Font("Serif", Font.BOLD, 25));
        emprestimosLabel.setForeground(new Color(51, 153, 255));
        emprestimosLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Add left margin
        navigationPanel.add(emprestimosLabel);

        JLabel consultarLabel = new JLabel("Consultar empréstimos");
        consultarLabel.setFont(new Font("Serif", Font.BOLD, 25));
        consultarLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(consultarLabel);

        topPanel.add(navigationPanel);

        add(topPanel, BorderLayout.NORTH);

        // Main panel with table
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Table
        String[] columnNames = {"Exemplar", "Título", "Número de Sócio", "Data Empréstimo", "Data Devolução", "Devolvido"};
        // Fetch the list of Emprestimos
        List<Emprestimo> emprestimosUnfiltered = Emprestimos.getInstance().getEmprestimos();

        // filter emprestimos that have socio as the current socio
        List<Emprestimo> emprestimos = emprestimosUnfiltered.stream().filter(emprestimo -> emprestimo.getSocio().equals(socio)).collect(Collectors.toList());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // Convert the list of Emprestimos into a 2D Object array
        Object[][] data = new Object[emprestimos.size()][6];
        for (int i = 0; i < emprestimos.size(); i++) {
            Emprestimo emprestimo = emprestimos.get(i);
            data[i][0] = emprestimo.getExemplar().getCodigo();
            data[i][1] = emprestimo.getExemplar().getTitulo();
            data[i][2] = emprestimo.getSocio().getNumeroDeSocio();
            data[i][3] = dateFormat.format(emprestimo.getDataDeEmprestimo());
            data[i][4] = emprestimo.getDataDeDevolucao() != null ? dateFormat.format(emprestimo.getDataDeDevolucao()) : null;
            data[i][5] = emprestimo.isDevolvido();
        }


        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                return (column == 5) ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                // Only allow editing in the "check" column
                if (column == 5) {
                    Boolean isChecked = (Boolean) getValueAt(row, column);
                    return !isChecked; // If the checkbox is already checked, don't allow further interaction
                }
                return false;
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                if (column == 5 && aValue instanceof Boolean && (Boolean) aValue == true) {
                    // If the checkbox is not checked, show a confirmation dialog before allowing to check it
                    int response = JOptionPane.showConfirmDialog(null, "Tens a certeza que queres confirmar a devolução?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        super.setValueAt(aValue, row, column);
                        // Get the selected Emprestimos.Emprestimo and call the devolver() method
                        Emprestimo selectedEmprestimo = emprestimos.get(row);
                        selectedEmprestimo.devolver();
                        // Update the "Data Devolucao" column in the table
                        super.setValueAt(dateFormat.format(selectedEmprestimo.getDataDeDevolucao()), row, 4);
                    }
                } else {
                    super.setValueAt(aValue, row, column);
                }
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
