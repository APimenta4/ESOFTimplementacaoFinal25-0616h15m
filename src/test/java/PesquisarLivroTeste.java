import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class PesquisarLivroTeste {

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
    public void testPesquisarLivro() {

        PesquisarLivro pesquisarLivro = new PesquisarLivro();
        String message = pesquisarLivro.pesquisarLivro("Aventuras tintin", "Hergé", "Aventura");

        assertEquals("O exemplar não existe.", message);
    }

    @Test
    public void testPesquisarLivroExemplarExiste() {

        Editora editora1 = new Editora("Editora1");
        Exemplar exemplar4 = new Exemplar("Tintin", "4", "Autora", "Aventura", editora1, "2020", "464", "P1/E5");
        Exemplares.getInstance().addExemplar(exemplar4);

        PesquisarLivro pesquisarLivro = new PesquisarLivro();
        String message = pesquisarLivro.pesquisarLivro("Tintin", "Autora", "Aventura");

        assertEquals("O exemplar existe.", message);
    }


}
