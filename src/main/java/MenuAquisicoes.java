import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class MenuAquisicoes extends JFrame {
    public MenuAquisicoes() {
        setTitle("Aquisições - BIBLIOTECH");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top panel with "BIBLIOTECH" label and "Aquisições" title
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

        JLabel titleLabel = new JLabel("Aquisições");
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
        gbc.fill = GridBagConstraints.BOTH;  // Allow resizing
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        JButton registarNovaAquisicaoButton = createButton("Registar nova aquisição", "plus.png");
        JPanel buttonPanel1 = new JPanel();
        buttonPanel1.setLayout(new BoxLayout(buttonPanel1, BoxLayout.X_AXIS));
        buttonPanel1.add(registarNovaAquisicaoButton);
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(buttonPanel1, gbc);

        JButton consultarTodasAquisicoesButton = createButton("Consultar todas as aquisições", "list.png");
        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setLayout(new BoxLayout(buttonPanel2, BoxLayout.X_AXIS));
        buttonPanel2.add(consultarTodasAquisicoesButton);
        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonPanel.add(buttonPanel2, gbc);

        JButton consultarPorCodigoExemplarButton = createButton("Consultar por código exemplar", "search.png");
        JPanel buttonPanel3 = new JPanel();
        buttonPanel3.setLayout(new BoxLayout(buttonPanel3, BoxLayout.X_AXIS));
        buttonPanel3.add(consultarPorCodigoExemplarButton);
        gbc.gridx = 2;
        gbc.gridy = 0;
        buttonPanel.add(buttonPanel3, gbc);

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

        // Add ActionListeners for other buttons if needed
        registarNovaAquisicaoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                NovaAquisicao novaAquisicao = new NovaAquisicao();
                novaAquisicao.setVisible(true);
            }
        });

        consultarTodasAquisicoesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ConsultarAquisicoes consultarAquisicoes = new ConsultarAquisicoes();
                consultarAquisicoes.setVisible(true);
            }
        });

        consultarPorCodigoExemplarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ConsultarAquisicoesExemplar consultarAquisicoesExemplar = new ConsultarAquisicoesExemplar();
                consultarAquisicoesExemplar.setVisible(true);
            }
        });
    }

    private JButton createButton(String text, String iconName) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout());
        button.setFont(new Font("Arial", Font.PLAIN, 26));
        button.setPreferredSize(new Dimension(375, 170));
        button.setMaximumSize(new Dimension(375, 170));  // Set maximum size for the button
        button.setFocusPainted(false);  // Remove focus indicator

        try {
            URL iconURL = getClass().getResource(iconName);
            BufferedImage img = ImageIO.read(iconURL);
            ImageIcon icon = new ImageIcon(img);

            // Scale the icon if needed
            Image scaledImg = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImg);

            // Add the icon with a small margin
            JLabel iconLabel = new JLabel(scaledIcon);
            iconLabel.setBorder(new EmptyBorder(0, 10, 0, 10)); // Adjust as needed
            button.add(iconLabel, BorderLayout.WEST);

            // Add the text
            JLabel textLabel = new JLabel("<html>" + text + "</html>");
            textLabel.setFont(new Font("Arial", Font.PLAIN, 26));
            textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.add(textLabel, BorderLayout.CENTER);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return button;
    }



}