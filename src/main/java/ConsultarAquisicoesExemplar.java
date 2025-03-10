import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class ConsultarAquisicoesExemplar extends JFrame {
    public ConsultarAquisicoesExemplar() {
        // Layout da página
        setTitle("Consultar Aquisições - BIBLIOTECH");
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
        backLabel.setForeground(new Color(51, 153, 255)); // Blue color
        backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backLabel.setBorder(new EmptyBorder(0, 25, 0, 0)); // Add left margin
        backLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Handle back click
                setVisible(false);
                new MenuAquisicoes().setVisible(true);
            }
        });
        navigationPanel.add(backLabel);

        JLabel aquisicoesLabel = new JLabel("Aquisições/");
        aquisicoesLabel.setFont(new Font("Serif", Font.BOLD, 25));
        aquisicoesLabel.setForeground(new Color(51, 153, 255));
        aquisicoesLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Add left margin
        navigationPanel.add(aquisicoesLabel);

        JLabel consultarLabel = new JLabel("Consultar por código");
        consultarLabel.setFont(new Font("Serif", Font.BOLD, 25));
        consultarLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(consultarLabel);

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
        JLabel codigoExemplarLabel = new JLabel("Código de exemplar");
        codigoExemplarLabel.setFont(labelFont);
        formPanel.add(codigoExemplarLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField codigoExemplarField = new JTextField();
        codigoExemplarField.setFont(textFieldFont);
        codigoExemplarField.setPreferredSize(new Dimension(300, 40)); // Increased size
        formPanel.add(codigoExemplarField, gbc);

        mainPanel.add(formPanel);

        JButton searchButton = new JButton("Pesquisar aquisição");
        searchButton.setPreferredSize(new Dimension(250, 50)); // Set the preferred width to 250 and height to 50
        searchButton.setMaximumSize(new Dimension(250, 50)); // Set maximum size for the button
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoExemplar = codigoExemplarField.getText();

                if(codigoExemplar == null || codigoExemplar.isEmpty()){
                    JOptionPane.showMessageDialog(null, "O código de exemplar não pode estar vazio");
                    return;
                }

                List<Aquisicao> aquisicoes = Aquisicoes.getInstance().getAquisicoes();

                for (Aquisicao aquisicao : aquisicoes) {
                    if (aquisicao.getExemplar().getCodigo().equals(codigoExemplar)) {
                        setVisible(false);
                        new ConsultarAquisicoes(aquisicao).setVisible(true);
                        return;
                    }
                }

                JOptionPane.showMessageDialog(null, "O exemplar especificado não existe ou não possui aquisição");
            }
        });

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(searchButton);

        add(mainPanel, BorderLayout.CENTER);
    }


    protected String pesquisarAquisicoesExemplar(String codigoExemplar) {
        if(codigoExemplar == null || codigoExemplar.isEmpty()){
            return "O código de exemplar não pode ser vazio";
        }

        List<Aquisicao> aquisicoes = Aquisicoes.getInstance().getAquisicoes();

        for (Aquisicao aquisicao : aquisicoes) {
            if (aquisicao.getExemplar().getCodigo().equals(codigoExemplar)) {
                new ConsultarAquisicoes(aquisicao).setVisible(true);
                return "Aquisição encontrada";
            }
        }

        return "O exemplar especificado não existe ou não possui aquisição";
    }

}
