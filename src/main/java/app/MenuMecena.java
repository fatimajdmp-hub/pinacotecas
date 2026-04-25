package app;

import dao.MecenaDaoImpl;
import modelo.Mecena;

import java.util.List;
import java.util.Scanner;

/**
 * Clase encargada de gestionar la interfaz de usuario por consola para los Mecenas.
 * Permite registrar, consultar, modificar y eliminar mecenas, así como gestionar
 * sus relaciones (vincular/desvincular) con los pintores.
 *
 * @author Hugo Rodriguez Vigueras
 * @version 1.0
 * @since 2026-04-20
 */
public class MenuMecena {

    /**
     * Muestra el menú principal de gestión de mecenas y dirige la ejecución
     * según la opción seleccionada por el usuario.
     *
     * @param sc        Objeto Scanner para la lectura de datos.
     * @param mecenaDao Implementación del DAO para la persistencia de datos.
     */
    public static void mostrar(Scanner sc, MecenaDaoImpl mecenaDao) {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE MECENAS ---");
            System.out.println("1. Registrar Mecenas");
            System.out.println("2. Consultar Listado y Filtrar");
            System.out.println("3. Modificar datos de un Mecenas");
            System.out.println("4. Eliminar un Mecenas");
            System.out.println("5. Asociar Mecenas a un Pintor o modificar relación");
            System.out.println("6. Desvincular Mecenas de un Pintor");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = Main.leerInt(sc);

            switch (opcion) {
                case 1 -> registrar(sc, mecenaDao);
                case 2 -> consultar(sc, mecenaDao);
                case 3 -> modificar(sc, mecenaDao);
                case 4 -> eliminar(sc, mecenaDao);
                case 5 -> asociar(sc, mecenaDao);
                case 6 -> desvincular(sc, mecenaDao);
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    /**
     * Solicita todos los atributos para registrar un nuevo mecenas en el sistema.
     *
     * @param sc        Scanner para capturar la entrada del usuario.
     * @param mecenaDao DAO para ejecutar la inserción.
     */
    public static void registrar(Scanner sc, MecenaDaoImpl mecenaDao) {
        System.out.print("Nombre del mecenas: ");
        String nombre = sc.nextLine();
        System.out.print("Fecha de nacimiento: ");
        String fecha = sc.nextLine();
        System.out.print("País: ");
        String pais = sc.nextLine();
        System.out.print("Ciudad de nacimiento: ");
        String ciudad = sc.nextLine();
        System.out.print("Fecha de defunción (vacío si vive): ");
        String fDef = sc.nextLine();

        Mecena m = new Mecena();
        m.setNombre(nombre);
        m.setFecha(fecha);
        m.setPais(pais);
        m.setCiudadNacimiento(ciudad);
        m.setFechaDeFuncion(fDef.isBlank() ? null : fDef);

        mecenaDao.insertarMecena(m);
    }

    /**
     * Consulta el listado de mecenas y permite aplicar filtros por nombre,
     * país o ciudad de nacimiento.
     *
     * @param sc        Scanner para elegir el tipo de filtro y el término de búsqueda.
     * @param mecenaDao DAO para obtener la lista de la base de datos.
     */
    public static void consultar(Scanner sc, MecenaDaoImpl mecenaDao) {
        List<Mecena> lista = mecenaDao.listarMecenas();
        if (lista.isEmpty()) {
            System.out.println("No hay mecenas registrados.");
            return;
        }

        System.out.println("1. Ver todos | 2. Filtrar por nombre | 3. Filtrar por país | 4. Filtrar por ciudad");
        System.out.print("Selección: ");
        int filtro = Main.leerInt(sc);

        switch (filtro) {
            case 1 -> imprimirLista(lista);
            case 2 -> {
                System.out.print("Nombre a buscar: ");
                String n = sc.nextLine().toLowerCase();
                imprimirLista(lista.stream().filter(m -> m.getNombre().toLowerCase().contains(n)).toList());
            }
            case 3 -> {
                System.out.print("País a buscar: ");
                String p = sc.nextLine().toLowerCase();
                imprimirLista(lista.stream().filter(m -> m.getPais() != null
                        && m.getPais().toLowerCase().contains(p)).toList());
            }
            case 4 -> {
                System.out.print("Ciudad a buscar: ");
                String c = sc.nextLine().toLowerCase();
                imprimirLista(lista.stream().filter(m -> m.getCiudadNacimiento() != null
                        && m.getCiudadNacimiento().toLowerCase().contains(c)).toList());
            }
            default -> System.out.println("Opción no válida.");
        }
    }

    /**
     * Modifica los datos personales de un mecenas ya existente.
     *
     * @param sc        Scanner para capturar los nuevos datos.
     * @param mecenaDao DAO para ejecutar la actualización.
     */
    public static void modificar(Scanner sc, MecenaDaoImpl mecenaDao) {
        System.out.print("Nombre del mecenas a modificar: ");
        String nombre = sc.nextLine();

        System.out.print("Nueva fecha de nacimiento: ");
        String fecha = sc.nextLine();
        System.out.print("Nuevo país: ");
        String pais = sc.nextLine();
        System.out.print("Nueva ciudad de nacimiento: ");
        String ciudad = sc.nextLine();
        System.out.print("Nueva fecha de defunción: ");
        String fDef = sc.nextLine();

        Mecena m = new Mecena();
        m.setNombre(nombre);
        m.setFecha(fecha);
        m.setPais(pais);
        m.setCiudadNacimiento(ciudad);
        m.setFechaDeFuncion(fDef.isBlank() ? null : fDef);

        mecenaDao.actualizarMecena(m);
    }

    /**
     * Elimina un mecenas por completo de la base de datos.
     *
     * @param sc        Scanner para capturar el nombre del mecenas.
     * @param mecenaDao DAO para ejecutar el borrado.
     */
    public static void eliminar(Scanner sc, MecenaDaoImpl mecenaDao) {
        System.out.print("Nombre del mecenas a eliminar: ");
        String nombre = sc.nextLine();
        mecenaDao.eliminarMecena(nombre);
    }

    /**
     * Asocia un mecenas a un pintor, indicando la relación entre ambos.
     * También sirve para modificar una relación existente gracias al 'REPLACE' del DAO.
     *
     * @param sc        Scanner para capturar los nombres y la relación.
     * @param mecenaDao DAO para crear la asociación.
     */
    public static void asociar(Scanner sc, MecenaDaoImpl mecenaDao) {
        System.out.print("Nombre del mecenas: ");
        String mecenas = sc.nextLine();
        System.out.print("Nombre del pintor a asociar: ");
        String pintor = sc.nextLine();
        System.out.print("Tipo de relación (ej. Financiera, Alojamiento, Protector...): ");
        String relacion = sc.nextLine();

        mecenaDao.asociarMecenaConPintor(mecenas, pintor, relacion);
    }

    /**
     * Desvincula (elimina la relación) entre un mecenas y un pintor específico.
     *
     * @param sc        Scanner para capturar los nombres a desvincular.
     * @param mecenaDao DAO para eliminar la asociación.
     */
    public static void desvincular(Scanner sc, MecenaDaoImpl mecenaDao) {
        System.out.print("Nombre del mecenas: ");
        String mecenas = sc.nextLine();
        System.out.print("Nombre del pintor a desvincular: ");
        String pintor = sc.nextLine();

        mecenaDao.desvincularMecenaDePintor(mecenas, pintor);
    }

    /**
     * Método auxiliar privado para imprimir por pantalla el listado de mecenas.
     *
     * @param lista La lista de objetos Mecena a formatear e imprimir.
     */
    private static void imprimirLista(List<Mecena> lista) {
        if (lista.isEmpty()) {
            System.out.println("Sin resultados.");
        } else {
            System.out.println("\n--- LISTADO DE MECENAS ---");
            lista.forEach(m -> System.out.println("- " + m.getNombre() +
                    " (" + m.getPais() + " - " + m.getCiudadNacimiento() + ")"));
        }
    }


}
