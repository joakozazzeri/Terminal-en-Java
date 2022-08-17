package Clases_Abstractas;

import java.io.Serial;
import java.io.Serializable;

public abstract class Vehiculo implements Serializable {
    private String patente;
    private String marca;
    private String modelo;

    //---Constructores.
    public Vehiculo(String patente, String marca, String modelo) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
    }

    //---Metodos.
    @Override
    public boolean equals(Object obj) {
        boolean esIgual = false;
        if (obj != null) {
            if (obj instanceof Vehiculo) {
                Vehiculo otro = (Vehiculo) obj;
                if (getPatente().equals(otro.getPatente())) {
                    esIgual = true;
                }
            }
        }

        return esIgual;
    }

    @Override
    public String toString() {
        return  "\nPatente: " + patente +
                "\nMarca: " + marca +
                "\nModelo: " + modelo;
    }

    //---Setter.

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }


    //---Getter.

    public String getPatente() {
        return patente;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }
}
