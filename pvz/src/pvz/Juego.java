package pvz;

public class Juego {
    public static void main (String[] args){
        Girasol g = new Girasol();
        LanzaGuisantes l = new LanzaGuisantes();
        Zombie z = new Zombie();

        g.imprimir();
        l.imprimir();
        z.imprimir();

    }
}
