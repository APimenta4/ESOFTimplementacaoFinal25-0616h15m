import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.util.List;

public class ValorMultas extends JFrame {

    public ValorMultas(String numeroSocioField) {
        Socio socio = Socios.getInstance().getSocioByNumero(Integer.parseInt(numeroSocioField));
        int memberNumber = Integer.parseInt(numeroSocioField);

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
                new MenuPagamentos().setVisible(true);
            }
        });
        navigationPanel.add(backLabel);

        JLabel pagamentosLabel = new JLabel("Pagamentos e multas/");
        pagamentosLabel.setFont(new Font("Serif", Font.BOLD, 25));
        pagamentosLabel.setForeground(new Color(51, 153, 255));
        pagamentosLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Adicionar margem à esquerda
        navigationPanel.add(pagamentosLabel);

        JLabel pagarAnuidadeLabel = new JLabel("Pagar multas");
        pagarAnuidadeLabel.setFont(new Font("Serif", Font.BOLD, 25));
        pagarAnuidadeLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(pagarAnuidadeLabel);

        topPanel.add(navigationPanel);

        add(topPanel, BorderLayout.NORTH);

        double valorMulta = 0;
        List<Emprestimo> emprestimos = Emprestimos.getInstance().getEmprestimos();
        List<Emprestimo> emprestimosFiltered = emprestimos.stream().filter(emprestimo -> emprestimo.getSocio().getNumeroDeSocio() == memberNumber).toList();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.isMultaPaga() == false) {
                valorMulta += emprestimo.getValorMulta();
            }
        }

        //List<Emprestimo> emprestimos = Emprestimos.getInstance().getEmprestimos();
        // filter emprestimos if numeroDeSocio is equal to memberNumber
        //
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
        JTextField numeroSocio = new JTextField(String.valueOf(socio.getNumeroDeSocio()));
        numeroSocio.setFont(textFieldFont);
        numeroSocio.setPreferredSize(new Dimension(300, 40));
        numeroSocio.setEditable(false);
        formPanel.add(numeroSocio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        JLabel valorPagarLabel = new JLabel("Valor a pagar");
        valorPagarLabel.setFont(labelFont);
        formPanel.add(valorPagarLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField valorPagarField = new JTextField(String.valueOf(valorMulta));
        valorPagarField.setFont(textFieldFont);
        valorPagarField.setPreferredSize(new Dimension(300, 40));
        valorPagarField.setEditable(false);
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
                String valorPagar = valorPagarField.getText();
                String numeroTelemovel = numeroTelemovelField.getText();


                // Validação dos campos
                if (numeroTelemovel.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha o número de telemóvel.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!Pattern.matches("\\d{9}", numeroTelemovel)) {
                    JOptionPane.showMessageDialog(null, "Telefone deve ter 9 dígitos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Aqui você pode adicionar lógica adicional para processar o pagamento via MB Way

                JOptionPane.showMessageDialog(null, "Pagamento gerado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                // Opcionalmente, limpar os campos após gerar o pagamento
                valorPagarField.setText("");
                numeroTelemovelField.setText("");

                // Setar multas como pagas
                for (Emprestimo emprestimo : emprestimosFiltered) {
                    emprestimo.setMultaPaga(true);
                }

                setVisible(false);
                new JanelaPrincipal().setVisible(true);

            }
        });

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espaçamento
        mainPanel.add(gerarPagamentoButton);

        add(mainPanel, BorderLayout.CENTER);
    }



    protected String pagarMultas(int numeroSocio, String numeroTelemovel){
        Socio socio = Socios.getInstance().getSocioByNumero(numeroSocio);
        int memberNumber = numeroSocio;

        double valorMulta = 0;
        List<Emprestimo> emprestimos = Emprestimos.getInstance().getEmprestimos();
        List<Emprestimo> emprestimosFiltered = emprestimos.stream().filter(emprestimo -> emprestimo.getSocio().getNumeroDeSocio() == memberNumber).toList();
        for (Emprestimo emprestimo : emprestimosFiltered) {
            if(emprestimo.isMultaPaga() == false){
                valorMulta += emprestimo.getValorMulta();
            }
        }

        if(valorMulta == 0.0){
            return "O sócio não tem multas por pagar.";
        }

        if (!Socios.getInstance().existsSocio(numeroSocio)) {
            return "Não existe sócio com este número.";
        }

        if (numeroTelemovel.isEmpty()) {
            return "Por favor, preencha o número de telemóvel.";
        }

        if (!Pattern.matches("\\d{9}", numeroTelemovel)) {
            return "Telefone deve ter 9 dígitos numéricos.";
        }


        return "Multas pagas com sucesso.";
    }

}