package Clases_Abstractas;

import java.io.Serializable;
import java.util.Date;

public abstract class Persona implements Serializable {
    private String nombre;
    private String apellido;
    private String dni;
    private String fechaDeNacimiento;
    private String direccion;
    private String telefono;


    //---Constructores.
    public Persona(){}

    public Persona(String nombre, String apellido, String dni, String fechaDeNacimiento, String direccion, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
    }


    //---Metodos.

    @Override
    public boolean equals(Object obj) {
        boolean esIgual = false;
        if (obj != null) {
            if (obj instanceof Persona) {
                Persona otro = (Persona) obj;
                if (getDni().equals(otro.getDni())) {
                    esIgual = true;
                }
            }
        }

        return esIgual;
    }

    @Override
    public String toString() {
        return  "\nNombre: " + nombre +
                "\nApellido: " + apellido +
                "\nDNI: " + dni +
                "\nFecha de nacimiento: " + fechaDeNacimiento +
                "\nDireccion: " + direccion +
                "\nTelefono: " + telefono;
    }

    public String toStringUsuario() {
        return  "\nNombre: " + nombre +
                "\nApellido: " + apellido +
                "\nDNI: " + dni;
    }

    //---Setter.

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //---Getter.

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }
}
