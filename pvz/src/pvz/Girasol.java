package pvz;

public class Girasol extends Personaje {

    private int frecuencia;
    private int coste;
    private int solesNuevos;

    public Girasol (int fila, int columna){
        this.resistencia = 1;
        this.frecuencia = 2;
        this.solesNuevos = 10;
        this.coste = 20;
        this.daño = 0;
        this.tiempoJugando = 1;
        this.fila = fila;
        this.columna = columna;
    }

    public int getFrecuencia(){
        return frecuencia;
    }

    public int getCoste() {
        return coste;
    }

    public int getTiempoJugando() {
        return tiempoJugando;
    }

    public int getSolesNuevos() {
        return solesNuevos;
    }

    @Override
    public void imprimir() {
        System.out.print("G("+ resistencia + ")");
    }
}
