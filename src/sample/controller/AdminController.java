package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Main;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    public Main mainapp;

    @FXML
    private Button btnEmpleados;

    @FXML
    private  Button btnDepartamentos;

    @FXML
    private Label lblNombreUsuario;

    @FXML
    private Button btnSalir;

    @FXML
    private void handleSalir() {
        mainapp.showLogin();
        Stage stage = (Stage) lblNombreUsuario.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void handleEmpleados() {

        Stage stage = (Stage) lblNombreUsuario.getScene().getWindow();
        stage.close();

        mainapp.showEmpleados();

    }

    @FXML
    private void handleDepartamentos(){

        Stage stage = (Stage) lblNombreUsuario.getScene().getWindow();
        stage.close();

        mainapp.showDepartamentos();

    }

    public void ponerDataDeMain() {
        lblNombreUsuario.setText(mainapp.usuarioGlobal.getNombre());
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb){



    }


}
