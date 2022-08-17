package Clases_Generales;

import java.io.Serializable;
import java.util.Date;

public class Boleto implements Serializable {
    private static int cantBoletos;
    private int idBoleto;
    private Viaje viaje;
    private Cliente cliente;
    private String fechaCompra;

    //---Constructores.
    public Boleto(Viaje viaje, Cliente cliente, String fechaCompra)
    {
        idBoleto = cantBoletos+1;
        cantBoletos++;

        this.viaje = viaje;
        this.cliente = cliente;
        this.fechaCompra = fechaCompra;
    }

    //---Metodos.

    @Override
    public String toString() {
        return  "\nID de Boleto: " + idBoleto +
                "\nInformacion del viaje: " + viaje.toStringUsuario() +
                "\nInformacion del cliente: " + cliente.toStringUsuario() +
                "\nFecha de compra del pasaje:" + fechaCompra;
    }


    //---Setter.

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public void setFechaActual(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    //---Getter.


    public static int getCantBoletos() {
        return cantBoletos;
    }

    public int getIdBoleto() {
        return idBoleto;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }
}
