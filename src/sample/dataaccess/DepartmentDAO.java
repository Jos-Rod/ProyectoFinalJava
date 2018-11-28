package sample.dataaccess;

import sample.model.Department;

import java.util.List;

public interface DepartmentDAO {

    boolean save(Department department);

    boolean delete(Department department);

    Department find(int id);

    //List<Department> findByDepartment(int departmentId);

    List<Department> findAll();

    boolean update(Department employee);

}
