package pvz;


import java.util.*;

public class Partida {


    private Scanner sc = new Scanner(System.in); // VARIABLE SCANNER

    public boolean inicioPartida (){
        boolean salir = false;   // para salir del juego
        int opcion;

        // BUCLE DE INICIO
        while(!salir) {
            System.out.println("1. Empezar nueva partida");
            System.out.println("2. Salir de la aplicacion");

            System.out.println("Escribe una de las opciones");
            opcion = sn.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Nueva partida");
                    break;
                case 2:
                    salir = true;
                    break;
                default:
                    System.out.println("Solo números entre 1 y 2");
            }
        }
    }

    public void seleccionarDificultad (){
        Dificultad dif;

        System.out.println("Selecciones dificultad para su partida (BAJA, MEDIA, ALTA o IMPOSIBLE)");
        String opcion = sc.nextLine();

        switch (opcion) {
            case BAJA:

                break;
            case MEDIA:

                break;
            case ALTA:

                break;
            case IMPOSIBLE:

                break;
            default:
                System.out.println("No es una opcion valida");
        }
    }
}
