import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ConsultarEmprestimosSocioTeste {

    @Before
    public void setUp() {
        // This method is called before each test
        Socios.clearInstance();
        Exemplares.clearInstance();
        Emprestimos.clearInstance();
    }

    @After
    public void tearDown() {
        // This method is called after each test
        Socios.clearInstance();
        Exemplares.clearInstance();
        Emprestimos.clearInstance();
    }

    @Test
    public void testSearchSocioWithoutEmprestimos() {
        Socio socio = new Socio("João", "sms", "Premium", "adea", 123456789, "Rua do João", 123456789);
        Socios.getInstance().addSocio(socio);

        ConsultarEmprestimosSocio consultarEmprestimosSocio = new ConsultarEmprestimosSocio();

        String numeroSocio = Integer.toString(socio.getNumeroDeSocio());

        String message = consultarEmprestimosSocio.consultarEmprestimo(numeroSocio);

        assertEquals("O sócio especificado não possui empréstimos", message);
    }

    @Test
    public void testSearchSocioWithEmprestimos() {
        Socio socio = new Socio("João", "sms", "Premium", "adea", 123456789, "Rua do João", 123456789);
        Socios.getInstance().addSocio(socio);
        Exemplar exemplar = new Exemplar("123", "1", "Valid Book", "Genre", new Editora("Valid Editora"), "2020", "100", "P1/E1");
        Exemplares.getInstance().addExemplar(exemplar);
        Emprestimo emprestimo = new Emprestimo(socio, exemplar);
        Emprestimos.getInstance().addEmprestimo(emprestimo);

        ConsultarEmprestimosSocio consultarEmprestimosSocio = new ConsultarEmprestimosSocio();

        String numeroSocio = Integer.toString(socio.getNumeroDeSocio());

        String message = consultarEmprestimosSocio.consultarEmprestimo(numeroSocio);

        assertEquals("Empréstimos encontrados", message);
    }

    @Test
    public void testSearchSocioInvalidNumber() {

        ConsultarEmprestimosSocio consultarEmprestimosSocio = new ConsultarEmprestimosSocio();

        String numeroSocio = "teste";

        String message = consultarEmprestimosSocio.consultarEmprestimo(numeroSocio);

        assertEquals("O número de sócio especificado é inválido", message);
    }

    @Test
    public void testSearchSocioDoesNotExist() {
        ConsultarEmprestimosSocio consultarEmprestimosSocio = new ConsultarEmprestimosSocio();

        String numeroSocio = "1000";

        String message = consultarEmprestimosSocio.consultarEmprestimo(numeroSocio);

        assertEquals("O sócio especificado não existe", message);
    }


    @Test
    public void testSearchSocioWithMultipleEmprestimos() {
        Socio socio = new Socio("João", "sms", "Premium", "adea", 123456789, "Rua do João", 123456789);
        Socios.getInstance().addSocio(socio);
        Exemplar exemplar = new Exemplar("123", "1", "Valid Book", "Genre", new Editora("Valid Editora"), "2020", "100", "P1/E1");
        Exemplares.getInstance().addExemplar(exemplar);
        Exemplar exemplar2 = new Exemplar("123", "2", "Valid Book", "Genre", new Editora("Valid Editora"), "2020", "100", "P1/E1");
        Exemplares.getInstance().addExemplar(exemplar2);
        Emprestimo emprestimo = new Emprestimo(socio, exemplar);
        Emprestimos.getInstance().addEmprestimo(emprestimo);
        Emprestimo emprestimo2 = new Emprestimo(socio, exemplar2);
        Emprestimos.getInstance().addEmprestimo(emprestimo2);

        ConsultarEmprestimosSocio consultarEmprestimosSocio = new ConsultarEmprestimosSocio();

        String numeroSocio = Integer.toString(socio.getNumeroDeSocio());

        String message = consultarEmprestimosSocio.consultarEmprestimo(numeroSocio);

        assertEquals("Empréstimos encontrados", message);
    }


}