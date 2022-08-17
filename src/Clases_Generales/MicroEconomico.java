package Clases_Generales;

public class MicroEconomico extends Micro{

    //---Constructores.
    public MicroEconomico(String patente, String marca, String modelo, String empresa) {
        super(patente, marca, modelo, empresa);
        this.setCapacidadDelMicro(32);
        this.setCocheCama(false);
        this.setCalefaccion(false);
        this.setServicioDeCatering(false);
    }

    //---Metodos.

    @Override
    public String toString() {
        return "\nEste micro corresponde a la línea económica: " + super.toString();
    }

    public String toStringUsuario() {
        return "\nEste micro corresponde a la línea económica: " + super.toStringUsuario();
    }


}
