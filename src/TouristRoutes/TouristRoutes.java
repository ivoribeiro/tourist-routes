package TouristRoutes;

import Collections.NonLinear.Network.DiNetworkAdjMatrixTrajeto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.time.LocalTime;
import java.util.Iterator;

/**
 * Created by ivo on 18-01-2016.
 */
public class TouristRoutes {

    public static void main(String[] args) {

        /**
        Route novaRota = new Route("Amarante","Porto");
        Trajeto  trajeto1 =new Trajeto(new Autocarro("RodoNorte"),10,20.5,0.5);
        novaRota.addTrajeto(trajeto1);
        novaRota.addHorario(trajeto1,LocalTime.parse("12:10"));
        novaRota.addHorario(trajeto1,LocalTime.parse("10:10"));

        Trajeto trajeto2 = new Trajeto(new Aviao("TAP"),20,10.5,0.5);
        novaRota.addTrajeto(trajeto2);
        novaRota.addHorario(trajeto2,LocalTime.parse("13:10"));
        novaRota.addHorario(trajeto2,LocalTime.parse("09:10"));

        System.out.println(novaRota.toString());
        */

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(
                    "src/routes.json"));
            DiNetworkAdjMatrixTrajeto<String> touristRoutes = new DiNetworkAdjMatrixTrajeto<>();
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray rotas = (JSONArray) jsonObject.get("rotas");
            Iterator<JSONObject> iterator = rotas.iterator();
            while (iterator.hasNext()) {
                JSONObject rota = iterator.next();

                Autocarro transporte = new Autocarro("RodoNorte");

                String cidadeOrigem = (String) rota.get("CidadeOrigem");
                String cidadeDestino = (String) rota.get("CidadeDestino");
                double precoKmIda = (double) rota.get("preco/KmIda");
                String horariosIda1 = (String) rota.get("HoráriosIda1");
                String horariosIda2 = (String) rota.get("HoráriosIda2");
                String horariosIda3 = (String) rota.get("HoráriosIda3");
                String horariosVinda1 = (String) rota.get("HoráriosVinda1");
                String horariosVinda2 = (String) rota.get("HoráriosVinda2");
                String horariosVinda3 = (String) rota.get("HoráriosVinda3");

                Long tempo = (Long) rota.get("Tempo(m)");
                Object km = rota.get("Km");
                Trajeto  trajetoIDA = null;
                Trajeto trajetoVinda = null;

                if (km instanceof Double) {
                    trajetoIDA = new Trajeto(transporte,tempo.intValue(),(double)km,precoKmIda);
                }
                if (km instanceof Long){
                    trajetoIDA = new Trajeto(transporte,tempo.intValue(),((Long) km).doubleValue(),precoKmIda);
                }




                touristRoutes.addVertex(cidadeOrigem);
                touristRoutes.addVertex(cidadeDestino);





                if (!horariosIda1.isEmpty()) {
                    LocalTime horarioida1 = LocalTime.parse(horariosIda1);
                    trajetoIDA.addHorario(horarioida1);

                }
                if (!horariosIda2.isEmpty()) {
                    LocalTime horarioida2 = LocalTime.parse(horariosIda2);
                    trajetoIDA.addHorario(horarioida2);

                }

                if (!horariosIda3.isEmpty()) {
                    LocalTime horarioida3 = LocalTime.parse(horariosIda3);
                    trajetoIDA.addHorario(horarioida3);

                }

                touristRoutes.addEdge(cidadeOrigem,cidadeDestino,trajetoIDA);

                double precoKmVinda = (double) rota.get("preco/KmVinda");


                trajetoVinda = new Trajeto(transporte,tempo.intValue(),((Long) km).doubleValue(),precoKmVinda);


                if (!horariosVinda1.isEmpty()) {
                    LocalTime horarioVinda1 = LocalTime.parse(horariosVinda1);
                   trajetoVinda.addHorario(horarioVinda1);

                }
                if (!horariosVinda2.isEmpty()) {
                    LocalTime horarioVinda2 = LocalTime.parse(horariosVinda2);
                    trajetoVinda.addHorario(horarioVinda2);

                }
                if (!horariosVinda3.isEmpty()) {
                    LocalTime horarioVinda3 = LocalTime.parse(horariosVinda3);
                    trajetoVinda.addHorario(horarioVinda3);
                }

                touristRoutes.addEdge(cidadeDestino,cidadeOrigem,trajetoVinda);



            }

            System.out.println(touristRoutes.toString());



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
