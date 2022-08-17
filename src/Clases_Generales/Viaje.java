package Clases_Generales;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Viaje implements Serializable {

    private int idViaje;
    private Micro micro;
    private ArrayList<Cliente> pasajeros;
    private String fechaDeViaje;
    private String destino;
    private int cantidadDeKm;
    private float precio;
    private boolean estado;
    private boolean hayEspacio;


    //---Constructores.
    public Viaje(String fechaDeViaje, String destino, int cantidadDeKm)
    {
        this.fechaDeViaje = fechaDeViaje;
        this.destino = destino;
        this.cantidadDeKm = cantidadDeKm;
        this.setEstado(true);
        this.setHayEspacio(true);
        pasajeros = new ArrayList<>();
    }

    //---Metodos.

    @Override
    public String toString() {
        return  "\nID de Viaje: " + idViaje +
                "\n\nDatos del Micro: " + micro +
                "\n\nPasajeros a bordo: " + pasajeros +
                "\nFecha de viaje: " + fechaDeViaje +
                "\nDestino: " + destino +
                "\nPrecio: " + precio;
    }

    public String toStringUsuario() {
        return  "\nID de Viaje: " + idViaje +
                "\n\nDatos del Micro: " + micro.toStringUsuario() +
                "\n\nFecha de viaje: " + fechaDeViaje +
                "\nDestino: " + destino +
                "\nPrecio: " + precio;

    }

    public void agregarPasajeroAlViaje(Cliente nuevoPasajero)
    {
        pasajeros.add(nuevoPasajero);
    }

    public boolean comprobarEspacio()
    {
        boolean hayEspacio = true;

        if(pasajeros.size() == micro.getCapacidadDelMicro())
        {
            hayEspacio = false;
        }

        return hayEspacio;
    }



    //---Setter.


    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public void setMicro(Micro micro) {
        this.micro = micro;
    }

    public void setPasajeros(ArrayList<Cliente> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public void setFechaDeViaje(String fechaDeViaje) {
        this.fechaDeViaje = fechaDeViaje;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setHayEspacio(boolean hayEspacio) {
        this.hayEspacio = hayEspacio;
    }

    //---Getter.

    public int getIdViaje() {
        return idViaje;
    }

    public Micro getMicro() {
        return micro;
    }

    public ArrayList<Cliente> getPasajeros() {
        return pasajeros;
    }

    public String getFechaDeViaje() {
        return fechaDeViaje;
    }

    public String getDestino() {
        return destino;
    }

    public float getPrecio() {
        return precio;
    }

    public boolean isEstado() {
        return estado;
    }

    public boolean isHayEspacio() {
        return hayEspacio;
    }

    public int getCantidadDeKm() {
        return cantidadDeKm;
    }
}
