package Clases_Generales;

public class MicroDeluxe extends Micro{

    //---Constructores.
    public MicroDeluxe(String patente, String marca, String modelo, String empresa) {
        super(patente, marca, modelo, empresa);
        this.setCapacidadDelMicro(3);
        this.setCocheCama(true);
        this.setCalefaccion(true);
        this.setServicioDeCatering(true);
    }

    //---Metodos.

    @Override
    public String toString() {
        return  "\nEste micro corresponde a la línea premium, es coche-cama: " +
                "\nPosee calefacción y servicio de catering." +
                super.toString();
    }

    public String toStringUsuario() {
        return "\nEste micro corresponde a la línea premium, es coche-cama: " +
                "\nPosee calefacción y servicio de catering." +
                super.toStringUsuario();
    }
}
