package sample.dataaccess;

import sample.model.Employee;

import java.util.List;

public interface EmployeeDAO {

    boolean save(Employee employee);

    boolean delete(Employee employee);

    Employee find(int id);

    List<Employee> findByDepartment(int departmentId);

    List<Employee> findAll();

    boolean update(Employee employee);

}
