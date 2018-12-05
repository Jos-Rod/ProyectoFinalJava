package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.api.DepartamentoAPI;
import sample.model.Departamento;

public class AddDepartamentoController {

    public Main mainapp;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnAgregar;

    @FXML
    private TextField txtNombreDep;

    @FXML
    private TextField txtLocation;

    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    private void handleBack(){
        mainapp.showDepartamentos();
        Stage stage = (Stage) txtNombreDep.getScene().getWindow();
        stage.close();

    }
    @FXML
    private void handleAgregar(){

        //checar vacios
        if (txtNombreDep.getText().trim().equals("") || txtLocation.getText().trim().equals("")) {
            //TODO: Mandar alerta de que faltan campos por llenar

            alert.setHeaderText("Campos sin llenar");
            alert.setContentText("Verifique sus Datos");
            alert.showAndWait();



        } else {
            //Guardar departamento
            DepartamentoAPI dapi = new DepartamentoAPI();
            Departamento d = new Departamento();
            d.setName(txtNombreDep.getText());
            d.setLocation(txtLocation.getText());
            alert.setHeaderText("Nuevo departamento ");
            alert.setContentText("Departamento " + txtNombreDep.getText() + " guardado");
            alert.showAndWait();
            dapi.saveDepartamento(d);

            mainapp.showDepartamentos();
            Stage stage = (Stage) txtNombreDep.getScene().getWindow();
            stage.close();


        }

    }

}
