package sample.dataaccess;

import sample.datautil.UConnection;
import sample.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOMySQL implements EmployeeDAO {

    @Override
    public boolean save(Employee employee) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = UConnection.getConnection();
            String sql = "INSERT INTO Employee (Name, DepartmentId, HireDate, DOB) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getDepartmentId());
            ps.setDate(3, employee.getHireDate());
            ps.setDate(4, employee.getDob());
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
    public boolean delete(Employee employee) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = UConnection.getConnection();
            String sql = "DELETE FROM Employee WHERE Id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, employee.getId());
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
    public Employee find(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null; //Solo cuando sea un select, porque regresara algo
        List<Employee> employeeList = new ArrayList<>();

        try {

            conn = UConnection.getConnection();
            String sql = "SELECT * FROM Employee WHERE Id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            //El numero             ^ se refiere al primer parametro^ con el signo de interrogacion
            rs = ps.executeQuery();
            Employee employee = new Employee();
            while (rs.next()) { //mientas el result set tenga elementos

                employee.setId(rs.getInt("Id"));
                employee.setDepartmentId(rs.getInt("DepartmentId"));
                employee.setDob(rs.getDate("DOB"));
                employee.setName(rs.getString("Name"));
                employee.setHireDate(rs.getDate("HireDate"));

                employeeList.add(employee);
            }
            return employee;
        } catch (Exception e) {
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
    public List<Employee> findByDepartment(int departmentId) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null; //Solo cuando sea un select, porque regresara algo
        List<Employee> employeeList = new ArrayList<>();

        try {

            conn = UConnection.getConnection();
            String sql = "SELECT * FROM Employee WHERE DepartmentId=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, departmentId);
            //El numero             ^ se refiere al primer parametro^ con el signo de interrogacion
            rs = ps.executeQuery();
            while (rs.next()) { //mientas el result set tenga elementos
                Employee employee = new Employee();
                employee.setId(rs.getInt("Id"));
                employee.setDepartmentId(rs.getInt("DepartmentId"));
                employee.setDob(rs.getDate("DOB"));
                employee.setName(rs.getString("Name"));
                employee.setHireDate(rs.getDate("HireDate"));

                employeeList.add(employee);
            }
            return employeeList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return employeeList;
    }

    @Override
    public List<Employee> findAll() {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null; //Solo cuando sea un select, porque regresara algo
        List<Employee> employeeList = new ArrayList<>();

        try {

            conn = UConnection.getConnection();
            String sql = "SELECT * FROM Employee";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) { //mientas el result set tenga elementos
                Employee employee = new Employee();
                employee.setId(rs.getInt("Id"));
                employee.setDepartmentId(rs.getInt("DepartmentId"));
                employee.setDob(rs.getDate("DOB"));
                employee.setName(rs.getString("Name"));
                employee.setHireDate(rs.getDate("HireDate"));

                employeeList.add(employee);
            }
            return employeeList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return employeeList;
    }

    @Override
    public boolean update(Employee employee) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = UConnection.getConnection();
            String sql = "UPDATE Employee SET Name=?, DepartmentId=?, HireDate=?, DOB=? WHERE Id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getDepartmentId());
            ps.setDate(3, employee.getHireDate());
            ps.setDate(4, employee.getDob());
            ps.setInt(5, employee.getId());
            //El numero             ^ se refiere al primer parametro^ con el signo de interrogacion
            int res = ps.executeUpdate(); //devuelve un booleano
            return res==1;
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
