import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class EditarInformacoesSocio extends JFrame {
    public EditarInformacoesSocio(String numeroSocioField) {

        // Pega as informações do sócio
        Socio socio = Socios.getInstance().getSocioByNumero(Integer.parseInt(numeroSocioField));

        setTitle("Alterar dados de um sócio - BIBLIOTECH");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel superior com o título e navegação
        JPanel topPanel = new JPanel(new BorderLayout());
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

        topPanel.add(bibliotechPanel, BorderLayout.WEST);

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

        JLabel alterarLabel = new JLabel("Alterar dados de um sócio");
        alterarLabel.setFont(new Font("Serif", Font.BOLD, 25));
        alterarLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(alterarLabel);

        topPanel.add(navigationPanel, BorderLayout.SOUTH);

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

        // Número de Sócio
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        JLabel numeroSocioLabel = new JLabel("Número de sócio");
        numeroSocioLabel.setFont(labelFont);
        formPanel.add(numeroSocioLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField numeroSocio = new JTextField(String.valueOf(socio.getNumeroDeSocio()));
        numeroSocio.setFont(textFieldFont);
        numeroSocio.setPreferredSize(new Dimension(300, 40)); // Tamanho aumentado
        numeroSocio.setEditable(false);
        formPanel.add(numeroSocio, gbc);

        // Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        JLabel nomeLabel = new JLabel("Nome");
        nomeLabel.setFont(labelFont);
        formPanel.add(nomeLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField nomeField = new JTextField(socio.getName());
        nomeField.setFont(textFieldFont);
        nomeField.setPreferredSize(new Dimension(300, 40)); // Tamanho aumentado
        formPanel.add(nomeField, gbc);

        // NIF ou CC
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        JLabel nifCcLabel = new JLabel("NIF ou CC");
        nifCcLabel.setFont(labelFont);
        formPanel.add(nifCcLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField nifCcField = new JTextField(String.valueOf(socio.getNifCC()));
        nifCcField.setFont(textFieldFont);
        nifCcField.setPreferredSize(new Dimension(300, 40)); // Tamanho aumentado
        formPanel.add(nifCcField, gbc);

        // Morada
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        JLabel moradaLabel = new JLabel("Morada");
        moradaLabel.setFont(labelFont);
        formPanel.add(moradaLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField moradaField = new JTextField(socio.getMorada());
        moradaField.setFont(textFieldFont);
        moradaField.setPreferredSize(new Dimension(300, 40)); // Tamanho aumentado
        formPanel.add(moradaField, gbc);

        // Telefone
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        JLabel telefoneLabel = new JLabel("Telefone");
        telefoneLabel.setFont(labelFont);
        formPanel.add(telefoneLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField telefoneField = new JTextField(String.valueOf(socio.getTelefone()));
        telefoneField.setFont(textFieldFont);
        telefoneField.setPreferredSize(new Dimension(300, 40)); // Tamanho aumentado
        formPanel.add(telefoneField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.3;
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(labelFont);
        formPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField emailField = new JTextField(socio.getEmail());
        emailField.setFont(textFieldFont);
        emailField.setPreferredSize(new Dimension(300, 40)); // Tamanho aumentado
        formPanel.add(emailField, gbc);

        // Tipo de Sócio
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0.3;
        JLabel tipoSocioLabel = new JLabel("Tipo de sócio");
        tipoSocioLabel.setFont(labelFont);
        formPanel.add(tipoSocioLabel, gbc);
        JPanel tipoSocioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton normalButton = new JRadioButton("Normal");
        JRadioButton premiumButton = new JRadioButton("Premium");
        ButtonGroup tipoSocioGroup = new ButtonGroup();
        tipoSocioGroup.add(normalButton);
        tipoSocioGroup.add(premiumButton);
        tipoSocioPanel.add(normalButton);
        tipoSocioPanel.add(premiumButton);
        gbc.gridx = 1;
        formPanel.add(tipoSocioPanel, gbc);

        // Preferência de Contacto
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weightx = 0.3;
        JLabel preferenciaContactoLabel = new JLabel("Preferência de contacto");
        preferenciaContactoLabel.setFont(labelFont);
        formPanel.add(preferenciaContactoLabel, gbc);
        JPanel preferenciaContactoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton emailButton = new JRadioButton("EMAIL");
        JRadioButton smsButton = new JRadioButton("SMS");
        ButtonGroup preferenciaContactoGroup = new ButtonGroup();
        preferenciaContactoGroup.add(emailButton);
        preferenciaContactoGroup.add(smsButton);
        preferenciaContactoPanel.add(emailButton);
        preferenciaContactoPanel.add(smsButton);
        gbc.gridx = 1;
        formPanel.add(preferenciaContactoPanel, gbc);

        mainPanel.add(formPanel);
        add(mainPanel, BorderLayout.CENTER);

        if (socio.getMembershipType().equalsIgnoreCase("Normal")) {
            normalButton.setSelected(true);
        } else if (socio.getMembershipType().equalsIgnoreCase("Premium")) {
            premiumButton.setSelected(true);
        }
        if (socio.getContactType().equalsIgnoreCase("EMAIL")) {
            emailButton.setSelected(true);
        } else if (socio.getContactType().equalsIgnoreCase("SMS")) {
            smsButton.setSelected(true);
        }

        // Painel inferior com o botão Confirmar dados
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton confirmarButton = new JButton("Confirmar dados");
        confirmarButton.setFont(new Font("Arial", Font.PLAIN, 20));
        confirmarButton.setPreferredSize(new Dimension(200, 50)); // Tamanho do botão
        confirmarButton.setBackground(new Color(51, 153, 255)); // Cor do fundo do botão
        confirmarButton.setForeground(Color.WHITE); // Cor do texto do botão
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obter dados dos campos de texto e botões de rádio
                //String numeroSocio = numeroSocioField.getText();
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
                                "Confirmar alterações?",
                        "Confirmar Dados",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                // Se o usuário confirmar, atualizar o sócio
                if (response == JOptionPane.YES_OPTION) {
                    // Criar novo sócio com os dados fornecidos

                    Socios.getInstance().updateSocio(socio, Integer.parseInt(numeroSocioField), nome, Integer.parseInt(nifCc), morada, Integer.parseInt(telefone), email, tipoSocio, preferenciaContacto);

                    // Aqui, adicione o código para salvar o sócio na base de dados ou na lista de sócios

                    JOptionPane.showMessageDialog(null, "Sócio atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);


                }

                setVisible(false);
                new JanelaPrincipal().setVisible(true);
            }
        });

        bottomPanel.add(confirmarButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

}