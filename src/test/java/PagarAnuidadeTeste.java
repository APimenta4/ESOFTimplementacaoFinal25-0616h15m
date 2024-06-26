import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PagarAnuidadeTeste {

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
    // Teste para verificar se o método paga a anuidade de um sócio
    public void testPagarAnuidadeValid() {
        PagarAnuidade pagarAnuidade = new PagarAnuidade();
        Socio socio1 = new Socio("João", "sms", "Premium",2024, "adea", 123456789,"Rua do João", 123456789);
        Socios.getInstance().addSocio(socio1);

        String message = pagarAnuidade.pagarAnuidade(socio1.getNumeroDeSocio(), 150, 123456789);
        assertEquals("O sócio já tem anuidade paga.", message);
    }

    @Test
    // Teste para verificar se o método paga a anuidade de um sócio
    public void testPagarAnuidadeInvalid() {
        PagarAnuidade pagarAnuidade = new PagarAnuidade();
        Socio socio1 = new Socio("João", "sms", "Premium",2023, "adea", 123456789,"Rua do João", 123456789);
        Socios.getInstance().addSocio(socio1);
        Socio socio2 = new Socio("João", "sms", "Premium",2024, "adea", 123456789,"Rua do João", 123456789);
        Socios.getInstance().addSocio(socio2);

        String message = pagarAnuidade.pagarAnuidade(socio2.getNumeroDeSocio(), 150, 123456789);
        assertEquals("O sócio já tem anuidade paga.", message);
    }

}
