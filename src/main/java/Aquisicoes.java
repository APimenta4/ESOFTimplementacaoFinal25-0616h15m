import java.util.ArrayList;
import java.util.List;

public class Aquisicoes {
    private static Aquisicoes instance;
    private final List<Aquisicao> aquisicoes;

    private Aquisicoes() {
        aquisicoes = new ArrayList<>();
    }

    public static synchronized Aquisicoes getInstance() {
        if (instance == null) {
            instance = new Aquisicoes();
        }
        return instance;
    }

    public void addAquisicao(Aquisicao aquisicao) {
        aquisicoes.add(aquisicao);
        Exemplares.getInstance().addExemplar(aquisicao.getExemplar());
    }

    public List<Aquisicao> getAquisicoes() {
        return aquisicoes;
    }
}