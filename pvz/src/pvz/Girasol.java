package pvz;

public class Girasol extends Personaje {

    private  int frecuencia;
    private int coste;

    public Girasol (){
        super.resistencia = 1;
        frecuencia = 10;
        coste = 20;
        super.daño = 0;
    }

    @Override
    public void imprimir() {
        System.out.println("G("+ resistencia + ")");
    }
}
