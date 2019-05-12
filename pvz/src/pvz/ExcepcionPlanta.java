package pvz;

public class ExcepcionPlanta extends Exception {

    public ExcepcionPlanta(String error){
        super(error);
    }

    /*
    public ExcepcionPlanta (int soles, int coste) throws ExcepcionPlanta {
        if (coste > soles) {
            throw new ExcepcionPlanta("No te lo puedes permitir. ");
        }
    }*/

}
