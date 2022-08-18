package Menu;


import Clases_Generales.*;
import Excepciones.UsuarioPassIncorrecto;

import java.util.Locale;
import java.util.Scanner;

public class FuncionesMenu {
    private PrintsMenu menu;

    public FuncionesMenu(){
        menu = new PrintsMenu();
    }


    public boolean registro(Scanner teclado, Terminal terminal)
    {
        boolean exitoso = false;

        System.out.println("MENÚ DE REGISTRO DE USUARIO: ");
        System.out.println("- Ingrese sus datos -");
        System.out.println("1- Nombre:");
        String nombre = teclado.nextLine();
        System.out.println("2- Apellido:");
        String apellido = teclado.nextLine();
        char seguro;
        String dni;
        do {
            System.out.println("-DNI: ");
            dni = teclado.nextLine();
            System.out.println("¿Está seguro de que ese es el DNI?\nRevise los datos teniendo en cuenta que el mismo no podrá ser modificado posteriormente.");
            System.out.println("Si está seguro presione 'S', de lo contrario presione cualquier tecla.");
            seguro = teclado.nextLine().toLowerCase(Locale.ROOT).charAt(0);
        }while (seguro != 's');
        System.out.println("4- Fecha de nacimiento:");
        String fechaNacimiento = teclado.nextLine();
        System.out.println("5- Dirección:");
        String direccion = teclado.nextLine();
        System.out.println("6- Teléfono:");
        String telefono = teclado.nextLine();
        System.out.println("7- Mail:");
        String mail = teclado.nextLine();
        while (!mail.contains("@") || !mail.contains(".")){
            System.out.println("El mail debe contener @ y .: intente nuevamente.");

            mail = teclado.nextLine();

        }
        System.out.println("8- Contraseña: ");
        String pass = teclado.nextLine();

        Cliente nuevo = new Cliente(nombre, apellido, dni, fechaNacimiento, direccion, telefono, mail, pass);

        exitoso = terminal.registro(nuevo);

        return exitoso;
    }

