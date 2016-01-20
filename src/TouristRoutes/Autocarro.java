package TouristRoutes;

/**
 * Created by ivo on 18-01-2016.
 */
public class Autocarro extends Transporte {

    private String transportadora;
    private int lugares;
    private String conforto;
    private boolean casaDeBanho;
    private boolean alimentacao;

    public Autocarro(String transportadora) {
        super("Terrestre");
        this.transportadora = transportadora;
    }

    public Autocarro(String transportadora, int lugares, String conforto, boolean casaDeBanho, boolean alimentacao) {
        super("Terrestre");
        this.transportadora = transportadora;
        this.lugares = lugares;
        this.conforto = conforto;
        this.casaDeBanho = casaDeBanho;
        this.alimentacao = alimentacao;
    }

    public String getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(String transportadora) {
        this.transportadora = transportadora;
    }

    public int getLugares() {
        return lugares;
    }

    public void setLugares(int lugares) {
        this.lugares = lugares;
    }

    public String getConforto() {
        return conforto;
    }

    public void setConforto(String conforto) {
        this.conforto = conforto;
    }

    public boolean isCasaDeBanho() {
        return casaDeBanho;
    }

    public void setCasaDeBanho(boolean casaDeBanho) {
        this.casaDeBanho = casaDeBanho;
    }

    public boolean isAlimentacao() {
        return alimentacao;
    }

    public void setAlimentacao(boolean alimentacao) {
        this.alimentacao = alimentacao;
    }

    @Override
    public String toString() {

        return /*super.toString()+*/
                 "Transportadora: " + transportadora + "\t";
//                + "Lugares: " + lugares + "\t"
//                + "Conforto: " + conforto + "\t"
//                + "Casa de Banho: " + casaDeBanho + "\t"
//                + "Alimentação: " + alimentacao + "\t";
    }
}
