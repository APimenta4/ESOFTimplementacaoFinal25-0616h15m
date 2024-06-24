public class ValoresPredefinicoes {
    private static ValoresPredefinicoes instance;
    private int diasEmprestimo;
    private int maxEmprestimos;
    private int multaDia;
    private int anuidadeNormal;
    private int anuidadePremium;
    private int extraPremium;  // número de empréstimos extra que um sócio premium pode fazer

    private ValoresPredefinicoes() {
        // Initialize with default values
        diasEmprestimo = 5;
        maxEmprestimos = 2;
        multaDia = 5;
        anuidadeNormal = 120;
        anuidadePremium = 150;
        extraPremium = 2;
    }

    public static synchronized ValoresPredefinicoes getInstance() {
        if (instance == null) {
            instance = new ValoresPredefinicoes();
        }
        return instance;
    }



    public int getExtraPremium() {
        return extraPremium;
    }

    public void setExtraPremium(int extraPremium) {
        this.extraPremium = extraPremium;
    }


    public int getDiasEmprestimo() {
        return diasEmprestimo;
    }

    public void setDiasEmprestimo(int diasEmprestimo) {
        this.diasEmprestimo = diasEmprestimo;
    }

    public int getMaxEmprestimos() {
        return maxEmprestimos;
    }

    public void setMaxEmprestimos(int maxEmprestimos) {
        this.maxEmprestimos = maxEmprestimos;
    }

    public int getMultaDia() {
        return multaDia;
    }

    public void setMultaDia(int multaDia) {
        this.multaDia = multaDia;
    }

    public int getAnuidadeNormal() {
        return anuidadeNormal;
    }

    public void setAnuidadeNormal(int anuidadeNormal) {
        this.anuidadeNormal = anuidadeNormal;
    }

    public int getAnuidadePremium() {
        return anuidadePremium;
    }

    public void setAnuidadePremium(int anuidadePremium) {
        this.anuidadePremium = anuidadePremium;
    }
}