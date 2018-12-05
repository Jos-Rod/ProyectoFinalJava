package sample.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import sample.model.Departamento;
import sample.model.Usuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class DepartamentoAPI {

    private final String baseURL = "http://localhost:8080";

    public List<Departamento> getAll() {

        try {
            int d;
            Gson gson  = new Gson();
            String endpoint = baseURL + "/departamento";
            URL url = new URL (endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("contentType", "application/json");

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK)
            {
                throw new RuntimeException ("Failed HTTP error code : " + conn.getResponseCode());

            }

            BufferedReader br = new BufferedReader (new InputStreamReader(conn.getInputStream()));
            StringBuilder response  = new StringBuilder ();
            String output;

            while ((output = br.readLine()) != null ) {
                response.append(output);
            }
            conn.disconnect();
            br.close();
            List <Departamento> listaDepartamentos = gson.fromJson(response.toString(), new TypeToken<List<Departamento>>() {}.getType());

            return listaDepartamentos;


        }
        catch (MalformedURLException e ){
            e.printStackTrace();

        }
        catch (IOException e ){
            e.printStackTrace();

        }
        return null;

    }

    public Departamento saveDepartamento(Departamento departamento) {

        try {
            Gson gson = new Gson();
            String str = baseURL + "/departamento";
            URL url = new URL(str);

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("content-Type", "application/json");

            String body = gson.toJson(departamento);
            OutputStream os = conn.getOutputStream(); //Manda los datos al servicio
            os.write(body.getBytes());
            os.flush(); // asdf

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed: Http error code: " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); //Para leer los datos que nos regreso

            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }

            conn.disconnect();
            br.close();

            Departamento d = gson.fromJson(response.toString(), Departamento.class); //Cuando lo regresa ya deberia tener el id
            return d;


        }
        catch (MalformedURLException e ){
            e.printStackTrace();

        }
        catch (IOException e ){
            e.printStackTrace();

        }
        return null;

    }

    public Departamento getDepartamento(int id) {

        try {
            Gson gson = new Gson();
            String str = baseURL + "/departamento/" + id;
            URL url = new URL(str);

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("content-Type", "application/json");

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed: Http error code: " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); //Para leer los datos que nos regreso

            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }

            conn.disconnect();
            br.close();

            Departamento d = gson.fromJson(response.toString(), Departamento.class); //Cuando lo regresa ya deberia tener el id
            return d;


        }
        catch (MalformedURLException e ){
            e.printStackTrace();

        }
        catch (IOException e ){
            e.printStackTrace();

        }
        return null;

    }

}
