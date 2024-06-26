import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PagarMultasTeste {
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
    public void testPagarMultasValid() {
        Socio socio1 = new Socio("João", "sms", "Premium", "adea", 123456789,"Rua do João", 123456789);
        Socios.getInstance().addSocio(socio1);

        ValorMultas pagarMultas = new ValorMultas(Integer.toString(socio1.getNumeroDeSocio()));

        Editora editora3 = new Editora("e3");
        Date date = new Date();
        date.setTime(date.getTime() - 30 * 24 * 60 * 60 * 1000);
        Exemplar exemplar1 = new Exemplar("123", "1", "Afonso Pimenta1", "Drama", editora3, "2020", "444", "P1/E5");

        Emprestimo emprestimo1 = new Emprestimo(socio1, exemplar1, date);
        Emprestimos.getInstance().addEmprestimo(emprestimo1);
        emprestimo1.devolver();

        String message = pagarMultas.pagarMultas(socio1.getNumeroDeSocio(), "123456789");

        assertEquals("Multas pagas com sucesso.", message);
    }


}
