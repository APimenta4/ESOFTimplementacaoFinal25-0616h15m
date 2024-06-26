import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CancelarAnuidadeTeste {

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
    public void testCancelarAnuidadeValid() {

        Socio socio1 = new Socio("João", "sms", "Premium", "adea", 123456789,"Rua do João", 123456789);
        Socios.getInstance().addSocio(socio1);
        CancelarAnuidade cancelarAnuidade = new CancelarAnuidade();

        String message = cancelarAnuidade.cancelarAnuidade(socio1.getNumeroDeSocio());
        assertEquals("Anuidade cancelada com sucesso.", message);
    }

    @Test
    public void testCancelarAnuidadeInvalid() {

        Socio socio1 = new Socio("João", "sms", "Premium",2023, "adea", 123456789,"Rua do João", 123456789);
        Socios.getInstance().addSocio(socio1);
        CancelarAnuidade cancelarAnuidade = new CancelarAnuidade();


        String message = cancelarAnuidade.cancelarAnuidade(socio1.getNumeroDeSocio());
        assertEquals("O sócio não tem anuidade paga.", message);
    }


    @Test
    public void testCancelarAnuidadeNumeroInvalid() {

        Socio socio1 = new Socio("João", "sms", "Premium",2023, "adea", 123456789,"Rua do João", 123456789);
        Socios.getInstance().addSocio(socio1);
        CancelarAnuidade cancelarAnuidade = new CancelarAnuidade();

        String message = cancelarAnuidade.cancelarAnuidade(1000);
        assertEquals("Não existe sócio com este número.", message);
    }

}
