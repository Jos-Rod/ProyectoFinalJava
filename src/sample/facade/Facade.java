package sample.facade;


import sample.model.Employee;
import sample.datautil.UFactory;
import sample.dataaccess.EmployeeDAO;

import java.util.List;

public class Facade { //Intermediario entre todos los datos y la vista. (Se llama desde la vista)

    public List<Employee> findEmployee() {
        EmployeeDAO employeeDAO = (EmployeeDAO)UFactory.getInstance("EmployeeDAO"); //Como le pusimos en factory.properties
        //cuando nosotros le estamos mandando                                   ^^^^^^^, nos debolvera un objecto de tipo EmployeeDAO
        return employeeDAO.findAll();
    }

    public Employee findEmployee(int id) {
        EmployeeDAO employeeDAO = (EmployeeDAO)UFactory.getInstance("EmployeeDAO");
        return employeeDAO.find(id);
    }

    public boolean deleteEmployee(Employee employee) {
        EmployeeDAO employeeDAO = (EmployeeDAO)UFactory.getInstance("EmployeeDAO");
        return employeeDAO.delete(employee);
    }

    public boolean updateEmployee(Employee employee) {
        EmployeeDAO employeeDAO = (EmployeeDAO)UFactory.getInstance("EmployeeDAO");
        return employeeDAO.update(employee);
    }

    public boolean saveEmployee(Employee employee) {
        EmployeeDAO employeeDAO = (EmployeeDAO)UFactory.getInstance("EmployeeDAO");
        return employeeDAO.save(employee);
    }

}
