package pvz;

public class Zombie extends Personaje {

    private int velocidad;


    public Zombie(int fila, int columna){
        this.daño = 1;
        this.resistencia = 5;
        this.fila = fila;
        this.columna = columna;
        this.velocidad = 1;
        this.tiempoJugando = 0;

    }

    public int getFila(){
        return this.fila;
    }

    public int getColumna(){
        return this.columna;
    }

    public void setColumna(int columna){
        this.columna = columna;
    }

    public int getTiempoJugando(){
        return this.tiempoJugando;
    }

    @Override
    public void imprimir() {
        System.out.print("Z("+ resistencia + ")");
    }
}
