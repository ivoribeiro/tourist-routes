package TouristRoutes;

import Collections.Linear.Interfaces.OrderedListADT;
import Collections.Linear.Interfaces.UnorderedListADT;
import Collections.Linear.List.UnorderedList.LinkedUnorderedList;

import java.time.LocalTime;

/**
 * Created by ivo on 17-01-2016.
 */


public class Route {
    private String cidadeOrigem;
    private String cidadeDestino;
    private UnorderedListADT<Trajeto> trajetos;

    public Route(String cidadeOrigem, String cidadeDestino) {
        this.cidadeOrigem = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
        trajetos = new LinkedUnorderedList<>();
    }

    public String getCidadeOrigem() {
        return cidadeOrigem;
    }

    public String getCidadeDestino() {
        return cidadeDestino;
    }

    public void addTrajeto(Trajeto tranjeto) {
        trajetos.addToFront(tranjeto);
    }

    public void addHorario(Trajeto trajeto, LocalTime horario){
        trajeto.addHorario(horario);
    }

    @Override
    public String toString() {
        return "Route{" +
                "cidadeOrigem='" + cidadeOrigem + '\'' +
                ", cidadeDestino='" + cidadeDestino + '\'' +
                ", trajetos=" + trajetos +
                '}';
    }
}
