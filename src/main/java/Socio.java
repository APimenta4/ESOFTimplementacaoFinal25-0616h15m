import java.time.Year;

public class Socio {

    private static int nextNumeroDeSocio = 1; // Static variable that increments each time a new Socio is created
    private int numeroDeSocio; // Unique number
    private String name;
    private String contactType; // "sms" or "email"           //Isto pode ser trocado por um boolean "prefereSms" (true = sms/false = email)
    private String membershipType; // "Premium" or "Normal"   //Isto pode ser trocado por um boolean "isPremium" (true = premium/false = normal)
    private String nif;
    private int quantEmprestimos;
    private int anuidadePaga;     //assume-se que quando um sócio é criado, a anuidade é logo paga
                                  //este atributo guarda o inteiro do último ano que a anuidade foi paga
                                  //"anuidadePaga = 2024" significa que a anuidade de 2024 foi paga
                                  //se o ano atual for 2025 e "anuidadePaga = 2024", o sócio não tem a anuidade em dia

    public Socio(String name, String contactType, String membershipType) {
        this.numeroDeSocio = nextNumeroDeSocio++; // Assign the current value of nextNumeroDeSocio to numeroDeSocio, then increment nextNumeroDeSocio
        this.name = name;
        this.contactType = contactType;
        this.membershipType = membershipType;
        this.quantEmprestimos = 0;
        this.anuidadePaga = Year.now().getValue();
    }


    // este construtor com anuidadePaga só existe por razões de teste
    public Socio(String name, String contactType, String membershipType, int anuidadePaga) {
        this.numeroDeSocio = nextNumeroDeSocio++; // Assign the current value of nextNumeroDeSocio to numeroDeSocio, then increment nextNumeroDeSocio
        this.name = name;
        this.contactType = contactType;
        this.membershipType = membershipType;
        this.quantEmprestimos = 0;
        this.anuidadePaga = anuidadePaga;
    }


    public int getAnuidadePaga() {
        return anuidadePaga;
    }
    public void pagarAnuidade(){
        this.anuidadePaga++;
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
}