import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class NovoEmprestimoTeste {

    @Test
    public void testAdicionarEmprestimoWithValidSocioAndExemplar() {
        // Create valid Socio and Exemplar objects
        Socio socio = new Socio("Valid Socio", "socio@example.com", "Premium");
        Socios.getInstance().addSocio(socio);
        Exemplar exemplar = new Exemplar("123", "1", "Valid Book", "Genre", new Editora("Valid Editora"), "2020", "100", "P1/E1");
        Exemplares.getInstance().addExemplar(exemplar);

        // Create a new NovoEmprestimo object
        NovoEmprestimo novoEmprestimo = new NovoEmprestimo();

        String numeroDeSocio = "1";
        String codigoExemplar = "1";

        // Call adicinarEmprestimo method and capture the message
        String message = novoEmprestimo.adicionarEmprestimo(numeroDeSocio, codigoExemplar);

        // Assert the returned message
        assertEquals("Empréstimo registrado com sucesso", message);
    }

    @Test
    public void testAdicionarEmprestimoWithValidSocioAndNonExistentExemplar() {
        // Create valid Socio object
        Socio socio = new Socio("Valid Socio", "socio@example.com", "Premium");
        Socios.getInstance().addSocio(socio);

        // Exemplar with non-existent code
        String codigoExemplar = "999";

        // Create a new NovoEmprestimo object
        NovoEmprestimo novoEmprestimo = new NovoEmprestimo();

        String numeroDeSocio = "1";

        // Call adicinarEmprestimo method and capture the message
        String message = novoEmprestimo.adicionarEmprestimo(numeroDeSocio, codigoExemplar);

        // Assert the returned message
        assertEquals("O exemplar especificado não existe", message);
    }

    @Test
    public void testAdicionarEmprestimoWithNonExistentSocio() {
        // Socio with non-existent number
        String numeroDeSocio = "999";

        // Create a valid Exemplar object
        Exemplar exemplar = new Exemplar("123", "1", "Valid Book", "Genre", new Editora("Valid Editora"), "2020", "100", "P1/E1");
        Exemplares.getInstance().addExemplar(exemplar);

        // Create a new NovoEmprestimo object
        NovoEmprestimo novoEmprestimo = new NovoEmprestimo();

        String codigoExemplar = "1";

        // Call adicinarEmprestimo method and capture the message
        String message = novoEmprestimo.adicionarEmprestimo(numeroDeSocio, codigoExemplar);

        // Assert the returned message
        assertEquals("O sócio especificado não existe  ", message);
    }

    @Test
    public void testAdicionarEmprestimoWithNormalSocioAtMaxLoans() {
        // Create a Socio with maximum allowed loans
        Socio socio = new Socio("Max Loan Socio", "maxloan@example.com", "Normal");
        Socios.getInstance().addSocio(socio);

        // Add exemplars to reach the maximum loan limit
        Exemplar exemplar = new Exemplar("123", "1", "Valid Book", "Genre", new Editora("Valid Editora"), "2020", "100", "P1/E1");
        Exemplares.getInstance().addExemplar(exemplar);
        Exemplar exemplar2 = new Exemplar("123", "2", "Valid Book", "Genre", new Editora("Valid Editora"), "2020", "100", "P1/E1");
        Exemplares.getInstance().addExemplar(exemplar2);
        Exemplar exemplar3 = new Exemplar("123", "3", "Valid Book", "Genre", new Editora("Valid Editora"), "2020", "100", "P1/E1");
        Exemplares.getInstance().addExemplar(exemplar3);

        // Create and add emprestimo
        Emprestimo emprestimo = new Emprestimo(socio, exemplar);
        Emprestimos.getInstance().addEmprestimo(emprestimo);
        Emprestimo emprestimo2 = new Emprestimo(socio, exemplar2);
        Emprestimos.getInstance().addEmprestimo(emprestimo2);

        // Create a new NovoEmprestimo object
        NovoEmprestimo novoEmprestimo = new NovoEmprestimo();

        // Attempt to add one more loan
        String numeroDeSocio = "1";
        String codigoExemplar = "3";

        // Call adicinarEmprestimo method and capture the message
        String message = novoEmprestimo.adicionarEmprestimo(numeroDeSocio, codigoExemplar);

        // Assert the returned message
        assertEquals("O sócio especificado já possui o máximo de empréstimos simultâneos atualmente", message);
    }


    @Test
    public void testAdicionarEmprestimoWithPremiumSocioAtMaxLoans() {
        // Create a Socio with maximum allowed loans
        Socio socio = new Socio("Max Loan Socio", "maxloan@example.com", "Normal");
        Socios.getInstance().addSocio(socio);

        // Add exemplars to reach the maximum loan limit
        Exemplar exemplar = new Exemplar("123", "1", "Valid Book", "Genre", new Editora("Valid Editora"), "2020", "100", "P1/E1");
        Exemplares.getInstance().addExemplar(exemplar);
        Exemplar exemplar2 = new Exemplar("123", "2", "Valid Book", "Genre", new Editora("Valid Editora"), "2020", "100", "P1/E1");
        Exemplares.getInstance().addExemplar(exemplar2);
        Exemplar exemplar3 = new Exemplar("123", "3", "Valid Book", "Genre", new Editora("Valid Editora"), "2020", "100", "P1/E1");
        Exemplares.getInstance().addExemplar(exemplar3);
        Exemplar exemplar4 = new Exemplar("123", "4", "Valid Book", "Genre", new Editora("Valid Editora"), "2020", "100", "P1/E1");
        Exemplares.getInstance().addExemplar(exemplar4);
        Exemplar exemplar5 = new Exemplar("123", "5", "Valid Book", "Genre", new Editora("Valid Editora"), "2020", "100", "P1/E1");
        Exemplares.getInstance().addExemplar(exemplar5);

        // Create and add emprestimo
        Emprestimo emprestimo = new Emprestimo(socio, exemplar);
        Emprestimos.getInstance().addEmprestimo(emprestimo);
        Emprestimo emprestimo2 = new Emprestimo(socio, exemplar2);
        Emprestimos.getInstance().addEmprestimo(emprestimo2);

        // Create a new NovoEmprestimo object
        NovoEmprestimo novoEmprestimo = new NovoEmprestimo();

        // Attempt to add one more loan
        String numeroDeSocio = "1";
        String codigoExemplar = "5";

        // Call adicinarEmprestimo method and capture the message
        String message = novoEmprestimo.adicionarEmprestimo(numeroDeSocio, codigoExemplar);

        // Assert the returned message
        assertEquals("O sócio especificado já possui o máximo de empréstimos simultâneos atualmente", message);
    }


    @Test
    public void testAdicionarEmprestimoWithInvalidExemplarCodeFormat() {
        // Create a valid Socio object
        Socio socio = new Socio("Valid Socio", "socio@example.com", "Premium");
        Socios.getInstance().addSocio(socio);

        // Create a new NovoEmprestimo object
        NovoEmprestimo novoEmprestimo = new NovoEmprestimo();

        // Invalid exemplar code (non-numeric)
        String numeroDeSocio = "1";
        String codigoExemplar = "invalid";

        // Call adicinarEmprestimo method and capture the message
        String message = novoEmprestimo.adicionarEmprestimo(numeroDeSocio, codigoExemplar);

        // Assert the returned message
        assertEquals("O exemplar especificado não existe", message);
    }

    @Test
    public void testAdicionarEmprestimoWithLoanedExemplar() {
        // Create Socio 1
        Socio socio1 = new Socio("Socio 1", "socio1@example.com", "Regular");
        Socios.getInstance().addSocio(socio1);

        // Create Socio 2
        Socio socio2 = new Socio("Socio 2", "socio2@example.com", "Regular");
        Socios.getInstance().addSocio(socio2);

        // Create Exemplar
        Exemplar exemplar = new Exemplar("123", "1", "Book 1", "Genre", new Editora("Editora 1"), "2020", "100", "P1/E1");
        Exemplares.getInstance().addExemplar(exemplar);

        // Create new emprestimo
        Emprestimo emprestimo = new Emprestimo(socio1, exemplar);
        // Loan the exemplar to Socio 1
        Emprestimos.getInstance().addEmprestimo(emprestimo);

        // Create a new NovoEmprestimo object
        NovoEmprestimo novoEmprestimo = new NovoEmprestimo();

        // Attempt to loan the same exemplar to Socio 2
        String numeroDeSocio = "2";
        String codigoExemplar = "1";

        // Call adicinarEmprestimo method and capture the message
        String message = novoEmprestimo.adicionarEmprestimo(numeroDeSocio, codigoExemplar);

        // Assert the returned message
        assertEquals("O exemplar especificado já está emprestado", message);
    }

    // anuidade paga




}