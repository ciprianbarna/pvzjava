package pvz;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Juego {

    private int soles = 50; //soles iniciales 50
    private int turno = 0; //turno actual
    private String input;
    private int inputFilas;
    private int inputColumnas;
    private Dificultad dificultad;
    private Tablero tablero;
    private ArrayList<Girasol> girasoles = new ArrayList<>();
    private ArrayList<LanzaGuisantes> lanzaGuisantes = new ArrayList<>();
    private ArrayList<Zombie> zombies = new ArrayList<>();
    private int[] turnosZombies;

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

                    turno++;

                    tablero.imprimeTablero();
                    break;

                case "G":
                    Girasol girasol = new Girasol();
                    inputFilas = Integer.parseInt(comando[1]);
                    inputColumnas = Integer.parseInt(comando[2]);
                    if (tablero.casillaVacia(inputFilas-1, inputColumnas-1)){
                        tablero.añadirPersonaje(girasol, inputFilas-1, inputColumnas-1);
                    } else System.out.println("No se puede añadir un girasol en la posición indicada. Casilla ocupada");
                    girasoles.add(girasol);
                    soles -= girasol.getCoste();
                    tablero.imprimeTablero();
                    turno++;
                    break;

                case "L":
                    LanzaGuisantes lanza = new LanzaGuisantes();
                    inputFilas = Integer.parseInt(comando[1]);
                    inputColumnas = Integer.parseInt(comando[2]);
                    if (tablero.casillaVacia(inputFilas-1, inputColumnas-1)){
                        tablero.añadirPersonaje(lanza, inputFilas-1, inputColumnas-1);
                    } else System.out.println("No se puede añadir un lanza guisantes en la posición indicada. Casilla ocupada");
                    lanzaGuisantes.add(lanza);
                    soles -= lanza.getCoste();

                    tablero.imprimeTablero();
                    turno++;
                    break;

            }

            actualizarSoles();
            actualizaTiempoJugando();
            System.out.println("Tienes " +  soles + " soles y es el turno " + turno);

            generaZombies(dificultad, turno);

            input = scanner.nextLine();
            comando = input.split(" ");

        }

    }

    public void actualizarSoles(){
        girasoles.forEach(girasol -> {
            if(girasol.getTiempoJugando() % girasol.getFrecuencia() == 0)
                soles += girasol.getSolesNuevos();
        });
    }

    public void actualizaTiempoJugando (){
        girasoles.forEach(girasol -> {
            girasol.incrementarTiempoJugando();
        });
    }

    public void generaZombies (Dificultad dificultad, int turno){
        Random random = new Random();
        int filaIn = random.nextInt(inputFilas);
        int turnoZombie;
        int numeroZombies = dificultad.getNumZombies();
        turnosZombies = new int[dificultad.getSinZombies() + dificultad.getTurnos()];

        for (int i=0; i < turnosZombies.length-1; i++){
            turnosZombies[i] = 0;
        }

        for (int i= dificultad.getSinZombies(); i <= dificultad.getTurnos(); i++){
            if(numeroZombies > 0){
                turnoZombie = random.nextInt(dificultad.getTurnos()) + dificultad.getSinZombies();
                turnosZombies[turnoZombie]++;
                numeroZombies--;
            }

        }
        /*
        for (int i=0; i < turnosZombies.length-1; i++){
            System.out.println("turno "+ i + " n zombi   " +  turnosZombies[i]);
        }*/


        if (turnosZombies[turno] > 0){
            int z = turnosZombies[turno];
            while(z > 0){
                Zombie zombie = new Zombie();
                if (tablero.casillaVacia(inputFilas, inputColumnas)){
                    tablero.añadirPersonaje(zombie, filaIn, inputColumnas-1);
                    zombies.add(zombie);
                    z--;
                } else filaIn = random.nextInt(inputFilas);
            }

        }

    }
}