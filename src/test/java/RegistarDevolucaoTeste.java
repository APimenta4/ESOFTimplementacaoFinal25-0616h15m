import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistarDevolucaoTeste {

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
    public void testVerTodosSuccessful() {
        Socio socio = new Socio("Valid Socio", "socio@example.com", "Premium");
        Socios.getInstance().addSocio(socio);
        Exemplar exemplar = new Exemplar("123", "1", "Valid Book", "Genre", new Editora("Valid Editora"), "2020", "100", "P1/E1");
        Exemplares.getInstance().addExemplar(exemplar);
        Emprestimo emprestimo = new Emprestimo(socio, exemplar);
        Emprestimos.getInstance().addEmprestimo(emprestimo);

        RegistarDevolucao registarDevolucao = new RegistarDevolucao();
        String result = registarDevolucao.verTodos(Integer.toString(socio.getNumeroDeSocio()));

        assertEquals("A apresentar empréstimos do sócio", result);
    }


    @Test
    public void testVerTodosSocioWithoutEmprestimos() {
        Socio socio = new Socio("Valid Socio", "socio@example.com", "Premium");
        Socios.getInstance().addSocio(socio);

        RegistarDevolucao registarDevolucao = new RegistarDevolucao();
        String result = registarDevolucao.verTodos(Integer.toString(socio.getNumeroDeSocio()));

        assertEquals("O sócio especificado não possui empréstimos", result);
    }

    @Test
    public void testVerTodosSocioDoesntExist() {
        RegistarDevolucao registarDevolucao = new RegistarDevolucao();
        String result = registarDevolucao.verTodos("1000");

        assertEquals("O sócio especificado não existe", result);
    }

    @Test
    public void testVerTodosInvalidSocio() {
        RegistarDevolucao registarDevolucao = new RegistarDevolucao();
        String result = registarDevolucao.verTodos("test");

        assertEquals("O número de sócio especificado é inválido", result);
    }

    @Test
    public void testRegistarDevolucaoSuccessful() {
        Socio socio = new Socio("Valid Socio", "socio@example.com", "Premium");
        Socios.getInstance().addSocio(socio);
        Exemplar exemplar = new Exemplar("123", "1", "Valid Book", "Genre", new Editora("Valid Editora"), "2020", "100", "P1/E1");
        Exemplares.getInstance().addExemplar(exemplar);
        Emprestimo emprestimo = new Emprestimo(socio, exemplar);
        Emprestimos.getInstance().addEmprestimo(emprestimo);

        RegistarDevolucao registarDevolucao = new RegistarDevolucao();
        String result = registarDevolucao.registarDevolucao(Integer.toString(socio.getNumeroDeSocio()), exemplar.getCodigo());

        assertEquals("Empréstimo devolvido com sucesso", result);
    }

    @Test
    public void testRegistarDevolucaoInvalidSocio() {
        RegistarDevolucao registarDevolucao = new RegistarDevolucao();
        String result = registarDevolucao.registarDevolucao("test", "1");

        assertEquals("O número de sócio especificado é inválido", result);
    }

    @Test
    public void testRegistarDevolucaoSocioDoesntExist() {
        RegistarDevolucao registarDevolucao = new RegistarDevolucao();
        String result = registarDevolucao.registarDevolucao("1000", "1");

        assertEquals("O sócio especificado não existe", result);
    }

    @Test
    public void testRegistarDevolucaoSocioWithoutOpenEmprestimos() {
        Socio socio = new Socio("Valid Socio", "socio@example.com", "Premium");
        Socios.getInstance().addSocio(socio);
        Exemplar exemplar = new Exemplar("123", "1", "Valid Book", "Genre", new Editora("Valid Editora"), "2020", "100", "P1/E1");
        Exemplares.getInstance().addExemplar(exemplar);
        Emprestimo emprestimo = new Emprestimo(socio, exemplar);
        Emprestimos.getInstance().addEmprestimo(emprestimo);

        RegistarDevolucao registarDevolucao = new RegistarDevolucao();
        registarDevolucao.registarDevolucao(Integer.toString(socio.getNumeroDeSocio()), "1");

        String result = registarDevolucao.registarDevolucao(Integer.toString(socio.getNumeroDeSocio()), "2");

        assertEquals("O sócio especificado não possui empréstimos em aberto", result);

    }

    @Test
    public void testRegistarDevolucaoSocioWithoutEmprestimos() {
        Socio socio = new Socio("Valid Socio", "socio@example.com", "Premium");
        Socios.getInstance().addSocio(socio);

        RegistarDevolucao registarDevolucao = new RegistarDevolucao();

        String result = registarDevolucao.registarDevolucao(Integer.toString(socio.getNumeroDeSocio()), "2");

        assertEquals("O sócio especificado não possui empréstimos em aberto", result);

    }

    @Test
    public void testRegistarDevolucaoExemplarNotEmprestado() {
        Socio socio = new Socio("Valid Socio", "socio@example.com", "Premium");
        Socios.getInstance().addSocio(socio);
        Exemplar exemplar = new Exemplar("123", "1", "Valid Book", "Genre", new Editora("Valid Editora"), "2020", "100", "P1/E1");
        Exemplares.getInstance().addExemplar(exemplar);
        Emprestimo emprestimo = new Emprestimo(socio, exemplar);
        Emprestimos.getInstance().addEmprestimo(emprestimo);

        RegistarDevolucao registarDevolucao = new RegistarDevolucao();

        String result = registarDevolucao.registarDevolucao(Integer.toString(socio.getNumeroDeSocio()), "2");

        assertEquals("O exemplar não se encontra emprestado ao sócio especificado", result);
    }


}