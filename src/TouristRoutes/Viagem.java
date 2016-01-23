/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TouristRoutes;

import Collections.Linear.Interfaces.UnorderedListADT;
import Collections.Linear.List.UnorderedList.LinkedUnorderedList;
import java.util.Iterator;

/**
 *
 * @author ivo
 */
public class Viagem {

    //Indicadores sobre o preço das viagens
    /**
     * Retorna o preço da viagem indicada
     *
     * @param viagem
     * @return
     */
    public static double getPrecoViagem(LinkedUnorderedList<Trajeto> viagem) {
        double precoViagem = 0;
        Iterator<Trajeto> it = viagem.iterator();
        while (it.hasNext()) {
            Trajeto temp = it.next();
            precoViagem += Trajeto.getPrecoTrajeto(temp);
        }
        return precoViagem;
    }

    /**
     * Retorna a viagem mais barata
     *
     * @param viagens
     * @return
     */
    public static LinkedUnorderedList<Trajeto> getViagemMaisbarata(UnorderedListADT<LinkedUnorderedList<Trajeto>> viagens) {
        Iterator<LinkedUnorderedList<Trajeto>> itViagens = viagens.iterator();
        LinkedUnorderedList<Trajeto> viagemMaisBarata = null;
        double precoMaisBarato = 0;

        while (itViagens.hasNext()) {
            LinkedUnorderedList<Trajeto> tempViagem = itViagens.next();
            double tempPreco = Viagem.getPrecoViagem(tempViagem);

            if (tempPreco < precoMaisBarato) {
                precoMaisBarato = tempPreco;
                viagemMaisBarata = tempViagem;
            }
        }

        return viagemMaisBarata;

    }

    /**
     * Retorna a viagem mais cara
     *
     * @param viagens
     * @return
     */
    public static LinkedUnorderedList<Trajeto> getViagemMaisCara(UnorderedListADT<LinkedUnorderedList<Trajeto>> viagens) {
        Iterator<LinkedUnorderedList<Trajeto>> itViagens = viagens.iterator();
        LinkedUnorderedList<Trajeto> viagemMaisCara = null;
        double precoMaisCaro = 0;

        while (itViagens.hasNext()) {
            LinkedUnorderedList<Trajeto> tempViagem = itViagens.next();
            double tempPreco = Viagem.getPrecoViagem(tempViagem);

            if (tempPreco > precoMaisCaro) {
                precoMaisCaro = tempPreco;
                viagemMaisCara = tempViagem;
            }
        }

        return viagemMaisCara;

    }

    //Indicadores sobre tempo das  viagens
    /**
     * Retorna o tempo da viagem indicada
     *
     * @param viagem
     * @return
     */
    public static double getTempoViagem(LinkedUnorderedList<Trajeto> viagem) {
        double tempoViagem = 0;
        Iterator<Trajeto> it = viagem.iterator();
        while (it.hasNext()) {
            Trajeto temp = it.next();
            tempoViagem += temp.getTempoViagem();
        }
        return tempoViagem;
    }

    /**
     * Retorna a viagem mais longa (tempo viagem)
     *
     * @param viagens
     * @return
     */
    public static LinkedUnorderedList<Trajeto> getViagemMaisLonga(UnorderedListADT<LinkedUnorderedList<Trajeto>> viagens) {
        Iterator<LinkedUnorderedList<Trajeto>> itViagens = viagens.iterator();
        LinkedUnorderedList<Trajeto> viagemMaisLonga = null;
        double tempoMaisLongo = 0;

        while (itViagens.hasNext()) {
            LinkedUnorderedList<Trajeto> tempViagem = itViagens.next();
            double tempTempo = Viagem.getTempoViagem(tempViagem);

            if (tempTempo > tempoMaisLongo) {
                tempoMaisLongo = tempTempo;
                viagemMaisLonga = tempViagem;
            }
        }

        return viagemMaisLonga;
    }

