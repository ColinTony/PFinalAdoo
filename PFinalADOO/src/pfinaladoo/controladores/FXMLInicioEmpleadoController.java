package pfinaladoo.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import pfinaladoo.modelos.Usuario;

/**
 * FXML Controller class
 *
 * @author colin
 */
public class FXMLInicioEmpleadoController implements Initializable {

    private Usuario user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void obtenerDatos(Usuario empl) {
        this.user = empl;
    }
    
}
