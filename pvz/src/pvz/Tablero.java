package pvz;

import java.util.Random;

public class Tablero {

    private Personaje tablero [][];
    private int filas;
    private int columnas;
    private int soles = 50;


    public Tablero(int filas, int columnas){
        this.filas = filas;
        this.columnas = columnas;
        tablero = new Personaje[filas][columnas];
       /* for (int i=0; i < filas; i++){
            for (int j=0; j < filas; j++){
                tablero[i][j] = null;
            }
        }*/
    }

    public void añadirPersonaje (Personaje personaje, int fila, int columna){
        tablero[fila][columna] = personaje;
    }

 // creo q esto se hace luego en jugo.java
    public void generaZombies (Dificultad dificultad){
        Random random = new Random();
        int rand = random.nextInt(filas);
        int numeroZombies = dificultad.getNumZombies();

        while (numeroZombies > 0) {
            Zombie zombie = new Zombie();
            añadirPersonaje(zombie,rand, columnas-1);
            numeroZombies--;
        }

    }


    //hay que añadirle a este método las decoraciones
    public void imprimeTablero (){
        for (int i=0; i < filas; i++){
            lineaDecoracion(columnas);
            System.out.println();
            for (int j=0; j < columnas; j++){
                System.out.print("|");
                if (tablero[i][j] != null){
                    System.out.print(" ");
                    tablero[i][j].imprimir();
                    System.out.print(" ");
                } else System.out.print("      ");

            }
            System.out.print("|");
            System.out.println();
        }
        lineaDecoracion(columnas);

    }

    private void lineaDecoracion(int columnas){
        System.out.print("|");
        for (int i = 0; i < columnas; i++){
            if(i== columnas-1) System.out.print("------|");
            else System.out.print("-------");
        }
    }

    public int getFilas(){
        return filas;
    }

    public int getColumnas(){
        return columnas;
    }


}
