package TouristRoutes;

/**
 * Created by aluno on 1/19/16.
 */
public class Aviao extends Transporte {

    private String companhiaAerea;
    private int lugares;
    private String conforto;
    private boolean casaDeBanho;
    private boolean alimentacao;
    
    /**
     * Create classe with airline name
     * @param transportadora  
     */
    public Aviao(String companhiaAerea) {
        super("Aerea");
        this.companhiaAerea = companhiaAerea;
    }

    public String getCompanhiaAerea() {
        return companhiaAerea;
    }

    @Override
    public String toString() {
        return super.toString()
                + "Companhia Aerea: " + companhiaAerea + "\n"
                + "Lugares: " + lugares + "\n"
                + "Conforto: " + conforto + "\n"
                + "Casa De Banho: " + casaDeBanho + "\n"
                + "Alimentacão: " + alimentacao + "\n";
    }
}
