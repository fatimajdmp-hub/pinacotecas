package app;

import dao.*;

import java.util.Scanner;

/**
 * @author Juan Francisco Garrido Ariza
 * @version 25.0.1
 * @since 2026.04.23
 */
public class Main {

    public static void main(String[] args) {

        // Inicializar tablas
        PinacotecaDaoImpl pinacotecaDao = new PinacotecaDaoImpl();
        EscuelaDaoImpl escuelaDao = new EscuelaDaoImpl();
        PintorDaoImpl pintorDao = new PintorDaoImpl();
        CuadradoDaoImpl cuadradoDao = new CuadradoDaoImpl();
        MecenaDaoImpl mecenaDao = new MecenaDaoImpl();

        pinacotecaDao.crearTabla();
        escuelaDao.crearEscuela();
        pintorDao.crearTabla();
        cuadradoDao.crearTabla();
        mecenaDao.crearTabla();

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("===============================");
            System.out.println("   GESTIÓN DE PINACOTECAS      ");
            System.out.println("===============================");
            System.out.println("|  1. Pinacotecas              |");
            System.out.println("|  2. Cuadros                  |");
            System.out.println("|  3. Pintores                 |");
            System.out.println("|  4. Escuelas                 |");
            System.out.println("|  5. Mecenas                  |");
            System.out.println("|  0. Salir                    |");
            System.out.println("================================");
            System.out.print("Opción: ");

            opcion = leerInt(sc);

            switch (opcion) {
                case 1 -> MenuPinacotecas.mostrar(sc, pinacotecaDao);
                case 2 -> MenuCuadro.mostrar(sc, cuadradoDao);
                case 3 -> MenuPintor.mostrar(sc, pintorDao);
                case 4 -> MenuEscuela.mostrar(sc, escuelaDao);
                case 5 -> MenuMecena.mostrar(sc, mecenaDao);
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        sc.close();
    }

    //Este metodo es para no hacer 50 scanner si lo quieres cambiar esta bien
    public static int leerInt(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
