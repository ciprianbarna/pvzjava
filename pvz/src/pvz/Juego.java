package pvz;

import java.util.ArrayList;
import java.util.Scanner;

public class Juego {

    private int soles = 50; //soles iniciales 50
    private int turno = 1; //turno actual
    private String input;
    private int inputFilas;
    private int inputColumnas;
    private Dificultad dificultad;
    private Tablero tablero;
    private ArrayList<Girasol> girasoles = new ArrayList<>();
    private ArrayList<LanzaGuisantes> lanzaGuisantes = new ArrayList<>();
    private ArrayList<Zombie> zombies = new ArrayList<>();

    public Juego(){

    }

    public void interfazInicio(){
        System.out.println("Bienvenid@ al juego de Plantas versus Zombies!!");
        System.out.println("(Teclear ayuda para obtener una lista de comandos. <Enter> para finalizar el turno)");

        Scanner scanner = new Scanner(System.in);

        input = scanner.nextLine();
        String[] comando = input.split(" ");

        //for (int i=0; i<comando.length; i++) System.out.println(comando[i]);

        while(comando[0]!="S"){

            switch(comando[0]){
                case "ayuda":
                    System.out.println("Lista de comandos: ");
                    System.out.println("N <filas> <columnas> <Dificultad>: Nueva partida (Dificultad:BAJA, MEDIA, ALTA, IMPOSIBLE");
                    System.out.println("G <fila> <columna>: Colocar girasol. Únicamente se podrá añadir un nuevo Girasol por turno y");
                    System.out.println("                    sólo si se tienen suficientes soles. No se podrá añadir un Girasol en una");
                    System.out.println("                    casilla ocupada por otra planta o por un zombie");
                    System.out.println("L <fila> <columna>: Colocar LanzaGuisantes. Únicamente se podrá añadir un nuevo LanzaGuisantes");
                    System.out.println("                    por turno y si tiene el número suficientes de soles. No podrá añadir un LanzaGuisantes");
                    System.out.println("                    en una casilla ocupada por otra planta o por un zombie");
                    System.out.println("S: Salir de la aplicación");
                    System.out.println("<Enter>: Pasar turno");
                    System.out.println("ayuda: este comando solicita a la aplicación que muestre la ayuda sobre cómo utilizar los comandos");
                    break;

                case "N":
                    inputFilas = Integer.parseInt(comando[1]);
                    inputColumnas = Integer.parseInt(comando[2]);
                    switch (comando[3]){
                        case "BAJA" : dificultad = dificultad.BAJA; break;
                        case "MEDIA" : dificultad = dificultad.MEDIA; break;
                        case "ALTA" : dificultad = dificultad.ALTA; break;
                        case "IMPOSIBLE" : dificultad = dificultad.IMPOSIBLE; break;
                    }

                    tablero = new Tablero(inputFilas, inputColumnas);

                    System.out.println("Comienza la partida...");

                    tablero.imprimeTablero();

                /*
                System.out.println("filas" +  inputFilas);
                System.out.println("columnas" + inputColumnas);
                System.out.println("dificultad" + dificultad.getValor());*/

                    break;

                case "G":
                    Girasol girasol = new Girasol();
                    inputFilas = Integer.parseInt(comando[1]);
                    inputColumnas = Integer.parseInt(comando[2]);
                    if (tablero.casillaVacia(inputFilas, inputColumnas)){
                        tablero.añadirPersonaje(girasol, inputFilas, inputColumnas);
                    } else System.out.println("No se puede añadir un girasol en la posición indicada. Casilla ocupada");
                    girasoles.add(girasol);

                    tablero.imprimeTablero();
                    break;

            }

            input = scanner.nextLine();
            comando = input.split(" ");

        }

    }

}
