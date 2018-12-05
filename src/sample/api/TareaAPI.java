package sample.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import sample.model.Tarea;
import sample.model.Usuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class TareaAPI {

    private final String baseURL = "http://localhost:8080";

    public List<Tarea> getAll() {

        try {
            int d;
            Gson gson  = new Gson();
            String endpoint = baseURL + "/tarea";
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
            List <Tarea> listaTareas = gson.fromJson(response.toString(), new TypeToken<List<Tarea>>() {}.getType());

            return listaTareas;


        }
        catch (MalformedURLException e ){
            e.printStackTrace();

        }
        catch (IOException e ){
            e.printStackTrace();

        }
        return null;

    }

    public Tarea saveTarea(Tarea t) {

        try {
            Gson gson = new Gson();
            String str = baseURL + "/tarea";
            URL url = new URL(str);

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("content-Type", "application/json");

            String body = gson.toJson(t);
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

            Tarea tarea = gson.fromJson(response.toString(), Tarea.class); //Cuando lo regresa ya deberia tener el id
            return tarea;


        }
        catch (MalformedURLException e ){
            e.printStackTrace();

        }
        catch (IOException e ){
            e.printStackTrace();

        }
        return null;

    }

    public List<Tarea> getAllFromUser(int idUsuario) {

        try {
            int d;
            Gson gson  = new Gson();
            String endpoint = baseURL + "/tarea/" + idUsuario;
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
            List <Tarea> listaTareas = gson.fromJson(response.toString(), new TypeToken<List<Tarea>>() {}.getType());

            return listaTareas;


        }
        catch (MalformedURLException e ){
            e.printStackTrace();

        }
        catch (IOException e ){
            e.printStackTrace();

        }
        return null;

    }

    public Tarea actualizarEstado(Tarea tarea) {

        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String str = baseURL + "/actualizarEstado";
            URL url = new URL(str);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("content-Type", "application/json");

            String body = gson.toJson(tarea);
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

            Tarea t = gson.fromJson(response.toString(), Tarea.class); //Cuando lo regresa ya deberia tener el id

            return t;


        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

}
