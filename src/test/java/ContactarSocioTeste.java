import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ContactarSocioTeste {
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
    public void testContactarSocioValid() {
        ContactarSocio contactarSocio = new ContactarSocio();

        Socio socio1 = new Socio("João", "sms", "Premium",2024, "adea", 123456789,"Rua do João", 123456789);
        Socios.getInstance().addSocio(socio1);
        String message = contactarSocio.contactarSocio(1, "Mensagem de teste");

        assertEquals("Sócio contactado com sucesso", message);
    }

    @Test
    public void testContactarSocioInvalid() {
        ContactarSocio contactarSocio = new ContactarSocio();

        Socio socio1 = new Socio("João", "sms", "Premium",2024, "adea", 123456789,"Rua do João", 123456789);
        Socios.getInstance().addSocio(socio1);
        String message = contactarSocio.contactarSocio(1000, "Mensagem de teste");

        assertEquals("Não existe um sócio com este número.", message);
    }
}
