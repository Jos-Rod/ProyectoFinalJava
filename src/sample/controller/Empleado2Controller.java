package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Main;
import sample.api.DepartamentoAPI;
import sample.api.TareaAPI;
import sample.model.Departamento;
import sample.model.Tarea;
import sample.model.Usuario;

import java.net.URL;
import java.util.ResourceBundle;

public class Empleado2Controller implements Initializable {

    public Main mainapp;

    @FXML
    private Button btnRealizado;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblDepartamento;

    @FXML
    private TableView <Tarea> tblTarea;

    @FXML
    private TableColumn <Tarea, String> tblTareaN;

    @FXML
    private  TableColumn <Tarea, String> tblDescripcion;

    @FXML
    private  TableColumn <Tarea, String> tblStatus;


    @FXML
    private Button btnSalir;

    @FXML
    public void handleSalir() {

        salir();

    }

    private void salir() {
        mainapp.showLogin();
        Stage stage = (Stage) lblNombre.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblTareaN.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tblDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tblStatus.setCellValueFactory(new PropertyValueFactory<>("estado"));
        btnRealizado.setVisible(false);
    }

    @FXML
    public  void handleRealizado(){
        TareaAPI tapi = new TareaAPI();
        Tarea actualizada = new Tarea();

        switch (estado) {
            case 1:
                tareaSeleccionadaActual.setEstado("En Proceso");
                tareaSeleccionadaActual.setFechaDeAsignacion(null);
                actualizada = tapi.actualizarEstado(tareaSeleccionadaActual);

                if (actualizada != null) {
                    //TODO: Alerta de estado actualizado
                    llenarTabla();
                    btnRealizado.setVisible(false);
                } else {
                    //error
                }

                break;
            case 2:
                tareaSeleccionadaActual.setEstado("Terminado");
                tareaSeleccionadaActual.setFechaDeAsignacion(null);
                actualizada = tapi.actualizarEstado(tareaSeleccionadaActual);

                if (actualizada != null) {
                    //TODO: Alerta de estado actualizado
                    llenarTabla();
                    btnRealizado.setVisible(false);
                } else {
                    //error
                }
                break;
                default:
                    //ya esta terminada o no seleccionada

                    break;
        }

    }

    private int estado = 0;

    public void llenarTabla(){
        tblTarea.getItems().clear();
        TareaAPI api = new TareaAPI();
        ObservableList<Tarea> listaTareas = FXCollections.observableArrayList();
        listaTareas.addAll(api.getAllFromUser(mainapp.usuarioGlobal.getIdUser())); //aqui llenamos el array y la anadimos a la tabla

        tblTarea.setItems(listaTareas);
    }

    public void ponerDatos() {
        DepartamentoAPI dapi = new DepartamentoAPI();

        lblNombre.setText(mainapp.usuarioGlobal.getNombre());
        lblDepartamento.setText(dapi.getDepartamento(mainapp.usuarioGlobal.getDepartamento()).getName());

        tblTarea.setRowFactory(tv -> {
            TableRow<Tarea> rowChida = new TableRow<>();
            rowChida.setOnMouseClicked(e -> handleClickTabla(tblTarea.getSelectionModel().getSelectedItem()));
            return rowChida;
        });

    }

    private Tarea tareaSeleccionadaActual;

    public void handleClickTabla(Tarea tareaSeleccionada) {
        tareaSeleccionadaActual = tareaSeleccionada;

        //ver el estado de la tarea
        if (tareaSeleccionada.getEstado().equals("Pendiente")) {
            btnRealizado.setText("En Proceso");
            estado = 1;
            btnRealizado.setVisible(true);
        } else if (tareaSeleccionada.getEstado().equals("En Proceso")) {
            btnRealizado.setText("Terminado");
            estado = 2;
            btnRealizado.setVisible(true);
        } else if (tareaSeleccionada.getEstado().equals("Terminado")) {
            btnRealizado.setVisible(false);
            estado = 3;
        }

    }

}