    public int iniciarSesion(Scanner teclado, Terminal terminal, String mail, String pass, int sesionIniciada)
    {
        try {
            System.out.println("Ingrese el mail");

            mail = teclado.nextLine();

            while (!mail.contains("@") || !mail.contains(".")){
                System.out.println("El mail debe contener @ y .: intente nuevamente.");

                mail = teclado.nextLine();

            }
            System.out.println("Ingrese la contraseña:");

            pass = teclado.nextLine();
            sesionIniciada = terminal.login(mail, pass);
        } catch (UsuarioPassIncorrecto e) {
            System.out.println(e.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return sesionIniciada;
    }


    ////////////////////------------GESTION USUARIO------------/////////////////////

    public boolean gestionUsuario(Scanner teclado, Terminal terminal)
    {
        int seleccionUser;

        seleccionUser = teclado.nextInt();
        teclado.nextLine();

        boolean sesionActiva = true;
        Cliente sesionActual = terminal.retornaSesionActual();

        boolean salir = false;


        switch(seleccionUser) {
            case 1:
                if (terminal.ofrecerViajesAlUsuario().isEmpty()) {
                    System.out.println("\n---------------------------------------------");
                    System.out.println("NO HAY VIAJES DISPONIBLES.");
                    System.out.println("---------------------------------------------");

                }
                else {
                    System.out.println(terminal.ofrecerViajesAlUsuario());
                    System.out.println("Ingrese el ID del viaje que desea comprar: ");
                    int idViaje = teclado.nextInt();
                    teclado.nextLine();
                    if (terminal.comprarViaje(idViaje)) System.out.println("El boleto se compró exitosamente.");
                    else System.out.println("Ese ID de viaje no existe. Intente nuevamente");
                }
                break;
            case 2:
                if (terminal.ofrecerViajesAlUsuario().isEmpty()) {
                    System.out.println("\n---------------------------------------------");
                    System.out.println("NO HAY VIAJES DISPONIBLES.");
                    System.out.println("---------------------------------------------");
                }
                else System.out.println(terminal.ofrecerViajesAlUsuario());
                break;
            case 3:
                System.out.println("Lista de boletos que usted posee:");
                if (terminal.mostrarBoletosUsuario().isEmpty()){
                    System.out.println("\n---------------------------------------------");
                    System.out.println("NO POSEE BOLETOS.");
                    System.out.println("---------------------------------------------");
                }
                else System.out.println(terminal.mostrarBoletosUsuario());
                break;
            case 4:
                System.out.println("Sus datos: ");
                System.out.println(terminal.verDatosUsuario());
                break;
            case 5:
                System.out.println("MENÚ DE EDICIÓN DE SUS DATOS: ");
                while (!salir){
                    menu.printModificacionUsuario();
                    salir = modificarUsuario(teclado, terminal);
                }
                break;
            case 6:
                System.out.println("¿Esta seguro que desea darse de baja? ('S'/'N')");
                char seguro = teclado.nextLine().toLowerCase(Locale.ROOT).charAt(0);
                if(seguro=='s'){
                    sesionActual.setEstado(false);
                    System.out.println("Su cuenta se dio de baja exitosamente.");
                    sesionActiva=false;
                }
                break;
            case 0:
                sesionActiva = false;
                break;
            default:
                System.out.println("La opción ingresada no es válida. Intente nuevamente.");
                break;

        }
        return sesionActiva;
    }


    public boolean modificarUsuario(Scanner teclado, Terminal terminal) {
        Cliente sesionActual;
        sesionActual = terminal.retornaSesionActual();

        boolean salir = false;

        int opcion = teclado.nextInt();
        teclado.nextLine();

        switch (opcion){
            case 1:
                System.out.println("Ingrese el nombre:");
                sesionActual.setNombre(teclado.nextLine());
                break;
            case 2:
                System.out.println("Ingrese el apellido:");
                sesionActual.setApellido(teclado.nextLine());
                break;
            case 3:
                System.out.println("Ingrese la fecha de nacimiento:");
                sesionActual.setFechaDeNacimiento(teclado.nextLine());
                break;
            case 4:
                System.out.println("Ingrese la dirección:");
                sesionActual.setDireccion(teclado.nextLine());
                break;
            case 5:
                System.out.println("Ingrese el teléfono.");
                sesionActual.setTelefono(teclado.nextLine());
                break;
            case 0:
                salir = true;
                break;
            default:
                System.out.println("La opción ingresada no es válida. Intente nuevamente.");
                break;
        }

        return salir;

    }
    ////////////////////------------GESTION USUARIO------------/////////////////////



    ////////////////////------------GESTION ADMINISTRADOR------------/////////////////////
    public boolean gestionAdmin(Scanner teclado, Terminal terminal) {

        int seleccionAdmin;

        seleccionAdmin = teclado.nextInt();
        teclado.nextLine();

        boolean sesionActiva = true;

        boolean salir = false;

        switch (seleccionAdmin) {
            case 1:
                while (!salir) {
                    menu.printGestionClientes();
                    salir = gestionAdmClientes(teclado, terminal);
                }
                    break;
            case 2:
                while (!salir){
                    menu.printGestionChoferes();
                    salir = gestionAdmChoferes(teclado, terminal);
                }
                break;
            case 3:
                while (!salir){
                    menu.printGestionMicros();
                    salir = gestionAdmMicros(teclado, terminal);
                }
                break;
            case 4:
                while (!salir) {
                    menu.printGestionViajes();
                    salir = gestionAdmViajes(teclado, terminal);
                }
                break;
            case 5:
                terminal.exportarJsonChoferes();
                terminal.exportarJsonMicros();
                terminal.exportarJsonViajes();
                terminal.exportarJsonClientes();
                System.out.println("Los JSON fueron exportados correctamente.");
                break;
            case 0:
                sesionActiva = false;
                break;
            default:
                System.out.println("La opción ingresada no es válida. Intente nuevamente.");
                break;

        }
        return sesionActiva;
    }


    ///---Gestion de clientes desde el menú administrador.
    public boolean gestionAdmClientes(Scanner teclado, Terminal terminal) {

        String dniBuscado;
        Cliente aux = null;

        boolean salir = false;

        int opcionCase;
        opcionCase = teclado.nextInt();
        teclado.nextLine();

        switch (opcionCase)
        {
            case 1:
                if(terminal.mostrarClientes().isEmpty()) System.out.println("\nNO HAY CLIENTES CARGADOS");
                else System.out.println(terminal.mostrarClientes());
                    break;
            case 2:
                System.out.println("Ingrese el DNI del usuario que busca:");
                aux = retornaClientePorDni(teclado, terminal, aux);
                if (aux==null) System.out.println("No se encontró el cliente.");
                else System.out.println(aux.toString());
                break;
            case 3:
                System.out.println("Ingrese el DNI del usuario que desea conocer los boletos que posee:");
                aux = retornaClientePorDni(teclado, terminal, aux);
                if (aux==null) System.out.println("No se encontró el cliente.");
                else {
                    StringBuilder boletosCliente = terminal.verBoletosCliente(aux.getDni());
                    if (boletosCliente.isEmpty()) System.out.println("El cliente no posee boletos.");
                    else System.out.println(boletosCliente);
                }
                break;
            case 4:
                System.out.println("Ingrese el dni del cliente que desea dar de baja:");
                aux = retornaClientePorDni(teclado, terminal, aux);
                boolean dadoDeBaja = false;
                if(aux!=null){
                    dadoDeBaja = terminal.cambiarEstadoCliente(aux);
                    System.out.println("Cliente dado de baja correctamente.");
                }
                else System.out.println("No se ha podido dar de baja el cliente.");
                break;
            case 0:
                salir = true;
                break;
            default: System.out.println("La opción ingresada no es válida. Intente nuevamente.");
                break;
        }
        return salir;
    }

    public Cliente retornaClientePorDni(Scanner teclado, Terminal terminal, Cliente aux)
    {
        String dniABuscar = teclado.nextLine();
        aux = terminal.buscarClientePorDni(dniABuscar);

        return aux;
    }
    ///---Fin gestion clientes administrador.


    ///---Gestion de choferes.
    public boolean gestionAdmChoferes(Scanner teclado, Terminal terminal)
    {
        boolean salir = false;
        boolean exitModificacion = false;

        Chofer aux = null;

        int opcionCase;
        opcionCase = teclado.nextInt();
        teclado.nextLine();

        switch (opcionCase) {
            case 1:
                System.out.println("Listado de choferes:\n");
                if (terminal.mostrarChoferes().isEmpty()) System.out.println("NO HAY CHOFERES CARGADOS.");
                else System.out.println(terminal.mostrarChoferes());
                break;
            case 2:
                Chofer nuevoChofer = ingresarChoferTeclado(teclado);
                boolean seAgrego = false;
                if (terminal.agregarChofer(nuevoChofer)) {
                    seAgrego = true;
                    System.out.println("Se agregó al chofer correctamente.");
                }
                else {
                    System.out.println("El chofer no pudo ser agregado. Verifique los datos ingresados e intente nuevamente.");
                }
                break;
            case 3:
                System.out.println("Ingrese el DNI del chofer que busca:");
                aux = retornaChoferPorDni(teclado, terminal);
                if (aux == null) System.out.println("No se encontró el chofer.");
                else System.out.println(aux.toString());
                break;
            case 4:
                System.out.println("Ingrese el DNI del chofer que desea modificar.");
                aux = retornaChoferPorDni(teclado, terminal);
                if (aux==null) System.out.println("No se encontró el chofer.");
                else {
                    while (!exitModificacion)
                    {
                        menu.printModificacionChofer();
                        exitModificacion = modificarChofer(teclado, aux);
                    }
                }
                break;
            case 0:
                salir = true;
                break;
            default:
                System.out.println("La opción ingresada no es válida. Intente nuevamente.");
                break;
        }
        return salir;
    }

    public Chofer ingresarChoferTeclado(Scanner teclado)
    {
        Chofer nuevoChofer = new Chofer();
        System.out.println("INGRESE LOS DATOS DEL CHOFER A AGREGAR:");
        System.out.println("- Nombre:");
        nuevoChofer.setNombre(teclado.nextLine());
        System.out.println("- Apellido: ");
        nuevoChofer.setApellido(teclado.nextLine());

        char seguro = 's';

        do {
            System.out.println("-DNI: ");
            nuevoChofer.setDni(teclado.nextLine());
            System.out.println("¿Está seguro de que ese es el DNI?\nRevise los datos teniendo en cuenta que el mismo no podrá ser modificado posteriormente.");
            System.out.println("Si está seguro presione 'S', de lo contrario presione cualquier tecla.");
            seguro = teclado.nextLine().toLowerCase(Locale.ROOT).charAt(0);
        }while (seguro != 's');

        System.out.println("- Fecha de nacimiento: ");
        nuevoChofer.setFechaDeNacimiento(teclado.nextLine());
        System.out.println("- Teléfono: ");
        nuevoChofer.setTelefono(teclado.nextLine());
        System.out.println("- Dirección: ");
        nuevoChofer.setDireccion(teclado.nextLine());
        nuevoChofer.setDisponible(true);

        return nuevoChofer;
    }

    public Chofer retornaChoferPorDni(Scanner teclado, Terminal terminal)
    {
        Chofer aux;

        String dniABuscar = teclado.nextLine();

        aux = terminal.buscarChoferPorDni(dniABuscar);

        return aux;
    }

    public boolean modificarChofer(Scanner teclado, Chofer aux) {
        boolean salir = false;

        int opcion = teclado.nextInt();
        teclado.nextLine();

        switch (opcion){
            case 1:
                System.out.println("Ingrese el nombre:");
                aux.setNombre(teclado.nextLine());
                break;
            case 2:
                System.out.println("Ingrese el apellido:");
                aux.setApellido(teclado.nextLine());
                break;
            case 3:
                System.out.println("Ingrese la fecha de nacimiento:");
                aux.setFechaDeNacimiento(teclado.nextLine());
                break;
            case 4:
                System.out.println("Ingrese la dirección:");
                aux.setDireccion(teclado.nextLine());
                break;
            case 5:
                System.out.println("Ingrese el teléfono.");
                aux.setTelefono(teclado.nextLine());
                break;
            case 0:
                salir = true;
                break;
            default:
                System.out.println("La opción ingresada no es válida. Intente nuevamente.");
                break;
        }

        return salir;

    }
    ///---Fin gestion choferes administrador.


    ///---Gestión de micros.
    public boolean gestionAdmMicros(Scanner teclado, Terminal terminal)
    {
        boolean salir = false;
        boolean exitModificacion = false;

        int opcion;
        opcion = teclado.nextInt();
        teclado.nextLine();

        switch (opcion){
            case 1:
                if(terminal.mostrarMicros().isEmpty()) System.out.println("\nNO HAY MICROS CARGADOS");
                else System.out.println("Listado de micros:\n" + terminal.mostrarMicros());
                break;
            case 2:
                agregarMicro(teclado, terminal);
                break;
            case 3:
                System.out.println("Ingrese la patente del micro que desea modificar: ");
                Micro aux = retornaMicroPorPatente(teclado, terminal);
                if (aux == null) System.out.println("No se encontró el micro. Intente nuevamente.");
                else {
                    while (!exitModificacion) {
                        menu.printModificacionMicros();
                        exitModificacion = modificarMicro(teclado, aux);
                    }
                }
                break;
            case 0:
                salir = true;
                break;
            default: System.out.println("La opción ingresada no es válida. Intente nuevamente.");
                break;
        }
        return salir;
    }


    public Micro retornaMicroPorPatente(Scanner teclado, Terminal terminal)
    {
        String patente = teclado.nextLine();

        Micro aux = terminal.buscaMicroPorPatente(patente);

        return aux;
    }


    public void agregarMicro(Scanner teclado, Terminal terminal)
    {
        System.out.println("Ingrese el tipo de micro que desea agregar:");
        System.out.println("1 - Micro DELUXE.");
        System.out.println("2 - Micro ECONOMICO.");
        int opcion;
        opcion = teclado.nextInt();
        teclado.nextLine();
        switch (opcion){
            case 1:
                MicroDeluxe porAgregar = ingresarMicroDeluxeTeclado(teclado);
                if (terminal.agregarMicro(porAgregar))
                {
                    System.out.println("El micro se agregó correctamente.");
                }
                else System.out.println("No se pudo agregar el micro. Ya es existente o no hay choferes disponibles. Intente nuevamente");
                break;
            case 2:
                MicroEconomico aAgregar = ingresarMicroEconomicoTeclado(teclado);
                if (terminal.agregarMicro(aAgregar))
                {
                    System.out.println("El micro se agregó correctamente.");
                }
                else System.out.println("No se pudo agregar el micro. Ya es existente o no hay choferes disponibles. Intente nuevamente");
                break;
            default: System.out.println("La opción ingresada no es válida. Intente nuevamente.");
                break;
        }
    }

    public MicroDeluxe ingresarMicroDeluxeTeclado(Scanner teclado)
    {
        char seguro = 's';
        String patente;
        do {
            System.out.println("Ingrese la patente: ");
            patente = teclado.nextLine();
            System.out.println("¿Está seguro de que esa es la patente?\nRevise los datos teniendo en cuenta que la misma no podrá ser modificada posteriormente.");
            System.out.println("Si está seguro presione 'S', de lo contrario presione cualquier tecla.");
            seguro = teclado.nextLine().toLowerCase(Locale.ROOT).charAt(0);
        }while (seguro != 's');

        System.out.println("Ingrese la marca: ");
        String marca = teclado.nextLine();
        System.out.println("Ingrese el modelo: ");
        String modelo = teclado.nextLine();
        System.out.println("Ingrese la empresa: ");
        String empresa = teclado.nextLine();

        MicroDeluxe nuevo = new MicroDeluxe(patente, marca, modelo, empresa);

        return nuevo;
    }

    public MicroEconomico ingresarMicroEconomicoTeclado(Scanner teclado)
    {
        char seguro = 's';
        String patente;
        do {
            System.out.println("Ingrese la patente: ");
            patente = teclado.nextLine();
            System.out.println("¿Está seguro de que esa es la patente?\nRevise los datos teniendo en cuenta que la misma no podrá ser modificada posteriormente.");
            System.out.println("Si está seguro presione 'S', de lo contrario presione cualquier tecla.");
            seguro = teclado.nextLine().toLowerCase(Locale.ROOT).charAt(0);
        }while (seguro != 's');

        System.out.println("Ingrese la marca: ");
        String marca = teclado.nextLine();
        System.out.println("Ingrese el modelo: ");
        String modelo = teclado.nextLine();
        System.out.println("Ingrese la empresa: ");
        String empresa = teclado.nextLine();

        MicroEconomico nuevo = new MicroEconomico(patente, marca, modelo, empresa);

        return nuevo;
    }

    public boolean modificarMicro(Scanner teclado, Micro aux)
    {
        boolean salir = false;

        int opcion;
        opcion = teclado.nextInt();
        teclado.nextLine();

        switch (opcion)
        {
            case 1:
                System.out.println("Ingrese la marca:");
                aux.setMarca(teclado.nextLine());
                break;
            case 2:
                System.out.println("Ingrese el modelo:");
                aux.setModelo(teclado.nextLine());
                break;
            case 3:
                System.out.println("Ingrese la empresa:");
                aux.setEmpresa(teclado.nextLine());
                break;
            case 0:
                salir = true;
                break;
            default:
                System.out.println("La opcion ingresada es invalida. Intente nuevamente.");
                break;
        }
        return salir;
    }
    ///---Fin gestión micros administrador.


    ///---Gestión de viajes.
    public boolean gestionAdmViajes(Scanner teclado, Terminal terminal)
    {
        boolean salir = false;
        boolean exitModificacion = false;

        int opcion;
        opcion = teclado.nextInt();
        teclado.nextLine();

        switch (opcion){
            case 1:
                if(terminal.mostrarViajesAdmin().isEmpty()) System.out.println("\nNO HAY VIAJES CARGADOS");
                else System.out.println("Listado de viajes:\n" + terminal.mostrarViajesAdmin());
                break;
            case 2:
                System.out.println("Ingrese la fecha del viaje:");
                String fechaViaje = teclado.nextLine();
                System.out.println("Ingrese el destino del viaje:");
                String destino = teclado.nextLine();
                System.out.println("Ingrese la cantidad de KM que hay hasta el destino:");
                int cantKm = teclado.nextInt();
                teclado.nextLine();
                Viaje nuevoViaje = new Viaje(fechaViaje, destino, cantKm);
                if (terminal.agregarViaje(nuevoViaje)){
                    System.out.println("El viaje se cargó correctamente.");
                }
                else System.out.println("No se pudo agregar el viaje. No hay choferes disponibles. Intente nuevamente");
                break;
            case 0:
                salir = true;
                break;
            default: System.out.println("La opción ingresada no es válida. Intente nuevamente.");
                break;
        }
        return salir;
    }
    ////////////////////------------GESTION ADMINISTRADOR------------/////////////////////


}
