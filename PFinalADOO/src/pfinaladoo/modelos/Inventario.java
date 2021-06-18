package pfinaladoo.modelos;

import java.util.ArrayList;

/**
 *
 * @author colin
 */
public class Inventario {
    private ArrayList<Producto> inventario;
    
    
    
    private void cargarInventario()
    {
        // se carga usando la conexion a la base de datos
    }
    
    // Getter and Setters

    public ArrayList<Producto> getInventario() {
        return inventario;
    }

    public void setInventario(ArrayList<Producto> inventario) {
        this.inventario = inventario;
    }
    
    
    
}
