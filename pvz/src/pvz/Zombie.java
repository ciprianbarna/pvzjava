package pvz;

public class Zombie extends Personaje {

    private int velocidad;


    public Zombie(int fila, int columna){
        super.daño = 1;
        super.resistencia = 5;
        super.fila = fila;
        super.columna = columna;
        this.velocidad = 1;

    }

    @Override
    public void imprimir() {
        System.out.print("Z("+ resistencia + ")");
    }
}
