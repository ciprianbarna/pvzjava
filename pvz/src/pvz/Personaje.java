package pvz;

public class  Personaje {
    protected int resistencia;
    protected int daño;
    protected int fila;
    protected int columna;
    protected int tiempoJugando;

    public  Personaje(){

    }

    public void imprimir(){
    }

    public void incrementarTiempoJugando() {
        tiempoJugando++;
    }

    public int getResistencia(){
        return resistencia;
    }

    public int getFila(){
        return fila;
    }

    public int getColumna(){
        return columna;
    }
}