    /**
     * Retorna a viagem mais curta (tempo viagem)
     *
     * @param viagens
     * @return
     */
    public static LinkedUnorderedList<Trajeto> getViagemMaisCurta(UnorderedListADT<LinkedUnorderedList<Trajeto>> viagens) {
        Iterator<LinkedUnorderedList<Trajeto>> itViagens = viagens.iterator();
        LinkedUnorderedList<Trajeto> viagemMaisCurta = null;
        double tempoMaisCurto = 0;

        while (itViagens.hasNext()) {
            LinkedUnorderedList<Trajeto> tempViagem = itViagens.next();
            double tempTempo = Viagem.getTempoViagem(tempViagem);

            if (tempTempo < tempoMaisCurto) {
                tempoMaisCurto = tempTempo;
                viagemMaisCurta = tempViagem;
            }
        }

        return viagemMaisCurta;
    }

    //indicadores sobre os kms das viagens
    /**
     * Retorna os km da viagem indicada
     *
     * @param viagem
     * @return
     */
    public static double getDistanciaViagem(LinkedUnorderedList<Trajeto> viagem) {
        double distanciaViagem = 0;
        Iterator<Trajeto> it = viagem.iterator();
        while (it.hasNext()) {
            Trajeto temp = it.next();
            distanciaViagem += temp.getDistancia();
        }
        return distanciaViagem;
    }

    /**
     * Retorna a viagem mais comprida (Km)
     *
     * @param viagens
     * @return
     */
    public static LinkedUnorderedList<Trajeto> getViagemMaisComprida(UnorderedListADT<LinkedUnorderedList<Trajeto>> viagens) {
        Iterator<LinkedUnorderedList<Trajeto>> itViagens = viagens.iterator();
        LinkedUnorderedList<Trajeto> viagemMaisComprida = null;
        double distanciaMaisComprida = 0;

        while (itViagens.hasNext()) {
            LinkedUnorderedList<Trajeto> tempViagem = itViagens.next();
            double tempDistancia = Viagem.getTempoViagem(tempViagem);

            if (tempDistancia > distanciaMaisComprida) {
                distanciaMaisComprida = tempDistancia;
                viagemMaisComprida = tempViagem;
            }
        }

        return viagemMaisComprida;
    }

    /**
     * Retorna a viagem mais comprida (Km)
     *
     * @param viagens
     * @return
     */
    public static LinkedUnorderedList<Trajeto> getViagemMenosComprida(UnorderedListADT<LinkedUnorderedList<Trajeto>> viagens) {
        Iterator<LinkedUnorderedList<Trajeto>> itViagens = viagens.iterator();
        LinkedUnorderedList<Trajeto> viagemMenosComprida = null;
        double distanciaMenosComprida = 0;

        while (itViagens.hasNext()) {
            LinkedUnorderedList<Trajeto> tempViagem = itViagens.next();
            double tempDistancia = Viagem.getTempoViagem(tempViagem);

            if (tempDistancia < distanciaMenosComprida) {
                distanciaMenosComprida = tempDistancia;
                viagemMenosComprida = tempViagem;
            }
        }

        return viagemMenosComprida;
    }

    /**
     * Retorna as viagens com determinado transporte
     *
     * @param viagens
     * @param transporte
     * @return
     */
    //TODO
    public static UnorderedListADT<LinkedUnorderedList<Trajeto>> getViagensByTransporte(UnorderedListADT<LinkedUnorderedList<Trajeto>> viagens, Transporte transporte) {
        return null;
    }

    /**
     *
     * @param viagens
     * @param trasportadora
     * @return
     */
    //TODO
    public static UnorderedListADT<LinkedUnorderedList<Trajeto>> getViagensByTransportadora(UnorderedListADT<LinkedUnorderedList<Trajeto>> viagens, String trasportadora) {
        return null;
    }
    //Filtragem por preco

