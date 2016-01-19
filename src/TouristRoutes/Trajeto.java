package TouristRoutes;

import Collections.Linear.Interfaces.UnorderedListADT;
import Collections.Linear.List.UnorderedList.LinkedUnorderedList;

import java.time.LocalTime;

/**
 * Created by aluno on 1/19/16.
 */
public class Trajeto<T extends Transporte> {
    private T transporteUsado;
    private int tempoViagem;
    private double distancia;
    private double precoKm;
    private UnorderedListADT<LocalTime> horarios;

    public Trajeto(T transporteUsado,int tempoViagem, double distancia, double precoKm){
        this.transporteUsado = transporteUsado;
        this.distancia= distancia;
        this.precoKm = precoKm;
        this.tempoViagem = tempoViagem;
        this.horarios = new LinkedUnorderedList<>();
    }

    public void addHorario(LocalTime horario){
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
        return "Trajeto{" +
                "transporteUsado=" + transporteUsado +
                ", tempoViagem=" + tempoViagem +
                ", distancia=" + distancia +
                ", precoKm=" + precoKm +
                ", horarios=" + horarios +
                '}';
    }
}
