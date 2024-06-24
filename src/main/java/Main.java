import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Socio socio1 = new Socio("João", "sms", "Premium");
                Socio socio2 = new Socio("Tomas", "sms", "Premium");
                Socio socio3 = new Socio("Maria", "sms", "Premium");
                Socio socio4 = new Socio("Antonia", "sms", "Premium", 2023); // Sócio sem anuidade paga
                Socio socio5 = new Socio("Antonio", "sms", "Premium"); // Sócio com o número máximo de empréstimos (2)
                Socio socio6 = new Socio("Rodrigo", "sms", "Premium"); // Sócio sem empréstimos

                Socios.getInstance().addSocio(socio1);
                Socios.getInstance().addSocio(socio2);
                Socios.getInstance().addSocio(socio3);
                Socios.getInstance().addSocio(socio4);
                Socios.getInstance().addSocio(socio5);
                Socios.getInstance().addSocio(socio6);

                Editora editora1 = new Editora("e1");
                Editora editora2 = new Editora("e2");
                Editora editora3 = new Editora("e3");
                Editoras.getInstance().addEditora(editora1);
                Editoras.getInstance().addEditora(editora2);
                Editoras.getInstance().addEditora(editora3);

                Distribuidor distribuidor1 = new Distribuidor("d1");
                Distribuidor distribuidor2 = new Distribuidor("d2");
                Distribuidor distribuidor3 = new Distribuidor("d3");
                Distribuidores.getInstance().addDistribuidor(distribuidor1);
                Distribuidores.getInstance().addDistribuidor(distribuidor2);
                Distribuidores.getInstance().addDistribuidor(distribuidor3);

                // A implementação da organização dos códigos de cada livro fica ao critério do utilizador, contudo, têm de ser únicos
                Exemplar exemplar1 = new Exemplar("123", "1", "Afonso Pimenta1", "Drama - Ação", editora3, "2020", "424", "P1/E5");
                Exemplar exemplar2 = new Exemplar("123", "2", "Afonso Pimenta2", "Drama", editora2, "2020", "414", "P1/E5");
                Exemplar exemplar3 = new Exemplar("123", "3", "Afonso Pimenta3", "Drama", editora1, "2020", "454", "P1/E5");
                Exemplar exemplar4 = new Exemplar("123", "4", "Afonso Pimenta3", "Drama", editora1, "2020", "464", "P1/E5");
                Exemplar exemplar5 = new Exemplar("123", "LivroOcupado1", "Afonso Pimenta3", "Drama", editora1, "2020", "7", "P1/E5");
                Exemplar exemplar6 = new Exemplar("123", "LivroOcupado2", "Afonso Pimenta3", "Drama", editora1, "2020", "1", "P1/E5");
                Exemplares.getInstance().addExemplar(exemplar1);
                Exemplares.getInstance().addExemplar(exemplar2);
                Exemplares.getInstance().addExemplar(exemplar3);
                Exemplares.getInstance().addExemplar(exemplar4);
                Exemplares.getInstance().addExemplar(exemplar5);
                Exemplares.getInstance().addExemplar(exemplar6);

                Aquisicao aquisicao1 = new Aquisicao(exemplar1, distribuidor2);
                Aquisicao aquisicao2 = new Aquisicao(exemplar3, distribuidor3);
                Aquisicao aquisicao3 = new Aquisicao(exemplar2, distribuidor1);
                Aquisicoes.getInstance().addAquisicao(aquisicao1);
                Aquisicoes.getInstance().addAquisicao(aquisicao2);
                Aquisicoes.getInstance().addAquisicao(aquisicao3);

                Emprestimo emprestimo1 = new Emprestimo(socio1, exemplar3);
                Emprestimo emprestimo2 = new Emprestimo(socio2, exemplar2);
                Emprestimo emprestimo3 = new Emprestimo(socio3, exemplar1);
                Emprestimo emprestimo4 = new Emprestimo(socio5, exemplar5);
                Emprestimo emprestimo5 = new Emprestimo(socio5, exemplar6);
                Emprestimos.getInstance().addEmprestimo(emprestimo1);
                Emprestimos.getInstance().addEmprestimo(emprestimo2);
                Emprestimos.getInstance().addEmprestimo(emprestimo3);
                Emprestimos.getInstance().addEmprestimo(emprestimo4);
                Emprestimos.getInstance().addEmprestimo(emprestimo5);

                new JanelaPrincipal().setVisible(true);
            }
        });
    }
}