import java.util.ArrayList;
import java.util.List;

public class Emprestimos {
    private static Emprestimos instance;
    private final List<Emprestimo> emprestimos;

    private Emprestimos() {
        emprestimos = new ArrayList<>();
    }

    public static synchronized Emprestimos getInstance() {
        if (instance == null) {
            instance = new Emprestimos();
        }
        return instance;
    }

    public void addEmprestimo(Emprestimo emprestimo) {
        if(emprestimo.getExemplar().isEmprestado()){
            throw new IllegalArgumentException("O exemplar já está emprestado");
        }
        emprestimos.add(emprestimo);
        emprestimo.getExemplar().setEmprestado(true);
        emprestimo.getSocio().incQuantEmprestimos();
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
}