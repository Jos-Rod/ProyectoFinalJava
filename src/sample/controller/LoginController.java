package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Main;
import sample.api.UsuarioAPI;
import sample.dataaccess.UsuarioDAO;
import sample.dataaccess.UsuarioDAOMySQL;
import sample.model.Usuario;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    UsuarioAPI uapi = new UsuarioAPI();

    public Main mainapp;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnOlvidado;

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private void handleOlvidado() {

        //Checar que este escrito un correo
        if (txtEmail.getText().trim().equals("")) {
            //no tiene correo escrito
            //TODO: Mostrar alerta para ingresar correo
            System.out.println("Favor de ingresar un correo para recuperar tu contraseña");
        } else {
            //si tiene correo

            //mandar a view para responder la pregunta
            Usuario u = new Usuario();
            u.setEmail(txtEmail.getText());
            mainapp.showOlvidoPassword(u);

            Stage stage = (Stage) txtEmail.getScene().getWindow();
            // do what you have to do
            stage.close();

        }

    }



    @FXML
    public void initialize(URL url, ResourceBundle rb){    }

    @FXML
    private void handleEntrar() {
        Usuario u = new Usuario();

        u.setEmail(txtEmail.getText());
        u.setPassword(txtPassword.getText());

        Usuario usuario = uapi.login(u);
        //checar que los campos este llenos
        if (txtEmail.getText().trim().equals("") || txtPassword.getText().trim().equals("")) {
            alert.setHeaderText("Campos sin llenar");
            alert.setContentText("Verifique sus Datos");
            alert.showAndWait();
        } else {

            if (usuario != null)  {

                if (usuario.getIdUser() != 0) {

                    System.out.println("Todo correcto, pasando...");


                    mainapp.usuarioGlobal = usuario;

                    //ver si es usuario administrador o empleado
                    if (usuario.getTipo().equals("Admin")) {
                        //es administrador
                        mainapp.showPrincipalAdmin();

                    } else {
                        //es empleado
                        mainapp.showEmpleado2();
                    }

                    Stage stage = (Stage) txtEmail.getScene().getWindow();
                    // do what you have to do
                    stage.close();

                } else {
                    alert.setHeaderText("Password incorrecto");
                    alert.setContentText("Verifique su correo");
                    alert.showAndWait();
                }

            } else {

                // Mandar alerta al usuario

                alert.setHeaderText("Correo incorrecto");
                alert.setContentText("Verifique su correo");
                alert.showAndWait();

            }

        }

    }



}
