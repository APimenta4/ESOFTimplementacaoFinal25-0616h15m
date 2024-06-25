import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RegistarDevolucao extends JFrame {
    public RegistarDevolucao() {
        // Layout da página
        setTitle("Registar Devolução - BIBLIOTECH");
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

        JLabel registarLabel = new JLabel("Registar devolução");
        registarLabel.setFont(new Font("Serif", Font.BOLD, 25));
        registarLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(registarLabel);

        topPanel.add(navigationPanel);

        add(topPanel, BorderLayout.NORTH);

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
        gbc.weightx = 0.4;
        JTextField numeroSocioField = new JTextField();
        numeroSocioField.setFont(textFieldFont);
        numeroSocioField.setPreferredSize(new Dimension(200, 40));
        formPanel.add(numeroSocioField, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0.3;
        // "Ver todos" button
        JButton verTodosButton = new JButton("Ver todos");
        verTodosButton.setPreferredSize(new Dimension(150, 40));
        verTodosButton.setMaximumSize(new Dimension(150, 40));

        List<Socio> socios = Socios.getInstance().getSocios();
        verTodosButton.addActionListener(new ActionListener() {
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

                List<Emprestimo> emprestimos = Emprestimos.getInstance().getEmprestimos();
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
        formPanel.add(verTodosButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        JLabel codigoExemplarLabel = new JLabel("Código exemplar");
        codigoExemplarLabel.setFont(labelFont);
        formPanel.add(codigoExemplarLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField codigoExemplarField = new JTextField();
        codigoExemplarField.setFont(textFieldFont);
        codigoExemplarField.setPreferredSize(new Dimension(300, 40));
        formPanel.add(codigoExemplarField, gbc);

        mainPanel.add(formPanel);


        JButton registerButton = new JButton("Registar devolução");
        registerButton.setPreferredSize(new Dimension(250, 50));
        registerButton.setMaximumSize(new Dimension(250, 50));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numeroSocio;
                List<Socio> socios = Socios.getInstance().getSocios();
                try {
                    numeroSocio = Integer.parseInt(numeroSocioField.getText());
                } catch (NumberFormatException e2) {
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
                String codigoExemplar = codigoExemplarField.getText();

                List<Emprestimo> emprestimos = Emprestimos.getInstance().getEmprestimos();
                boolean hasEmprestimos = false;
                for (Emprestimo emprestimo : emprestimos) {
                    if (emprestimo.getSocio().getNumeroDeSocio() == numeroSocio && !emprestimo.isDevolvido()) {
                        hasEmprestimos = true;
                        if (emprestimo.getExemplar().getCodigo().equals(codigoExemplar)) {
                            emprestimo.devolver();
                            JOptionPane.showMessageDialog(null, "Empréstimo devolvido com sucesso");
                            setVisible(false);
                            new JanelaPrincipal().setVisible(true);
                            return;
                        }
                    }
                }
                if (!hasEmprestimos) {
                    JOptionPane.showMessageDialog(null, "O sócio especificado não possui empréstimos em aberto");
                    return;
                }

                JOptionPane.showMessageDialog(null, "O exemplar não se encontra emprestado ao sócio especificado");
            }
        });

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(registerButton);

        add(mainPanel, BorderLayout.CENTER);
    }


    protected String verTodos(String numeroSocioText){
        int numeroSocio;
        try {
            numeroSocio = Integer.parseInt(numeroSocioText);
        } catch (NumberFormatException ex) {
            return "O número de sócio especificado é inválido";
        }

        List<Socio> socios = Socios.getInstance().getSocios();
        Socio socio = null;
        for (Socio s : socios) {
            if (s.getNumeroDeSocio() == numeroSocio) {
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

        return "A apresentar empréstimos do sócio";
    }


    protected String registarDevolucao(String numeroDeSocioText, String codigoExemplarText){
        int numeroSocio;
        List<Socio> socios = Socios.getInstance().getSocios();
        try {
            numeroSocio = Integer.parseInt(numeroDeSocioText);
        } catch (NumberFormatException e2) {
            return "O número de sócio especificado é inválido";
        }

        Socio socio = null;
        for (Socio s : socios) {
            if (s.getNumeroDeSocio() == numeroSocio) {
                socio = s;
                break;
            }
        }

        if (socio == null) {
            return "O sócio especificado não existe";
        }

        String codigoExemplar = codigoExemplarText;

        List<Emprestimo> emprestimos = Emprestimos.getInstance().getEmprestimos();
        boolean hasEmprestimos = false;
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getSocio().getNumeroDeSocio() == numeroSocio && !emprestimo.isDevolvido()) {
                hasEmprestimos = true;
                if (emprestimo.getExemplar().getCodigo().equals(codigoExemplar)) {
                    emprestimo.devolver();
                    return "Empréstimo devolvido com sucesso";
                }
            }
        }
        if (!hasEmprestimos) {
            return "O sócio especificado não possui empréstimos em aberto";
        }

        return "O exemplar não se encontra emprestado ao sócio especificado";
    }




}
