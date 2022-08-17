package Clases_Generales;

import Clases_Abstractas.Persona;

import java.util.ArrayList;
import java.util.Date;

public class Cliente extends Persona{
    //private static int cantidadDeClientes = 0;
    private int idCliente;
    private String mail;
    private String pass;
    private ArrayList<Boleto> boletos;
    private boolean estado;

    //---Constructores.
    public Cliente(String nombre, String apellido, String dni, String fechaDeNacimiento, String direccion, String telefono, String mail, String pass) {
        super(nombre, apellido, dni, fechaDeNacimiento, direccion, telefono);
        this.mail = mail;
        this.pass = pass;
        boletos = new ArrayList<>();
        this.estado = true;
        //idCliente = cantidadDeClientes+1;
        //cantidadDeClientes++;
    }

    //---Metodos.
    @Override
    public boolean equals(Object obj) {
        boolean esIgual = false;
        esIgual = super.equals(obj);

        if(!esIgual)
        {
            Cliente otro = (Cliente) obj;
            if (getMail().equals(otro.getMail())) {
                esIgual = true;
            }
        }

        return esIgual;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return  "\nID de Cliente: " + idCliente +
                "\nMail: " + mail + super.toString() +
                "\nEstado: " + estado;
    }

    public String toStringUsuario(){
        return "\nMail: " + mail + super.toStringUsuario();

    }

    public StringBuilder mostrarArrayBoletos()
    {
        StringBuilder string = new StringBuilder();

        for (Boleto boleto:boletos) {
            string.append("---------------------------------------------");
            string.append("\n" + boleto + "\n");
            string.append("---------------------------------------------");
        }

        return string;
    }

    public void agregarBoletoAlArray(Boleto nuevoBoleto)
    {
        boletos.add(nuevoBoleto);
    }

    //---Setter.


    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    //---Getter.

    public int getIdCliente() {
        return idCliente;
    }

    public String getMail() {
        return mail;
    }

    public String getPass() {
        return pass;
    }

    public boolean isEstado() {
        return estado;
    }

    public ArrayList<Boleto> getBoletos() {
        return boletos;
    }
}
