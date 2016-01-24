/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TouristRoutes;

import Enumerations.Language;
import Exceptions.CoordinatesNotFound;
import geocoderapi.CalculateCoordinates;
import geocoderapi.Coordinate;
import googlemapsapis.Gmaps;
import googlemapsapis.MyMarker;
import java.io.IOException;

/**
 *
 * @author luis_
 */
public class mapa {
    /**
     * funçao estatica que calcula as coordenadas de um mapa
     * @param a
     * @param max 
     */
    public static void startMapa(Object[] a, int max) {
        Coordinate cordenadas;
        int i = 0;
        Gmaps map = new Gmaps();

        do {
            try {

                cordenadas = CalculateCoordinates.getCoordinate(a[i].toString(), Language.PT);

                MyMarker mTemp = new MyMarker(a[i].toString(), cordenadas.getLatitude(), cordenadas.getLongitude(), a[i].toString());
                map.addMarker(mTemp);
                i++;
            } catch (CoordinatesNotFound | IOException ex) {
            }
            
        }while (i < max);

        map.startMap(41.366700, -8.194861, 10);
    }
}
