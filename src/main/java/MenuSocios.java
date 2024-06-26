import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class MenuSocios extends JFrame {

    public MenuSocios() {
        setTitle("Sócios - BIBLIOTECH");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top panel with "BIBLIOTECH" label and "Sócios" title
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

        JLabel titleLabel = new JLabel("Sócios");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 50));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        topPanel.add(titleLabel);

        add(topPanel, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        JButton registarNovoSocioButton = createButton("Registar novo sócio", "add.png");
        registarNovoSocioButton.setFocusPainted(false);
        JButton pagarAnuidadeMultasButton = createButton("Pagar anuidade", "credit-card.png");
        pagarAnuidadeMultasButton.setFocusPainted(false);
        JButton cancelarAnuidadeButton = createButton("Cancelar anuidade", "remove.png");
        cancelarAnuidadeButton.setFocusPainted(false);
        JButton contactarSocioButton = createButton("Contactar sócio", "copy-document.png");
        contactarSocioButton.setFocusPainted(false);
        JButton pesquisarAlterarDadosButton = createButton("Pesquisar/alterar dados por número", "id-card.png");
        pesquisarAlterarDadosButton.setFocusPainted(false);
        JButton consultarDadosSociosButton = createButton("Consultar dados dos sócios", "list.png");
        consultarDadosSociosButton.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(registarNovoSocioButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonPanel.add(contactarSocioButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(pagarAnuidadeMultasButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonPanel.add(pesquisarAlterarDadosButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(cancelarAnuidadeButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        buttonPanel.add(consultarDadosSociosButton, gbc);

        add(buttonPanel, BorderLayout.CENTER);

        // Bottom panel
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

                // Show the main window (JanelaPrincipal)
                JanelaPrincipal mainFrame = new JanelaPrincipal();
                mainFrame.setVisible(true);
            }
        });

        // Add ActionListeners for other buttons
        registarNovoSocioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new NovoSocio().setVisible(true);
            }
        });

        pagarAnuidadeMultasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new PagarAnuidade().setVisible(true);
            }
        });

        cancelarAnuidadeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new CancelarAnuidade().setVisible(true);
            }
        });

        contactarSocioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new ContactarSocio().setVisible(true);
            }
        });

        pesquisarAlterarDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new PesquisarInformacoesSocio().setVisible(true);
            }
        });

        consultarDadosSociosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new ConsultarDadosSocios().setVisible(true);
            }
        });
    }

    private JButton createButton(String text, String iconName) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 32));
        button.setHorizontalAlignment(SwingConstants.LEFT);

        try {
            URL iconURL = getClass().getResource(iconName);
            BufferedImage img = ImageIO.read(iconURL);
            Image resizedImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(resizedImg));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return button;
    }
}