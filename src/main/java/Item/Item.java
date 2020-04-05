package Item;

public abstract class Item {
    private double precio;

    public Item(double precio) {
        this.precio = precio;
    }

    public double obtenerPrecio() {
        return this.precio;
    }

    public void cambiarPrecio(double precio) {
        this.precio = precio;
    }
}
