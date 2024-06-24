public class Exemplar {
    private String titulo;
    private String codigo;
    private String autores;
    private String generos;
    private Editora editora;
    private String edicaoAno;
    private String isbn;
    private String prateleiraEstante;
    private boolean emprestado;

    public Exemplar(String titulo, String codigo, String autores, String genero, Editora editora, String edicaoAno, String isbn, String prateleiraEstante) {
        this.titulo = titulo;
        this.codigo = codigo;
        this.autores = autores;
        this.generos = genero;
        this.editora = editora;
        this.edicaoAno = edicaoAno;
        this.isbn = isbn;
        this.prateleiraEstante = prateleiraEstante;
        this.emprestado = false;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }
    public boolean isEmprestado() {
        return emprestado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getGeneros() {
        return generos;
    }

    public void setGenero(String genero) {
        this.generos = genero;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public String getEdicaoAno() {
        return edicaoAno;
    }

    public void setEdicaoAno(String edicaoAno) {
        this.edicaoAno = edicaoAno;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPrateleiraEstante() {
        return prateleiraEstante;
    }

    public void setPrateleiraEstante(String prateleiraEstante) {
        this.prateleiraEstante = prateleiraEstante;
    }
}