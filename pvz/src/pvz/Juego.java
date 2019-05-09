package pvz;

public class Juego {
    public static void main (String[] args){

        static final int solesInicial = 50; //soles al inicio de partida
        private turno = 0; //turno actual


        Girasol g = new Girasol();
        LanzaGuisantes l = new LanzaGuisantes();
        Zombie z = new Zombie();

        g.imprimir();
        l.imprimir();
        z.imprimir();

    }
}
