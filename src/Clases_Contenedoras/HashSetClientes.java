package Clases_Contenedoras;

import Clases_Generales.Boleto;
import Clases_Generales.Cliente;
import Clases_Generales.Micro;
import Clases_Generales.Viaje;
import Interfaces.I_ComportamientoColecciones;
import Json.JsonUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class HashSetClientes implements I_ComportamientoColecciones<String, Cliente> {
    private HashSet<Cliente> clientes;

    //---Constructor.
    public HashSetClientes() {
        clientes = new HashSet<>();
    }

    //---Alta.

    @Override
    public boolean agregar(String dni, Cliente nuevo) {
        boolean fueAgregado = false;

        fueAgregado = clientes.add(nuevo);

        if (fueAgregado) {
            nuevo.setIdCliente(contar());
        }

        return fueAgregado;
    }

    //---Cambio de estado.
    @Override
    public boolean cambiarDeEstado(String dni) {
        Iterator<Cliente> iterator = clientes.iterator();
        Cliente cliente;
        Cliente clienteEncontrado = null;
        boolean encontrado = false;

        while (iterator.hasNext() && !encontrado) {
            cliente = iterator.next();
            if (cliente.getDni().equals(dni)) {
                clienteEncontrado = cliente;
                clienteEncontrado.setEstado(!clienteEncontrado.isEstado());
                encontrado = true;
            }
        }
        return encontrado;
    }

    //---Consultar.
    @Override
    public Cliente consultar(String dni) {
        Iterator<Cliente> iterator = clientes.iterator();
        Cliente cliente;
        Cliente clienteEncontrado = null;
        boolean encontrado = false;

        while (iterator.hasNext() && !encontrado) {
            cliente = iterator.next();
            if (cliente.getDni().equals(dni)) {
                clienteEncontrado = cliente;
                encontrado = true;
            }
        }
        return clienteEncontrado;
    }

    public Cliente consultarPorMail(String mail) {
        Iterator<Cliente> iterator = clientes.iterator();
        Cliente cliente;
        Cliente clienteEncontrado = null;
        boolean encontrado = false;

        while (iterator.hasNext() && !encontrado) {
            cliente = iterator.next();
            if (cliente.getMail().equals(mail)) {
                clienteEncontrado = cliente;
                encontrado = true;
            }
        }
        return clienteEncontrado;
    }


    //---Contar.
    @Override
    public int contar() {
        return clientes.size();
    }

    //---Mostrar.
    @Override
    public StringBuilder mostrar() {
        Iterator<Cliente> iterator = clientes.iterator();
        Cliente cliente;
        StringBuilder string = new StringBuilder();

        while (iterator.hasNext()) {
            cliente = iterator.next();

            string.append("---------------------------------------------");
            string.append(cliente.toString() + "\n");
            string.append("---------------------------------------------");


        }

        return string;
    }

    //--Archivos.
    public void cargarArchivo() {
        Iterator<Cliente> iterator = clientes.iterator();
        Cliente cliente;

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("clientes.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            while (iterator.hasNext()) {
                cliente = iterator.next();
                objectOutputStream.writeObject(cliente);
            }

            objectOutputStream.close();

        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void leerArchivo() {

        try {
            FileInputStream fileInputStream = new FileInputStream("clientes.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            int lectura = 1;

            while (lectura == 1) {
                Cliente cliente = (Cliente) objectInputStream.readObject();
                clientes.add(cliente);
            }
        } catch (FileNotFoundException f) {
            System.out.println("El archivo no existe.");

        } catch (EOFException e) {
            System.out.println("Fin del archivo");

        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void exportarJsonClientes() {
        try {

            JSONArray arrayClientes = new JSONArray();

            Iterator<Cliente> iterator = clientes.iterator();
            Cliente cliente;

            while (iterator.hasNext()) {
                cliente = iterator.next();


                JSONObject clienteJson = new JSONObject();
                clienteJson.put("nombre", cliente.getNombre());
                clienteJson.put("apellido", cliente.getApellido());
                clienteJson.put("dni", cliente.getDni());
                clienteJson.put("fechaDeNacimiento", cliente.getFechaDeNacimiento());
                clienteJson.put("direccion", cliente.getDireccion());
                clienteJson.put("telefono", cliente.getTelefono());
                clienteJson.put("idCliente", cliente.getIdCliente());
                clienteJson.put("mail", cliente.getMail());
                clienteJson.put("pass", cliente.getPass());
                clienteJson.put("estado", cliente.isEstado());

                ArrayList<Boleto> boletos = cliente.getBoletos();
                JSONArray arrayBoletos = new JSONArray();

                for (Boleto boleto : boletos) {
                    JSONObject boletoJson = new JSONObject();
                    boletoJson.put("idBoleto", boleto.getIdBoleto());
                    JSONObject viajeJson = new JSONObject();
                    Viaje viaje = boleto.getViaje();
                    viajeJson.put("idViaje", viaje.getIdViaje());

                    JSONObject microJson = new JSONObject();
                    Micro micro = viaje.getMicro();
                    microJson.put("idMicro", micro.getIdMicro());
                    microJson.put("patente", micro.getPatente());
                    microJson.put("empresa", micro.getEmpresa());
                    microJson.put("capacidadDelMicro", micro.getCapacidadDelMicro());

                    viajeJson.put("micro", microJson);

                    ArrayList<Cliente> pasajeros = viaje.getPasajeros();
                    JSONArray arrayPasajeros = new JSONArray();
                    for (Cliente pasajero : pasajeros) {
                        JSONObject pasajeroJson = new JSONObject();
                        pasajeroJson.put("dni", pasajero.getDni());
                        arrayPasajeros.put(pasajeroJson);
                    }

                    viajeJson.put("pasajeros", arrayPasajeros);
                    viajeJson.put("fechaDeViaje", viaje.getFechaDeViaje());
                    viajeJson.put("destino", viaje.getDestino());
                    viajeJson.put("precio", viaje.getPrecio());
                    viajeJson.put("estado", viaje.isEstado());
                    viajeJson.put("hayEspacio", viaje.isHayEspacio());

                    boletoJson.put("viaje", viajeJson);
                    boletoJson.put("fechaCompra", boleto.getFechaCompra());
                    arrayBoletos.put(boletoJson);

                }

                clienteJson.put("boletos", arrayBoletos);
                clienteJson.put("estado", cliente.isEstado());

                arrayClientes.put(clienteJson);

                JsonUtiles.grabar(arrayClientes, "clientes");

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
