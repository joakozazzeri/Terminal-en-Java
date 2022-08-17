package Clases_Contenedoras;

import Clases_Generales.Chofer;
import Clases_Generales.Micro;
import Interfaces.*;
import Json.JsonUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapGenerico<String, V extends I_CambiarDeEstado & I_GenerarID & I_ConocerEstado & I_ConseguirClave & I_Getters> implements I_ComportamientoColecciones<String, V> {
    private HashMap<String, V> hashMap;

    public HashMapGenerico() {
        hashMap = new HashMap<>();
    }


    @Override
    public boolean agregar(String clave, V nuevo) {
        boolean fueAgregado = false;

        if (!hashMap.containsKey(clave)) {
            hashMap.put(clave, nuevo);
            fueAgregado = true;

        }

        if (fueAgregado) {
            nuevo.generadorID(contar());
        }

        return fueAgregado;
    }

    @Override
    public boolean cambiarDeEstado(String clave) {
        Iterator<Map.Entry<String, V>> filas = hashMap.entrySet().iterator();
        boolean encontrado = false;

        while (filas.hasNext()) {
            Map.Entry<String, V> unaFila = filas.next();

            if (unaFila.getKey().equals(clave)) {
                unaFila.getValue().cambiarEstado();
                encontrado = true;
            }


        }

        return encontrado;
    }

    @Override
    public V consultar(String clave) {
        Iterator<Map.Entry<String, V>> filas = hashMap.entrySet().iterator();
        boolean encontrado = false;
        V aBuscar = null;

        while (filas.hasNext()) {
            Map.Entry<String, V> unaFila = filas.next();

            if (unaFila.getKey().equals(clave)) {
                aBuscar = unaFila.getValue();
                encontrado = true;
            }
        }

        return aBuscar;
    }

    @Override
    public int contar() {
        return hashMap.size();
    }

    @Override
    public StringBuilder mostrar() {
        Iterator<Map.Entry<String, V>> filas = hashMap.entrySet().iterator();
        StringBuilder string = new StringBuilder();

        while (filas.hasNext()) {
            Map.Entry<String, V> unaFila = filas.next();
            string.append("---------------------------------------------");
            string.append("\n" + unaFila.getValue() + "\n");
            string.append("---------------------------------------------");
        }

        return string;
    }

    public V retornarDisponible() {
        Iterator<Map.Entry<String, V>> filas = hashMap.entrySet().iterator();
        V disponible = null;
        boolean encontrado = false;

        while (filas.hasNext() && !encontrado) {
            Map.Entry<String, V> unaFila = filas.next();

            if (unaFila.getValue().conocerEstado()) {
                disponible = unaFila.getValue();
                encontrado = true;
            }

        }

        return disponible;

    }

    //---Archivos.
    public void cargarArchivo(String nombreArchivo) {
        Iterator<Map.Entry<String, V>> filas = hashMap.entrySet().iterator();
        V aCargar;

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(nombreArchivo.toString());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            while (filas.hasNext()) {
                Map.Entry<String, V> unaFila = filas.next();
                aCargar = unaFila.getValue();
                objectOutputStream.writeObject(aCargar);
            }

            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void leerArchivo(String nombreArchivo) {
        try {
            FileInputStream fileInputStream = new FileInputStream((java.lang.String) nombreArchivo);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            int lectura = 1;

            while (lectura == 1) {
                V aLeer = (V) objectInputStream.readObject();
                hashMap.put((String) aLeer.conseguirClave(), aLeer);
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

    public void exportarJsonChoferes() {

        try {

            Iterator<Map.Entry<String, V>> filas = hashMap.entrySet().iterator();
            V chofer;

            JSONArray arrayChoferes = new JSONArray();


            while (filas.hasNext()) {
                Map.Entry<String, V> unChofer = filas.next();
                chofer = unChofer.getValue();

                JSONObject choferJson = new JSONObject();
                choferJson.put("nombre", chofer.getterNombre());
                choferJson.put("apellido", chofer.getterApellido());
                choferJson.put("dni", chofer.getterDni());
                choferJson.put("fechaDeNacimiento", chofer.getterFechaDeNacimiento());
                choferJson.put("direccion", chofer.getterDireccion());
                choferJson.put("telefono", chofer.getterTelefono());
                choferJson.put("idChofer", chofer.getterIdChofer());
                choferJson.put("disponible", chofer.getterDisponible());
                arrayChoferes.put(choferJson);
            }

            JsonUtiles.grabar(arrayChoferes, "choferes");


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void exportarJsonMicros() {

        try {

            Iterator<Map.Entry<String, V>> filas = hashMap.entrySet().iterator();
            V micro;

            JSONArray arrayMicros = new JSONArray();


            while (filas.hasNext()) {
                Map.Entry<String, V> unMicro = filas.next();
                micro = unMicro.getValue();

                JSONObject microJson = new JSONObject();
                microJson.put("patente", micro.getterPatente());
                microJson.put("marca", micro.getterMarca());
                microJson.put("modelo", micro.getterModelo());
                microJson.put("idMicro", micro.getterIdMicro());

                JSONObject choferJson = new JSONObject();
                Chofer chofer = micro.getterChofer();
                choferJson.put("nombre", chofer.getNombre());
                choferJson.put("apellido", chofer.getApellido());
                choferJson.put("dni", chofer.getDni());
                choferJson.put("fechaDeNacimiento", chofer.getFechaDeNacimiento());
                choferJson.put("direccion", chofer.getDireccion());
                choferJson.put("telefono", chofer.getTelefono());
                choferJson.put("idChofer", chofer.getIdChofer());
                choferJson.put("disponible", chofer.getterDisponible());
                microJson.put("chofer", choferJson);

                microJson.put("capacidadDelMicro", micro.getterCapacidadDelMicro());
                microJson.put("disponible", micro.getterDisponible());
                microJson.put("cocheCama", micro.getterCocheCama());
                microJson.put("servicioDeCatering", micro.getterServicioDeCatering());
                microJson.put("calefaccion", micro.getterCalefaccion());

                arrayMicros.put(microJson);
            }

            JsonUtiles.grabar(arrayMicros, "micros");


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

