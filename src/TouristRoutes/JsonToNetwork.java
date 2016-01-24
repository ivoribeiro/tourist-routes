package TouristRoutes;

import Collections.NonLinear.Network.DiNetworkAdjMatrixTrajeto;
import java.io.FileInputStream;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.Iterator;

/**
 * Created by aluno on 1/21/16.
 */
public class JsonToNetwork {
    /**
     * class static that populates the network class with the file data .json
     * @param touristRoutes 
     */
    public static void JsonToNetwork(DiNetworkAdjMatrixTrajeto<String> touristRoutes) {

        JSONParser parser = new JSONParser();

        //parse do ficheiro para objeto json
        Object obj = null;
        try {
            obj = parser.parse(new InputStreamReader(new FileInputStream("src/r.json"), "ISO-8859-1"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = (JSONObject) obj;
        JSONArray rotas = (JSONArray) jsonObject.get("rotas");
        Iterator<JSONObject> iterator = rotas.iterator();

        while (iterator.hasNext()) {
            
            JSONObject rota = iterator.next();
            //Não existem outro tipo de veiculos no problema
            
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
            String Transportadora = (String) rota.get("Transportadora");
            String TipoTransporte = (String) rota.get("Tipo de Transporte");
            Long tempo = (Long) rota.get("Tempo(m)");
            Double km = (Double) rota.get("Km");
            Autocarro transporte = new Autocarro(Transportadora,TipoTransporte);
            //definição dos trajetos de cada linha do json (ex: Porto -> Amarante , Amarante -> Porto)
            Trajeto trajetoIDA = null;
            Trajeto trajetoVinda = null;

            trajetoIDA = new Trajeto(cidadeOrigem, cidadeDestino, transporte, tempo.intValue(), km, precoKmIda);
            trajetoVinda = new Trajeto(cidadeDestino, cidadeOrigem, transporte, tempo.intValue(), km, precoKmVinda);

            touristRoutes.addVertex(cidadeOrigem);
            touristRoutes.addVertex(cidadeDestino);
            
            //converter os dados de string para LocalTime
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
            //adicionar a aresta no sentido ->
            touristRoutes.addEdge(cidadeOrigem, cidadeDestino, trajetoIDA);
            
            //converter os dados de String para LocalTime
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
            
            //adicionar a aresta no sentido <-
            touristRoutes.addEdge(cidadeDestino, cidadeOrigem, trajetoVinda);

        }
    }

}
