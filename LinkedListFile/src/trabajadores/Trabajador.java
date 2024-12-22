package trabajadores;

import java.io.Serializable;
/*
 * Encabezado del Registro del trabajador:
 * Direccion del registro anterior 8 bytes
 * Direccion del registro siguiente 8 bytes
 * Tamaño del registro 4 bytes
 */
public class Trabajador implements Serializable
{
    public static final long serialVersionUID = 1L;
    private String nombre;
    private String ID;
    private String departamento;
    private double salario;
    
    public Trabajador(String nombre, String iD, String departamento, double salario) {
        setDepartamento(departamento);
        setNombre(nombre);
        setID(iD);
        setSalario(salario);
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    
}
