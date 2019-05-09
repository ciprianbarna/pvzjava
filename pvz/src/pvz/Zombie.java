package pvz;

public class Zombie extends Personaje {

    private int velocidad;

    public Zombie(){
        super.daño = 1;
        super.resistencia = 5;
        this.velocidad = 1;
    }

    @Override
    public void imprimir() {
        System.out.println("Z("+ resistencia + ")");
    }
}
