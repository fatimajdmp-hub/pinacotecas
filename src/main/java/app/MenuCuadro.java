package app;

import dao.CuadradoDaoImpl;
import modelo.Cuadrado;

import java.util.List;
import java.util.Scanner;

/**
 * @author Juan Francisco Garrido Ariza
 * @version 25.0.1
 * @since 2026.04.23
 */
public class MenuCuadro {

    public static void mostrar(Scanner sc, CuadradoDaoImpl cuadradoDao) {
        int opcion;
        do {
            System.out.println("\n--- CUADROS ---");
            System.out.println("1. Registrar cuadro");
            System.out.println("2. Listar todos");
            System.out.println("3. Modificar cuadro");
            System.out.println("4. Eliminar cuadro");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = Main.leerInt(sc);

            switch (opcion) {
                case 1 -> registrar(sc, cuadradoDao);
                case 2 -> listar(cuadradoDao);
                case 3 -> modificar(sc, cuadradoDao);
                case 4 -> eliminar(sc, cuadradoDao);
                case 0 -> {}
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void registrar(Scanner sc, CuadradoDaoImpl cuadradoDao) {
        System.out.print("Código: ");
        String codigo = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Dimensiones: ");
        String dimensiones = sc.nextLine();
        System.out.print("Fecha pintado: ");
        String fecha = sc.nextLine();
        System.out.print("Técnica: ");
        String tecnica = sc.nextLine();
        System.out.print("Nombre pinacoteca: ");
        String pinacoteca = sc.nextLine();
        System.out.print("Nombre pintor: ");
        String pintor = sc.nextLine();

        cuadradoDao.insertarCuadrado(new Cuadrado(codigo, nombre, dimensiones, fecha, tecnica, pinacoteca, pintor));
        System.out.println("Cuadro registrado.");
    }

    private static void listar(CuadradoDaoImpl cuadradoDao) {
        List<Cuadrado> lista = cuadradoDao.listarCuadrado();
        if (lista.isEmpty()) {
            System.out.println("No hay cuadros registrados.");
        } else {
            System.out.println("\n=== CUADROS ===");
            for (Cuadrado c : lista) {
                System.out.println(c);
            }
        }
    }

    private static void modificar(Scanner sc, CuadradoDaoImpl cuadradoDao) {
        System.out.print("Código del cuadro a modificar: ");
        String codigo = sc.nextLine().trim();

        Cuadrado existente = null;
        for (Cuadrado c : cuadradoDao.listarCuadrado()) {
            if (c.getCodigo().equals(codigo)) {
                existente = c;
                break;
            }
        }

        if (existente == null) {
            System.out.println("Cuadro no encontrado.");
            return;
        }

        System.out.print("Antiguo nombre" + existente.getNombre() + "Nuevo nombre:");
        String nombre = sc.nextLine();
        System.out.print("Antiguas dimensiones:" + existente.getDimensiones() + "Nuevas dimensiones: ");
        String dimensiones = sc.nextLine();
        System.out.print("Antigua fecha" + existente.getFechaPintado() + "Nueva fecha:");
        String fecha = sc.nextLine();
        System.out.print("Antigua tecnica: " + existente.getTecnica() + "Nueva técnica: ");
        String tecnica = sc.nextLine();
        System.out.print("Antgua pinacoteca"  + existente.getNombre_pinacoteca() + "Nueva pinacoteca: ");
        String pinacoteca = sc.nextLine();
        System.out.print("Antiguo pintor: " + existente.getNombre_pintor() + "Nuevo pintor: ");
        String pintor = sc.nextLine();

        if (!nombre.isEmpty()) existente.setNombre(nombre);
        if (!dimensiones.isEmpty()) existente.setDimensiones(dimensiones);
        if (!fecha.isEmpty()) existente.setFechaPintado(fecha);
        if (!tecnica.isEmpty()) existente.setTecnica(tecnica);
        if (!pinacoteca.isEmpty()) existente.setNombre_pinacoteca(pinacoteca);
        if (!pintor.isEmpty()) existente.setNombre_pintor(pintor);

        cuadradoDao.actualizarCuadrado(existente);
        System.out.println("Cuadro actualizado.");
    }

    private static void eliminar(Scanner sc, CuadradoDaoImpl cuadradoDao) {
        System.out.print("Código a eliminar: ");
        String codigo = sc.nextLine();
        System.out.print("¿Confirmar eliminación? (s/n): ");
        String decision = sc.nextLine();
        if (decision.equals("s")) {
            cuadradoDao.eliminarCuadrado(codigo);
            System.out.println("Cuadro eliminado.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }
}

