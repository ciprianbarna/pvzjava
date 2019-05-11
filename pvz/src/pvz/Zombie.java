package pvz;

public class Zombie extends Personaje {

    private int velocidad;


    public Zombie(int fila, int columna){
        super.daño = 1;
        super.resistencia = 5;
        super.fila = fila;
        super.columna = columna;
        this.velocidad = 1;
        super.tiempoJugando = 0;

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
