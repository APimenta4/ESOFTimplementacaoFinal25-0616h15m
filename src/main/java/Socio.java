import java.time.Year;

public class Socio {

    private static int nextNumeroDeSocio = 1; // Static variable that increments each time a new Socio is created
    private int numeroDeSocio;
    private String name;
    private String contactType; // "sms" or "email"
    private String membershipType; // "Premium" or "Normal"
    private int nifCC;
    private String morada;
    private int telefone;
    private String email;

    private int quantEmprestimos;
    private int anuidadePaga;       //assume-se que quando um sócio é criado, a anuidade é logo paga
                                    //este atributo guarda o inteiro do último ano que a anuidade foi paga
                                    //"anuidadePaga = 2024" significa que a anuidade de 2024 foi paga
                                    //se o ano atual for 2025 e "anuidadePaga = 2024", o sócio não tem a anuidade em dia

    public Socio(String name, String contactType, String membershipType, String email, int telefone, String morada, int nifCC) {
        this.numeroDeSocio = nextNumeroDeSocio++;
        this.name = name;
        this.contactType = contactType;
        this.membershipType = membershipType;
        this.quantEmprestimos = 0;
        this.email = email;
        this.morada = morada;
        this.telefone = telefone;
        this.nifCC = nifCC;
        this.anuidadePaga = Year.now().getValue();
    }

    // Construtor secundário que só existe por motivos de teste (criar um sócio sem anuidade paga quando se corre o main)
    public Socio(String name, String contactType, String membershipType, int anuidadePaga, String email, int telefone, String morada, int nifCC) {
        this.numeroDeSocio = nextNumeroDeSocio++;
        this.name = name;
        this.contactType = contactType;
        this.membershipType = membershipType;
        this.quantEmprestimos = 0;
        this.email = email;
        this.morada = morada;
        this.telefone = telefone;
        this.nifCC = nifCC;
        this.anuidadePaga = anuidadePaga;
    }


    public int getAnuidadePaga() {
        return anuidadePaga;
    }

    public void pagarAnuidade(){
        this.anuidadePaga++;
    }

    public void cancelarAnuidade(){
        this.anuidadePaga--;
    }

    public int getQuantEmprestimos() {
        return quantEmprestimos;
    }

    public void incQuantEmprestimos() {
        this.quantEmprestimos++;
    }


    public void decQuantEmprestimos() {
        this.quantEmprestimos--;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public int getNumeroDeSocio() {
        return numeroDeSocio;
    }

    public int getNifCC() {
        return nifCC;
    }

    public String getMorada() {
        return morada;
    }

    public int getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public static void setNextNumeroDeSocio(int nextNumeroDeSocio) {
        Socio.nextNumeroDeSocio = nextNumeroDeSocio;
    }

    public void setNumeroDeSocio(int numeroDeSocio) {
        this.numeroDeSocio = numeroDeSocio;
    }

    public void setNifCC(int nifCC) {
        this.nifCC = nifCC;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setQuantEmprestimos(int quantEmprestimos) {
        this.quantEmprestimos = quantEmprestimos;
    }

    public void setAnuidadePaga(int anuidadePaga) {
        this.anuidadePaga = anuidadePaga;
    }
}