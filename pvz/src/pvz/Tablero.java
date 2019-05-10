package pvz;

public class Tablero {

    private Personaje tablero [][];
    private int filas;
    private int columnas;
    static final int solesInicial = 50; //soles al inicio de partida

    public Tablero(int filas, int columnas){
        this.filas = filas;
        this.columnas = columnas;
        tablero = new Personaje[filas][columnas];
       /* for (int i=0; i < filas; i++){
            for (int j=0; j < filas; j++){
                tablero[i][j] = null;
            }
        }*/
    }

    public void añadirPersonaje (Personaje personaje, int fila, int columna){
        tablero[fila][columna] = personaje;
    }

    //hay que añadirle a este método las decoraciones
    public void imprimeTablero (){
        for (int i=0; i < filas; i++){
            for (int j=0; j < columnas; j++){
                if (tablero[i][j] != null) tablero[i][j].imprimir();
                else System.out.print("*");
            }
            System.out.println();
        }

    }

    public int getFilas(){
        return filas;
    }

    public int getColumnas(){
        return columnas;
    }


}
