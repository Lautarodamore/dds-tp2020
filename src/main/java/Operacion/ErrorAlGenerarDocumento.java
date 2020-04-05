package Operacion;

public class ErrorAlGenerarDocumento extends Exception {
    public ErrorAlGenerarDocumento(String documento) {
        super("No se puede generar el documento: " + documento);
    }
}
