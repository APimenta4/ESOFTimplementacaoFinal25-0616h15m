import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConsultarAquisicaoExemplarTeste{

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
    public void testPesquisarAquisicoesExemplarValid() {
        Socio socio = new Socio("João", "sms", "Normal");
        Socios.getInstance().addSocio(socio);
        Editora editora = new Editora("editora");
        Editoras.getInstance().addEditora(editora);
        Distribuidor distribuidor = new Distribuidor("distribuidor");
        Distribuidores.getInstance().addDistribuidor(distribuidor);
        Exemplar exemplar = new Exemplar("123", "1", "Afonso Pimenta1", "Drama - Ação", editora, "2020", "424", "P1/E5");
        Exemplares.getInstance().addExemplar(exemplar);
        Aquisicao aquisicao = new Aquisicao(exemplar, distribuidor);
        Aquisicoes.getInstance().addAquisicao(aquisicao);

        ConsultarAquisicoesExemplar consultarAquisicaoExemplar = new ConsultarAquisicoesExemplar();

        String message = consultarAquisicaoExemplar.pesquisarAquisicoesExemplar("1");

        assertEquals("Aquisição encontrada", message);
    }

    @Test
    public void testPesquisarAquisicoesExemplarEmpty() {
        ConsultarAquisicoesExemplar consultarAquisicaoExemplar = new ConsultarAquisicoesExemplar();

        String message = consultarAquisicaoExemplar.pesquisarAquisicoesExemplar("");

        assertEquals("O código de exemplar não pode ser vazio", message);
    }

    @Test
    public void testPesquisarAquisicoesExemplarInvalid() {
        Socio socio = new Socio("João", "sms", "Normal");
        Socios.getInstance().addSocio(socio);
        Editora editora = new Editora("editora");
        Editoras.getInstance().addEditora(editora);
        Distribuidor distribuidor = new Distribuidor("distribuidor");
        Distribuidores.getInstance().addDistribuidor(distribuidor);
        Exemplar exemplar = new Exemplar("123", "1", "Afonso Pimenta1", "Drama - Ação", editora, "2020", "424", "P1/E5");
        Exemplares.getInstance().addExemplar(exemplar);
        Aquisicao aquisicao = new Aquisicao(exemplar, distribuidor);
        Aquisicoes.getInstance().addAquisicao(aquisicao);

        ConsultarAquisicoesExemplar consultarAquisicaoExemplar = new ConsultarAquisicoesExemplar();

        String message = consultarAquisicaoExemplar.pesquisarAquisicoesExemplar("2");

        assertEquals("O exemplar especificado não existe ou não possui aquisição", message);
    }

    @Test
    public void testPesquisarAquisicoesExemplarNoAquisicao() {
        Socio socio = new Socio("João", "sms", "Normal");
        Socios.getInstance().addSocio(socio);
        Editora editora = new Editora("editora");
        Editoras.getInstance().addEditora(editora);
        Distribuidor distribuidor = new Distribuidor("distribuidor");
        Distribuidores.getInstance().addDistribuidor(distribuidor);
        Exemplar exemplar = new Exemplar("123", "1", "Afonso Pimenta1", "Drama - Ação", editora, "2020", "424", "P1/E5");
        Exemplares.getInstance().addExemplar(exemplar);

        ConsultarAquisicoesExemplar consultarAquisicaoExemplar = new ConsultarAquisicoesExemplar();

        String message = consultarAquisicaoExemplar.pesquisarAquisicoesExemplar("1");

        assertEquals("O exemplar especificado não existe ou não possui aquisição", message);
    }


}