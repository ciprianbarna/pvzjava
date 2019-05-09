public enum Dificultad {

    BAJA ("BAJA", 30, 5, 10),
    MEDIA ("MEDIA", 30, 15, 7),
    ALTA ("ALTA", 30, 25, 5),
    IMPOSIBLE ("IMPOSIBLE", 30, 50, 5);

    private final String valor;     //nombre dificultad
    private final int turnos;       //N turnos reparto zombies
    private final int numZombies;  //N Zombis en la Partida
    private final int sinZombies;  //turnos sin zombies

    //constructor
    public Dificultad (String valor, int turnos, int numZombies, int sinZombies) {
        this.valor = valor;
        this.turnos = turnos;
        this.numZombies = numZombies;
        this.sinZombies = sinZombies;
    }

    //getters

    public String getValor() {
        return valor;
    }

    public int getTurnos() {
        return turnos;
    }

    public int getNumZombies() {
        return numZombies;
    }

    public int getSinZombies() {
        return sinZombies;
    }
}
