

public class Reserva {
    private static int nextNumeroDeReserva = 1;
    private final int numeroDeReserva;
    private final String numeroDeSocio;

    private final String tituloLivro;
    private final String data;
    private final boolean entregue;
    private final boolean anulada;
    private final String autor;

    public Reserva(String numeroDeSocio, String tituloLivro,String autor , String data) {
        this.numeroDeReserva = nextNumeroDeReserva++;
        this.numeroDeSocio = numeroDeSocio;
        this.tituloLivro = tituloLivro;
        this.data = data;
        this.entregue = false;
        this.anulada = false;
        this.autor = autor;
    }


    public String getAutor() {
        return autor;
    }

    public String getData() {
        return data;
    }

    public int getNumeroDeReserva() {
        return nextNumeroDeReserva;
    }

    public String getRequisitadoPor() {
        return numeroDeSocio;
    }

    public String getTituloDoLivro() {
        return tituloLivro;
    }

    public boolean isEntregue() {
        return entregue;
    }

    public boolean isAnulada() {
        return anulada;
    }


}
