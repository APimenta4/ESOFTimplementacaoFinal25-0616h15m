import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class JanelaPrincipal extends JFrame {
    public JanelaPrincipal() {
        setTitle("BIBLIOTECH");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("BIBLIOTECH");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 52));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton emprestimosButton = new JButton("Empréstimos");
        emprestimosButton.setFont(new Font("Arial", Font.PLAIN, 32));
        emprestimosButton.setFocusPainted(false);

        JButton sociosButton = new JButton("Sócios");
        sociosButton.setFont(new Font("Arial", Font.PLAIN, 32));
        sociosButton.setFocusPainted(false);

        JButton livrosButton = new JButton("Livros e Aquisições");
        livrosButton.setFont(new Font("Arial", Font.PLAIN, 32));
        livrosButton.setFocusPainted(false);

        JButton reservasButton = new JButton("Reservas");
        reservasButton.setFont(new Font("Arial", Font.PLAIN, 32));
        reservasButton.setFocusPainted(false);

        JButton pagamentosButton = new JButton("Pagamentos e Multas");
        pagamentosButton.setFont(new Font("Arial", Font.PLAIN, 32));
        pagamentosButton.setFocusPainted(false);

        JButton pesquisasButton = new JButton("Pesquisas e Estatística");
        pesquisasButton.setFont(new Font("Arial", Font.PLAIN, 32));
        pesquisasButton.setFocusPainted(false);

        buttonPanel.add(emprestimosButton);
        buttonPanel.add(sociosButton);
        buttonPanel.add(livrosButton);
        buttonPanel.add(reservasButton);
        buttonPanel.add(pagamentosButton);
        buttonPanel.add(pesquisasButton);

        add(buttonPanel, BorderLayout.CENTER);

        URL iconURL = getClass().getResource("settings.png");
        ImageIcon settingsIcon = null;
        try {
            BufferedImage img = ImageIO.read(iconURL);
            Image resizedImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            settingsIcon = new ImageIcon(resizedImg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a panel for the exit button and settings icon
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JButton exitButton = new JButton("Sair");
        JLabel settingsLabel = new JLabel(settingsIcon);

        // Create a panel for the exit button with a small margin
        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exitPanel.setBorder(new EmptyBorder(0, 0, 7, 25)); // top, left, bottom, right
        exitPanel.add(exitButton);

        // Create a panel for the settings icon with a small margin
        JPanel settingsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        settingsPanel.setBorder(new EmptyBorder(0, 25, 15, 0)); // top, left, bottom, right
        settingsPanel.add(settingsLabel);

        bottomPanel.add(settingsPanel, BorderLayout.WEST);
        bottomPanel.add(exitPanel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        emprestimosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hide the current window
                setVisible(false);

                // Show the new window
                new MenuEmprestimos().setVisible(true);
            }
        });

        sociosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Sócios selected");
            }
        });

        livrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hide the current window
                setVisible(false);

                // Show the new window
                new MenuAquisicoes().setVisible(true);
            }
        });

        reservasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Reservas selected");
            }
        });

        pagamentosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Pagamentos e Multas selected");
            }
        });

        pesquisasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Pesquisas e Estatística selected");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        settingsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Hide the current window
                setVisible(false);

                // Show the new window
                new Predefinicoes().setVisible(true);
            }
        });


    }

}
