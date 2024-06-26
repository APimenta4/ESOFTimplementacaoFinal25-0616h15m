import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerInformacoesSocio extends JFrame {
    public VerInformacoesSocio(String numeroSocioField) {

        // Pega as informações do sócio
        Socio socio = Socios.getInstance().getSocioByNumero(Integer.parseInt(numeroSocioField));
        //JTextField diasEmprestimoField = new JTextField(String.valueOf(valoresPredefinicoes.getDiasEmprestimo()));

        setTitle("Pesquisar Informações do Sócio - BIBLIOTECH");
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


        // Adiciona o botão ao canto superior direito
        JPanel Panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton infoButton = new JButton("Alterar informações");
        Panel.add(infoButton);
        add(Panel, BorderLayout.NORTH);
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hide the current window (Emprestimos, Aquisicoes, etc.)
                setVisible(false);

                EditarInformacoesSocio frame = new EditarInformacoesSocio(numeroSocioField);
                frame.setVisible(true);
            }
        });

        topPanel.add(infoButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);


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

        JLabel pesquisarLabel = new JLabel("Pesquisar por número");
        pesquisarLabel.setFont(new Font("Serif", Font.BOLD, 25));
        pesquisarLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(pesquisarLabel);

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
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        JLabel nomeLabel = new JLabel("Nome");
        nomeLabel.setFont(labelFont);
        formPanel.add(nomeLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField nomeField = new JTextField(socio.getName());
        nomeField.setFont(textFieldFont);
        nomeField.setPreferredSize(new Dimension(300, 40)); // Tamanho aumentado
        nomeField.setEditable(false);
        formPanel.add(nomeField, gbc);

        // NIF ou CC
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        JLabel nifCcLabel = new JLabel("NIF ou CC");
        nifCcLabel.setFont(labelFont);
        formPanel.add(nifCcLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField nifCcField = new JTextField(String.valueOf(socio.getNifCC()));
        nifCcField.setFont(textFieldFont);
        nifCcField.setPreferredSize(new Dimension(300, 40)); // Tamanho aumentado
        nifCcField.setEditable(false);
        formPanel.add(nifCcField, gbc);

        // Morada
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        JLabel moradaLabel = new JLabel("Morada");
        moradaLabel.setFont(labelFont);
        formPanel.add(moradaLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField moradaField = new JTextField(socio.getMorada());
        moradaField.setFont(textFieldFont);
        moradaField.setPreferredSize(new Dimension(300, 40)); // Tamanho aumentado
        moradaField.setEditable(false);
        formPanel.add(moradaField, gbc);

        // Telefone
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.3;
        JLabel telefoneLabel = new JLabel("Telefone");
        telefoneLabel.setFont(labelFont);
        formPanel.add(telefoneLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField telefoneField = new JTextField(String.valueOf(socio.getTelefone()));
        telefoneField.setFont(textFieldFont);
        telefoneField.setPreferredSize(new Dimension(300, 40)); // Tamanho aumentado
        telefoneField.setEditable(false);
        formPanel.add(telefoneField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.3;
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(labelFont);
        formPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField emailField = new JTextField(socio.getEmail());
        emailField.setFont(textFieldFont);
        emailField.setPreferredSize(new Dimension(300, 40)); // Tamanho aumentado
        emailField.setEditable(false);
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


        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton voltarButton = new JButton("Voltar");
        bottomPanel.setBorder(new EmptyBorder(0, 0, 20, 20));
        bottomPanel.add(voltarButton);
        add(bottomPanel, BorderLayout.SOUTH);
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hide the current window (Emprestimos, Aquisicoes, etc.)
                setVisible(false);

                MenuSocios mainFrame = new MenuSocios();
                mainFrame.setVisible(true);
            }
        });

        add(mainPanel, BorderLayout.CENTER);
    }

}

