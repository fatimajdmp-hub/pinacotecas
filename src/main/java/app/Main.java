package app;

import dao.PintorDaoImpl;
import dao.PintorDao;
import modelo.Pintor;

import java.util.List;

/**
 *
 * @author Diego Manuel carrasco villarán
 * @version 25.0.1
 * @since 2026.04.21
 */

public class Main {
    public static void main(String[] args) {
        PintorDao pintor = new PintorDaoImpl();
        pintor.crearTabla();

        Pintor pintor1 = new Pintor("ale","españa","bollullos","14/04/2006","14/04/2050","ies delgado","rafa");
        Pintor pintor2 = new Pintor("ale","francia","bollullos","14/04/2006","14/04/2050","ies delgado","rafa");

        pintor.insertarPintor(pintor1);

        pintor.actualizarDatosPintor(pintor2);

        List<Pintor> pintors = pintor.listarPintor();
        if(pintors.isEmpty()) {
            System.out.println("No hay publicaciones");
        }else  {
            pintors.forEach(System.out::println);
        }

    }
}
