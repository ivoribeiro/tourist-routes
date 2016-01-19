package TouristRoutes;

/**
 * Created by aluno on 1/19/16.
 */
public class Aviao extends Transporte{

    private String companhiaAerea;
    private int lugares;
    private String conforto;
    private boolean casaDeBanho;
    private boolean alimentacao;

    public Aviao(String companhiaAerea) {
        super("Aerea");
        this.companhiaAerea = companhiaAerea;
    }

    public String getCompanhiaAerea() {
        return companhiaAerea;
    }

    @Override
    public String toString() {
        return "Aviao{" +super.toString()+
                "companhiaAerea='" + companhiaAerea + '\'' +
                ", lugares=" + lugares +
                ", conforto='" + conforto + '\'' +
                ", casaDeBanho=" + casaDeBanho +
                ", alimentacao=" + alimentacao +
                '}';
    }
}
