package pvz;

import java.util.Random;
import java.util.Scanner;

public class Juego {

    static final int solesInicial = 50; //soles al inicio de partida
    private int turno = 1; //turno actual

    public static void main(String[] args) {

        Girasol g = new Girasol();
        LanzaGuisantes l = new LanzaGuisantes();
        Zombie z = new Zombie();
        Tablero tablero = new Tablero(5,5);

        //g.imprimir();
        //l.imprimir();
        //z.imprimir();

        tablero.añadirPersonaje(g, 0,0);
        tablero.añadirPersonaje(l,1,1);
        tablero.añadirPersonaje(z,0,1);


        System.out.println(tablero.getColumnas());
        System.out.println(tablero.getFilas());
        tablero.imprimeTablero();



    }
}



//Interfaz inicio

        /*System.out.println("Bienvenido a Plants vs Zombies/n");
        System.out.println("NUEVO JUEGO/n");
        System.out.print("Cuantas filas tiene el tablero:");
        int tablero.filas = Scanner.class.
        System.out.print("Cuantas columnas tiene el tablero:");
        int tablero.columnas=scanner.nextInt();*/


        /*for (int k = 0; k < 3; k++){
            for (int i = 0; i < 5; i++){
                System.out.print("|" + "-------");
            }
            System.out.println("|");
            for (int j = 0; j < 5; j++){
                System.out.print("|" + "       ");
            }
            System.out.println("|");
        }
        for (int i = 0; i < 5; i++){
            System.out.print("|" + "-------");
        }
        System.out.println("|");*/


        /*for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!tablero[i][j]) System.out.println("       ");
            }
        }*/