import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Predefinicoes extends JFrame {
    public Predefinicoes() {
        // Layout da página
        setTitle("Empréstimos - BIBLIOTECH");
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

        JLabel definicoesLabel = new JLabel("Definições");
        definicoesLabel.setFont(new Font("Serif", Font.BOLD, 36));
        definicoesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(definicoesLabel);

        add(topPanel, BorderLayout.NORTH);

        // Conteúdo
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel settingsPanel = new JPanel(new GridBagLayout());
        settingsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.PLAIN, 20);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 20);

        ValoresPredefinicoes valoresPredefinicoes = ValoresPredefinicoes.getInstance();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        JLabel diasEmprestimoLabel = new JLabel("Dias de empréstimo");
        diasEmprestimoLabel.setFont(labelFont);
        settingsPanel.add(diasEmprestimoLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField diasEmprestimoField = new JTextField(String.valueOf(valoresPredefinicoes.getDiasEmprestimo()));
        diasEmprestimoField.setFont(textFieldFont);
        settingsPanel.add(diasEmprestimoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        JLabel maxEmprestimosLabel = new JLabel("Máximo empréstimos simultâneos / Sócio");
        maxEmprestimosLabel.setFont(labelFont);
        settingsPanel.add(maxEmprestimosLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField maxEmprestimosField = new JTextField(String.valueOf(valoresPredefinicoes.getMaxEmprestimos()));
        maxEmprestimosField.setFont(textFieldFont);
        settingsPanel.add(maxEmprestimosField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        JLabel multaDiaLabel = new JLabel("Multa/Dia atraso");
        multaDiaLabel.setFont(labelFont);
        settingsPanel.add(multaDiaLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField multaDiaField = new JTextField(String.valueOf(valoresPredefinicoes.getMultaDia()));
        multaDiaField.setFont(textFieldFont);
        settingsPanel.add(multaDiaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        JLabel anuidadeNormalLabel = new JLabel("Valor anuidade (Sócio normal)");
        anuidadeNormalLabel.setFont(labelFont);
        settingsPanel.add(anuidadeNormalLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField anuidadeNormalField = new JTextField(String.valueOf(valoresPredefinicoes.getAnuidadeNormal()));
        anuidadeNormalField.setFont(textFieldFont);
        settingsPanel.add(anuidadeNormalField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.3;
        JLabel anuidadePremiumLabel = new JLabel("Valor anuidade (Sócio premium)");
        anuidadePremiumLabel.setFont(labelFont);
        settingsPanel.add(anuidadePremiumLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField anuidadePremiumField = new JTextField(String.valueOf(valoresPredefinicoes.getAnuidadePremium()));
        anuidadePremiumField.setFont(textFieldFont);
        settingsPanel.add(anuidadePremiumField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.3;
        JLabel emprestimosExtraPremiumLabel = new JLabel("Empréstimos simultâneos extra (Sócios Premium)");
        emprestimosExtraPremiumLabel.setFont(labelFont);
        settingsPanel.add(emprestimosExtraPremiumLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField emprestimosExtraPremiumField = new JTextField(String.valueOf(valoresPredefinicoes.getExtraPremium()));
        emprestimosExtraPremiumField.setFont(textFieldFont);
        settingsPanel.add(emprestimosExtraPremiumField, gbc);

        mainPanel.add(settingsPanel);

        JButton confirmButton = new JButton("Confirmar dados");
        confirmButton.setPreferredSize(new Dimension(250, 50));
        confirmButton.setMaximumSize(new Dimension(250, 50));
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String diasEmprestimoText = diasEmprestimoField.getText();
                String maxEmprestimosText = maxEmprestimosField.getText();
                String multaDiaText = multaDiaField.getText();
                String anuidadeNormalText = anuidadeNormalField.getText();
                String anuidadePremiumText = anuidadePremiumField.getText();

                try {
                    int diasEmprestimo = Integer.parseInt(diasEmprestimoText);
                    int maxEmprestimos = Integer.parseInt(maxEmprestimosText);
                    int multaDia = Integer.parseInt(multaDiaText);
                    int anuidadeNormal = Integer.parseInt(anuidadeNormalText);
                    int anuidadePremium = Integer.parseInt(anuidadePremiumText);
                    int extraPremium = Integer.parseInt(emprestimosExtraPremiumField.getText());

                    ValoresPredefinicoes valoresPredefinicoes = ValoresPredefinicoes.getInstance();
                    valoresPredefinicoes.setDiasEmprestimo(diasEmprestimo);
                    valoresPredefinicoes.setMaxEmprestimos(maxEmprestimos);
                    valoresPredefinicoes.setMultaDia(multaDia);
                    valoresPredefinicoes.setAnuidadeNormal(anuidadeNormal);
                    valoresPredefinicoes.setAnuidadePremium(anuidadePremium);
                    valoresPredefinicoes.setExtraPremium(extraPremium);

                    JOptionPane.showMessageDialog(null, "Definições alteradas com sucesso");
                    setVisible(false);
                    new JanelaPrincipal().setVisible(true);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Os valores inseridos são inválidos");
                }
            }
        });


        mainPanel.add(confirmButton);

        add(mainPanel, BorderLayout.CENTER);
    }

}
