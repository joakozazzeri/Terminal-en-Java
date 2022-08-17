package Clases_Generales;

import Clases_Contenedoras.HashMapGenerico;
import Clases_Contenedoras.HashSetClientes;
import Clases_Contenedoras.ArrayListViajes;
import Excepciones.OpcionInvalida;
import Excepciones.UsuarioPassIncorrecto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Terminal {
    private HashMapGenerico<String, Micro> micros;
    private HashMapGenerico<String, Chofer> choferes;
    private HashSetClientes clientes;
    private ArrayListViajes viajes;
    private Cliente sesionActual;
    private String fechaActual;

    //---Constructor.
    public Terminal(){
        micros = new HashMapGenerico<>();
        choferes = new HashMapGenerico<>();
        clientes = new HashSetClientes();
        viajes = new ArrayListViajes();
    }


    ////---PRIMER MENU: REGISTRO O LOGIN.
    public boolean registro (Cliente nuevoCliente)
    {
        boolean fueAgregado = false;
        fueAgregado = clientes.agregar(nuevoCliente.getDni(), nuevoCliente);

        return fueAgregado;
    }

    public boolean loginAdmin(String mail, String pass) throws UsuarioPassIncorrecto
    {
        boolean loginExitoso = false;
        if (!mail.equals("administrador@terminal.com"))
        {
            if (pass.equals("soyadmin"))
            {
                loginExitoso = true;
            }else {
                throw new UsuarioPassIncorrecto("La contraseña ingresada no pertenece al administrador.");
            }
        }
        else {
            throw new UsuarioPassIncorrecto("El mail ingresado no pertenece al administrador.");
        }

        return loginExitoso;
    }

    public int login(String mail, String pass) throws UsuarioPassIncorrecto
    {
        int loginExitoso = 0; // 0=error 1=user 2=admin

        Cliente buscado = clientes.consultarPorMail(mail);

        if (mail.equals("administrador@terminal.com"))
        {
            if (pass.equals("soyadmin"))
            {
                loginExitoso = 2;
            }
            else
            {
                throw new UsuarioPassIncorrecto("El mail/contraseña ingresados no pertenecen al administrador.");
            }
        }
        else if(buscado!=null)
        {
            if(buscado.getPass().equals(pass))
            {
                sesionActual = buscado;
                loginExitoso = 1;
            }else
            {
                throw new UsuarioPassIncorrecto("El mail/contraseña ingresados no son correctos.");
            }

        }else
        {
            throw new UsuarioPassIncorrecto("El mail/contraseña ingresados no son correctos.");
        }

        return loginExitoso;
    }

    public void obtencionFechaActual()
    {
        Date fechaAConvertir = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        fechaActual = dateFormat.format(fechaAConvertir);
    }

    //////---MÉTODOS USUARIO:

// if el user selecciona comprar viajes, se le ofrecen los disponibles y se le solicita ingresar el id del viaje que quiere comprar.
    public StringBuilder ofrecerViajesAlUsuario()
    {
        StringBuilder string = viajes.mostrarViajesDisponiblesAlUsuario();

        return string;
    }

    public boolean comprarViaje(int idViaje)
    {
        Viaje aComprar;
        boolean fueComprado = false;

        aComprar = viajes.consultar(idViaje);

        if(aComprar!=null)
        {
            aComprar.agregarPasajeroAlViaje(sesionActual);
            fueComprado = true;

            if(!aComprar.comprobarEspacio())
            {
                aComprar.setHayEspacio(false);
            }

            Boleto nuevoBoleto = new Boleto(aComprar, sesionActual, fechaActual);
            sesionActual.agregarBoletoAlArray(nuevoBoleto);

        }
        return fueComprado;
    }

    public StringBuilder mostrarBoletosUsuario()
    {
        StringBuilder string = sesionActual.mostrarArrayBoletos();

        return string;
    }

    public String verDatosUsuario()
    {
        return sesionActual.toString();
    }

    public Cliente retornaSesionActual()
    {
        return sesionActual;
    }

    //////---FIN metodos usuario.

    //////---METODOS ADMINISTRADOR:

    //-ADMINISTRACIÓN DE Clientes
    public StringBuilder mostrarClientes()
    {
        StringBuilder string = new StringBuilder();
        string.append(clientes.mostrar());

        return string;
    }

    public Cliente buscarClientePorDni(String dni)
    {
        Cliente aBuscar = clientes.consultar(dni);

        return aBuscar;
    }

    public StringBuilder verBoletosCliente(String dni)
    {
        StringBuilder string = new StringBuilder();
        Cliente aMostrarBoleto = clientes.consultar(dni);
        if (aMostrarBoleto!=null) {
            string.append(aMostrarBoleto.mostrarArrayBoletos());
        }
        return string;
    }

    public boolean cambiarEstadoCliente(Cliente cliente)
    {
        boolean fueCambiado = clientes.cambiarDeEstado(cliente.getDni());

        return fueCambiado;
    }


    //-FIN MÉTODOS ADM CLIENTES.


    //-MÉTODOS ADM CHOFER.
    public StringBuilder mostrarChoferes()
    {
        StringBuilder string= choferes.mostrar();;

        return string;
    }

    public boolean agregarChofer(Chofer nuevoChofer)
    {
        boolean fueAgregado = choferes.agregar(nuevoChofer.getDni(), nuevoChofer);

        return fueAgregado;
    }

    public Chofer buscarChoferPorDni(String dni){
        Chofer aBuscar = choferes.consultar(dni);

        return aBuscar;
    }

    //-FIN MÉTODOS CHOFER.

    //-MÉTODOS MICRO.
    public boolean agregarMicro(Micro nuevoMicro)
    {
        boolean fueAgregado = false;
        Chofer disponible = choferes.retornarDisponible();

        if(disponible!=null)
        {
            nuevoMicro.setChofer(disponible);
            fueAgregado = micros.agregar(nuevoMicro.getPatente(), nuevoMicro);
        }

        if(fueAgregado)
        {
            disponible.setDisponible(false);
        }

        return fueAgregado;
    }

    public StringBuilder mostrarMicros()
    {
        StringBuilder string = micros.mostrar();

        return string;
    }

    public Micro buscaMicroPorPatente(String patente)
    {
        Micro aBuscar = micros.consultar(patente);

        return aBuscar;
    }


    //-FIN MÉTODOS MICRO.

    //-MÉTODOS VIAJE.
    public boolean agregarViaje(Viaje nuevoViaje)
    {
        boolean fueAgregado = false;
        Micro disponible = micros.retornarDisponible();

        if(disponible!=null)
        {
            nuevoViaje.setMicro(disponible);
            fueAgregado = viajes.agregar(nuevoViaje.getIdViaje(), nuevoViaje);
        }

        if(fueAgregado)
        {
            disponible.setDisponible(false);

            if(disponible instanceof MicroDeluxe)
            {
                nuevoViaje.setPrecio(20*nuevoViaje.getCantidadDeKm());
            }
            else if (disponible instanceof MicroEconomico)
            {
                nuevoViaje.setPrecio(10*nuevoViaje.getCantidadDeKm());
            }
        }

        return fueAgregado;
    }

    public StringBuilder mostrarViajesAdmin()
    {
        StringBuilder string = viajes.mostrar();

        return string;
    }

    public String realizarViajesDelDia()
    {
        return "Se realizaron " + viajes.realizarViajes(fechaActual) + " viajes el dia de hoy.";
    }

    //-FIN MÉTODOS VIAJE.

    //---ARCHIVOS.
    public void cargarArchivoClientes()
    {
        clientes.cargarArchivo();
    }

    public void leerArchivoClientes()
    {
        clientes.leerArchivo();
    }

    public void cargarArchivoViajes()
    {
        viajes.cargarArchivo();
    }

    public void leerArchivoViajes()
    {
        viajes.leerArchivo();
    }

    public void cargarArchivoChoferes()
    {
        String nombreArchivo = "choferes.bin";

        choferes.cargarArchivo(nombreArchivo);
    }

    public void leerArchivoChoferes()
    {
        String nombreArchivo = "choferes.bin";

        choferes.leerArchivo(nombreArchivo);
    }

    public void cargarArchivoMicros()
    {
        String nombreArchivo = "micros.bin";

        micros.cargarArchivo(nombreArchivo);
    }

    public void leerArchivoMicros()
    {
        String nombreArchivo = "micros.bin";

        micros.leerArchivo(nombreArchivo);
    }

    //---JSON.
    public void exportarJsonClientes(){
        clientes.exportarJsonClientes();
    }

    public void exportarJsonViajes(){
        viajes.exportarJsonViajes();
    }

    public void exportarJsonChoferes(){
        choferes.exportarJsonChoferes();
    }

    public void exportarJsonMicros(){
        micros.exportarJsonMicros();
    }
}
