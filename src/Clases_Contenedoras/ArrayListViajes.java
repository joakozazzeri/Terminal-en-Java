package Clases_Contenedoras;

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
import java.util.LinkedList;

public class ArrayListViajes implements I_ComportamientoColecciones<Integer, Viaje> {
    private ArrayList<Viaje> viajes;

    public ArrayListViajes() {
        viajes = new ArrayList<>();
    }

    @Override
    public boolean agregar(Integer dato, Viaje nuevo) {
        boolean fueAgregado;

        if (viajes.contains(nuevo)) {
            fueAgregado = false;

        } else {
            viajes.add(nuevo);
            fueAgregado = true;
        }

        if (fueAgregado) {
            nuevo.setIdViaje(contar());
        }

        return fueAgregado;
    }


    @Override
    public boolean cambiarDeEstado(Integer dato) {
        Viaje viajeEncontrado = null;
        boolean encontrado = false;


        for (Viaje viaje : viajes) {
            if (dato.equals(viaje.getIdViaje())) {
                viajeEncontrado = viaje;
                viajeEncontrado.setEstado(!viajeEncontrado.isEstado());
                encontrado = true;
            }
        }

        return encontrado;
    }

    @Override
    public Viaje consultar(Integer dato) {
        Viaje aBuscar = null;

        for (Viaje viaje : viajes) {

            if (dato.equals(viaje.getIdViaje()) && viaje.isEstado() && viaje.isHayEspacio()) {

                aBuscar = viaje;

            }

        }

        return aBuscar;
    }

    @Override
    public int contar() {
        return viajes.size();
    }

    @Override
    public StringBuilder mostrar() {
        StringBuilder string = new StringBuilder();

        for (Viaje viaje : viajes) {

            string.append("---------------------------------------------");
            string.append(viaje + "\n");
            string.append("---------------------------------------------");
        }

        return string;
    }

    public StringBuilder mostrarViajesDisponiblesAlUsuario() {
        StringBuilder string = new StringBuilder();

        for (Viaje viaje : viajes) {

            if (viaje.isHayEspacio() && viaje.isEstado()) {
                string.append("---------------------------------------------");
                string.append(viaje.toStringUsuario() + "\n");
                string.append("---------------------------------------------");
            }

        }

        return string;

    }

    public int realizarViajes(String fechaActual)
    {
        int viajesRealizadosEnElDia=0;

        for(Viaje viaje:viajes)
        {
            if(viaje.getFechaDeViaje().equals(fechaActual))
            {
                viaje.setEstado(false);
                viajesRealizadosEnElDia++;

            }
        }

        return viajesRealizadosEnElDia;
    }

    //---Archivo.
    public void cargarArchivo() {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("viajes.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (Viaje viaje: viajes) {
                objectOutputStream.writeObject(viaje);
            }

            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void leerArchivo() {

        try{
            FileInputStream fileInputStream = new FileInputStream("viajes.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            int lectura = 1;

            while(lectura==1){
                Viaje viaje = (Viaje) objectInputStream.readObject();
                viajes.add(viaje);
            }

        }catch (FileNotFoundException f) {
            System.out.println("El archivo no existe.");

        }catch (EOFException e){
            System.out.println("Fin del archivo");

        }catch (IOException e){
            e.printStackTrace();

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void exportarJsonViajes(){

        try {
            JSONArray arrayViajes = new JSONArray();

            for (Viaje viaje:viajes) {
                JSONObject viajeJson = new JSONObject();
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
                for(Cliente pasajero:pasajeros){
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

                arrayViajes.put(viajeJson);

                JsonUtiles.grabar(arrayViajes, "viajes");

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
