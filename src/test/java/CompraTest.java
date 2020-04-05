import Documento.Remito;
import Item.Articulo;
import Item.Servicio;
import Operacion.Compra;
import Operacion.ErrorAlGenerarDocumento;
import Operacion.OperacionCerrada;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompraTest {

    @Test
    public void obtenerPrecioBaseDeUnaCompra() throws OperacionCerrada {
        agregarItemsACompra();
        compra.cerrarOperacion();

        double total = compra.obtenerPrecio();

        assertEquals(total, 50.25, 0.1);
    }

    @Test(expected = OperacionCerrada.class)
    public void cambiarPrecioACompraCerrada() throws OperacionCerrada {
        compra.agregarItem(articulo);
        compra.cerrarOperacion();

        compra.agregarItem(otroArticulo);
    }

    @Test
    public void cambiarPrecioItem() throws OperacionCerrada {
        agregarItemsACompra();
        compra.cerrarOperacion();
        articulo.cambiarPrecio(50);

        double total = compra.obtenerPrecio();

        assertEquals(total, 50.25, 0.1);
    }

    @Test
    public void generarRemito() throws OperacionCerrada, ErrorAlGenerarDocumento {
        agregarItemsACompra();
        compra.cerrarOperacion();

        Remito remito = compra.generarRemito();

        assertEquals(remito.compraId, compra.id);
    }

    @Test(expected = ErrorAlGenerarDocumento.class)
    public void tratarDeGenerarRemito() throws OperacionCerrada, ErrorAlGenerarDocumento {
        agregarItemsACompra();
        compra.agregarItem(servicio);
        compra.cerrarOperacion();

        compra.generarRemito();
    }


    Compra compra = new Compra(1);
    Articulo articulo = new Articulo(20);
    Articulo otroArticulo = new Articulo(30.25);
    Servicio servicio = new Servicio(10.50);

    public void agregarItemsACompra() throws OperacionCerrada {
        compra.agregarItem(articulo);
        compra.agregarItem(otroArticulo);
    }
}
