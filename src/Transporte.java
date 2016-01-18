import Collections.Linear.List.OrderedList.LinkedOrderedList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by ivo on 18-01-2016.
 */
public class Transporte<T> {
    T tipoTransporte;
    LinkedOrderedList<Time> horarios;
    Time tempoViagem;
    double distancia;
    double precoKm;

    public Transporte(T tipoTransporte, Time tempoViagem, double distancia, double precoKm) {
        this.tipoTransporte = tipoTransporte;
        this.tempoViagem = tempoViagem;
        this.distancia = distancia;
        this.precoKm = precoKm;
    }

    public T getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(T tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    public Time getPrimeiroHorario() {
        return horarios.getFront().getElement();
    }

    public Time getUltimoHorario() {
        return horarios.getRear().getElement();
    }

    public Time[] getHorarios() {
        throw new NotImplementedException();
    }

    public void addHorario(Time horario) {
        this.horarios.add(horario);
    }

    public Time getTempoViagem() {
        return tempoViagem;
    }

    public void setTempoViagem(Time tempoViagem) {
        this.tempoViagem = tempoViagem;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getPrecoKm() {
        return precoKm;
    }

    public void setPrecoKm(double precoKm) {
        this.precoKm = precoKm;
    }
}
