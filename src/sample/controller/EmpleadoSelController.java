package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Main;
import sample.api.DepartamentoAPI;
import sample.api.UsuarioAPI;
import sample.model.Departamento;
import sample.model.Usuario;

import java.util.ArrayList;
import java.util.List;


public class EmpleadoSelController {

    public Main mainapp;

    public Usuario viendoUsuario;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnTarea;

    @FXML
    private Button btnGra;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtApellido;

    @FXML
    private ComboBox cmbDep;
    @FXML
    private TextField txtPassword;



    @FXML
    private void handleBack(){
        salir();
    }
    private void salir(){
        mainapp.showEmpleados();
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void handleEditar(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        UsuarioAPI uapi = new UsuarioAPI();
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(txtNombre.getText());
        nuevoUsuario.setApellidos(txtApellido.getText());
        nuevoUsuario.setDepartamento(cmbDep.getSelectionModel().getSelectedIndex() + 1);
        nuevoUsuario.setPassword(txtPassword.getText());
        nuevoUsuario.setEmail(txtEmail.getText());
        nuevoUsuario.setIdUser(viendoUsuario.getIdUser());

        if ( txtNombre.getText().trim().equals("") || txtApellido.getText().trim().equals("")  || txtEmail.getText().trim().equals("")){
            // todo: alerta llenar los campos
            alert.setTitle("Error");
            alert.setHeaderText("Campos Vacios");
            alert.setContentText("Llene todos los campos");
            alert.showAndWait();
            salir();

        }
        else
        {
            //TODO : MANDAR ALERTA
            uapi.UpdateUsuario(nuevoUsuario);
            alert.setTitle("Actualizado");
            alert.setHeaderText("Empleado actualizado");
            alert.showAndWait();
            salir();

        }


    }

    @FXML
    private void handleTarea(){

        //mandar a agregar tarea
        mainapp.showTareaView(viendoUsuario);
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void handleGra(){

        mainapp.showGrafica(viendoUsuario);
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();

    }

    //metodo para poner todos los datos al iniciar la ventana
    public void ponerDatos(Usuario usuarioSeleccionado) {

        txtNombre.setText(usuarioSeleccionado.getNombre());
        txtApellido.setText(usuarioSeleccionado.getApellidos());
        txtEmail.setText(usuarioSeleccionado.getEmail());
        txtPassword.setText(usuarioSeleccionado.getPassword());

        //Listas los departamentos pero seleccionar el del usuario
        DepartamentoAPI dapi = new DepartamentoAPI();
        List<Departamento> listaDepartamentos = dapi.getAll();
        ObservableList<String> old = FXCollections.observableArrayList();
        for (Departamento depa : listaDepartamentos) {
            old.add(depa.getName());
        }

        cmbDep.setItems(old);

        //obtener el del usuario actual
        cmbDep.getSelectionModel().select(usuarioSeleccionado.getDepartamento() - 1 );


    }


}
