package app;

import dao.PintorDaoImpl;
import modelo.Pintor;

import java.util.List;
import java.util.Scanner;

/**
 * Clase encargada de gestionar la interfaz de usuario por consola para los Pintores.
 * Permite registrar pintores (con su relación de maestro), consultar y filtrar listados,
 * modificar sus datos y eliminarlos cumpliendo con la restricción de cuadros asociados.
 *
 * @author Hugo Rodriguez Vigueras
 * @version 1.0
 * @since 2026-04-20
 */
public class MenuPintor {

    /**
     * Muestra el menú principal de gestión de pintores y gestiona la navegación
     * del usuario según la opción elegida.
     *
     * @param sc        Objeto Scanner para la lectura de datos desde la consola.
     * @param pintorDao Implementación del DAO para realizar las operaciones en la base de datos.
     */
    public static void mostrar(Scanner sc, PintorDaoImpl pintorDao) {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE PINTORES ---");
            System.out.println("1. Registrar Pintor");
            System.out.println("2. Consultar Listado y Filtrar");
            System.out.println("3. Modificar Pintor");
            System.out.println("4. Eliminar Pintor");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = Main.leerInt(sc);

            switch (opcion) {
                case 1 -> registrar(sc, pintorDao);
                case 2 -> consultar(sc, pintorDao);
                case 3 -> modificar(sc, pintorDao);
                case 4 -> eliminar(sc, pintorDao);
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    /**
     * Solicita al usuario todos los atributos necesarios para registrar un nuevo pintor,
     * permitiendo establecer relaciones opcionales con una escuela o un maestro.
     *
     * @param sc        Objeto Scanner para capturar los datos de entrada.
     * @param pintorDao Objeto DAO para ejecutar la inserción.
     */
    public static void registrar(Scanner sc, PintorDaoImpl pintorDao) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("País: ");
        String pais = sc.nextLine();
        System.out.print("Ciudad de nacimiento: ");
        String ciudad = sc.nextLine();
        System.out.print("Fecha de nacimiento: ");
        String fNac = sc.nextLine();
        System.out.print("Fecha de defunción (vacío si vive): ");
        String fDef = sc.nextLine();
        System.out.print("Nombre de la escuela (vacío si no tiene): ");
        String escuela = sc.nextLine();
        System.out.print("Nombre del maestro (vacío si no tiene): ");
        String maestro = sc.nextLine();

        Pintor p = new Pintor(nombre, pais, ciudad, fNac,
                fDef.isBlank() ? null : fDef,
                escuela.isBlank() ? null : escuela,
                maestro.isBlank() ? null : maestro);

        pintorDao.insertarPintor(p);
    }

    /**
     * Permite consultar el listado de pintores. Ofrece un submenú para aplicar filtros
     * específicos por nombre, país o escuela a la que pertenecen.
     *
     * @param sc        Objeto Scanner para elegir la opción de filtrado.
     * @param pintorDao Objeto DAO para obtener la lista inicial de pintores.
     */
    public static void consultar(Scanner sc, PintorDaoImpl pintorDao) {
        List<Pintor> lista = pintorDao.listarPintor();
        if (lista.isEmpty()) {
            System.out.println("No hay pintores registrados.");
            return;
        }

        System.out.println("1. Ver todos");
        System.out.println("2. Filtrar por nombre");
        System.out.println("3. Filtrar por país");
        System.out.println("4. Filtrar por escuela");
        System.out.print("Selección: ");
        int filtro = Main.leerInt(sc);

        switch (filtro) {
            case 1 -> imprimirLista(lista);
            case 2 -> {
                System.out.print("Nombre a buscar: ");
                String n = sc.nextLine().toLowerCase();
                imprimirLista(lista.stream().filter(p -> p.getNombre().toLowerCase().contains(n)).toList());
            }
            case 3 -> {
                System.out.print("País a buscar: ");
                String p = sc.nextLine().toLowerCase();
                imprimirLista(lista.stream().filter(pintor -> pintor.getPais() != null
                        && pintor.getPais().toLowerCase().contains(p)).toList());
            }
            case 4 -> {
                System.out.print("Escuela a buscar: ");
                String e = sc.nextLine().toLowerCase();
                imprimirLista(lista.stream().filter(pintor -> pintor.getNombre_escuela() != null
                        && pintor.getNombre_escuela().toLowerCase().contains(e)).toList());
            }
            default -> System.out.println("Opción no válida.");
        }
    }

    /**
     * Solicita el nombre de un pintor existente y permite actualizar todos sus datos,
     * incluyendo la modificación de su relación de maestro y su escuela.
     *
     * @param sc        Objeto Scanner para capturar los nuevos datos.
     * @param pintorDao Objeto DAO para ejecutar la actualización.
     */
    public static void modificar(Scanner sc, PintorDaoImpl pintorDao) {
        System.out.print("Nombre del pintor a modificar: ");
        String nombre = sc.nextLine();

        System.out.print("Nuevo país: ");
        String pais = sc.nextLine();
        System.out.print("Nueva ciudad: ");
        String ciudad = sc.nextLine();
        System.out.print("Nueva fecha nacimiento: ");
        String fNac = sc.nextLine();
        System.out.print("Nueva fecha defunción: ");
        String fDef = sc.nextLine();
        System.out.print("Nueva escuela: ");
        String escuela = sc.nextLine();
        System.out.print("Nuevo maestro: ");
        String maestro = sc.nextLine();

        Pintor p = new Pintor(nombre, pais, ciudad, fNac,
                fDef.isBlank() ? null : fDef,
                escuela.isBlank() ? null : escuela,
                maestro.isBlank() ? null : maestro);

        pintorDao.actualizarDatosPintor(p);
    }

    /**
     * Gestiona la eliminación de un pintor. Antes de proceder, verifica estrictamente
     * que el pintor no tenga cuadros asociados en la base de datos.
     *
     * @param sc        Objeto Scanner para capturar el nombre del pintor a borrar.
     * @param pintorDao Objeto DAO para verificar cuadros asociados y ejecutar el borrado.
     */
    public static void eliminar(Scanner sc, PintorDaoImpl pintorDao) {
        System.out.print("Nombre del pintor a eliminar: ");
        String nombre = sc.nextLine();

        if (pintorDao.tieneCuadrosAsociados(nombre)) {
            System.out.println("Error: No se puede eliminar a " + nombre + " porque tiene cuadros asociados.");
        } else {
            pintorDao.eliminarPintor(nombre);
            System.out.println("Pintor eliminado correctamente.");
        }
    }

    /**
     * Método auxiliar privado que formatea e imprime por pantalla una lista de
     * objetos Pintor, mostrando su maestro y escuela si los tienen.
     *
     * @param lista La lista de pintores a mostrar en la consola.
     */
    private static void imprimirLista(List<Pintor> lista) {
        if (lista.isEmpty()) {
            System.out.println("Sin resultados.");
        } else {
            System.out.println("\n--- LISTADO ---");
            lista.forEach(p -> System.out.println("- " + p.getNombre() +
                    " (" + p.getPais() + ") | Escuela: " +
                    (p.getNombre_escuela() != null ? p.getNombre_escuela() : "Ninguna") +
                    " | Maestro: " +
                    (p.getNombre_maestro() != null ? p.getNombre_maestro() : "Ninguno")));
        }
    }

    /**
     * Método auxiliar privado que formatea e imprime por pantalla si un pintor es maestro o no lo es
     *
     * @param sc
     * @param pintorDao
     */
    public static void esMaestro(Scanner sc, PintorDaoImpl pintorDao) {
        System.out.print("Introduce el nombre del pintor para saber si es maestro: ");
        String nombrePintor = sc.nextLine();

        String maestro = pintorDao.isMaestro(nombrePintor) ? "es maestro" : "no es maestro";
        System.out.println(nombrePintor + " " + maestro);
    }
}
