package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.api.DepartamentoAPI;
import sample.api.TareaAPI;
import sample.api.UsuarioAPI;
import sample.controller.*;
import sample.model.Departamento;
import sample.model.Tarea;
import sample.model.Usuario;

import java.io.File;
import java.net.URL;
import java.util.List;

public class Main extends Application {

    public Usuario usuarioGlobal = new Usuario();

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
//        LoginController lc =
//        primaryStage.setTitle("Empresa");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();

        URL url = new File("src/sample/view/login.fxml").toURL();

        primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(url);
        AnchorPane root = (AnchorPane)loader.load();
        LoginController controller = (LoginController) loader.getController();
        controller.mainapp = this;
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ASDF");
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();

        UsuarioAPI uapi = new UsuarioAPI();

//        List<Usuario> listaU = uapi.getAll();
//
//        for (Usuario usuario : listaU) {
//            System.out.println("Nombre del usuario: " + usuario.getNombre());
//        }


//        Usuario user = new Usuario();
//        user.setEmail("george.padilla97@gmail.com");
//        user.setPassword("1234");
//        Usuario u = uapi.login(user);
//
//        if (u.getNombre().isEmpty()) {
//            System.out.println("Error en login");
//        } else {
//            System.out.println("Hurra!");
//        }

//        Usuario user = new Usuario();
//        user.setPassword("12345");
//        user.setEmail("luiscoche9@gmail.com");
//        user.setApellidos("Rodriguez");
//        user.setNombre("Jose Luis");
//        user.setDepartamento(1);
//        user.setDob("1998-09-11");
//        user.setDoc("2018-04-13");
//        user.setPreguntaSecreta("Cual es tu sobre nombre");
//        user.setRespuestaSecreta("coche");
//        user.setTipo("Empl");
//
//        Usuario u = uapi.saveUsuario(user);
//        if (u.getNombre().isEmpty()) {
//            System.out.println("Usuario no creado");
//        } else  {
//            System.out.println("Usuario creado hurra!!");
//        }

        DepartamentoAPI dapi = new DepartamentoAPI();
//        List<Departamento> listaDepartamentos = dapi.getAll();
//        for (Departamento depa : listaDepartamentos) {
//            System.out.println("Nombre del departamento: " + depa.getName());
//        }

//        Departamento depa = new Departamento();
//        depa.setName("Paqueteria");
//        depa.setLocation("Piso 9");
//        System.out.println("Departamento insertado: " + dapi.saveDepartamento(depa).getName());


