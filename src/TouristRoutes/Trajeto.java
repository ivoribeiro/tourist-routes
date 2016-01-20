package TouristRoutes;

import Collections.Linear.Interfaces.UnorderedListADT;
import Collections.Linear.List.UnorderedList.LinkedUnorderedList;

import java.time.LocalTime;
import java.util.Objects;

/**
 * Created by aluno on 1/19/16.
 */
public class Trajeto<T extends Transporte> {

    private T transporteUsado;
    private int tempoViagem;
    private double distancia;
    private double precoKm;
    private UnorderedListADT<LocalTime> horarios;

    public Trajeto(T transporteUsado, int tempoViagem, double distancia, double precoKm) {
        this.transporteUsado = transporteUsado;
        this.distancia = distancia;
        this.precoKm = precoKm;
        this.tempoViagem = tempoViagem;
        this.horarios = new LinkedUnorderedList<>();
    }

    public void addHorario(LocalTime horario) {
        horarios.addToFront(horario);
    }

    public T getTransporteUsado() {
        return transporteUsado;
    }

    public int getTempoViagem() {
        return tempoViagem;
    }

    public double getDistancia() {
        return distancia;
    }

    public double getPrecoKm() {
        return precoKm;
    }

    @Override
    public String toString() {
        return "\n\n" + transporteUsado.toString() + "\n"
                + "Tempo Viagem: " + tempoViagem + "\n"
                + "Distancia: " + distancia + "\n"
                + "Preco/Km: " + precoKm + "\n"
                + "Horarios: " + horarios.toString();
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
        final Trajeto<?> other = (Trajeto<?>) obj;
        if (this.tempoViagem != other.tempoViagem) {
            return false;
        }
        if (Double.doubleToLongBits(this.distancia) != Double.doubleToLongBits(other.distancia)) {
            return false;
        }
        if (Double.doubleToLongBits(this.precoKm) != Double.doubleToLongBits(other.precoKm)) {
            return false;
        }
        if (!Objects.equals(this.transporteUsado, other.transporteUsado)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.transporteUsado);
        hash = 97 * hash + this.tempoViagem;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.distancia) ^ (Double.doubleToLongBits(this.distancia) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.precoKm) ^ (Double.doubleToLongBits(this.precoKm) >>> 32));
        return hash;
    }

}
