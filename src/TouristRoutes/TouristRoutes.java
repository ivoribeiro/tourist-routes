package TouristRoutes;

import Collections.NonLinear.Network.DiNetworkAdjMatrixTrajeto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
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


        //Intancia da network
        DiNetworkAdjMatrixTrajeto<String> touristRoutes = new DiNetworkAdjMatrixTrajeto<>();

        //parse do ficheiro para objeto json
        Object obj = null;
        try {
            obj = parser.parse(new FileReader("src/routes.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray rotas = (JSONArray) jsonObject.get("rotas");
        Iterator<JSONObject> iterator = rotas.iterator();

        while (iterator.hasNext()) {

            JSONObject rota = iterator.next();
            //Não existem outro tipo de veiculos no problema
            Autocarro transporte = new Autocarro("RodoNorte");
            //armazena os dados obtidos
            String cidadeOrigem = (String) rota.get("CidadeOrigem");
            String cidadeDestino = (String) rota.get("CidadeDestino");
            Double precoKmIda = (Double) rota.get("preco/KmIda");
            Double precoKmVinda = (Double) rota.get("preco/KmVinda");
            String horariosIda1 = (String) rota.get("HoráriosIda1");
            String horariosIda2 = (String) rota.get("HoráriosIda2");
            String horariosIda3 = (String) rota.get("HoráriosIda3");
            String horariosVinda1 = (String) rota.get("HoráriosVinda1");
            String horariosVinda2 = (String) rota.get("HoráriosVinda2");
            String horariosVinda3 = (String) rota.get("HoráriosVinda3");
            Long tempo = (Long) rota.get("Tempo(m)");
            Object km = rota.get("Km");

            //definição dos trajetos de cada linha do json (Porto -> Amarante , Amarante -> Porto)
            Trajeto trajetoIDA = null;
            Trajeto trajetoVinda = null;

            if (km instanceof Double) {
                trajetoIDA = new Trajeto(transporte, tempo.intValue(), (Double) km, precoKmIda);
                trajetoVinda =new Trajeto(transporte, tempo.intValue(), (Double) km, precoKmVinda);
            }
            if (km instanceof Long) {
                trajetoIDA = new Trajeto(transporte, tempo.intValue(), ((Long) km).doubleValue(), precoKmIda);
                trajetoVinda = new Trajeto(transporte, tempo.intValue(), ((Long) km).doubleValue(), precoKmVinda);

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

            touristRoutes.addEdge(cidadeOrigem, cidadeDestino, trajetoIDA);


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

            touristRoutes.addEdge(cidadeDestino, cidadeOrigem, trajetoVinda);


        }

        Trajeto  trajeto1 =new Trajeto(new Autocarro("RodoNorte"),52,67.8,0.1);
        touristRoutes.removeEdge("Porto", "Guimarães",trajeto1);
        System.out.println(touristRoutes.toString());


    }
}

