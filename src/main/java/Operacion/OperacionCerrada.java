package Operacion;

public class OperacionCerrada extends Exception {
    public OperacionCerrada() {
        super("Esta operacion esta cerrada y no se puede cambiar el precio");
    }
}
