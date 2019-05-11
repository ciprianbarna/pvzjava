package pvz;

import java.util.ArrayList;
import java.util.Random;
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
    private int turnosZombies[];
    private int zombiesRestantes;
    private boolean salir = false;
    Zombie z = new Zombie(5,5);
    Zombie z1 = new Zombie (4,5);

    public Juego(){

    }

    public void interfazInicio(){
        System.out.println("Bienvenid@ al juego de Plantas versus Zombies!!");
        System.out.println("(Teclear ayuda para obtener una lista de comandos. <Enter> para finalizar el turno)");

        Scanner scanner = new Scanner(System.in);

        input = scanner.nextLine();
        String[] comando = input.split(" ");


        while(!salir){

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

                    zombiesRestantes = dificultad.getNumZombies(); // quiza sobra jeje
                    tablero = new Tablero(inputFilas, inputColumnas);
                    generaZombies(dificultad);

                    System.out.println("Comienza la partida...");

                    tablero.imprimeTablero();
                    break;

                case "G":
                    inputFilas = Integer.parseInt(comando[1]);
                    inputColumnas = Integer.parseInt(comando[2]);
                    Girasol girasol = new Girasol(inputFilas, inputColumnas);
                    if (tablero.casillaVacia(inputFilas-1, inputColumnas-1)){
                        tablero.añadirPersonaje(girasol, inputFilas-1, inputColumnas-1);
                    } else System.out.println("No se puede añadir un girasol en la posición indicada. Casilla ocupada");
                    girasoles.add(girasol);

                    tablero.imprimeTablero();
                    turno++;
                    break;

                case "L":
                    inputFilas = Integer.parseInt(comando[1]);
                    inputColumnas = Integer.parseInt(comando[2]);
                    LanzaGuisantes lanza = new LanzaGuisantes(inputFilas, inputColumnas);
                    if (tablero.casillaVacia(inputFilas-1, inputColumnas-1)){
                        tablero.añadirPersonaje(lanza, inputFilas-1, inputColumnas-1);
                    } else System.out.println("No se puede añadir un lanza guisantes en la posición indicada. Casilla ocupada");
                    lanzaGuisantes.add(lanza);

                    tablero.imprimeTablero();
                    turno++;
                    break;

                case "":
                    tablero.imprimeTablero();
                    turno ++;
                    break;

                case "S": salir = true; break;

            }

            if(!salir) {
                actualizarSoles();
                actualizaTiempoJugando();
                System.out.println("Soles: " + soles);
                System.out.println("Turno: " + turno);
                System.out.println("Turnos sin zombies: " + dificultad.getSinZombies());
                System.out.println("Turnos restantes: " + (dificultad.getTurnos() + dificultad.getSinZombies()));
                System.out.println("Zombies restantes por salir: " + zombiesRestantes);

                insertaZombies(turnosZombies, turno);
                zombiesRestantes = zombiesRestantes - zombies.size();
                zombies.forEach(zombie -> {
                    if(zombie.getTiempoJugando()!=0)  desplazarZombie(zombie);
                });

                zombies.forEach(zombie -> {
                    zombie.incrementarTiempoJugando();
                });

                input = scanner.nextLine();
                comando = input.split(" ");
            }

        }

        System.out.println("Fin de la partida. ");

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

    public void desplazarZombie(Zombie zombie){
        int fila = zombie.getFila();
        int columna = zombie.getColumna();
        tablero.añadirPersonaje(zombie, fila, columna - 1);
        tablero.eliminarPersonaje(fila, columna);
        zombie.setColumna(columna-1);
    }



    public void generaZombies (Dificultad dificultad){
        Random random = new Random();
        int turnoZombie;
        int numeroZombies = dificultad.getNumZombies();
        turnosZombies = new int[dificultad.getSinZombies() + dificultad.getTurnos()];

        for (int i=0; i < turnosZombies.length; i++){
            turnosZombies[i] = 0;
        }

        for (int i= dificultad.getSinZombies(); i <= dificultad.getTurnos(); i++){
            if(numeroZombies > 0){
                turnoZombie = random.nextInt(dificultad.getTurnos()) + dificultad.getSinZombies();
                turnosZombies[turnoZombie]++;
                numeroZombies--;
            }
        }

    }

    public void insertaZombies(int[] turnosZombies, int turno){
        Random random = new Random();
        int filaIn = random.nextInt(inputFilas);

        if (turnosZombies[turno] > 0){
            int z = turnosZombies[turno];
            while(z > 0){
                if (tablero.casillaVacia( filaIn, inputColumnas-1)){
                    Zombie zombie = new Zombie(filaIn, inputColumnas-1);
                    tablero.añadirPersonaje(zombie, filaIn, inputColumnas-1);
                    zombies.add(zombie);
                    z--;
                } else filaIn = random.nextInt(inputFilas);
            }

        }
    }
}