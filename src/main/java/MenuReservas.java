import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;



public class MenuReservas extends JFrame {

    public MenuReservas() {
        setTitle("Reservas - BIBLIOTECH");
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

        JLabel titleLabel = new JLabel("Reservas");
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

        JButton registarNovaReservaButton = createButton("Registar nova reserva", "plus.png");
        registarNovaReservaButton.setFocusPainted(false);
        JButton consultarTodasReservasButton = createButton("Consultar todas as reservas", "list.png");
        consultarTodasReservasButton.setFocusPainted(false);
        JButton consultarPorTituloDoLivroButton = createButton("Consultar por titulo do livro", "id-card 1.png");
        consultarPorTituloDoLivroButton.setFocusPainted(false);
        JButton anularReservaButton = createButton("Anular Reserva", "remove 1.png");
        anularReservaButton.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(registarNovaReservaButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonPanel.add(consultarTodasReservasButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(consultarPorTituloDoLivroButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonPanel.add(anularReservaButton, gbc);

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
                setVisible(false);

                JanelaPrincipal main = new JanelaPrincipal();
                main.setVisible(true);
            }
        });

        registarNovaReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                new RegistarNovaReserva().setVisible(true);
            }
        });

        consultarTodasReservasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                new ConsultarTodasReservas().setVisible(true);
            }
        });

        consultarPorTituloDoLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                new ConsultarPorTituloDoLivro().setVisible(true);
            }
        });

        anularReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                new AnularReserva().setVisible(true);
            }
        });



    }





        private JButton createButton (String text, String iconName){
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