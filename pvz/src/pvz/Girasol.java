package pvz;

public class Girasol extends Personaje {

    private  int frecuencia;
    private int coste;

    public Girasol (){
        super.resistencia = 1;
        this.frecuencia = 10;
        this.coste = 20;
        super.daño = 0;
    }

    @Override
    public void imprimir() {
        System.out.print("G("+ resistencia + ")");
    }
}
