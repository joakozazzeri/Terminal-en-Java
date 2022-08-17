package app;

import Clases_Generales.*;
import Excepciones.OpcionInvalida;
import Excepciones.UsuarioPassIncorrecto;
import Menu.PrintsMenu;
import Menu.FuncionesMenu;

import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    static Scanner teclado;

    public static void main(String[] args) {

        teclado = new Scanner(System.in);

        PrintsMenu menus = new PrintsMenu();
        FuncionesMenu funcionesMenu = new FuncionesMenu();

        Terminal terminalOmnibus = new Terminal();
        terminalOmnibus.obtencionFechaActual();

        terminalOmnibus.leerArchivoChoferes();
        terminalOmnibus.leerArchivoMicros();
        terminalOmnibus.leerArchivoViajes();
        terminalOmnibus.leerArchivoClientes();
        System.out.println(terminalOmnibus.realizarViajesDelDia());


        //---Variables controladoras.
        int opcion = -1;
        char salir = 's';
        //---Variables login.
        String mail = new String();
        String password = new String();
        int sesionIniciada;
        boolean sesionActiva = false;

        boolean registroExitoso = false;


        do {
            sesionIniciada = 0;
            menus.printInicial();
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                    case 1:
                        if (sesionIniciada==0) {
                            sesionIniciada = funcionesMenu.iniciarSesion(teclado, terminalOmnibus, mail, password, sesionIniciada);
                        }
                        if (sesionIniciada==1){//sesion Usuario.
                            sesionActiva = true;
                            while (sesionActiva) {
                                if(!terminalOmnibus.retornaSesionActual().isEstado()){
                                    System.out.println("Su cuenta esta dada de baja, desea activarla nuevamente? ('S'/'N')");
                                    char seguro = teclado.nextLine().toLowerCase(Locale.ROOT).charAt(0);
                                    if(seguro!='s'){
                                        sesionActiva=false;
                                    }else{
                                        terminalOmnibus.retornaSesionActual().setEstado(true);
                                        menus.printMenuUsuario();
                                        sesionActiva = funcionesMenu.gestionUsuario(teclado, terminalOmnibus);
                                    }
                                }
                                else{
                                    menus.printMenuUsuario();
                                    sesionActiva = funcionesMenu.gestionUsuario(teclado, terminalOmnibus);
                                }

                            }
                        }
                        else if (sesionIniciada==2){ //sesion admin.
                            sesionActiva = true;
                            while (sesionActiva) {
                                menus.printMenuAdmin();
                                sesionActiva = funcionesMenu.gestionAdmin(teclado, terminalOmnibus);
                            }
                        }
                    break;
                    case 2:
                        registroExitoso = funcionesMenu.registro(teclado, terminalOmnibus);
                        if (registroExitoso) System.out.println("El registro se realizó correctamente.");
                        else System.out.println("Mail o DNI ya existentes. Verifique los datos ingresados e intente nuevamente.");
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("La opción ingresada no es válida");
            }
            System.out.println("¿Desea salir del programa? De ser así, presione 's', de lo contrario presione cualquier tecla para volver al menú principal.");
            salir = teclado.nextLine().toLowerCase(Locale.ROOT).charAt(0);

        }while (salir != 's') ;

        terminalOmnibus.cargarArchivoChoferes();
        terminalOmnibus.cargarArchivoMicros();
        terminalOmnibus.cargarArchivoViajes();
        terminalOmnibus.cargarArchivoClientes();
    }
}
