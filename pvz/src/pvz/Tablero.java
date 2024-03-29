package pvz;


public class Tablero {

    private Personaje tablero [][];
    private int filas;
    private int columnas;
    private int soles = 50;


    public Tablero(int filas, int columnas){
        this.filas = filas;
        this.columnas = columnas;
        tablero = new Personaje[filas][columnas];
    }

    public void añadirPersonaje (Personaje personaje, int fila, int columna){
        tablero[fila][columna] = personaje;
    }

    public boolean casillaVacia(int fila, int columna){
        if(tablero[fila][columna] == null) return true;
        else return false;
    }

    public void eliminarPersonaje(int fila, int columna){
        tablero[fila][columna] = null ;
    }


    public void imprimeTablero (){
        for (int i=0; i < filas; i++){
            lineaDecoracion(columnas);
            System.out.println();
            for (int j=0; j < columnas; j++){
                System.out.print("|");
                if (tablero[i][j] != null){
                    System.out.print(" ");
                    tablero[i][j].imprimir();
                    System.out.print(" ");
                } else System.out.print("      ");

            }
            System.out.print("|");
            System.out.println();
        }
        lineaDecoracion(columnas);
        System.out.println();
    }

    private void lineaDecoracion(int columnas){
        System.out.print("|");
        for (int i = 0; i < columnas; i++){
            if(i== columnas-1) System.out.print("------|");
            else System.out.print("-------");
        }
    }

    public int getFilas(){
        return filas;
    }

    public int getColumnas(){
        return columnas;
    }


}
