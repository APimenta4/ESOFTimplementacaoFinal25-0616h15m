

import java.util.Date;

public class Emprestimo {
    private Socio socio;
    private Exemplar exemplar;
    private Date dataDeEmprestimo;
    private Date dataDeDevolucao;
    private Date dataLimite;
    private boolean devolvido;
    private double valorMulta;
    private boolean multaPaga;
    public Emprestimo(Socio socio, Exemplar exemplar) {
        this.socio = socio;
        this.exemplar = exemplar;
        this.dataDeEmprestimo = new Date();
        this.devolvido = false;
        this.valorMulta = 0.0;
        this.dataLimite = new Date();
        this.dataLimite.setDate(this.dataDeEmprestimo.getDate() + ValoresPredefinicoes.getInstance().getDiasEmprestimo());
        this.multaPaga = false;
    }

    // Construtor secondário para experimentar com empréstimos com multa
    public Emprestimo(Socio socio, Exemplar exemplar, Date data){
        this.socio = socio;
        this.exemplar = exemplar;
        this.dataDeEmprestimo = data;
        this.devolvido = false;
        this.valorMulta = 0.0;
        this.dataLimite = new Date();
        this.dataLimite.setDate(this.dataDeEmprestimo.getDate() + ValoresPredefinicoes.getInstance().getDiasEmprestimo());
        this.multaPaga = false;
    }

    public void devolver() {
        this.devolvido = true;
        this.dataDeDevolucao = new Date();
        this.exemplar.setEmprestado(false);
        this.socio.decQuantEmprestimos();
        if (this.dataDeDevolucao.after(this.dataLimite)) {
            long diff = this.dataDeDevolucao.getTime() - this.dataLimite.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            this.valorMulta = diffDays * ValoresPredefinicoes.getInstance().getMultaDia();
        }
    }


    public double getValorMulta() {
        return valorMulta;
    }

    public boolean isMultaPaga() {
        return multaPaga;
    }

    public void setMultaPaga(boolean multaPaga) {
        this.multaPaga = multaPaga;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public Date getDataDeEmprestimo() {
        return dataDeEmprestimo;
    }

    public void setDataDeEmprestimo(Date dataDeEmprestimo) {
        this.dataDeEmprestimo = dataDeEmprestimo;
    }

    public Date getDataDeDevolucao() {
        return dataDeDevolucao;
    }

    public void setDataDeDevolucao(Date dataDeDevolucao) {
        this.dataDeDevolucao = dataDeDevolucao;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }
}