import java.util.ArrayList;
import java.util.List;

public class Editoras {
    private static Editoras instance;
    private List<Editora> editoras;

    private Editoras() {
        editoras = new ArrayList<>();
    }

    public static Editoras getInstance() {
        if (instance == null) {
            instance = new Editoras();
        }
        return instance;
    }

    public List<Editora> getEditoras() {
        return editoras;
    }

    public void addEditora(Editora editora) {
        editoras.add(editora);
    }
}