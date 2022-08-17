package Clases_Generales;

import Clases_Abstractas.Persona;
import Interfaces.*;

import java.util.Date;

public class Chofer extends Persona implements I_CambiarDeEstado, I_GenerarID, I_ConocerEstado, I_ConseguirClave, I_Getters {
    //private static int cantidadChoferes = 0;
    private int idChofer;
    private boolean disponible;

    //---Constructores.
    public Chofer(){
        super();
    }

    public Chofer(String nombre, String apellido, String dni, String fechaDeNacimiento, String direccion, String telefono) {
        super(nombre, apellido, dni, fechaDeNacimiento, direccion, telefono);
        disponible = true;

    }

    //---Metodos.


    @Override
    public String toString() {
        return  "\nID de Chofer: " + idChofer +
                "\nEstado: " + disponible +
                super.toString();
    }

    public String toStringReducido() {
        return super.toStringUsuario();
    }





    //---Setter.


    public void setIdChofer(int idChofer) {
        this.idChofer = idChofer;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    //---Getter.

    public int getIdChofer() {
        return idChofer;
    }

    public boolean isDisponible() {
        return disponible;
    }


    @Override
    public void cambiarEstado() {
        this.setDisponible(!this.isDisponible());
    }

    @Override
    public void generadorID(int id) {
        this.setIdChofer(id);
    }

    @Override
    public boolean conocerEstado() {
        return this.isDisponible();
    }

    @Override
    public String conseguirClave() {
        return this.getDni();
    }

    @Override
    public String getterNombre() {
        return this.getNombre();
    }

    @Override
    public String getterApellido() {
        return this.getApellido();
    }

    @Override
    public String getterDni() {
        return this.getDni();
    }

    @Override
    public String getterFechaDeNacimiento() {
        return this.getFechaDeNacimiento();
    }

    @Override
    public String getterDireccion() {
        return this.getDireccion();
    }

    @Override
    public String getterTelefono() {
        return this.getTelefono();
    }

    @Override
    public int getterIdChofer() {
        return this.getIdChofer();
    }

    @Override
    public boolean getterDisponible() {
        return this.isDisponible();
    }

    @Override
    public boolean getterCocheCama() {
        return false;
    }

    @Override
    public boolean getterServicioDeCatering() {
        return false;
    }

    @Override
    public boolean getterCalefaccion() {
        return false;
    }

    @Override
    public String getterPatente() {
        return null;
    }

    @Override
    public String getterMarca() {
        return null;
    }

    @Override
    public String getterModelo() {
        return null;
    }

    @Override
    public int getterIdMicro() {
        return 0;
    }

    @Override
    public Chofer getterChofer() {
        return null;
    }

    @Override
    public String getterEmpresa() {
        return null;
    }

    @Override
    public int getterCapacidadDelMicro() {
        return 0;
    }
}
