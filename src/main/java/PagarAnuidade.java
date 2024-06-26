import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class PagarAnuidade extends JFrame {

    public PagarAnuidade() {
        setTitle("Pagar Anuidade - BIBLIOTECH");
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

        JLabel pagamentosLabel = new JLabel("Pagamentos e multas/");
        pagamentosLabel.setFont(new Font("Serif", Font.BOLD, 25));
        pagamentosLabel.setForeground(new Color(51, 153, 255));
        pagamentosLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Adicionar margem à esquerda
        navigationPanel.add(pagamentosLabel);

        JLabel pagarAnuidadeLabel = new JLabel("Pagar anuidade");
        pagarAnuidadeLabel.setFont(new Font("Serif", Font.BOLD, 25));
        pagarAnuidadeLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(pagarAnuidadeLabel);

        topPanel.add(navigationPanel);

        add(topPanel, BorderLayout.NORTH);

        // Painel principal
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
        JLabel numeroSocioLabel = new JLabel("Número de sócio");
        numeroSocioLabel.setFont(labelFont);
        formPanel.add(numeroSocioLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField numeroSocioField = new JTextField();
        numeroSocioField.setFont(textFieldFont);
        numeroSocioField.setPreferredSize(new Dimension(300, 40));
        formPanel.add(numeroSocioField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        JLabel valorPagarLabel = new JLabel("Valor a pagar");
        valorPagarLabel.setFont(labelFont);
        formPanel.add(valorPagarLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField valorPagarField = new JTextField();
        valorPagarField.setFont(textFieldFont);
        valorPagarField.setPreferredSize(new Dimension(300, 40));
        formPanel.add(valorPagarField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        JLabel mbWayLabel = new JLabel("Número de telemóvel:");
        mbWayLabel.setFont(labelFont);
        formPanel.add(mbWayLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField numeroTelemovelField = new JTextField();
        numeroTelemovelField.setFont(textFieldFont);
        numeroTelemovelField.setPreferredSize(new Dimension(300, 40));
        formPanel.add(numeroTelemovelField, gbc);

        mainPanel.add(formPanel);

        // Botão de gerar pagamento
        JButton gerarPagamentoButton = new JButton("Gerar pagamento");
        gerarPagamentoButton.setPreferredSize(new Dimension(250, 50));
        gerarPagamentoButton.setFont(new Font("Arial", Font.BOLD, 20));
        gerarPagamentoButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        gerarPagamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroSocio = numeroSocioField.getText();
                String valorPagar = valorPagarField.getText();
                String numeroTelemovel = numeroTelemovelField.getText();

                // Validação dos campos
                if (numeroSocio.isEmpty() || valorPagar.isEmpty() || numeroTelemovel.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(Socios.getInstance().getSocioByNumero(Integer.parseInt(numeroSocio)).getAnuidadePaga() == 2024){
                    JOptionPane.showMessageDialog(null, "O sócio já tem anuidade paga.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!Pattern.matches("\\d{9}", numeroTelemovel)) {
                    JOptionPane.showMessageDialog(null, "Telefone deve ter 9 dígitos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int anuidadeNormal = ValoresPredefinicoes.getInstance().getAnuidadeNormal();
                int anuidadePremium = ValoresPredefinicoes.getInstance().getAnuidadePremium();
                if(Integer.parseInt(valorPagar) != anuidadePremium && Integer.parseInt(valorPagar) != anuidadeNormal){
                    JOptionPane.showMessageDialog(null, "O valor a pagar deve ser " + anuidadePremium + "€ caso pretenda ser Sócio Premium ou" + anuidadeNormal + "€ caso pretenda ser Sócio Normal.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                // Aqui você pode adicionar lógica adicional para processar o pagamento via MB Way

                JOptionPane.showMessageDialog(null, "Pagamento gerado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                // Opcionalmente, limpar os campos após gerar o pagamento
                numeroSocioField.setText("");
                valorPagarField.setText("");
                numeroTelemovelField.setText("");

                Socio socio = Socios.getInstance().getSocioByNumero(Integer.parseInt(numeroSocio));
                socio.pagarAnuidade();

                if(Integer.parseInt(valorPagar) == anuidadePremium){
                    socio.setMembershipType("Premium");
                } else {
                    socio.setMembershipType("Normal");
                }
            }
        });

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espaçamento
        mainPanel.add(gerarPagamentoButton);

        add(mainPanel, BorderLayout.CENTER);
    }


    protected String pagarAnuidade(int numeroSocio, int valorPagar, int numeroTelemovel) {
        Socio socio = Socios.getInstance().getSocioByNumero(numeroSocio);
        int anuidadeNormal = ValoresPredefinicoes.getInstance().getAnuidadeNormal();
        int anuidadePremium = ValoresPredefinicoes.getInstance().getAnuidadePremium();

        if(socio.getAnuidadePaga() == 2024){
            return "O sócio já tem anuidade paga.";
        }

        if (!Pattern.matches("\\d{9}", String.valueOf(numeroTelemovel))) {
            return "Telefone deve ter 9 dígitos numéricos.";
        }

        if(valorPagar != anuidadePremium && valorPagar != anuidadeNormal){
            return "O valor a pagar deve ser 150€ caso pretenda ser Sócio Premium ou 120€ caso pretenda ser Sócio Normal.";
        }

        socio.pagarAnuidade();

        if(valorPagar == anuidadePremium){
            socio.setMembershipType("Premium");
        } else {
            socio.setMembershipType("Normal");
        }

        return "Pagamento gerado com sucesso!";
    }



}