
package pfinaladoo.modelos;

/**
 *
 * @author colin
 */
public class Tipo {
    private String idTipo;
    private String tipo;

    public Tipo(String idTipo, String tipo) {
        this.idTipo = idTipo;
        this.tipo = tipo;
    }
    
    public Tipo()
    {
        
    }
    
    // getters and setters

    public String getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(String idTipo) {
        this.idTipo = idTipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
