package pvz;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Juego {

    private int soles = 50; //soles iniciales 50
    private int turno = 1; //turno actual
    private String input;
    private int filaInicial;
    private int columnaInicial;
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
    private boolean derrotado = false;
    private boolean ayuda = false;
    private Scanner scanner = new Scanner(System.in);

    public Juego(){

    }

    public void interfazInicio(){
        System.out.println("Bienvenid@ al juego de Plantas versus Zombies!!");
        System.out.println("(Teclear ayuda para obtener una lista de comandos. <Enter> para finalizar el turno)");


        while(!salir && !derrotado){
            input = scanner.nextLine();
            String[] comando = input.split(" ");

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
                    ayuda = true;
                    break;

                case "N":
                    filaInicial = Integer.parseInt(comando[1]);
                    columnaInicial = Integer.parseInt(comando[2]);
                    switch (comando[3]){
                        case "BAJA" : dificultad = dificultad.BAJA; break;
                        case "MEDIA" : dificultad = dificultad.MEDIA; break;
                        case "ALTA" : dificultad = dificultad.ALTA; break;
                        case "IMPOSIBLE" : dificultad = dificultad.IMPOSIBLE; break;
                    }

                    zombiesRestantes = dificultad.getNumZombies(); // quiza sobra jeje
                    tablero = new Tablero(filaInicial, columnaInicial);
                    generaZombies(dificultad);

                    System.out.println("Comienza la partida...");

                    break;

                case "G":
                    try{
                        insertaGirasoles(comando);
                    } catch(ExcepcionPlanta e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case "L":
                    inputFilas = Integer.parseInt(comando[1]);
                    inputColumnas = Integer.parseInt(comando[2]);
                    if (soles > 50) {
                        LanzaGuisantes lanza = new LanzaGuisantes(inputFilas-1, inputColumnas-1);
                        if (tablero.casillaVacia(inputFilas-1, inputColumnas-1)){
                            tablero.añadirPersonaje(lanza, inputFilas-1, inputColumnas-1);
                            lanzaGuisantes.add(lanza);
                            turno++;
                        } else System.out.println("No se puede añadir un lanza guisantes en la posición indicada. Casilla ocupada");
                    } else System.out.println("No te lo puedes permitir. ");

                    break;

                case "":
                    turno ++;
                    break;

                case "S": salir = true; break;

            }

            if(!salir && !ayuda) {

                accionesJuego();

                ayuda = false;
            }

        }

        if (salir) System.out.println("Ha escogido cerrar la aplicación. Fin de la partida.");
        if (derrotado) System.out.println("Ha perdido la partida. Gracias por jugar!!!");

        scanner.close();
    }

    public void insertaGirasoles(String[] comando) throws ExcepcionPlanta{
        inputFilas = Integer.parseInt(comando[1]);
        inputColumnas = Integer.parseInt(comando[2]);

        if (inputFilas > filaInicial || inputColumnas > columnaInicial || inputFilas <= 0 || inputColumnas <= 0 ){
            throw new ExcepcionPlanta("No se pueden colocar los girasoles fuera del tablero. ");
        } else if (!tablero.casillaVacia(inputFilas-1, inputColumnas-1)) {
            throw new ExcepcionPlanta("No se puede insertar en la casilla seleccionada. Casilla ocupada" );
        } else if (inputColumnas == columnaInicial){
            throw new ExcepcionPlanta("No se puede insertar una planta en la última columna. ");
        } else
        {
            Girasol girasol = new Girasol(inputFilas-1, inputColumnas-1);
            if (soles < girasol.getCoste()) {
                throw new ExcepcionPlanta("No hay soles suficientes.");
            } else {
                tablero.añadirPersonaje(girasol, inputFilas-1, inputColumnas-1);
                girasoles.add(girasol);
                soles -= girasol.getCoste();
                turno++;
            }
        }
    }


    public void accionesJuego(){
        incrementarSoles();
        actualizaTiempoJugando();

        System.out.println("Soles: " + soles);
        System.out.println("Turno: " + turno);
        System.out.println("Turnos sin zombies: " + dificultad.getSinZombies());
        System.out.println("Turnos restantes: " + (dificultad.getTurnos() + dificultad.getSinZombies()));
        System.out.println("Zombies restantes por salir: " + zombiesRestantes);

        insertaZombies(turnosZombies, turno);
        zombies.forEach(zombie -> {
            if(zombie.getTiempoJugando()!=0 && turno%2==0) desplazarZombie(zombie);
        });

        ataque();
        borraGirasoles();
        borraLanzaGuisantes();
        borraZombies();

        zombies.forEach(zombie -> {
            if(finPartida(zombie)) derrotado = true;
        });

        tablero.imprimeTablero();

    }

    public void incrementarSoles(){
        girasoles.forEach(girasol -> {
            if(girasol.getTiempoJugando() % girasol.getFrecuencia() == 0)
                soles += girasol.getSolesNuevos();
        });
    }

    public void actualizaTiempoJugando (){
        girasoles.forEach(girasol -> {
            girasol.incrementarTiempoJugando();
        });
        zombies.forEach(zombie -> {
            zombie.incrementarTiempoJugando();
        });
    }

    public void desplazarZombie(Zombie zombie){
        if(zombie.getResistencia()>0) {
            int fila = zombie.getFila();
            int columna = zombie.getColumna();
            tablero.añadirPersonaje(zombie, fila, columna - 1);
            tablero.eliminarPersonaje(fila, columna);
            zombie.setColumna(columna - 1);
        }
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
        int filaIn = random.nextInt(filaInicial);

        if (turnosZombies[turno] > 0){
            int z = turnosZombies[turno];
            while(z > 0){
                if (tablero.casillaVacia( filaIn, columnaInicial-1)){
                    Zombie zombie = new Zombie(filaIn, columnaInicial-1);
                    zombiesRestantes--;
                    tablero.añadirPersonaje(zombie, filaIn, columnaInicial-1);
                    zombies.add(zombie);
                    z--;
                } else filaIn = random.nextInt(filaInicial);
            }

        }
    }

    public boolean finPartida(Zombie zombie){
        if (zombie.getColumna() == 0) return true;
        else return false;
    }

    public void ataque(){
        lanzaGuisantes.forEach(lanza -> {
            zombies.forEach(zombie -> {
                if(lanza.getFila() == zombie.getFila()) {
                    zombie.recibeDaño();
                }
                if(lanza.getColumna() == zombie.getColumna()+1){
                    lanza.recibeDaño();
                }
            });
        });
    }

    public void borraLanzaGuisantes(){
        lanzaGuisantes.forEach(lanza-> {
            if (lanza.getResistencia()==0){
                lanzaGuisantes.remove(lanza);
                tablero.eliminarPersonaje(lanza.getFila(), lanza.getColumna());

            }
        });
    }

    public void borraGirasoles(){
        girasoles.forEach(girasol -> {
            if (girasol.getResistencia()==0) {
                girasoles.remove(girasol);
                tablero.eliminarPersonaje(girasol.getFila(), girasol.getColumna());

            }
        });
    }

    public void borraZombies(){
        zombies.forEach(zombie -> {
            if (zombie.getResistencia()==0){
                zombies.remove(zombie);
                tablero.eliminarPersonaje(zombie.getFila(), zombie.getColumna());

            }
        });
    }

}