package pvz;

public class LanzaGuisantes extends  Personaje{

    private  int frecuencia;
    private int coste;


    public LanzaGuisantes(){
        super.resistencia = 3;
        this.frecuencia = 1;
        this.coste = 50;
        super.daño = 1;
    }

    @Override
    public void imprimir() {
        System.out.println("L("+ resistencia + ")");
    }
}
