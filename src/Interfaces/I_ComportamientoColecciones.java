package Interfaces;

public interface I_ComportamientoColecciones <K,V> {

    boolean agregar(K clave, V nuevo);
    boolean cambiarDeEstado(K dato);
    V consultar (K dato);
    int contar();
    StringBuilder mostrar ();

}
