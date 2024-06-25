import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsultarEmprestimosSocio extends JFrame {
    public ConsultarEmprestimosSocio() {
        // Layout da página
        setTitle("Consultar Empréstimos - BIBLIOTECH");
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
        backLabel.setForeground(new Color(51, 153, 255));
        backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backLabel.setBorder(new EmptyBorder(0, 25, 0, 0));
        backLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setVisible(false);
                new MenuEmprestimos().setVisible(true);
            }
        });
        navigationPanel.add(backLabel);

        JLabel emprestimosLabel = new JLabel("Empréstimos/");
        emprestimosLabel.setFont(new Font("Serif", Font.BOLD, 25));
        emprestimosLabel.setForeground(new Color(51, 153, 255));
        emprestimosLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
        navigationPanel.add(emprestimosLabel);

        JLabel consultarLabel = new JLabel("Consultar empréstimo");
        consultarLabel.setFont(new Font("Serif", Font.BOLD, 25));
        consultarLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(consultarLabel);

        topPanel.add(navigationPanel);

        add(topPanel, BorderLayout.NORTH);

        // Conteúdo
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        Font labelFont = new Font("Arial", Font.PLAIN, 20);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 20);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        JLabel numeroSocioLabel = new JLabel("Número de sócio");
        numeroSocioLabel.setFont(labelFont);
        formPanel.add(numeroSocioLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField numeroSocioField = new JTextField();
        numeroSocioField.setFont(textFieldFont);
        numeroSocioField.setPreferredSize(new Dimension(300, 40));
        formPanel.add(numeroSocioField, gbc);

        mainPanel.add(formPanel);

        JButton searchButton = new JButton("Pesquisar empréstimos");
        searchButton.setPreferredSize(new Dimension(250, 50));
        searchButton.setMaximumSize(new Dimension(250, 50));
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        List<Socio> socios = Socios.getInstance().getSocios();
        List<Emprestimo> emprestimos = Emprestimos.getInstance().getEmprestimos();
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroSocioText = numeroSocioField.getText();

                int numeroSocio;
                try {
                    numeroSocio = Integer.parseInt(numeroSocioText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "O número de sócio especificado é inválido.");
                    return;
                }

                Socio socio = null;
                for (Socio s : socios) {
                    if (s.getNumeroDeSocio() == numeroSocio) {
                        socio = s;
                        break;
                    }
                }

                if (socio == null) {
                    JOptionPane.showMessageDialog(null, "O sócio especificado não existe");
                    return;
                }

                boolean hasEmprestimo = false;
                for (Emprestimo emprestimo : emprestimos) {
                    if (emprestimo.getSocio().equals(socio)) {
                        hasEmprestimo = true;
                        break;
                    }
                }

                if (!hasEmprestimo) {
                    JOptionPane.showMessageDialog(null, "O sócio especificado não possui empréstimos");
                    return;
                }

                setVisible(false);
                new ConsultarEmprestimos(socio).setVisible(true);
            }
        });

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(searchButton);

        add(mainPanel, BorderLayout.CENTER);
    }


    protected String consultarEmprestimo(String numeroDeSocioText) {

        int numeroDeSocio;
        try {
            numeroDeSocio = Integer.parseInt(numeroDeSocioText);
        } catch (NumberFormatException ex) {
            return "O número de sócio especificado é inválido";
        }

        List<Socio> socios = Socios.getInstance().getSocios();

        Socio socio = null;
        for (Socio s : socios) {
            if (s.getNumeroDeSocio() == numeroDeSocio) {
                socio = s;
                break;
            }
        }

        if (socio == null) {
            return "O sócio especificado não existe";
        }

        List<Emprestimo> emprestimos = Emprestimos.getInstance().getEmprestimos();
        boolean hasEmprestimo = false;
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getSocio().equals(socio)) {
                hasEmprestimo = true;
                break;
            }
        }

        if (!hasEmprestimo) {
            return "O sócio especificado não possui empréstimos";
        }

        return "Empréstimos encontrados";
    }


}
