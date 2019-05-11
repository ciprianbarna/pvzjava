package pvz;

public class Girasol extends Personaje {

    private  int frecuencia;
    private int coste;
    private int tiempoJugando;
    private int solesNuevos;

    public Girasol (int fila, int columna){
        super.resistencia = 1;
        this.frecuencia = 2;
        this.solesNuevos = 10;
        this.coste = 20;
        super.daño = 0;
        this.tiempoJugando = 0;
        super.fila = fila;
        super.columna = columna;
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

    public void incrementarTiempoJugando() {
        tiempoJugando++;
    }

    @Override
    public void imprimir() {
        System.out.print("G("+ resistencia + ")");
    }
}
