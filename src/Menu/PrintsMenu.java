package Menu;

public class PrintsMenu {
    public PrintsMenu(){}

    public void printInicial(){
        System.out.println("\nBIENVENIDO A LA TERMINAL!\nIngrese una opción válida:");
        System.out.println("1- Iniciar sesión.");
        System.out.println("2- Registrarse.");
        System.out.println("0- Salir.");
    }

    public void printMenuUsuario(){
        System.out.println("\nMENÚ DE USUARIO:");
        System.out.println("Ingrese una opción valida:");
        System.out.println("1- Comprar un pasaje.");
        System.out.println("2- Ver viajes DISPONIBLES.");
        System.out.println("3- Ver mis pasajes.");
        System.out.println("4- Ver mis datos");
        System.out.println("5- Editar mis datos");
        System.out.println("6- Boton de arrepentimiento.");
        System.out.println("\n0- Cerrar sesión.");
    }
    public void printModificacionUsuario(){
        System.out.println("\nMENÚ DE MODIFICACIÓN DE DATOS DEL USUARIO.\nIngrese el atributo que desea modificar:");
        System.out.println("1- Nombre.");
        System.out.println("2- Apellido.");
        System.out.println("3- Fecha de nacimiento.");
        System.out.println("4- Dirección.");
        System.out.println("5- Teléfono.");
        System.out.println("\n0- Volver atrás.");
    }

    public void printMenuAdmin(){
        System.out.println("\nMENÚ DE ADMINISTRADOR:\nIngrese una opción válida:");
        System.out.println("1- Gestionar clientes.");
        System.out.println("2- Gestionar choferes.");
        System.out.println("3- Gestionar micros.");
        System.out.println("4- Gestionar viajes.");
        System.out.println("5- Exportar JSON.");
        System.out.println("\n0- Cerrar sesión.");
    }

    public void printGestionClientes(){
        System.out.println("\nGESTIÓN CLIENTES:");
        System.out.println("1- Mostrar clientes.");
        System.out.println("2- Buscar un cliente por DNI.");
        System.out.println("3- Ver boletos cliente.");
        System.out.println("4- Dar de baja un cliente.");
        System.out.println("\n0- Volver al menú principal.");
    }

    public void printGestionChoferes(){
        System.out.println("\nGESTIÓN CHOFERES:");
        System.out.println("1- Mostrar choferes.");
        System.out.println("2- Agregar un chofer.");
        System.out.println("3- Buscar chofer por DNI.");
        System.out.println("4- Modificar un chofer.");
        System.out.println("\n0- Volver al menú principal.");
    }
    public void printModificacionChofer(){
        System.out.println("\nMENÚ DE MODIFICACIÓN DE CHOFER.\nIngrese el atributo que desea modificar:");
        System.out.println("1- Nombre.");
        System.out.println("2- Apellido.");
        System.out.println("3- Fecha de nacimiento.");
        System.out.println("4- Dirección.");
        System.out.println("5- Teléfono.");
        System.out.println("\n0- Volver atrás.");
    }

    public void printGestionMicros(){
        System.out.println("\nGESTIÓN MICROS:");
        System.out.println("1- Mostrar micros.");
        System.out.println("2- Agregar un micro.");
        System.out.println("3- Modificar un micro.");
        System.out.println("\n0- Volver al menú principal.");
    }

    public void printModificacionMicros(){
        System.out.println("\nMENÚ DE MODIFICACIÓN DE MICRO.\nIngrese el atributo que desea modificar:");
        System.out.println("1 - Marca.");
        System.out.println("2 - Modelo.");
        System.out.println("3 - Empresa.");
        System.out.println("\n0- Volver al menú principal.");
    }

    public void printGestionViajes(){
        System.out.println("\nGESTIÓN VIAJES:");
        System.out.println("1- Mostrar viajes.");
        System.out.println("2- Agregar un viaje.");
        System.out.println("\n0- Volver al menú principal.");
    }


}

