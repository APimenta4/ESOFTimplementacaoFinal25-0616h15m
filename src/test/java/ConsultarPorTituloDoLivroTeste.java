import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class ConsultarPorTituloDoLivroTeste {

    @Before
    public void setUp() {
        // This method is called before each test
        Socios.clearInstance();
        Exemplares.clearInstance();
        Emprestimos.clearInstance();
        Reservas.clearInstance();

    }

    @After
    public void tearDown() {
        // This method is called after each test
        Socios.clearInstance();
        Exemplares.clearInstance();
        Emprestimos.clearInstance();
        Editoras.clearInstance();
        Reservas.clearInstance();
    }

    @Test
    public void testConsultarPorTituloDoLivro() {

        ConsultarPorTituloDoLivro consultarPorTituloDoLivro = new ConsultarPorTituloDoLivro();
        String message = consultarPorTituloDoLivro.consultarPorTituloDoLivro("Aventuras tintin", "");

        assertEquals("Por favor, preencha todos os campos.", message);
    }


}
