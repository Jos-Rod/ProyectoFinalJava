package sample.model;

import java.sql.Date;

public class Employee {

    private int id;
    private String name;
    private int departmentId;
    private Date dob;
    private Date hireDate;

    public Employee() {
    }

    public Employee(int id, String name, int departmentId, Date hire, Date dob, Date hireDate) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.dob = dob;
        this.hireDate = hireDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Departamento Id: %d", name, departmentId);
    }
}
