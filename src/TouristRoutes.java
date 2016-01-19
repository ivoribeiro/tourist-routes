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
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(
                    "src/routes.json"));
            
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray rotas = (JSONArray) jsonObject.get("rotas");
            Iterator<JSONObject> iterator = rotas.iterator();
            while (iterator.hasNext()) {
                JSONObject rota = iterator.next();
                String cidadeOrigem = (String) rota.get("CidadeOrigem");
                double precoKmIda = (double) rota.get("preco/KmIda");
                String horariosIda1 = (String) rota.get("HoráriosIda1");
                String horariosIda2 = (String) rota.get("HoráriosIda2");
                String horariosIda3 = (String) rota.get("HoráriosIda3");
                String horariosVinda1 = (String) rota.get("HoráriosVinda1");
                String horariosVinda2 = (String) rota.get("HoráriosVinda2");
                String horariosVinda3 = (String) rota.get("HoráriosVinda3");


                if (!horariosIda1.isEmpty()) {
                    LocalTime horarioida1 = LocalTime.parse(horariosIda1);
                }
                if (!horariosIda2.isEmpty()) {
                    LocalTime horarioida2 = LocalTime.parse(horariosIda2);
                }

                if (!horariosIda3.isEmpty()) {
                    LocalTime horarioida3 = LocalTime.parse(horariosIda3);
                }

                String cidadeDestino = (String) rota.get("CidadeDestino");
                double precoKmVinda = (double) rota.get("preco/KmVinda");
                if (!horariosVinda1.isEmpty()) {
                    LocalTime horarioVinda1 = LocalTime.parse(horariosVinda1);
                }
                if (!horariosVinda2.isEmpty()) {
                    LocalTime horarioVinda2 = LocalTime.parse((String) rota.get("HoráriosVinda2"));
                }
                if (!horariosVinda3.isEmpty()) {
                    LocalTime horarioVinda3 = LocalTime.parse((String) rota.get("HoráriosVinda3"));
                }

                Object km = rota.get("Km");
                if (km instanceof Double) {
                    //System.out.println((Double) km);

                }
                if (km instanceof Long) {
                    //System.out.println((Long) km);
                }

                Long tempo = (Long) rota.get("Tempo(m)");


                System.out.println(rota);

            }

            //System.out.println(jsonObject);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
