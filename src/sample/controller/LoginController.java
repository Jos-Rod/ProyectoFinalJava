package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.dataaccess.UsuarioDAO;
import sample.dataaccess.UsuarioDAOMySQL;
import sample.model.Usuario;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    UsuarioDAO uDAO = new UsuarioDAOMySQL();

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    public void initialize(URL url, ResourceBundle rb){    }

    @FXML
    private void handleEntrar() {
        Usuario u = new Usuario();

        //TODO: Checar que no esten vacios los campos

        u.setEmail(txtEmail.getText());
        u.setPassword(txtPassword.getText());

        Usuario usuario = uDAO.login(u);
        if (!usuario.getNombre().isEmpty()) {
            System.out.println("Todo correcto, pasando...");
        } else {
            //TODO: Mandar alerta al usuario

        }

    }


}
