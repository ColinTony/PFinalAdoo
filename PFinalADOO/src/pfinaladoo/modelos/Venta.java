package pfinaladoo.modelos;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author colin
 */
public class Venta {
    
    private ArrayList<Producto> productos;
    private float precioTotal;
    private Date fecha;
    
    public Venta (ArrayList<Producto> productos)
    {
        this.productos = productos;
        this.precioTotal = 0.0f;
    }

    private void calcularTotal()
    {
        for(Producto producto : this.productos)
            this.precioTotal += Float.parseFloat(producto.getPrecio());
    }
    
    
    // Getters AND Setters
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
