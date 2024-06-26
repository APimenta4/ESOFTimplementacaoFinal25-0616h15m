import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;
import java.util.regex.Pattern;

public class NovoSocio extends JFrame {
    public NovoSocio() {
        setTitle("Registar Novo Sócio - BIBLIOTECH");
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

        JLabel registrarLabel = new JLabel("Registar novo sócio");
        registrarLabel.setFont(new Font("Serif", Font.BOLD, 25));
        registrarLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(registrarLabel);

        topPanel.add(navigationPanel);

        add(topPanel, BorderLayout.NORTH);

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Substituir as declarações de fonte atuais
        Font labelFont = new Font("Arial", Font.PLAIN, 20);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 20);

        // Labels e campos de texto
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        JLabel nomeLabel = new JLabel("Nome");
        nomeLabel.setFont(labelFont);
        formPanel.add(nomeLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField nomeField = new JTextField();
        nomeField.setFont(textFieldFont);
        nomeField.setPreferredSize(new Dimension(300, 40)); // Tamanho aumentado
        formPanel.add(nomeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        JLabel nifCcLabel = new JLabel("NIF ou CC");
        nifCcLabel.setFont(labelFont);
        formPanel.add(nifCcLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField nifCcField = new JTextField();
        nifCcField.setFont(textFieldFont);
        nifCcField.setPreferredSize(new Dimension(300, 40)); // Tamanho aumentado
        formPanel.add(nifCcField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        JLabel moradaLabel = new JLabel("Morada");
        moradaLabel.setFont(labelFont);
        formPanel.add(moradaLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField moradaField = new JTextField();
        moradaField.setFont(textFieldFont);
        moradaField.setPreferredSize(new Dimension(300, 40)); // Tamanho aumentado
        formPanel.add(moradaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        JLabel telefoneLabel = new JLabel("Telefone");
        telefoneLabel.setFont(labelFont);
        formPanel.add(telefoneLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField telefoneField = new JTextField();
        telefoneField.setFont(textFieldFont);
        telefoneField.setPreferredSize(new Dimension(300, 40)); // Tamanho aumentado
        formPanel.add(telefoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.3;
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(labelFont);
        formPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField emailField = new JTextField();
        emailField.setFont(textFieldFont);
        emailField.setPreferredSize(new Dimension(300, 40)); // Tamanho aumentado
        formPanel.add(emailField, gbc);

        // Tipo de sócio
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.3;
        JLabel tipoSocioLabel = new JLabel("Tipo de sócio");
        tipoSocioLabel.setFont(labelFont);
        formPanel.add(tipoSocioLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JPanel tipoSocioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton normalButton = new JRadioButton("Normal");
        normalButton.setFont(labelFont);
        normalButton.setSelected(true);
        JRadioButton premiumButton = new JRadioButton("Premium");
        premiumButton.setFont(labelFont);
        ButtonGroup tipoSocioGroup = new ButtonGroup();
        tipoSocioGroup.add(normalButton);
        tipoSocioGroup.add(premiumButton);
        tipoSocioPanel.add(normalButton);
        tipoSocioPanel.add(Box.createRigidArea(new Dimension(20, 0))); // Espaçamento
        tipoSocioPanel.add(premiumButton);
        formPanel.add(tipoSocioPanel, gbc);

        // Preferência de contacto
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0.3;
        JLabel preferenciaContactoLabel = new JLabel("Preferência de contacto");
        preferenciaContactoLabel.setFont(labelFont);
        formPanel.add(preferenciaContactoLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JPanel preferenciaContactoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton emailButton = new JRadioButton("EMAIL");
        emailButton.setFont(labelFont);
        emailButton.setSelected(true);
        JRadioButton smsButton = new JRadioButton("SMS");
        smsButton.setFont(labelFont);
        ButtonGroup preferenciaContactoGroup = new ButtonGroup();
        preferenciaContactoGroup.add(emailButton);
        preferenciaContactoGroup.add(smsButton);
        preferenciaContactoPanel.add(emailButton);
        preferenciaContactoPanel.add(Box.createRigidArea(new Dimension(20, 0))); // Espaçamento
        preferenciaContactoPanel.add(smsButton);
        formPanel.add(preferenciaContactoPanel, gbc);

        mainPanel.add(formPanel);

        // Botão de confirmação
        JButton confirmButton = new JButton("Confirmar dados");
        confirmButton.setPreferredSize(new Dimension(250, 50)); // Setar tamanho preferido
        confirmButton.setMaximumSize(new Dimension(250, 50)); // Definir tamanho máximo
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obter dados dos campos de texto e botões de rádio
                String nome = nomeField.getText();
                String nifCc = nifCcField.getText();
                String morada = moradaField.getText();
                String telefone = telefoneField.getText();
                String email = emailField.getText();
                String tipoSocio = normalButton.isSelected() ? "Normal" : "Premium";
                String preferenciaContacto = emailButton.isSelected() ? "email" : "sms";

                // Validação dos campos
                if (nome.isEmpty() || nifCc.isEmpty() || morada.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validação do NIF/CC
                if (!Pattern.matches("\\d{9}", nifCc)) {
                    JOptionPane.showMessageDialog(null, "NIF ou CC deve ter 9 dígitos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validação do Telefone
                if (!Pattern.matches("\\d{9}", telefone)) {
                    JOptionPane.showMessageDialog(null, "Telefone deve ter 9 dígitos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                // Exibir uma mensagem de confirmação dos dados
                int response = JOptionPane.showConfirmDialog(null,
                        "Nome: " + nome + "\n" +
                                "NIF/CC: " + nifCc + "\n" +
                                "Morada: " + morada + "\n" +
                                "Telefone: " + telefone + "\n" +
                                "Email: " + email + "\n" +
                                "Tipo de Sócio: " + tipoSocio + "\n" +
                                "Preferência de Contacto: " + preferenciaContacto + "\n\n" +
                                "Confirmar registo?",
                        "Confirmar Dados",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                // Se o usuário confirmar, criar o novo sócio
                if (response == JOptionPane.YES_OPTION) {
                    // Criar novo sócio com os dados fornecidos

                    Socios.getInstance().addSocio(new Socio(nome, preferenciaContacto, tipoSocio, 2024, email, Integer.parseInt(telefone), morada, Integer.parseInt(nifCc)));

                    // Aqui, adicione o código para salvar o sócio na base de dados ou na lista de sócios
                    Socio lastSocio = Socios.getInstance().getLastSocio();
                    int socioNumero = lastSocio != null ? lastSocio.getNumeroDeSocio() : -1;

                    JOptionPane.showMessageDialog(null, "Imprimir cartão: Sócio nº " + socioNumero, "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                    // Limpar os campos após o registro
                    nomeField.setText("");
                    nifCcField.setText("");
                    moradaField.setText("");
                    telefoneField.setText("");
                    emailField.setText("");
                    normalButton.setSelected(true);
                    emailButton.setSelected(true);
                }
            }
        });

        mainPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Espaçamento
        mainPanel.add(confirmButton);

        add(mainPanel, BorderLayout.CENTER);
    }

    protected String registarSocio(String nome, String preferenciaContacto, String tipoSocio, String email, String telefone, String morada, String nifCc) {
        if (nome.isEmpty() || nifCc.isEmpty() || morada.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            return "Por favor, preencha todos os campos obrigatórios.";
        }

        // Validação do NIF/CC
        if (!Pattern.matches("\\d{9}", nifCc)) {
            return "NIF ou CC deve ter 9 dígitos numéricos.";
        }
        // Validação do Telefone
        if (!Pattern.matches("\\d{9}", telefone)) {
            return "Telefone deve ter 9 dígitos numéricos.";
        }

        Socios.getInstance().addSocio(new Socio(nome, preferenciaContacto, tipoSocio, 2024, email, Integer.parseInt(telefone), morada, Integer.parseInt(nifCc)));
    return "Sócio registado com sucesso";
    }
}