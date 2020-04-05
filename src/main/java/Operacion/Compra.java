package Operacion;

import Documento.DocumentoComercial;
import Documento.Remito;
import Item.Item;
import Item.Articulo;

import java.util.ArrayList;

public class Compra implements Operacion {
    public final int id;
    private ArrayList<Item> items;
    private boolean estaCerrada;
    private double precioFinal;

    public Compra(int id) {
        this.id = id;
        this.items = new ArrayList<>();
        this.estaCerrada = false;
    }

    public void agregarItem(Item item) throws OperacionCerrada {
        if(this.estaCerrada) throw new OperacionCerrada();
        this.items.add(item);
    }

    public double obtenerPrecio() {
        return this.precioFinal;
    }

    public void cerrarOperacion() {
        this.estaCerrada = true;
        this.precioFinal = this.calcularPrecio();
    }

    public Remito generarRemito() throws ErrorAlGenerarDocumento {
        if(this.generaDocumentoComercial()) return new Remito(this.id);
        throw new ErrorAlGenerarDocumento(Remito.class.getName());
    }

    private boolean generaDocumentoComercial() {
        return this.items.stream().allMatch(item -> item.getClass() == Articulo.class);
    }

    private double calcularPrecio() {
        return this.items.stream().mapToDouble(item -> item.obtenerPrecio()).sum();
    }
}
