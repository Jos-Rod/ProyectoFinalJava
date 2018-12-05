package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Main;
import sample.api.DepartamentoAPI;
import sample.api.UsuarioAPI;
import sample.model.Departamento;
import sample.model.Usuario;

import javax.sound.midi.Soundbank;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmpleadosController  implements Initializable {
    public Main mainapp;
    @FXML
    private Button btnBack;

    @FXML
    private Button btnGra;

    @FXML
    private TableView <Usuario> tblEmpleados;
    @FXML
    private TableColumn <Usuario, String> tblNombre;

    @FXML
    private TableColumn<Usuario, String> tblApellido;

    @FXML TableColumn<Usuario, Integer> tblDep;

    @FXML
    private  Button btnAddEmp;

    @FXML
    private TextField txtEmail;



    @FXML
    private TableColumn<Usuario, String> tblCorreoColumn;


    @FXML
    private void handleBack(){

        mainapp.showPrincipalAdmin();
        Stage stage = (Stage) btnBack.getScene().getWindow();
        // do what you have to do
        stage.close();

    }
    @FXML
    private  void handleAddEmp(){

        mainapp.showAddEmpleado();

        Stage stage = (Stage) btnBack.getScene().getWindow();
        // do what you have to do
        stage.close();

    }
    @FXML
    private void handleGra(){

        mainapp.showGraficaEmpleados();
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();

    }
    public void initialize(URL url, ResourceBundle rb){

        tblNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tblApellido.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        tblDep.setCellValueFactory(new PropertyValueFactory<>("departamentoNombre"));
        tblCorreoColumn.setCellValueFactory(new PropertyValueFactory<>("email"));


    }

    private List<Usuario> listaUsuariosGlobal = new ArrayList<Usuario>();

    public void llenarTable(){

        UsuarioAPI apiUsuario = new UsuarioAPI();
        DepartamentoAPI dapi = new DepartamentoAPI();
        ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();

        List<Usuario> lu = apiUsuario.getAll();
        lu.remove(0);

        listaUsuariosGlobal = lu;
        for (Usuario usuario : lu) {
            usuario.setDepartamentoNombre(dapi.getDepartamento(usuario.getDepartamento()).getName());
        }


        listaUsuarios.addAll(lu);

        tblEmpleados.setItems(listaUsuarios);

        tblEmpleados.setRowFactory(tv -> {
            TableRow<Usuario> rowChida = new TableRow<>();
            rowChida.setOnMouseClicked(e -> prueba(e.getClickCount(), rowChida, tblEmpleados.getSelectionModel().getSelectedItem()));
            return rowChida;
        });

    }

    private void llenarTablaConLista(ObservableList ol) {
        tblEmpleados.getItems().clear();
        tblEmpleados.setItems(ol);
    }

    private void prueba(int numeroClicks, TableRow tr, Usuario usuarioSeleccionado) {
        if (numeroClicks == 2 && (!tr.isEmpty())) {
            //ha dado dos clicks
            //System.out.println("Ha dado dos clicks en: " + usuarioSeleccionado.getNombre());
            mainapp.showEmpleadoSeleccionado(usuarioSeleccionado);
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.close();

        }
    }

    public void filtrarporcorreo(String buscado){

        List<Usuario> usuariosFiltrados = new ArrayList<Usuario>();

        for (Usuario usuario : listaUsuariosGlobal) {

            if (usuario.getEmail().contains(buscado) || usuario.getNombre().contains(buscado)) {
                //correo igual
                usuariosFiltrados.add(usuario);
            } else {
                //no se encuentran resultados

            }

        }

        ObservableList<Usuario> ol = FXCollections.observableArrayList();
        ol.addAll(usuariosFiltrados);

        llenarTablaConLista(ol);

    }

    public void handlekeyprressed (){
        txtEmail.setOnKeyPressed(event -> filtrarporcorreo(txtEmail.getText().trim()));

    }




}
