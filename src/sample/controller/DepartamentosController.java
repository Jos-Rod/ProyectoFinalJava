package sample.controller;

import com.mysql.cj.xdevapi.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Main;
import sample.api.DepartamentoAPI;
import sample.model.Departamento;
import sample.model.Department;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class DepartamentosController implements Initializable {

    public Main mainapp;
    @FXML
    private Button btnAddDep;

    @FXML private Button btnBack;

    @FXML
    private TableView <Departamento>tblDepartamentos;

    @FXML
    private TableColumn<Departamento, String> tblNombre;

    @FXML
    private TableColumn<Departamento, String> tblLocation;

    @FXML
    private void handleBack(){
        mainapp.showPrincipalAdmin();
        Stage stage = (Stage) btnBack.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    private void handleAddDep(){
        mainapp.showAddDepartamento();
        Stage stage = (Stage) btnBack.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    @FXML
    public void initialize(URL url, ResourceBundle rb){

        tblNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblLocation.setCellValueFactory(new PropertyValueFactory<>("location"));


    }


    public void llenarTabla(){
        DepartamentoAPI apiDep = new DepartamentoAPI();
        ObservableList<Departamento> listaDeps = FXCollections.observableArrayList();
        listaDeps.addAll(apiDep.getAll()); //aqui llenamos el array y la anadimos a la tabla



      tblDepartamentos.setItems(listaDeps);
    }

}

