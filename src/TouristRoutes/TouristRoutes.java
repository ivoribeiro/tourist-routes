package TouristRoutes;

import Collections.NonLinear.Network.DiNetworkAdjMatrixTrajeto;
import Enumerations.Language;
import Exceptions.CoordinatesNotFound;
import geocoderapi.CalculateCoordinates;
import geocoderapi.Coordinate;
import googlemapsapis.Gmaps;
import googlemapsapis.MyMarker;
import java.io.*;

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

        System.out.println("\nViagem com menos km percorridos\n");
        System.out.println(touristRoutes.shortestPathWeight("Porto", "Bragança", 0));
        System.out.println("\nViagem mais barata\n");
        System.out.println(touristRoutes.shortestPathWeight("Porto", "Bragança", 1));
        System.out.println("\nViagem mais curta(tempo de viagem)\n");
        System.out.println(touristRoutes.shortestPathWeight("Porto", "Bragança", 2));


        //map.startMap(41.366700, -8.194861, 1);
    }
}
