package sample.dataaccess;

import sample.model.Department;
import sample.datautil.UConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOMySQL implements DepartmentDAO {


    @Override
    public boolean save(Department department) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = UConnection.getConnection();
            String sql = "INSERT INTO Department (Name, Location) VALUES (?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, department.getName());
            ps.setString(2, department.getLocation());
            //El numero             ^ se refiere al primer parametro^ con el signo de interrogacion
            boolean res = ps.execute(); //devuelve un booleano
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean delete(Department department) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = UConnection.getConnection();
            String sql = "DELETE FROM Department WHERE Id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, department.getId());
            //El numero   ^ se refiere al primer parametro^ con el signo de interrogacion
            boolean res = ps.execute(); //devuelve un booleano
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public Department find(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = UConnection.getConnection();
            String sql = "SELECT * FROM Department WHERE Id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            //El numero   ^ se refiere al primer parametro^ con el signo de interrogacion
            rs = ps.executeQuery(); //devuelve un booleano
            Department d = new Department();
            while (rs.next()) { //mientas el result set tenga elementos
                d.setId(rs.getInt("Id"));
                d.setName(rs.getString("Name"));
                d.setLocation(rs.getString("Location"));
            }
            return d;
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

    @Override
    public List<Department> findAll() {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Department> departmentList = new ArrayList<>();

        try {

            conn = UConnection.getConnection();
            String sql = "SELECT * FROM Department";
            ps = conn.prepareStatement(sql);
            //El numero   ^ se refiere al primer parametro^ con el signo de interrogacion
            rs = ps.executeQuery(); //devuelve un booleano

            while (rs.next()) { //mientas el result set tenga elementos

                Department d = new Department();
                d.setId(rs.getInt("Id"));
                d.setName(rs.getString("Name"));
                d.setLocation(rs.getString("Location"));

                departmentList.add(d);
            }
            return departmentList;
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

    @Override
    public boolean update(Department department) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = UConnection.getConnection();
            String sql = "UPDATE Department SET Name=?, Location=? WHERE Id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, department.getName());
            ps.setString(2, department.getLocation());
            ps.setInt(3, department.getId());
            //El numero   ^ se refiere al primer parametro^ con el signo de interrogacion
            boolean res = ps.execute(); //devuelve un booleano
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return false;

    }
}
