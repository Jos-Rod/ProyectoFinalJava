package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.api.TareaAPI;
import sample.model.Tarea;
import sample.model.Usuario;


import java.util.PriorityQueue;

public class TareaController {

    public Main mainapp;

    public Usuario usuarioAgregarTarea;

    @FXML
    private Label lblNombre;

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnAgregar;

    @FXML
    private void handleBack(){

        salir();

    }

    private void salir() {
        mainapp.showEmpleadoSeleccionado(usuarioAgregarTarea);
        Stage stage = (Stage) lblNombre.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleAgregar(){

        //checar que no esten vacios los campos
        String titulo = txtTitulo.getText();
        String desc = txtDescripcion.getText();

        if (titulo.trim().equals("") || desc.trim().equals("")) {
            //no estan todos los campos llenos
            //TODO: Alert diciendo que los campos no estan llenos

        } else {
            //campos listos, asignar tarea
            TareaAPI tapi = new TareaAPI();
            Tarea t = new Tarea();
            t.setTitulo(titulo);
            t.setIdUsuario(usuarioAgregarTarea.getIdUser());
            t.setDescripcion(desc);

            tapi.saveTarea(t);

            salir();
        }

    }

    public void ponerDatos() {
        //Poner el nombre del empleado a asignar una tarea
        lblNombre.setText(usuarioAgregarTarea.getNombre());
    }


}
