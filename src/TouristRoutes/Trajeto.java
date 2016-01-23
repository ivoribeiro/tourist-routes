package TouristRoutes;

import Collections.Linear.Interfaces.UnorderedListADT;
import Collections.Linear.List.UnorderedList.LinkedUnorderedList;

import java.time.LocalTime;
import java.util.Iterator;
import java.util.Objects;

/**
 * Created by aluno on 1/19/16.
 *
 * @param <T>
 */
public class Trajeto<T extends Transporte> {

    private final String cidadeOrigem;
    private final String cidadeDestino;

    private final T transporteUsado;
    private final int tempoViagem;
    private final double distancia;
    private final double precoKm;
    private final UnorderedListADT<LocalTime> horarios;

    public Trajeto(String cidadeOrigem, String cidadeDestino, T transporteUsado, int tempoViagem, double distancia, double precoKm) {
        this.cidadeDestino = cidadeDestino;
        this.cidadeOrigem = cidadeOrigem;
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
        return "\n\n" + cidadeOrigem + "->"
                + cidadeDestino + "\n"
                + transporteUsado.toString() + "\n"
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
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.transporteUsado);
        hash = 67 * hash + this.tempoViagem;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.distancia) ^ (Double.doubleToLongBits(this.distancia) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.precoKm) ^ (Double.doubleToLongBits(this.precoKm) >>> 32));
        return hash;
    }

    /**
     * Retorna o trajeto mais curto dentro de uma determinada lista de trajetos
     *
     * @param trajetos
     * @return
     */
    public static Trajeto trajetoMenorDistancia(UnorderedListADT<Trajeto> trajetos) {
        if (!trajetos.isEmpty()) {
            Trajeto menorDistancia = null;
            Iterator<Trajeto> it = trajetos.iterator();
            while (it.hasNext()) {
                Trajeto temp = it.next();
                if (menorDistancia == null || temp.getDistancia() < menorDistancia.getDistancia()) {
                    menorDistancia = temp;
                }
            }
            return menorDistancia;
        } else {
            return null;
        }
    }

    
    /**
     * Filtra a distancia Maxima do trajeto dentro de uma determinada lista de
     * trajetos
     *
     * @param trajetos
     * @param distanciaMaximaTrajeto
     * @return
     */
    public static UnorderedListADT<Trajeto> filtrarDistanciaMaximaTrajeto(UnorderedListADT<Trajeto> trajetos, int distanciaMaximaTrajeto) {
        if (!trajetos.isEmpty()) {
            UnorderedListADT<Trajeto> tempList = new LinkedUnorderedList<>();
            Iterator<Trajeto> it = trajetos.iterator();
            while (it.hasNext()) {
                Trajeto temp = it.next();
                if (temp.getDistancia() <= distanciaMaximaTrajeto) {
                    tempList.addToRear(temp);
                }

            }
            return tempList;

        } else {
            return null;
        }
    }

    /**
     * Retorna o custo da viagem
     *
     * @param trajeto
     * @return
     */
    public static double getPrecoTrajeto(Trajeto trajeto) {
        return trajeto.getDistancia() * trajeto.getPrecoKm();
    }

    /**
     * Retorna o trajeto com o preco mais barato
     *
     * @param trajetos
     * @return
     */
    public static Trajeto trajetoMaisBarato(UnorderedListADT<Trajeto> trajetos) {
        if (!trajetos.isEmpty()) {
            Trajeto maisBarato = null;
            Double precoMaisBarato = null;
            Iterator<Trajeto> it = trajetos.iterator();
            while (it.hasNext()) {
                Trajeto temp = it.next();
                //o custo do trajeto é a distancia vezes o custo por km
                double preco = getPrecoTrajeto(temp);
                if (maisBarato == null || preco < precoMaisBarato) {
                    precoMaisBarato = preco;
                    maisBarato = temp;
                }
            }
            return maisBarato;
        } else {
            return null;
        }
    }

    /**
     * Filtra a preco maximo do trajeto dentro de uma determinada lista de
     * trajetos
     *
     * @param trajetos
     * @param precoMaximoTrajeto
     * @return
     */
    public static UnorderedListADT<Trajeto> filtrarPrecoMaximoTrajeto(UnorderedListADT<Trajeto> trajetos, int precoMaximoTrajeto) {
        if (!trajetos.isEmpty()) {
            UnorderedListADT<Trajeto> tempList = new LinkedUnorderedList<>();
            Iterator<Trajeto> it = trajetos.iterator();
            while (it.hasNext()) {
                Trajeto temp = it.next();
                if (getPrecoTrajeto(temp) <= precoMaximoTrajeto) {
                    tempList.addToRear(temp);
                }

            }
            return tempList;

        } else {
            return null;
        }
    }

    /**
     * Retorna o trajeto com menor tempo de viagem
     *
     * @param trajetos
     * @return
     */
    public static Trajeto trajetoMenosTempoViagem(UnorderedListADT<Trajeto> trajetos) {
        if (!trajetos.isEmpty()) {
            Trajeto menosTempoViagem = null;
            Iterator<Trajeto> it = trajetos.iterator();
            while (it.hasNext()) {
                Trajeto temp = it.next();
                if (menosTempoViagem == null || temp.getTempoViagem() < menosTempoViagem.getTempoViagem()) {
                    menosTempoViagem = temp;
                }
            }
            return menosTempoViagem;
        } else {
            return null;
        }
    }

    /**
     * Filtra a tempo maximo de viagem do trajeto dentro de uma determinada
     * lista de trajetos
     *
     * @param trajetos
     * @param tempoMaximoViagemTrajeto
     * @return uma lista dos trajetos que cumprem com esse criterio
     */
    public static UnorderedListADT<Trajeto> filtrarTempoViagemMaximoTrajeto(UnorderedListADT<Trajeto> trajetos, int tempoMaximoViagemTrajeto) {
        if (!trajetos.isEmpty()) {
            UnorderedListADT<Trajeto> tempList = new LinkedUnorderedList<>();
            Iterator<Trajeto> it = trajetos.iterator();
            while (it.hasNext()) {
                Trajeto temp = it.next();
                if (getPrecoTrajeto(temp) <= tempoMaximoViagemTrajeto) {
                    tempList.addToRear(temp);
                }

            }
            return tempList;

        } else {
            return null;
        }
    }

}
