import java.util.ArrayList;
import java.util.List;

public class Exemplares {
    private static Exemplares instance;
    private final List<Exemplar> exemplares;

    private Exemplares() {
        exemplares = new ArrayList<>();
    }

    public static synchronized Exemplares getInstance() {
        if (instance == null) {
            instance = new Exemplares();
        }
        return instance;
    }

    public void addExemplar(Exemplar exemplar) {
        exemplares.add(exemplar);
    }

    public List<Exemplar> getExemplares() {
        return exemplares;
    }

    public static void clearInstance() {
        instance = null;
    }
}