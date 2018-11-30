package sample.dataaccess;

import sample.datautil.UConnection;
import sample.model.Employee;
import sample.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOMySQL implements UsuarioDAO {


    @Override
    public void quechido(Usuario usuario) {

    }

    @Override
    public Usuario login(Usuario usuario) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = UConnection.getConnection();
            //String sql = "INSERT INTO Employee (Name, DepartmentId, HireDate, DOB) VALUES (?, ?, ?, ?)";
            String sql = "SELECT (iduser) FROM usuario WHERE email=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getEmail());
            rs = ps.executeQuery();

            boolean correoExiste = false;

            while (rs.next()) {
                correoExiste = true;
            }

            if (correoExiste) {
                //el correo si existe

                return contrasenaCorrecta(usuario);

            } else {
                //el correo no existe

                System.out.println("No existe el usuario con ese correo");

            }

            //Error, no hay usuario con ese correo

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }

    private Usuario contrasenaCorrecta(Usuario u) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = UConnection.getConnection();
            String sql = "SELECT iduser, nombre, apellidos, email FROM usuario WHERE email=? AND password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getPassword());
            rs = ps.executeQuery();
            Usuario usuario = new Usuario();

            while (rs.next()) { //mientas el result set tenga elementos

                usuario.setIdUser(rs.getInt("iduser"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setEmail(rs.getString("email"));

            }

            return usuario;
        } catch (Exception e) {
            System.out.println("No coinciden las contrasenas, error:");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Usuario getUsuario(int idUsuario) {
        return null;
    }

    @Override
    public boolean saveUsuario(Usuario usuario) {
        return false;
    }
}
