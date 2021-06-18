package pfinaladoo.controladores;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import pfinaladoo.modelos.ManejoDB;
import pfinaladoo.modelos.Usuario;

public class FXMLMainController {

    // Variables de vista
    @FXML
    private Label label;

    @FXML
    private JFXButton btnInicarSesion;

    @FXML
    private JFXButton btnCerrar;

    @FXML
    private ToggleGroup tipoUser;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPass;
    
    @FXML
    private RadioButton radioADM;
    
    // Variables para funcionamiento.
    private ManejoDB manejo;
    private VentanasController vControl;
    private Usuario usuario;
    
    @FXML
    void cerrar(ActionEvent event) {
        this.vControl.cerrarVentana(this.btnCerrar);
    }

    @FXML
    void iniciarSesion(ActionEvent event) throws SQLException, IOException 
    {
        if(this.checkInputs())
        {
            
            this.usuario = new Usuario();
            this.usuario.setEmail(this.txtEmail.getText().toLowerCase());
            this.usuario.setPass(this.txtPass.getText());
            ResultSet rs = this.manejo.login(usuario, this.radioADM.isSelected());
            if(rs.next())
            {
                if(SecurityController.checkPass(this.usuario.getPass(), rs.getString("pass")))
                {
                    if(this.radioADM.isSelected())
                        this.usuario.setIdUsuario(String.valueOf(rs.getInt("idAdm")));
                    else
                        this.usuario.setIdUsuario(String.valueOf(rs.getInt("idEmp")));
                    
                    this.usuario.setApeM(rs.getString("apeM"));
                    this.usuario.setApeP(rs.getString("apep"));
                    this.usuario.setNombre(rs.getString("nombre"));
                    this.usuario.setTel(rs.getString("numero"));
                    
                    if(this.radioADM.isSelected())
                        this.vControl.cambioVentanaLoginADM("FXMLInicioADM", "Bienvenido Administrador", usuario);
                    else
                        this.vControl.cambioVentanaLoginEmpleado("FXMLInicioEmpleado", "Bienvenido Empleado", usuario);
                    
                    this.cerrar(event);
                }else
                    this.vControl.mostrarAlerta(null, "ERROR", "Datos incorrectos", new Alert(Alert.AlertType.ERROR));
                
            }else
            {
                this.vControl.mostrarAlerta(null, "Error", "No se encuentra usuario",new Alert(Alert.AlertType.ERROR));
            }
        }
    }
    
    private boolean checkInputs()
    {
        boolean ok = false;
        
        if(!"".equals(this.txtPass.getText()) && !"".equals(this.txtEmail.getText()))
            return true;
        else
            this.vControl.mostrarAlerta(null, "Error", "Error al iniciar sesion",new Alert(Alert.AlertType.ERROR));
        
        return ok;
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        this.manejo = new ManejoDB();
        this.vControl = new VentanasController();
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert btnInicarSesion != null : "fx:id=\"btnInicarSesion\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert btnCerrar != null : "fx:id=\"btnCerrar\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert tipoUser != null : "fx:id=\"tipoUser\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'FXMLMain.fxml'.";
        assert txtPass != null : "fx:id=\"txtPass\" was not injected: check your FXML file 'FXMLMain.fxml'.";

    }
}