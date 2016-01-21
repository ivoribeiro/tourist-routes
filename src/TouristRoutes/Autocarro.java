package TouristRoutes;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.transportadora);
        hash = 89 * hash + this.lugares;
        hash = 89 * hash + Objects.hashCode(this.conforto);
        hash = 89 * hash + (this.casaDeBanho ? 1 : 0);
        hash = 89 * hash + (this.alimentacao ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Autocarro other = (Autocarro) obj;
        if (this.lugares != other.lugares) {
            return false;
        }
        if (this.casaDeBanho != other.casaDeBanho) {
            return false;
        }
        if (this.alimentacao != other.alimentacao) {
            return false;
        }
        if (!Objects.equals(this.transportadora, other.transportadora)) {
            return false;
        }
        if (!Objects.equals(this.conforto, other.conforto)) {
            return false;
        }
        return true;
    }
    
    
}
