import java.util.ArrayList;
import java.util.List;

public class Distribuidores {
    private static Distribuidores instance;
    private List<Distribuidor> distribuidores;

    private Distribuidores() {
        distribuidores = new ArrayList<>();
    }

    public static Distribuidores getInstance() {
        if (instance == null) {
            instance = new Distribuidores();
        }
        return instance;
    }

    public List<Distribuidor> getDistribuidores() {
        return distribuidores;
    }

    public void addDistribuidor(Distribuidor distribuidor) {
        distribuidores.add(distribuidor);
    }

    public static void clearInstance() {
        instance = null;
    }
}