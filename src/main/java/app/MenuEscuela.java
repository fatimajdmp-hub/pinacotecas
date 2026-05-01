package app;

import dao.CuadradoDaoImpl;
import dao.EscuelaDaoImpl;
import modelo.Escuela;

import java.util.List;
import java.util.Scanner;

/**
 * Clase encargada de gestionar la interfaz de usuario por consola para las Escuelas.
 * Proporciona un menú interactivo para realizar operaciones de registrar, consultar,
 * modificar y eliminar
 *
 * @author Hugo Rodríguez Vigueras
 * @version 1.0
 * @since 2026-04-20
 */
public class MenuEscuela {
    /**
     * Muestra el menú principal de gestión de escuelas y gestiona la navegación
     * del usuario según la opción elegida.
     *
     * @param sc         Objeto Scanner para la lectura de datos desde la consola.
     * @param escuelaDao Implementación del DAO para realizar las operaciones en la base de datos.
     */
    public static void mostrar(Scanner sc, EscuelaDaoImpl escuelaDao) {
        int opcion;
        do {
            System.out.println("\n--- ESCUELA ---");
            System.out.println("1. Registrar Escuela");
            System.out.println("2. Consultar Listado");
            System.out.println("3. Modificar escuela");
            System.out.println("4. Eliminar escuela");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = Main.leerInt(sc);

            switch (opcion) {
                case 1 -> registrar(sc, escuelaDao);
                case 2 -> consultar(sc, escuelaDao);
                case 3 -> modificar(sc, escuelaDao);
                case 4 -> eliminar(sc, escuelaDao);
                case 0 -> System.out.println("Saliendo... ");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    /**
     * Solicita al usuario los datos necesarios para registrar una nueva escuela
     * (nombre, país y fecha) y la inserta en la base de datos.
     *
     * @param sc         Objeto Scanner para capturar los datos de entrada.
     * @param escuelaDao Objeto DAO para ejecutar la inserción.
     */
    public static void registrar(Scanner sc, EscuelaDaoImpl escuelaDao) {
        System.out.println("Indica su nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Indica su pais: ");
        String pais = sc.nextLine();
        System.out.println("Indica su fecha de aparicion: ");
        String fecha = sc.nextLine();
        escuelaDao.insertarEscuela(new Escuela(nombre, pais, fecha));
        System.out.println("Escuela registrada exitosamente.");
    }

    /**
     * Permite consultar el listado de escuelas registradas. Ofrece un submenú
     * para visualizar todas las escuelas o aplicar filtros por nombre o país.
     *
     * @param sc         Objeto Scanner para elegir la opción de filtrado.
     * @param escuelaDao Objeto DAO para obtener la lista inicial de escuelas.
     */
    public static void consultar(Scanner sc, EscuelaDaoImpl escuelaDao) {
        List<Escuela> escuelas = escuelaDao.listarEscuelas();

        if (escuelas.isEmpty()) {
            System.out.println("No hay Escuelas registradas en la base de datos.");
            return;
        }

        System.out.println("¿Qué deseas hacer?");
        System.out.println("1. Ver todas las escuelas");
        System.out.println("2. Filtrar por nombre");
        System.out.println("3. Filtrar por país");
        System.out.print("Opción: ");
        int filtroOpc = Main.leerInt(sc);

        switch (filtroOpc) {
            case 1 -> imprimirLista(escuelas); // Muestra todas
            case 2 -> {
                System.out.print("Introduce el nombre a buscar: ");
                String filtroNombre = sc.nextLine().toLowerCase();
                // Filtramos la lista usando stream
                List<Escuela> filtradas = escuelas.stream()
                        .filter(e -> e.getNombre().toLowerCase().contains(filtroNombre))
                        .toList();
                imprimirLista(filtradas);
            }
            case 3 -> {
                System.out.print("Introduce el país a buscar: ");
                String filtroPais = sc.nextLine().toLowerCase();
                List<Escuela> filtradas = escuelas.stream()
                        .filter(e -> e.getPaisAparicion().toLowerCase().contains(filtroPais))
                        .toList();
                imprimirLista(filtradas);
            }
            default -> System.out.println("Opción de filtro no válida. Volviendo al menú...");
        }
    }

    /**
     * Método auxiliar privado que formatea e imprime por pantalla una lista de
     * objetos Escuela.
     *
     * @param lista La lista de escuelas a mostrar en la consola.
     */
    public static void imprimirLista(List<Escuela> lista) {
        if (lista.isEmpty()) {
            System.out.println("No se encontraron escuelas que coincidan.");
        } else {
            System.out.println("\n--- LISTADO ---");
            for (Escuela e : lista) {
                System.out.println(e.toString());
            }
        }
    }

    /**
     * Solicita el nombre de una escuela existente y permite actualizar su país
     * y fecha de aparición en la base de datos.
     *
     * @param sc         Objeto Scanner para capturar los nuevos datos.
     * @param escuelaDao Objeto DAO para ejecutar la actualización.
     */
    public static void modificar(Scanner sc, EscuelaDaoImpl escuelaDao) {
        System.out.println("Indica su nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Actualiza su pais: ");
        String pais = sc.nextLine();
        System.out.println("Actualiza su fecha de aparicion: ");
        String fecha = sc.nextLine();

        escuelaDao.actualizarEscuela(new Escuela(nombre, pais, fecha));

    }

    /**
     * Gestiona la eliminación de una escuela. Antes de proceder, verifica si la
     * escuela tiene pintores asociados para cumplir con las restricciones de
     * integridad referencial.
     *
     * @param sc         Objeto Scanner para capturar el nombre de la escuela a borrar.
     * @param escuelaDao Objeto DAO para verificar asociaciones y ejecutar el borrado.
     */
    public static void eliminar(Scanner sc, EscuelaDaoImpl escuelaDao) {
        System.out.print("Introduce el nombre de la escuela a eliminar: ");
        String nombre = sc.nextLine();

        // 1. Primero comprobamos si tiene pintores
        if (escuelaDao.tienePintoresAsociados(nombre)) {
            System.out.println("No se puede eliminar la escuela " + nombre +
                    " porque todavía tiene pintores asociados.");
        } else {
            // 2. Si no tiene pintores, procedemos a borrarla
            escuelaDao.eliminarEscuela(nombre);
            System.out.println("Escuela eliminada correctamente.");
        }
    }


}
