import java.util.ArrayList;
import java.util.List;

public class Reservas {

private static Reservas instance;
    private final List<Reserva> reservas;

    private Reservas() {
        reservas = new ArrayList<>();
    }

    public static synchronized Reservas getInstance() {
        if (instance == null) {
            instance = new Reservas();
        }
        return instance;
    }

    public void addReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void removeReserva(int numeroDeReserva) throws Exception {
        Reserva reservaToRemove = null;
        for (Reserva reserva : reservas) {
            if (reserva.getNumeroDeReserva() == numeroDeReserva) {
                reservaToRemove = reserva;
                break;
            }
        }
        if (reservaToRemove != null) {
            reservas.remove(reservaToRemove);
        } else {
            throw new Exception("Reserva não encontrada");
        }
    }

    public static void clearInstance() {
        instance = null;
    }
}
