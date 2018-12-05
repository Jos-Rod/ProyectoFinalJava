package sample.controller;

import com.sun.xml.internal.ws.wsdl.writer.document.StartWithExtensionsType;
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
    private ComboBox cmbStatus;

    @FXML
    private void handleStatus() {

        //ver cual es la seleccion para filtrar
        switch (cmbStatus.getSelectionModel().getSelectedIndex()) {
            case 0:
                //no filtro
                llenarTabla();
                break;
            case 1:
                //filtro pendiente
                llenarTablaConPendiente();
                break;
            case 2:
                //filtro en espera
                llenarTablaConEnEspera();
                break;
            case 3:
                //filtro terminado
                llenarTablaConTerminado();
                break;

                default:
                    //asdf
                    break;
        }

    }

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

        ObservableList<String> ol = FXCollections.observableArrayList();
        ol.add("Ninguno");
        ol.add("Pendiente");
        ol.add("En Proceso");
        ol.add("Terminado");

        cmbStatus.setItems(ol);
        cmbStatus.getSelectionModel().select(0);

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

    public void llenarTablaConPendiente(){
        tblTarea.getItems().clear();
        TareaAPI api = new TareaAPI();
        ObservableList<Tarea> listaTareas = FXCollections.observableArrayList();

        for (Tarea tarea : api.getAllFromUser(mainapp.usuarioGlobal.getIdUser())) {
            if (tarea.getEstado().equals("Pendiente")){
                listaTareas.add(tarea);
            }
        } //aqui llenamos el array y la anadimos a la tabla

        tblTarea.setItems(listaTareas);
    }

    public void llenarTablaConEnEspera(){
        tblTarea.getItems().clear();
        TareaAPI api = new TareaAPI();
        ObservableList<Tarea> listaTareas = FXCollections.observableArrayList();

        for (Tarea tarea : api.getAllFromUser(mainapp.usuarioGlobal.getIdUser())) {
            if (tarea.getEstado().equals("En Proceso")){
                listaTareas.add(tarea);
            }
        } //aqui llenamos el array y la anadimos a la tabla

        tblTarea.setItems(listaTareas);
    }

    public void llenarTablaConTerminado(){
        tblTarea.getItems().clear();
        TareaAPI api = new TareaAPI();
        ObservableList<Tarea> listaTareas = FXCollections.observableArrayList();

        for (Tarea tarea : api.getAllFromUser(mainapp.usuarioGlobal.getIdUser())) {
            if (tarea.getEstado().equals("Terminado")){
                listaTareas.add(tarea);
            }
        } //aqui llenamos el array y la anadimos a la tabla

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
