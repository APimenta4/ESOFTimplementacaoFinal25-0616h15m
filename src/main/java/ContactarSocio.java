import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactarSocio extends JFrame {
    public ContactarSocio() {
        setTitle("Contactar Sócio - BIBLIOTECH");
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

        JLabel contactarLabel = new JLabel("Contactar sócio");
        contactarLabel.setFont(new Font("Serif", Font.BOLD, 25));
        contactarLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(contactarLabel);

        topPanel.add(navigationPanel);

        add(topPanel, BorderLayout.NORTH);

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

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
        JLabel numeroSocioLabel = new JLabel("Número de sócio");
        numeroSocioLabel.setFont(labelFont);
        formPanel.add(numeroSocioLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField numeroSocioField = new JTextField();
        numeroSocioField.setFont(textFieldFont);
        numeroSocioField.setPreferredSize(new Dimension(300, 40)); // Tamanho aumentado
        formPanel.add(numeroSocioField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        JLabel mensagemLabel = new JLabel("Mensagem a enviar");
        mensagemLabel.setFont(labelFont);
        formPanel.add(mensagemLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextArea mensagemArea = new JTextArea();
        mensagemArea.setFont(textFieldFont);
        mensagemArea.setPreferredSize(new Dimension(300, 150)); // Tamanho aumentado
        mensagemArea.setLineWrap(true);
        mensagemArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(mensagemArea);
        scrollPane.setPreferredSize(new Dimension(300, 150));
        formPanel.add(scrollPane, gbc);

        mainPanel.add(formPanel);

        // Botão de enviar
        JButton enviarButton = new JButton("Enviar");
        enviarButton.setPreferredSize(new Dimension(150, 40)); // Setar tamanho preferido
        enviarButton.setMaximumSize(new Dimension(150, 40)); // Definir tamanho máximo
        enviarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obter dados dos campos de texto
                String numeroSocio = numeroSocioField.getText();
                String mensagem = mensagemArea.getText();

                // Validação dos campos
                if (numeroSocio.isEmpty() || mensagem.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Get the member number from the input field
                int numeroDeSocio;
                try {
                    numeroDeSocio = Integer.parseInt(numeroSocioField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Número inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check if a member with this number exists
                if (!Socios.getInstance().existsSocio(numeroDeSocio)) {
                    JOptionPane.showMessageDialog(null, "Não existe um sócio com este número.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Aqui, adicione o código para enviar a mensagem ao sócio

                JOptionPane.showMessageDialog(null, "Mensagem enviada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                setVisible(false);
                new JanelaPrincipal().setVisible(true);
            }
        });

        mainPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Espaçamento
        mainPanel.add(enviarButton);

        add(mainPanel, BorderLayout.CENTER);
    }


    protected String contactarSocio(int numeroSocio, String mensagem){
        // Aqui, adicione o código para enviar a mensagem ao sócio

        if (!Socios.getInstance().existsSocio(numeroSocio)) {
            return "Não existe um sócio com este número.";
        }

        return "Sócio contactado com sucesso";

    }

}