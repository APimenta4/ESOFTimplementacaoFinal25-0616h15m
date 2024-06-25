import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class NovaAquisicaoTeste{

    @Before
    public void setUp() {
        // This method is called before each test
        Socios.clearInstance();
        Exemplares.clearInstance();
        Emprestimos.clearInstance();
        Editoras.clearInstance();
        Distribuidores.clearInstance();
    }

    @After
    public void tearDown() {
        // This method is called after each test
        Socios.clearInstance();
        Exemplares.clearInstance();
        Emprestimos.clearInstance();
        Editoras.clearInstance();
        Distribuidores.clearInstance();
    }

    @Test
    public void testRegistarAquisicaoValid() {
        Editora editora = new Editora("editora");
        Editoras.getInstance().addEditora(editora);
        Distribuidor distribuidor = new Distribuidor("distribuidor");
        Distribuidores.getInstance().addDistribuidor(distribuidor);

        NovaAquisicao novaAquisicao = new NovaAquisicao();

        String message = novaAquisicao.registarAquisicao("Titulo", "Autor1", "2024-2025", "1", "Genero1- Subgenero1", "Prateleira 7 Estante 8", "888", editora, distribuidor);

        assertEquals("Aquisição registada com sucesso", message);
    }

    @Test
    public void testRegistarAquisicaoMissingNullParameters() {
        Editora editora = new Editora("editora");
        Editoras.getInstance().addEditora(editora);
        Distribuidor distribuidor = new Distribuidor("distribuidor");
        Distribuidores.getInstance().addDistribuidor(distribuidor);

        NovaAquisicao novaAquisicao = new NovaAquisicao();

        String message = novaAquisicao.registarAquisicao("Titulo", "", "2024-2025", "1", "Genero1- Subgenero1", "Prateleira 7 Estante 8", "888", editora, null);

        assertEquals("Todos os campos são obrigatórios", message);
    }


    @Test
    public void testRegistarAquisicaoDuplicateCodigo() {
        Editora editora = new Editora("editora");
        Editoras.getInstance().addEditora(editora);
        Distribuidor distribuidor = new Distribuidor("distribuidor");
        Distribuidores.getInstance().addDistribuidor(distribuidor);

        NovaAquisicao novaAquisicao = new NovaAquisicao();

        String message = novaAquisicao.registarAquisicao("Titulo", "Autor1", "2024-2025", "1", "Genero1- Subgenero1", "Prateleira 7 Estante 8", "888", editora, distribuidor);
        assertEquals("Aquisição registada com sucesso", message);

        message = novaAquisicao.registarAquisicao("Titulo", "Autor1", "2024-2025", "1", "Genero1- Subgenero1", "Prateleira 7 Estante 8", "888", editora, distribuidor);
        assertEquals("O código especificado já existe", message);
    }

    @Test
    public void testRegistarAquisicaoDuplicateISBN() {
        Editora editora = new Editora("editora");
        Editoras.getInstance().addEditora(editora);
        Distribuidor distribuidor = new Distribuidor("distribuidor");
        Distribuidores.getInstance().addDistribuidor(distribuidor);

        NovaAquisicao novaAquisicao = new NovaAquisicao();

        String message = novaAquisicao.registarAquisicao("Titulo", "Autor1", "2024-2025", "1", "Genero1- Subgenero1", "Prateleira 7 Estante 8", "888", editora, distribuidor);
        assertEquals("Aquisição registada com sucesso", message);

        message = novaAquisicao.registarAquisicao("Titulo", "Autor1", "2024-2025", "2", "Genero1- Subgenero1", "Prateleira 7 Estante 8", "888", editora, distribuidor);
        assertEquals("O ISBN especificado já existe", message);
    }


    @Test
    public void testRegistarEditoraValid() {
        NovaAquisicao novaAquisicao = new NovaAquisicao();

        String message = novaAquisicao.newEditora("editora");
        assertEquals("Editora adicionada com sucesso", message);

        message = novaAquisicao.newEditora("editoraDiferente");
        assertEquals("Editora adicionada com sucesso", message);
    }

    @Test
    public void testRegistarEditoraDuplicate() {
        NovaAquisicao novaAquisicao = new NovaAquisicao();

        String message = novaAquisicao.newEditora("editora");
        assertEquals("Editora adicionada com sucesso", message);

        message = novaAquisicao.newEditora("editora");
        assertEquals("O nome da editora especificado já existe", message);
    }

    @Test
    public void testRegistarEditoraNull() {
        NovaAquisicao novaAquisicao = new NovaAquisicao();

        String message = novaAquisicao.newEditora(null);
        assertEquals("O nome da editora não pode ser vazio", message);

        message = novaAquisicao.newEditora("");
        assertEquals("O nome da editora não pode ser vazio", message);
    }

    @Test
    public void testRegistarDistribuidorValid() {
        NovaAquisicao novaAquisicao = new NovaAquisicao();

        String message = novaAquisicao.newDistribuidor("distribuidor");
        assertEquals("Distribuidor adicionado com sucesso", message);

        message = novaAquisicao.newDistribuidor("distribuidorDiferente");
        assertEquals("Distribuidor adicionado com sucesso", message);
    }

    @Test
    public void testRegistarDistribuidorDuplicate() {
        NovaAquisicao novaAquisicao = new NovaAquisicao();

        String message = novaAquisicao.newDistribuidor("distribuidor");
        assertEquals("Distribuidor adicionado com sucesso", message);

        message = novaAquisicao.newDistribuidor("distribuidor");
        assertEquals("O nome do distribuidor especificado já existe", message);
    }

    @Test
    public void testRegistarDistribuidorNull() {
        NovaAquisicao novaAquisicao = new NovaAquisicao();

        String message = novaAquisicao.newDistribuidor(null);
        assertEquals("O nome do distribuidor não pode ser vazio", message);

        message = novaAquisicao.newDistribuidor("");
        assertEquals("O nome do distribuidor não pode ser vazio", message);
    }

}