        TareaAPI tapi = new TareaAPI();

//        List<Tarea> listaTareas = tapi.getAll();
//        for (Tarea tarea : listaTareas) {
//            System.out.println("Titulo de tarea: " + tarea.getTitulo());
//        }

//        Tarea t = new Tarea();
//        t.setTitulo("Tomar cheve");
//        t.setDescripcion("Tomar cheve");
//        t.setEstado("Tomar cheve");
//        t.setIdUsuario(1);
//        Tarea tarea = tapi.saveTarea(t);
//        if (tarea.getTitulo().isEmpty()) {
//            System.out.println("No se inserto la tarea");
//        } else {
//            System.out.println("Insertada tarea: " + tarea.getTitulo());
//        }

//        List<Tarea> lt = tapi.getAllFromUser(1);
//        for (Tarea tarea : lt) {
//            System.out.println("Titulo de tarea: " + tarea.getTitulo());
//        }



    }

    public void showPrincipalAdmin() {

        try {

            URL url = new File("src/sample/view/AdminView.fxml").toURL();

            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(url);
            AnchorPane root = (AnchorPane)loader.load();
            AdminController controller = (AdminController) loader.getController();
            controller.mainapp = this;
            controller.ponerDataDeMain();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Vista de Administrador");
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.show();



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showDepartamentos() {

        try {

            URL url = new File("src/sample/view/ViewDepartamentos.fxml").toURL();

            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(url);
            AnchorPane root = (AnchorPane)loader.load();
            DepartamentosController controller = (DepartamentosController) loader.getController();
            controller.mainapp = this;
            controller.llenarTabla();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Vista de Departamentos");
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.show();



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showEmpleados() {

        try {

            URL url = new File("src/sample/view/EmpleadosView.fxml").toURL();

            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(url);
            AnchorPane root = (AnchorPane)loader.load();
            EmpleadosController controller = (EmpleadosController) loader.getController();
            controller.mainapp = this;
            controller.llenarTable();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Vista de Empleados");
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showAddEmpleado() {

        try {

            URL url = new File("src/sample/view/AddEmpleadoView.fxml").toURL();

            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(url);
            AnchorPane root = (AnchorPane)loader.load();
            AddEmpleadoController controller = (AddEmpleadoController) loader.getController();
            controller.mainapp = this;
            controller.llenarFecha();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Agregar empleado");
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showOlvidoPassword(Usuario usuarioConEmail) {
        try {

            URL url = new File("src/sample/view/OlvidoPassword.fxml").toURL();

            UsuarioAPI uapi = new UsuarioAPI();
            this.usuarioGlobal = uapi.getPreguntaYRespuesta(usuarioConEmail);

            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(url);
            AnchorPane root = (AnchorPane)loader.load();
            OlvidoPasswordController controller = (OlvidoPasswordController) loader.getController();
            controller.mainapp = this;
            controller.ponerDatos();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Recuperar contraseña");
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showLogin() {

        try {

            URL url = new File("src/sample/view/login.fxml").toURL();

            this.usuarioGlobal = null;

            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(url);
            AnchorPane root = (AnchorPane)loader.load();
            LoginController controller = (LoginController) loader.getController();
            controller.mainapp = this;
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Login");
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showAddDepartamento() {

        try {

            URL url = new File("src/sample/view/AddDepartamentoView.fxml").toURL();

            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(url);
            AnchorPane root = (AnchorPane)loader.load();
            AddDepartamentoController controller = (AddDepartamentoController) loader.getController();
            controller.mainapp = this;
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Nuevo Departamento");
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showEmpleado2() {

        try {

            URL url = new File("src/sample/view/Empleado2View.fxml").toURL();

            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(url);
            AnchorPane root = (AnchorPane)loader.load();
            Empleado2Controller controller = (Empleado2Controller) loader.getController();
            controller.mainapp = this;
            controller.llenarTabla();
            controller.ponerDatos();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Empleado");
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showEmpleadoSeleccionado(Usuario usuarioSeleccionado) {

        try {

            URL url = new File("src/sample/view/EmpleadoSelView.fxml").toURL();

            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(url);
            AnchorPane root = (AnchorPane)loader.load();
            EmpleadoSelController controller = (EmpleadoSelController) loader.getController();
            controller.mainapp = this;
            controller.ponerDatos(usuarioSeleccionado);
            controller.viendoUsuario = usuarioSeleccionado;
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Empleado");
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showTareaView(Usuario usuarioAgegarTarea) {

        try {

            URL url = new File("src/sample/view/TareaView.fxml").toURL();

            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(url);
            AnchorPane root = (AnchorPane)loader.load();
            TareaController controller = (TareaController) loader.getController();
            controller.mainapp = this;
            controller.usuarioAgregarTarea = usuarioAgegarTarea;
            controller.ponerDatos();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Agregar Tarea a Empleado");
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showGrafica(Usuario usuario) {

        try {

            URL url = new File("src/sample/view/GraficaView.fxml").toURL();

            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(url);
            AnchorPane root = (AnchorPane)loader.load();
            GraficaController controller = (GraficaController) loader.getController();
            controller.mainapp = this;
            controller.usuarioGrafica = usuario;
            controller.ponerGrafica();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gráfica de Empleado");
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showGraficaEmpleados() {

        try {

            URL url = new File("src/sample/view/GraficaEmpleados.fxml").toURL();

            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(url);
            AnchorPane root = (AnchorPane)loader.load();
            GraficaEmpleadosController controller = (GraficaEmpleadosController) loader.getController();
            controller.mainapp = this;
            controller.ponerGrafica();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gráfica de la compañía");
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }

}
