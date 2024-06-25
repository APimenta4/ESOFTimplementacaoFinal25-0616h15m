import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class NovaAquisicao extends JFrame {
    public NovaAquisicao() {
        setTitle("Registar Nova Aquisição - BIBLIOTECH");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top panel with "BIBLIOTECH" label and navigation
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

        JLabel registarLabel = new JLabel("Registar nova aquisição");
        registarLabel.setFont(new Font("Serif", Font.BOLD, 25));
        registarLabel.setForeground(new Color(51, 153, 255));
        navigationPanel.add(registarLabel);

        topPanel.add(navigationPanel);

        add(topPanel, BorderLayout.NORTH);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Replace the current font declarations
        Font labelFont = new Font("Arial", Font.PLAIN, 20);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 20);

        // Labels and text fields
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        JLabel tituloLabel = new JLabel("Título");
        tituloLabel.setFont(labelFont);
        formPanel.add(tituloLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField tituloField = new JTextField();
        tituloField.setFont(textFieldFont);
        tituloField.setPreferredSize(new Dimension(300, 40));
        formPanel.add(tituloField, gbc);

        // Author(s)
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        JLabel autoresLabel = new JLabel("Autor(es)");
        autoresLabel.setFont(labelFont);
        formPanel.add(autoresLabel, gbc);
        gbc.gridx = 3;
        gbc.weightx = 0.7;
        JTextField autoresField = new JTextField();
        autoresField.setFont(textFieldFont);
        autoresField.setPreferredSize(new Dimension(300, 40));
        formPanel.add(autoresField, gbc);

        // Edition/Year
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        JLabel edicaoAnoLabel = new JLabel("Edição/Ano");
        edicaoAnoLabel.setFont(labelFont);
        formPanel.add(edicaoAnoLabel, gbc);
        gbc.gridx = 5;
        gbc.weightx = 0.7;
        JTextField edicaoAnoField = new JTextField();
        edicaoAnoField.setFont(textFieldFont);
        edicaoAnoField.setPreferredSize(new Dimension(300, 40));
        formPanel.add(edicaoAnoField, gbc);

        // Code
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        JLabel codigoLabel = new JLabel("Código");
        codigoLabel.setFont(labelFont);
        formPanel.add(codigoLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField codigoField = new JTextField();
        codigoField.setFont(textFieldFont);
        codigoField.setPreferredSize(new Dimension(300, 40));
        formPanel.add(codigoField, gbc);

        // Genre(s)
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        JLabel generoLabel = new JLabel("Gênero(s)");
        generoLabel.setFont(labelFont);
        formPanel.add(generoLabel, gbc);
        gbc.gridx = 3;
        gbc.weightx = 0.7;
        JTextField generoField = new JTextField();
        generoField.setFont(textFieldFont);
        generoField.setPreferredSize(new Dimension(300, 40));
        formPanel.add(generoField, gbc);

        // Shelf/Stand
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        JLabel prateleiraLabel = new JLabel("Prateleira/Estante");
        prateleiraLabel.setFont(labelFont);
        formPanel.add(prateleiraLabel, gbc);
        gbc.gridx = 5;
        gbc.weightx = 0.7;
        JTextField prateleiraField = new JTextField();
        prateleiraField.setFont(textFieldFont);
        prateleiraField.setPreferredSize(new Dimension(300, 40));
        formPanel.add(prateleiraField, gbc);

        // ISBN
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        JLabel isbnLabel = new JLabel("ISBN");
        isbnLabel.setFont(labelFont);
        formPanel.add(isbnLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField isbnField = new JTextField();
        isbnField.setFont(textFieldFont);
        isbnField.setPreferredSize(new Dimension(300, 40));
        formPanel.add(isbnField, gbc);

        // Publisher
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        JLabel editoraLabel = new JLabel("Editora");
        editoraLabel.setFont(labelFont);
        formPanel.add(editoraLabel, gbc);
        gbc.gridx = 3;
        gbc.weightx = 0.7;
        JComboBox<Editora> editoraComboBox = new JComboBox<>(Editoras.getInstance().getEditoras().toArray(new Editora[0]));
        editoraComboBox.setFont(textFieldFont);
        editoraComboBox.setPreferredSize(new Dimension(300, 40));
        formPanel.add(editoraComboBox, gbc);

        // Add button below the dropdown
        JLabel addEditoraLabel = new JLabel("Adicionar editora");
        addEditoraLabel.setFont(labelFont);

        // Update the gridx and gridy properties of gbc for the label
        gbc.gridx = 2;
        gbc.gridy = 3;

        formPanel.add(addEditoraLabel, gbc);

        JButton addEditoraButton = new JButton("+");
        addEditoraButton.setFont(labelFont);
        addEditoraButton.setFocusPainted(false);
        addEditoraButton.setPreferredSize(new Dimension(50, 40));
        addEditoraButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newEditoraName = JOptionPane.showInputDialog("Introduza o nome da nova editora:");
            if (newEditoraName != null && !newEditoraName.trim().isEmpty()) {
                // Check if the newEditoraName is unique among Editoras
                List<Editora> editoras = Editoras.getInstance().getEditoras();
                for (Editora editora : editoras) {
                    if (editora.getName().equals(newEditoraName)) {
                        JOptionPane.showMessageDialog(null, "O nome da editora especificado já existe");
                        return;
                    }
                }
                // If the newEditoraName is unique, create a new Editora
                Editora newEditora = new Editora(newEditoraName);
                Editoras.getInstance().addEditora(newEditora);
                editoraComboBox.addItem(newEditora);
                editoraComboBox.setSelectedItem(newEditora);
            }
        }
        });

        // Update the gridx and gridy properties of gbc for the button
        gbc.gridx = 3;
        gbc.gridy = 3;

        formPanel.add(addEditoraButton, gbc);


        // Distributor
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        JLabel distribuidorLabel = new JLabel("Distribuidor");
        distribuidorLabel.setFont(labelFont);
        formPanel.add(distribuidorLabel, gbc);
        gbc.gridx = 5;
        gbc.weightx = 0.7;
        JComboBox<Distribuidor> distribuidorComboBox = new JComboBox<>(Distribuidores.getInstance().getDistribuidores().toArray(new Distribuidor[0]));
        distribuidorComboBox.setFont(textFieldFont);
        distribuidorComboBox.setPreferredSize(new Dimension(300, 40));
        formPanel.add(distribuidorComboBox, gbc);

        // Add label "Adicionar distribuidor" below the dropdown
        JLabel addDistribuidorLabel = new JLabel("Adicionar distribuidor");
        addDistribuidorLabel.setFont(labelFont);

        // Update the gridx and gridy properties of gbc for the label
        gbc.gridx = 4;
        gbc.gridy = 3;

        formPanel.add(addDistribuidorLabel, gbc);

        // Add button below the dropdown
        JButton addDistribuidorButton = new JButton("+");
        addDistribuidorButton.setFont(labelFont);
        addDistribuidorButton.setFocusPainted(false);
        addDistribuidorButton.setPreferredSize(new Dimension(50, 40));
        addDistribuidorButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newDistribuidorName = JOptionPane.showInputDialog("Introduza o nome do novo distribuidor:");
            if (newDistribuidorName != null && !newDistribuidorName.trim().isEmpty()) {
                // Check if the newDistribuidorName is unique among Distribuidores
                List<Distribuidor> distribuidores = Distribuidores.getInstance().getDistribuidores();
                for (Distribuidor distribuidor : distribuidores) {
                    if (distribuidor.getName().equals(newDistribuidorName)) {
                        JOptionPane.showMessageDialog(null, "O nome do distribuidor especificado já existe");
                        return;
                    }
                }
                // If the newDistribuidorName is unique, create a new Distribuidor
                Distribuidor newDistribuidor = new Distribuidor(newDistribuidorName);
                Distribuidores.getInstance().addDistribuidor(newDistribuidor);
                distribuidorComboBox.addItem(newDistribuidor);
                distribuidorComboBox.setSelectedItem(newDistribuidor);
            }
        }
        });

        // Update the gridx and gridy properties of gbc for the button
        gbc.gridx = 5;
        gbc.gridy = 3;

        formPanel.add(addDistribuidorButton, gbc);

        mainPanel.add(formPanel);

        // Confirm button
        JButton confirmButton = new JButton("Confirmar aquisição");
        confirmButton.setPreferredSize(new Dimension(250, 50)); // Set the preferred width to 200 and height to 50
        confirmButton.setMaximumSize(new Dimension(250, 50)); // Set maximum size for the button
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the values from the text fields
                String titulo = tituloField.getText();
                String autores = autoresField.getText();
                String edicaoAno = edicaoAnoField.getText();
                String codigo = codigoField.getText();
                String genero = generoField.getText();
                String prateleira = prateleiraField.getText();
                String isbn = isbnField.getText();
                Editora editora = (Editora) editoraComboBox.getSelectedItem();
                Distribuidor distribuidor = (Distribuidor) distribuidorComboBox.getSelectedItem();

                // Call the registarAquisicao method
                String result = registarAquisicao(titulo, autores, edicaoAno, codigo, genero, prateleira, isbn, editora, distribuidor);

                if(result.equals("Aquisição registada com sucesso")) {
                    JOptionPane.showMessageDialog(null, result);
                    setVisible(false);
                    new JanelaPrincipal().setVisible(true);
                    return;
                }
                JOptionPane.showMessageDialog(null, result);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmButton);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add vertical spacing
        mainPanel.add(buttonPanel);

        add(mainPanel, BorderLayout.CENTER);
    }


    protected String registarAquisicao(String titulo, String autores, String edicaoAno, String codigo, String genero, String prateleira, String isbn, Editora editora, Distribuidor distribuidor){
        if (titulo.isEmpty() || autores.isEmpty() || edicaoAno.isEmpty() || codigo.isEmpty() || genero.isEmpty() || prateleira.isEmpty() || isbn.isEmpty() || editora == null || distribuidor == null) {
            return "Todos os campos são obrigatórios";
        }
        // Check if the codigo and isbn are unique among Exemplares
        List<Exemplar> exemplares = Exemplares.getInstance().getExemplares();
        for (Exemplar exemplar : exemplares) {
            if (exemplar.getCodigo().equals(codigo)) {
                return "O código especificado já existe";
            }
            if (exemplar.getIsbn().equals(isbn)) {
                return "O ISBN especificado já existe";
            }
        }

        // Create a new Exemplar object
        Exemplar exemplar = new Exemplar(titulo, codigo, autores, genero, editora, edicaoAno, isbn, prateleira);

        // Create a new Aquisicao object and add it to the Aquisicoes instance
        Aquisicao aquisicao = new Aquisicao(exemplar, distribuidor);
        Aquisicoes.getInstance().addAquisicao(aquisicao);

        // Show a success message
        return "Aquisição registada com sucesso";

    }

    protected String newEditora(String newEditoraName){
        if (newEditoraName != null && !newEditoraName.trim().isEmpty()) {
            // Check if the newEditoraName is unique among Editoras
            List<Editora> editoras = Editoras.getInstance().getEditoras();
            for (Editora editora : editoras) {
                if (editora.getName().equals(newEditoraName)) {
                    return "O nome da editora especificado já existe";
                }
            }
            // If the newEditoraName is unique, create a new Editora
            Editora newEditora = new Editora(newEditoraName);
            Editoras.getInstance().addEditora(newEditora);
            return "Editora adicionada com sucesso";
        }
        else{
            return "O nome da editora não pode ser vazio";
        }
    }

    protected String newDistribuidor(String newDistribuidorName){
        if (newDistribuidorName != null && !newDistribuidorName.trim().isEmpty()) {
            // Check if the newDistribuidorName is unique among Distribuidores
            List<Distribuidor> distribuidores = Distribuidores.getInstance().getDistribuidores();
            for (Distribuidor distribuidor : distribuidores) {
                if (distribuidor.getName().equals(newDistribuidorName)) {
                    return "O nome do distribuidor especificado já existe";
                }
            }
            // If the newDistribuidorName is unique, create a new Distribuidor
            Distribuidor newDistribuidor = new Distribuidor(newDistribuidorName);
            Distribuidores.getInstance().addDistribuidor(newDistribuidor);
            return "Distribuidor adicionado com sucesso";
        }
        else{
            return "O nome do distribuidor não pode ser vazio";
        }
    }

}
