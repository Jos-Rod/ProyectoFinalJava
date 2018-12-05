package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.api.DepartamentoAPI;
import sample.api.UsuarioAPI;
import sample.model.Departamento;

import javax.xml.soap.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OlvidoPasswordController implements Initializable {

    public Main mainapp;

    @FXML
    private Button btnRegresar;

    @FXML
    private Label lblPregunta;

    @FXML
    private TextField txtRespuesta;

    @FXML
    private Button btnConfirmar;

    @FXML
    private void handleConfirmar() {
        //mandar a olvido contrasena
        if (txtRespuesta.equals("")) {
            //alert: debes ingresar tu respuesta para poder verificarla
            //TODO: Agregar alerta con mensaje
        } else {
            //confirmar respuesta
            if (txtRespuesta.getText().equals(mainapp.usuarioGlobal.getRespuestaSecreta())) {
                //respuesta correcta,

                UsuarioAPI uapi = new UsuarioAPI();

                if (uapi.mandarCorreo(mainapp.usuarioGlobal)) {
                    //correo mandado

                    //Regresar a login...
                    //TODO: Mandar alerta diciendo que el correo ha sido enviado
                    mainapp.showLogin();
                    Stage stage = (Stage) lblPregunta.getScene().getWindow();
                    // do what you have to do
                    stage.close();

                } else {

                    //correo no mandado
                    //TODO: Alert algo ha salido mal
                }

            } else {
                //respuesta incorrecta
                //TODO: Alert de respuesta incorrecta

            }
        }

    }


    @FXML
    public void initialize(URL url, ResourceBundle rb){ }

    public void ponerDatos() {
        //poner la pregunta secreta
        lblPregunta.setText("¿" + mainapp.usuarioGlobal.getPreguntaSecreta() + "?");
    }

    @FXML
    public void handleRegresar() {
        //regresar a login

    }

}
