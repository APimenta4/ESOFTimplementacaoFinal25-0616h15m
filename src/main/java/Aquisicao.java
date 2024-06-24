import java.util.Date;

public class Aquisicao {
    private Exemplar exemplar;
    private Distribuidor distributor;
    private Date aquisitionDate;

    public Aquisicao(Exemplar exemplar, Distribuidor distributor) {
        this.exemplar = exemplar;
        this.distributor = distributor;
        this.aquisitionDate = new Date();
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public Distribuidor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distribuidor distributor) {
        this.distributor = distributor;
    }

    public Date getAquisitionDate() {
        return aquisitionDate;
    }

    public void setAquisitionDate(Date aquisitionDate) {
        this.aquisitionDate = aquisitionDate;
    }
}