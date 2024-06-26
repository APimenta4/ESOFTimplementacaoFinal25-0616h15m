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

    public boolean exemplarExists(String bookTitle, String author, String genre) {
        List<Exemplar> exemplares = Exemplares.getInstance().getExemplares();

        for (Exemplar exemplar : exemplares) {
            if (exemplar.getTitulo().equals(bookTitle) &&
                    exemplar.getAutores().equals(author) &&
                    exemplar.getGeneros().equals(genre)) {
                return true;
            }
        }

        return false;
    }

}