    /**
     * Retorna as viagens que cumprem com o preco maximo de viagem
     *
     * @param viagens
     * @param precoMax
     * @return
     */
    public static UnorderedListADT<LinkedUnorderedList<Trajeto>> getViagensByPrecoMax(UnorderedListADT<LinkedUnorderedList<Trajeto>> viagens, double precoMax) {
        Iterator<LinkedUnorderedList<Trajeto>> itViagens = viagens.iterator();
        UnorderedListADT<LinkedUnorderedList<Trajeto>> viagensPositivas = new LinkedUnorderedList<>();
        while (itViagens.hasNext()) {
            LinkedUnorderedList<Trajeto> tempViagem = itViagens.next();
            double tempPreco = Viagem.getPrecoViagem(tempViagem);

            if (precoMax > tempPreco) {
                viagensPositivas.addToRear(tempViagem);
            }
        }
        return viagensPositivas;
    }

    /**
     * Retorna todas as viagens que cumprem com o preco maximo de um trajeto
     * (troço)
     *
     * @param viagens
     * @param precoTrajetoMax
     * @return
     */
    public static UnorderedListADT<LinkedUnorderedList<Trajeto>> getViagensByPrecoTrajetoMax(UnorderedListADT<LinkedUnorderedList<Trajeto>> viagens, double precoTrajetoMax) {

        Iterator<LinkedUnorderedList<Trajeto>> itViagens = viagens.iterator();
        UnorderedListADT<LinkedUnorderedList<Trajeto>> viagensPositivas = new LinkedUnorderedList<>();
        while (itViagens.hasNext()) {
            LinkedUnorderedList<Trajeto> tempViagem = itViagens.next();

            if (Viagem.checkPrecoMaximoTrajetos(tempViagem, precoTrajetoMax)) {
                viagensPositivas.addToRear(tempViagem);
            }
        }

        return viagensPositivas;

    }

    /**
     * Verifica se todos os trajetos cumprem com o preço maximo indicado
     *
     * @param trajetos
     * @param precoMaximaTrajeto
     * @return
     */
    public static boolean checkPrecoMaximoTrajetos(LinkedUnorderedList<Trajeto> trajetos, double precoMaximaTrajeto) {
        if (!trajetos.isEmpty()) {
            Iterator<Trajeto> it = trajetos.iterator();
            while (it.hasNext()) {
                Trajeto temp = it.next();
                if (!Trajeto.checkPrecoMaximoTrajeto(temp, precoMaximaTrajeto)) {
                    return false;
                }

            }
            return true;
        }
        return false;
    }

    //Filtragem por Tempo
    /**
     * Retorna as viagens que cumprem com o tempo maximo de viagem
     *
     * @param viagens
     * @param tempoMax
     * @return
     */
    public static UnorderedListADT<LinkedUnorderedList<Trajeto>> getViagensByTempoMax(UnorderedListADT<LinkedUnorderedList<Trajeto>> viagens, double tempoMax) {
        Iterator<LinkedUnorderedList<Trajeto>> itViagens = viagens.iterator();
        UnorderedListADT<LinkedUnorderedList<Trajeto>> viagensPositivas = new LinkedUnorderedList<>();
        while (itViagens.hasNext()) {
            LinkedUnorderedList<Trajeto> tempViagem = itViagens.next();
            double tempTempo = Viagem.getTempoViagem(tempViagem);

            if (tempoMax > tempTempo) {
                viagensPositivas.addToRear(tempViagem);
            }
        }

        return viagensPositivas;
    }

