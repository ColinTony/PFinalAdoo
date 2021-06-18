
package pfinaladoo.modelos;

/**
 *
 * @author colin
 */
public class Producto {
    
    private String idProducto;
    private String nombre;
    private String tipo;
    private String idTipo;
    private String disponibilidad;
    private String precio;

    // constructores

    public Producto(String idProducto, String nombre, String tipo, String idTipo, String disponibilidad, String precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.tipo = tipo;
        this.idTipo = idTipo;
        this.disponibilidad = disponibilidad;
        this.precio = precio;
    }

    
    public Producto(){}

    // Getter and setters
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(String idTipo) {
        this.idTipo = idTipo;
    }
    
    
    
    
    
}
