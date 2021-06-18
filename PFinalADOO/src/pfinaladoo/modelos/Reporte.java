package pfinaladoo.modelos;

import java.util.ArrayList;

/**
 *
 * @author colin
 */
public class Reporte 
{
    private ArrayList<Venta> ventas;
    
    public void graficarDatos()
    {
        
    }

    public Reporte(ArrayList<Venta> ventas) {
        this.ventas = ventas;
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(ArrayList<Venta> ventas) {
        this.ventas = ventas;
    }
    
    
}
