import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NovoSocioTeste {
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
    public void testRegistarSocioValid() {
        NovoSocio novoSocio = new NovoSocio();

        String message = novoSocio.registarSocio("João", "mail", "Normal", "teste@sapo.pt", "123123123", "Rua do Lago", "123456789");
        assertEquals("Sócio registado com sucesso", message);
    }


    @Test
    public void testRegistarSocioInvalidNomeVazio() {
        NovoSocio novoSocio = new NovoSocio();

        String message = novoSocio.registarSocio("", "123456789", "email", "Normal", "asde", "123456789", "Rua Benfica campeão");
        assertEquals("Por favor, preencha todos os campos obrigatórios.", message);
    }

    @Test
    // Teste para verificar se o método regista um sócio com um NIF inválido
    public void testRegistarSocioInvalidNIF() {
        NovoSocio novoSocio = new NovoSocio();

        String message = novoSocio.registarSocio("João", "12345678", "email", "Normal", "asde", "123456789", "Rua Benfica campeão");
        assertEquals("NIF ou CC deve ter 9 dígitos numéricos.", message);
    }

    @Test
    public void testRegistarSocioInvalidTelefone() {
        NovoSocio novoSocio = new NovoSocio();

        String message = novoSocio.registarSocio("João", "mail", "Normal", "teste@sapo.pt", "1231231235", "Rua do Lago", "123456789");
        assertEquals("Telefone deve ter 9 dígitos numéricos.", message);
    }
}
