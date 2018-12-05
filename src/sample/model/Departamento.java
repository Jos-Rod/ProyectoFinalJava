package sample.model;

public class Departamento {

    private int idDepartamento;
    private String name;
    private String location;

    public Departamento() {
    }

    public Departamento(int idDepartamento, String name, String location) {
        this.idDepartamento = idDepartamento;
        this.name = name;
        this.location = location;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
