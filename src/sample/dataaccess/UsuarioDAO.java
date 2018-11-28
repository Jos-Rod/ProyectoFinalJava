package sample.dataaccess;

import sample.model.Usuario;

public interface UsuarioDAO {

    Usuario login(Usuario usuario); //devuelve un mensaje, correcto, contrasena incorrecta o no en base de datos

    Usuario getUsuario(int idUsuario);

    boolean saveUsuario(Usuario usuario); //para crear un usuario en base de datos

}
