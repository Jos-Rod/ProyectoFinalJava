package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.dataaccess.DepartmentDAO;
import sample.dataaccess.DepartmentDAOMySQL;
import sample.dataaccess.UsuarioDAO;
import sample.dataaccess.UsuarioDAOMySQL;
import sample.model.Usuario;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Empresa");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

//        DepartmentDAO dDAO = new DepartmentDAOMySQL();
//        System.out.println("Empleados: " + dDAO.findAll().toArray().toString());

        //Pruebas de inicio de sesion
//        UsuarioDAO uDAO = new UsuarioDAOMySQL();
//        Usuario u = new Usuario();
//        u.setEmail("luiscoche9@gmail.com");
//        u.setPassword("coche");
//
//        Usuario usuario = uDAO.login(u);
//        if (usuario.getNombre().isEmpty()) {
//            System.out.println("No se pudo iniciar sesion");
//        } else {
//            System.out.println("Todo correcto, pasando...");
//        }

    }


    public static void main(String[] args) {
        launch(args);
    }

}
