package Clases_Generales;

import Clases_Abstractas.Vehiculo;
import Interfaces.*;

public class Micro extends Vehiculo implements I_CambiarDeEstado, I_GenerarID, I_ConocerEstado, I_ConseguirClave, I_Getters {
    //private static int cantidadDeMicros=0;
    private int idMicro;
    private Chofer chofer;
    private String empresa;
    private int capacidadDelMicro;
    private boolean disponible;
    private boolean cocheCama;
    private boolean servicioDeCatering;
    private boolean calefaccion;




    //---Constructores.
    public Micro(String patente, String marca, String modelo, String empresa) {
        super(patente, marca, modelo);
        this.empresa = empresa;

        disponible = true;

        cocheCama = false;
        servicioDeCatering = false;
        calefaccion = false;
    }

    //---Metodos.
    @Override
    public boolean equals(Object obj) {
        boolean esIgual = false;
        if (obj != null) {
            if (obj instanceof Micro) {
                Micro otro = (Micro) obj;
                if (getPatente().equals(otro.getPatente())) {
                    esIgual = true;
                }
            }
        }
        return esIgual;
    }

    @Override
    public String toString() {
        return  "\nEmpresa: " + empresa +
                "\nCapacidad del Micro: " + capacidadDelMicro +
                "\nCoche cama: " + cocheCama +
                "\nServicio de catering: " + servicioDeCatering +
                "\nCalefaccion: " + calefaccion +
                "\nEstado: "+ disponible +
                "\nDatos del chofer: " + chofer.toStringReducido() + super.toString();
    }

    public String toStringUsuario() {
        return  "\nEmpresa: " + empresa;
    }

    public void asignarChofer(Chofer chofer)
    {
        this.setChofer(chofer);
    }

    //---Setter.

    public void setIdMicro(int idMicro) {
        this.idMicro = idMicro;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setCapacidadDelMicro(int capacidadDelMicro) {
        this.capacidadDelMicro = capacidadDelMicro;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setCocheCama(boolean cocheCama) {
        this.cocheCama = cocheCama;
    }

    public void setServicioDeCatering(boolean servicioDeCatering) {
        this.servicioDeCatering = servicioDeCatering;
    }

    public void setCalefaccion(boolean calefaccion) {
        this.calefaccion = calefaccion;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }

    //---Getter.

    public int getIdMicro() {
        return idMicro;
    }

    public Chofer getChofer() {
        return chofer;
    }

    public String getEmpresa() {
        return empresa;
    }

    public int getCapacidadDelMicro() {
        return capacidadDelMicro;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public boolean isCocheCama() {
        return cocheCama;
    }

    public boolean isServicioDeCatering() {
        return servicioDeCatering;
    }

    public boolean isCalefaccion() {
        return calefaccion;
    }


    @Override
    public void cambiarEstado() {
        this.setDisponible(!this.isDisponible());
    }

    @Override
    public void generadorID(int id) {
        this.setIdMicro(id);
    }

    @Override
    public boolean conocerEstado() {
        return this.isDisponible();
    }

    @Override
    public String conseguirClave() {
        return this.getPatente();
    }

    @Override
    public String getterPatente() {
        return this.getPatente();
    }

    @Override
    public String getterMarca() {
        return this.getMarca();
    }

    @Override
    public String getterModelo() {
        return this.getModelo();
    }

    @Override
    public int getterIdMicro() {
        return this.getIdMicro();
    }

    @Override
    public Chofer getterChofer() {
        return this.getChofer();
    }

    @Override
    public String getterEmpresa() {
        return this.getEmpresa();
    }

    @Override
    public int getterCapacidadDelMicro() {
        return this.getCapacidadDelMicro();
    }

    @Override
    public boolean getterCocheCama() {
        return this.isCocheCama();
    }

    @Override
    public boolean getterServicioDeCatering() {
        return this.isServicioDeCatering();
    }

    @Override
    public boolean getterCalefaccion() {
        return this.isCalefaccion();
    }

    @Override
    public boolean getterDisponible() {
        return this.isDisponible();
    }

    @Override
    public String getterNombre() {
        return null;
    }

    @Override
    public String getterApellido() {
        return null;
    }

    @Override
    public String getterDni() {
        return null;
    }

    @Override
    public String getterFechaDeNacimiento() {
        return null;
    }

    @Override
    public String getterDireccion() {
        return null;
    }

    @Override
    public String getterTelefono() {
        return null;
    }

    @Override
    public int getterIdChofer() {
        return 0;
    }
}
