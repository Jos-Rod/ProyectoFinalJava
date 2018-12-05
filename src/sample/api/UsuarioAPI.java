package sample.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import sample.model.Usuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class UsuarioAPI {

    private final String baseURL = "http://localhost:8080";

    public List<Usuario> getAll() {

        try {
            int d;
            Gson gson  = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String endpoint = baseURL + "/usuario";
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
            List <Usuario> listaUsuarios = gson.fromJson(response.toString(), new TypeToken<List<Usuario>>() {}.getType());

            return listaUsuarios;


        }
        catch (MalformedURLException e ){
            e.printStackTrace();

        }
        catch (IOException e ){
            e.printStackTrace();

        }
        return null;

    }

    public Usuario login(Usuario u) {

        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String str = baseURL + "/login";
            URL url = new URL(str);

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("content-Type", "application/json");

            String body = gson.toJson(u);
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

            Usuario user = gson.fromJson(response.toString(), Usuario.class); //Cuando lo regresa ya deberia tener el id
            return user;


        }
        catch (MalformedURLException e ){
            e.printStackTrace();

        }
        catch (IOException e ){
            e.printStackTrace();

        }
        return null;

    }

    public Usuario saveUsuario(Usuario u) {

        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String str = baseURL + "/usuario";
            URL url = new URL(str);

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("content-Type", "application/json");

            String body = gson.toJson(u);
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

            Usuario user = gson.fromJson(response.toString(), Usuario.class); //Cuando lo regresa ya deberia tener el id
            return user;


        }
        catch (MalformedURLException e ){
            e.printStackTrace();

        }
        catch (IOException e ){
            e.printStackTrace();

        }
        return null;

    }

    public Usuario getPreguntaYRespuesta(Usuario u) {

        try {
            Gson gson = new Gson();
                    //GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String str = baseURL + "/pyr";
            URL url = new URL(str);

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("content-Type", "application/json");

            String body = gson.toJson(u);
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

            Usuario user = gson.fromJson(response.toString(), Usuario.class); //Cuando lo regresa ya deberia tener el id
            return user;


        }
        catch (MalformedURLException e ){
            e.printStackTrace();

        }
        catch (IOException e ){
            e.printStackTrace();

        }
        return null;

    }

    public boolean mandarCorreo(Usuario u) {

        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String str = baseURL + "/pwdOlvidada";
            URL url = new URL(str);

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("content-Type", "application/json");

            String body = gson.toJson(u);
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

            Boolean user = gson.fromJson(response.toString(), Boolean.class); //Cuando lo regresa ya deberia tener el id

            return user;


        }
        catch (MalformedURLException e ){
            e.printStackTrace();

        }
        catch (IOException e ){
            e.printStackTrace();

        }
        return false;

    }

    public Usuario UpdateUsuario(Usuario u) {

        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            String str = baseURL + "/upadateUsuario";
            URL url = new URL(str);

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("content-Type", "application/json");

            String body = gson.toJson(u);
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

            Usuario user = gson.fromJson(response.toString(), Usuario.class); //Cuando lo regresa ya deberia tener el id
            return user;


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
