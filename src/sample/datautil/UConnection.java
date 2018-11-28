package sample.datautil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class UConnection {

    private static Connection conn = null; //Un static, se mantiene la misma variable para todos los objetos

    public static Connection getConnection() { //se importo el de sql
        try {

            if (conn == null) { //verificar si la la conexino es null,
                Runtime.getRuntime().addShutdownHook(new MyShDwnHook()); //agregarle un listener, cuando se cierre, se ejecutara lo que hay dentro de esa clase

                ResourceBundle rb = ResourceBundle.getBundle("jdbc");
                String driver = rb.getString("driver");
                String url = rb.getString("url");
                String pwd = rb.getString("pwd");
                String usr = rb.getString("usr");

                Class.forName(driver);
                conn = DriverManager.getConnection(url, usr, pwd);

            }
            return conn;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating connection", e);
        }
    }

    static class MyShDwnHook extends Thread {
        public void run() {
            try {
                Connection conn = UConnection.getConnection();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

}
