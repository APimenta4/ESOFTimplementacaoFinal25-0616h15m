import java.util.ArrayList;
import java.util.List;

public class Socios {
    private static Socios instance;
    private final List<Socio> socios;

    private Socios() {
        socios = new ArrayList<>();
    }

    public static synchronized Socios getInstance() {
        if (instance == null) {
            instance = new Socios();
        }
        return instance;
    }

    public void addSocio(Socio socio) {
        socios.add(socio);
    }

    public List<Socio> getSocios() {
        return socios;
    }
}