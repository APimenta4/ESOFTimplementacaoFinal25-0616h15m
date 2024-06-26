import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VerListagemSociosComMultas extends JFrame {

    public VerListagemSociosComMultas() {


        setTitle("Listagem de Sócios com Valores em Atraso - BIBLIOTECH");
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
                new MenuPagamentos().setVisible(true);
            }
        });
        navigationPanel.add(backLabel);

        JLabel pagamentosLabel = new JLabel("Pagamentos e multas/");
        pagamentosLabel.setFont(new Font("Serif", Font.BOLD, 25));
        pagamentosLabel.setForeground(new Color(51, 153, 255));
        pagamentosLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Adicionar margem à esquerda
        navigationPanel.add(pagamentosLabel);

        JLabel pagarAnuidadeLabel = new JLabel("Pagar anuidade");
        pagarAnuidadeLabel.setFont(new Font("Serif", Font.BOLD, 25));
        pagarAnuidadeLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(pagarAnuidadeLabel);

        topPanel.add(navigationPanel);

        add(topPanel, BorderLayout.NORTH);


        JLabel settingsLabel = new JLabel("Notificar Sócio");
        settingsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Hide the current window
                setVisible(false);

                // Show the new window
                new ContactarSocio().setVisible(true);
            }
        });

        // Painel principal com a tabela

        int numeroMultasPorPagar = 0;
        List<Emprestimo> emprestimos = Emprestimos.getInstance().getEmprestimos();
        List<Emprestimo> emprestimosComMulta = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (!emprestimo.isMultaPaga() && emprestimo.getValorMulta() != Integer.parseInt("0")){
                numeroMultasPorPagar += 1;
                emprestimosComMulta.add(emprestimo);
            }
        }

        String[] columnNames = {"Nome do sócio", "Número sócio", "Valor a pagar", ""};
        Object[][] data = new Object[numeroMultasPorPagar][4];  // 7 linhas e 4 colunas



        for (int x = 0; x < emprestimosComMulta.size(); x++) {
            data[x][0] = emprestimosComMulta.get(x).getSocio().getName();
            data[x][1] = emprestimosComMulta.get(x).getSocio().getNumeroDeSocio();
            data[x][2] = emprestimosComMulta.get(x).getValorMulta();
            data[x][3] = settingsLabel.getText();
        }


        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;  // Só permitir editar a coluna de botões
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(50);
        table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Painel inferior com o botão "Voltar"
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setFont(new Font("Arial", Font.BOLD, 20));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
        bottomPanel.add(voltarButton);
        add(bottomPanel, BorderLayout.SOUTH);

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fechar a janela atual e voltar para o menu de sócios
                dispose();
                new MenuPagamentos().setVisible(true);
            }
        });

    }


    // Classe para renderizar o botão na tabela
    class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // Classe para editar o botão na tabela
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }


        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}
