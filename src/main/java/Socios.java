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

    public static void clearInstance() {
        instance = null;
    }



    public void updateSocio(Socio socio, int numeroSocio, String name, int nifCC, String morada, int telefone, String email, String membershipType, String contactType){
        socio.setNumeroDeSocio(numeroSocio);
        socio.setName(name);
        socio.setNifCC(nifCC);
        socio.setMorada(morada);
        socio.setTelefone(telefone);
        socio.setEmail(email);
        socio.setContactType(contactType);
        socio.setMembershipType(membershipType);
    }

    public boolean existsSocio(int numeroSocio) {
        for (Socio socio : this.socios) {
            if (socio.getNumeroDeSocio() == numeroSocio) {
                return true;
            }
        }
        return false;
    }

    public Socio getSocioByNumero(int numeroSocio) {
        for (Socio socio : socios) {
            if (socio.getNumeroDeSocio() == numeroSocio) {
                return socio;
            }
        }
        return null;
    }

    public Socio getLastSocio() {
        if (socios.isEmpty()) {
            return null;
        }
        return socios.get(socios.size() - 1);
    }
}