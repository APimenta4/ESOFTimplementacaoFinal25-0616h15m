import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;

public class NovoEmprestimo extends JFrame {
    public JButton confirmButton;
    JTextField numeroSocioField;
    JTextField codigoExemplarField;
    public NovoEmprestimo() {
        // Layout da página
        setTitle("Empréstimos - BIBLIOTECH");
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

        JLabel registrarLabel = new JLabel("Registar novo empréstimo");
        registrarLabel.setFont(new Font("Serif", Font.BOLD, 25));
        registrarLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(registrarLabel);

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
        JLabel numeroSocioLabel = new JLabel("Número sócio");
        numeroSocioLabel.setFont(labelFont);
        formPanel.add(numeroSocioLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField numeroSocioField = new JTextField();
        numeroSocioField.setFont(textFieldFont);
        numeroSocioField.setPreferredSize(new Dimension(300, 40));

        formPanel.add(numeroSocioField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JLabel errorMessageLabel = new JLabel();
        errorMessageLabel.setFont(new Font("Arial", Font.BOLD, 20));
        errorMessageLabel.setForeground(Color.RED);
        formPanel.add(errorMessageLabel, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        JLabel codigoExemplarLabel = new JLabel("Código do exemplar");
        codigoExemplarLabel.setFont(labelFont);
        formPanel.add(codigoExemplarLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField codigoExemplarField = new JTextField();
        codigoExemplarField.setFont(textFieldFont);
        codigoExemplarField.setPreferredSize(new Dimension(300, 40));
        formPanel.add(codigoExemplarField, gbc);

        mainPanel.add(formPanel);


        confirmButton = new JButton("Confirmar empréstimo");
        confirmButton.setPreferredSize(new Dimension(250, 50));
        confirmButton.setMaximumSize(new Dimension(250, 50));
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroDeSocio = numeroSocioField.getText();
                String codigoExemplar = codigoExemplarField.getText();

                String result = adicionarEmprestimo(numeroDeSocio, codigoExemplar);
                if(result.equals("Empréstimo registrado com sucesso")) {
                    JOptionPane.showMessageDialog(null, result);
                    setVisible(false);
                    new JanelaPrincipal().setVisible(true);
                    return;
                }
                JOptionPane.showMessageDialog(null, result);

            }
        });

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(confirmButton);

        add(mainPanel, BorderLayout.CENTER);
    }

    protected String adicionarEmprestimo(String numeroDeSocio, String codigoExemplar) {

        Socio socio = Socios.getInstance().getSocios().stream().filter(s -> String.valueOf(s.getNumeroDeSocio()).equals(numeroDeSocio)).findFirst().orElse(null);
        Exemplar exemplar = Exemplares.getInstance().getExemplares().stream().filter(ex -> ex.getCodigo().equals(codigoExemplar)).findFirst().orElse(null);

        String message = "";

        if (socio != null && exemplar != null) {
            if (socio.getAnuidadePaga() < Year.now().getValue()) {
                message = "O sócio especificado não tem a anuidade paga";
            } else if ((socio.getQuantEmprestimos() >= ValoresPredefinicoes.getInstance().getMaxEmprestimos() && !socio.getMembershipType().equals("Premium"))
                    || (socio.getMembershipType().equals("Premium") && socio.getQuantEmprestimos() >= (ValoresPredefinicoes.getInstance().getMaxEmprestimos() + ValoresPredefinicoes.getInstance().getExtraPremium()))) {
                message = "O sócio especificado já possui o máximo de empréstimos simultâneos atualmente";
            } else if(exemplar.isEmprestado()){
                message = "O exemplar especificado já está emprestado";
            }else {
                Emprestimo emprestimo = new Emprestimo(socio, exemplar);
                Emprestimos.getInstance().addEmprestimo(emprestimo);
                message = "Empréstimo registrado com sucesso";
            }
        } else {
            StringBuilder errorMessage = new StringBuilder();
            if (socio == null) {
                errorMessage.append("O sócio especificado não existe  ");
            }
            if (exemplar == null) {
                errorMessage.append("O exemplar especificado não existe");
            }
            message = errorMessage.toString();
        }
        return message;
    }


}
