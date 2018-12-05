package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.converter.DateTimeStringConverter;
import sample.Main;
import sample.api.DepartamentoAPI;
import sample.api.UsuarioAPI;
import sample.model.Departamento;
import sample.model.Usuario;

import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddEmpleadoController implements Initializable {

    public Main mainapp;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField txtNombre;

    @FXML
    private  TextField txtApellido;

    @FXML
    private TextField txtEmail;

    @FXML
    private ComboBox cmbDep;

    @FXML
    private TextField txtPreguntaSecreta;

    @FXML
    private TextField txtRespuestaSecreta;

    @FXML
    private DatePicker dateP;

    @FXML
    private PasswordField txtPwd;

    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    private void handleBack(){
        regresar();
    }

    private void regresar() {
        mainapp.showEmpleados();
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    private void handleGuardar() {

        String nombre = txtNombre.getText();
        String apellidos = txtApellido.getText();
        String email = txtEmail.getText();
        String preguntaSecreta = txtPreguntaSecreta.getText();
        String respuestaSecreta = txtRespuestaSecreta.getText();
        String pwd = txtPwd.getText();
        int departamentoId = cmbDep.getSelectionModel().getSelectedIndex() + 1; //se suma uno porque siempre el index empieza en 0
        LocalDate date = dateP.getValue().plusDays(1);

        System.out.println("Fecha: " + date);
        System.out.println("Departamento id: " + departamentoId);
        Date fecha = Date.valueOf(date);

        //Checar si hay campos vacios
        if (nombre.trim().equals("")|| apellidos.trim().equals("") || email.trim().equals("") || preguntaSecreta.trim().equals("")  || respuestaSecreta.trim().equals("")|| pwd.trim().equals("")|| fecha.equals("1900-01-01") || departamentoId<=0) {
            //faltan campos
            System.out.println("Faltan campos para rellenar");

                alert.setHeaderText("Campos sin llenar");
                alert.setContentText("Verifique sus Datos");
                alert.showAndWait();




        } else {


            UsuarioAPI uapi = new UsuarioAPI();
            Usuario u = new Usuario();
            u.setNombre(nombre);
            u.setApellidos(apellidos);
            u.setEmail(email);
            u.setPreguntaSecreta(preguntaSecreta);
            u.setRespuestaSecreta(respuestaSecreta);
            u.setPassword(pwd);
            u.setDepartamento(departamentoId);
            u.setDob(fecha);
            uapi.saveUsuario(u);

            alert.setHeaderText("Guardando Usuarion");
            alert.setContentText("Los datos son correctos");
            alert.showAndWait();



            regresar();

        }


    }

    public void llenarFecha ()
    {
       dateP.setValue(LocalDate.now());
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb){

        //poner los departamentos en el combo box
        DepartamentoAPI dapi = new DepartamentoAPI();
        List<Departamento> listaDepartamentos = dapi.getAll();
        ObservableList<String> ld = FXCollections.observableArrayList();
        for (Departamento d : listaDepartamentos) {
            ld.add(d.getName());
        }
        cmbDep.setItems(ld);

    }



}
