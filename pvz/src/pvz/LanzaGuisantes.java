package pvz;

public class LanzaGuisantes extends  Personaje{

    private  int frecuencia;
    private int coste;


    public LanzaGuisantes(int fila, int columna){
        super.resistencia = 3;
        this.frecuencia = 1;
        this.coste = 50;
        super.daño = 1;
        super.fila = fila;
        super.columna = columna;
    }

    public int getCoste(){
        return coste;
    }

    @Override
    public void imprimir() {
        System.out.print("L("+ resistencia + ")");
    }
}