    /**
     * Retorna todas as viagens que cumprem com o tempo maximo de um trajeto
     * (troço)
     *
     * @param viagens
     * @param tempoTrajetosMax
     * @return
     */
    public static UnorderedListADT<LinkedUnorderedList<Trajeto>> getViagensByTempoTrajetosMax(UnorderedListADT<LinkedUnorderedList<Trajeto>> viagens, double tempoTrajetosMax) {
        Iterator<LinkedUnorderedList<Trajeto>> itViagens = viagens.iterator();
        UnorderedListADT<LinkedUnorderedList<Trajeto>> viagensPositivas = new LinkedUnorderedList<>();
        while (itViagens.hasNext()) {
            LinkedUnorderedList<Trajeto> tempViagem = itViagens.next();

            if (Viagem.checkPrecoMaximoTrajetos(tempViagem, tempoTrajetosMax)) {
                viagensPositivas.addToRear(tempViagem);
            }
        }

        return viagensPositivas;
    }

    /**
     * Verifica se todos os trajetos cumprem com o tempo de viagem maximo
     * indicado
     *
     * @param trajetos
     * @param tempoMaximoTrajeto
     * @return
     */
    public static boolean checkTempoMaximoTrajetos(LinkedUnorderedList<Trajeto> trajetos, double tempoMaximoTrajeto) {
        if (!trajetos.isEmpty()) {
            Iterator<Trajeto> it = trajetos.iterator();
            while (it.hasNext()) {
                Trajeto temp = it.next();
                if (!Trajeto.checkPrecoMaximoTrajeto(temp, tempoMaximoTrajeto)) {
                    return false;
                }

            }
            return true;
        }
        return false;
    }

    //Filtragem por distancia
    /**
     * Retorna as viagens que cumprem com o criterio da distancia maxima da
     * viagem
     *
     * @param viagens
     * @param distanciaMax
     * @return
     */
    public static UnorderedListADT<LinkedUnorderedList<Trajeto>> getViagensByDistanciaMax(UnorderedListADT<LinkedUnorderedList<Trajeto>> viagens, double distanciaMax) {
        Iterator<LinkedUnorderedList<Trajeto>> itViagens = viagens.iterator();
        UnorderedListADT<LinkedUnorderedList<Trajeto>> viagensPositivas = new LinkedUnorderedList<>();
        while (itViagens.hasNext()) {
            LinkedUnorderedList<Trajeto> tempViagem = itViagens.next();
            double tempDistancia = Viagem.getDistanciaViagem(tempViagem);

            if (distanciaMax > tempDistancia) {
                viagensPositivas.addToRear(tempViagem);
            }
        }

        return viagensPositivas;

    }

    /**
     * Retorna as viagens que cumprem com o criterio da distancia maxima por
     * trajeto
     *
     * @param viagens
     * @param distanciaTrajetosMax
     * @return
     */
    public static UnorderedListADT<LinkedUnorderedList<Trajeto>> getViagensByDistanciaTrajetosMax(UnorderedListADT<LinkedUnorderedList<Trajeto>> viagens, double distanciaTrajetosMax) {
        Iterator<LinkedUnorderedList<Trajeto>> itViagens = viagens.iterator();
        UnorderedListADT<LinkedUnorderedList<Trajeto>> viagensPositivas = new LinkedUnorderedList<>();
        while (itViagens.hasNext()) {
            LinkedUnorderedList<Trajeto> tempViagem = itViagens.next();

            if (Viagem.checkDistanciaMaximaTrajetos(tempViagem, distanciaTrajetosMax)) {
                viagensPositivas.addToRear(tempViagem);
            }
        }

        return viagensPositivas;
    }

    /**
     * Verifica se todos os trajetos cumprem com distancia Maxima indicada
     *
     * @param trajetos
     * @param distanciaMaximaTrajeto
     * @return
     */
    public static boolean checkDistanciaMaximaTrajetos(LinkedUnorderedList<Trajeto> trajetos, double distanciaMaximaTrajeto) {
        if (!trajetos.isEmpty()) {
            Iterator<Trajeto> it = trajetos.iterator();
            while (it.hasNext()) {
                Trajeto temp = it.next();
                if (!Trajeto.checkDistanciaMaximaTrajeto(temp, distanciaMaximaTrajeto)) {
                    return false;
                }

            }
            return true;
        }
        return false;
    }

}
