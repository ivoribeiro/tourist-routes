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
 * Created by aluno on 1/21/16.
 */
public class JsonToNetwork {

    public static void JsonToNetwork(DiNetworkAdjMatrixTrajeto<String> touristRoutes) {

        JSONParser parser = new JSONParser();

        //parse do ficheiro para objeto json
        Object obj = null;
        try {
            obj = parser.parse(new FileReader("src/routes.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = (JSONObject) obj;
        JSONArray rotas = (JSONArray) jsonObject.get("Rotas");
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
                trajetoIDA = new Trajeto(cidadeOrigem, cidadeDestino, transporte, tempo.intValue(), (Double) km, precoKmIda);
                trajetoVinda = new Trajeto(cidadeDestino, cidadeOrigem, transporte, tempo.intValue(), (Double) km, precoKmVinda);
            }
            if (km instanceof Long) {
                trajetoIDA = new Trajeto(cidadeOrigem, cidadeDestino, transporte, tempo.intValue(), ((Long) km).doubleValue(), precoKmIda);
                trajetoVinda = new Trajeto(cidadeDestino, cidadeOrigem, transporte, tempo.intValue(), ((Long) km).doubleValue(), precoKmVinda);

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
    }

}
