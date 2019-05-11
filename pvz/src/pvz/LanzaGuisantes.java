package pvz;

public class LanzaGuisantes extends  Personaje{

    private int frecuencia;
    private int coste;


    public LanzaGuisantes(int fila, int columna){
        this.resistencia = 3;
        this.frecuencia = 1;
        this.coste = 50;
        this.daño = 1;
        this.fila = fila;
        this.columna = columna;
    }

    public int getCoste(){
        return coste;
    }


    public void recibeDaño(){
        resistencia --;
    }

    @Override
    public void imprimir() {
        System.out.print("L("+ resistencia + ")");
    }
}
