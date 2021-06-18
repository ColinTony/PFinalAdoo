package pfinaladoo.controladores;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import pfinaladoo.modelos.Usuario;

public class FXMLInicioADMController {
    @FXML
    private Text txtNombre;

    @FXML
    private JFXButton btnAdmInv;

    @FXML
    private JFXButton btnAdmUsers;

    @FXML
    private JFXButton btnAdmVentas;

    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXButton btnAdmEmpleados;
    
    // Data
    private Usuario user;
    private VentanasController vController;
    
    @FXML
    void admEmp(ActionEvent event) throws IOException, SQLException 
    {
        this.vController.cambioVentanaAdmAdms("FXMLAdmAdmins", "Administrar Empleados", user,false);
        this.vController.cerrarVentana(btnCerrar);
    }
    
    @FXML
    void admUsuarios(ActionEvent event) throws IOException, SQLException 
    {
        // ventana para administrar Adms
        this.vController.cambioVentanaAdmAdms("FXMLAdmAdmins", "Administrar Admins", user,true);
        this.vController.cerrarVentana(btnCerrar);
    }
    
    @FXML
    void admInvent(ActionEvent event) throws IOException 
    {
        this.vController.cambioVentanaAdmInventario("FXMLAdmInventario", "Administrar inventario", user);
        this.vController.cerrarVentana(btnCerrar);
    }

    @FXML
    void admVentas(ActionEvent event) throws IOException 
    {
        this.vController.cambioVentanaAdmVentas("FXMLAdmVentas", "Grafico", user);
    }

    @FXML
    void cerrarSesion(ActionEvent event) 
    {
        this.vController.cambioVentana("FXMLMain", "Inicio sesión");
        this.vController.cerrarVentana(this.btnCerrar);
    }

    @FXML
    void initialize() 
    {
        this.vController = new VentanasController();
        assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'FXMLInicioADM.fxml'.";
        assert btnAdmInv != null : "fx:id=\"btnAdmInv\" was not injected: check your FXML file 'FXMLInicioADM.fxml'.";
        assert btnAdmUsers != null : "fx:id=\"btnAdmUsers\" was not injected: check your FXML file 'FXMLInicioADM.fxml'.";
        assert btnAdmVentas != null : "fx:id=\"btnAdmVentas\" was not injected: check your FXML file 'FXMLInicioADM.fxml'.";
        assert btnCerrar != null : "fx:id=\"btnCerrar\" was not injected: check your FXML file 'FXMLInicioADM.fxml'.";
    }

    void obtenerDatos(Usuario adm) {
        this.user = adm;
        this.txtNombre.setText(this.user.getNombre());
    }
}
