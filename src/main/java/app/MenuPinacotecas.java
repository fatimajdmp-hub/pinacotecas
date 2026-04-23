package app;

import dao.PinacotecaDaoImpl;
import modelo.Pinacoteca;

import java.util.List;
import java.util.Scanner;

/**
 * @author Juan Francisco Garrido Ariza
 * @version 25.0.1
 * @since 2026.04.23
 */
public class MenuPinacotecas {

    public static void mostrar(Scanner sc, PinacotecaDaoImpl pinacotecaDao) {
        int opcion;
        do {
            System.out.println("=== PINACOTECAS ===");
            System.out.println("1. Dar de alta");
            System.out.println("2. Listar todas");
            System.out.println("3. Buscar por nombre");
            System.out.println("4. Modificar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = Main.leerInt(sc);

            switch (opcion) {
                case 1 -> alta(sc, pinacotecaDao);
                case 2 -> listar(pinacotecaDao);
                case 3 -> buscar(sc, pinacotecaDao);
                case 4 -> modificar(sc, pinacotecaDao);
                case 5 -> eliminar(sc, pinacotecaDao);
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void alta(Scanner sc, PinacotecaDaoImpl pinacotecaDao) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Ciudad: ");
        String ciudad = sc.nextLine().trim();
        System.out.print("Dirección: ");
        String direccion = sc.nextLine().trim();
        System.out.print("Metros cuadrados: ");
        String metros = sc.nextLine().trim();
        pinacotecaDao.darAlta(new Pinacoteca(nombre, ciudad, direccion, metros));
        System.out.println("Pinacoteca registrada!");
    }

    private static void listar(PinacotecaDaoImpl pinacotecaDao) {
        List<Pinacoteca> listaPinacoteca = pinacotecaDao.listarTodos();
        if (listaPinacoteca.isEmpty()) {
            System.out.println("No hay pinacotecas registradas");
        } else {
            System.out.println("=== PINACOTECAS ===");
            for (Pinacoteca p : listaPinacoteca) {
                System.out.println(p);
            }
        }
    }

    private static void buscar(Scanner sc, PinacotecaDaoImpl pinacotecaDao) {
        System.out.print("Nombre a buscar: ");
        String nombre = sc.nextLine();
        Pinacoteca p = pinacotecaDao.buscarPorNombre(nombre);
        if (p == null) {
            System.out.println("No encontrada");
        } else {
            System.out.println(p);
        }
    }

    private static void modificar(Scanner sc, PinacotecaDaoImpl pinacotecaDao) {
        System.out.print("Nombre de la pinacoteca a modificar: ");
        String nombre = sc.nextLine().trim();
        //No se yo si esto se puede cambiar.
        Pinacoteca existente = pinacotecaDao.buscarPorNombre(nombre);
        if (existente == null) {
            System.out.println("No encontrada.");
            return;
        }
        System.out.print("Antigua ciudad: "+ existente.getCiudad() + "Nueva ciudad :");
        String ciudad = sc.nextLine();
        System.out.print("Antigua direccion: "+ existente.getDireccion() +"Nueva dirección :");
        String direccion = sc.nextLine();
        System.out.print("Antiguos metros cuadrados: "+ existente.getMetrosCuadrados() + "Nuevos metros cuadrados: ");
        String metros = sc.nextLine();

        //Esto alomejor se puede refactorizar
        if (!ciudad.isEmpty()) existente.setCiudad(ciudad);
        if (!direccion.isEmpty()) existente.setDireccion(direccion);
        if (!metros.isEmpty()) existente.setMetrosCuadrados(metros);

        pinacotecaDao.actualizar(existente);
        System.out.println("Pinacoteca actualizada!");
    }

    private static void eliminar(Scanner sc, PinacotecaDaoImpl pinacotecaDao) {
        System.out.print("Nombre a eliminar: ");
        String nombre = sc.nextLine();
        System.out.print("¿Seguiro que quieres borrar la pinacoteca: '" + nombre + "'? (s/n): ");
        String decision = sc.nextLine();
        if (decision.equals("s")) {
            pinacotecaDao.eliminar(nombre);
            System.out.println("Pinacoteca eliminada");
        } else {
            System.out.println("Operación cancelada");
        }
    }
}
