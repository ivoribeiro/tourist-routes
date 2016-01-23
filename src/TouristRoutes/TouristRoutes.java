package TouristRoutes;

import Collections.NonLinear.Network.DiNetworkAdjMatrixTrajeto;
import Enumerations.Language;
import Exceptions.CoordinatesNotFound;
import geocoderapi.CalculateCoordinates;
import geocoderapi.Coordinate;
import googlemapsapis.Gmaps;
import googlemapsapis.MyMarker;
import java.io.*;
import java.util.Iterator;

/**
 * Created by ivo on 18-01-2016.
 */
public class TouristRoutes {

    public static void main(String[] args) throws IOException {

//Intancia da network
        DiNetworkAdjMatrixTrajeto<String> touristRoutes = new DiNetworkAdjMatrixTrajeto<>();
//  Popula a network
        JsonToNetwork.JsonToNetwork(touristRoutes);

        Object[] a = touristRoutes.getVertices();
        Object[] b;
        Coordinate cordenadas;
        int i = 0;
        Gmaps map = new Gmaps();

        do {
            try {

                cordenadas = CalculateCoordinates.getCoordinate(a[i].toString(), Language.PT);

                MyMarker mTemp = new MyMarker(a[i].toString(), cordenadas.getLatitude(), cordenadas.getLongitude(), a[i].toString());

                map.addMarker(mTemp);
                i++;
            } catch (CoordinatesNotFound e) {
            }
        } while (i < touristRoutes.getNumVertices());

        Criterios criterios = new Criterios();
        criterios.setViagemMaisBarata(true);
        criterios.setViagemMenorDistancia(true);
        criterios.setVigemMenorTempoViagem(true);

        Iterator it = touristRoutes.iteratorBFS("Porto");

        while (it.hasNext()) {
            String temp = (String) it.next();
            System.out.println(temp);
        }
        //System.out.println(touristRoutes.shortestPathWeight("Porto", "Bragança", criterios));

        //map.startMap(41.366700, -8.194861, 1);
    }
}
