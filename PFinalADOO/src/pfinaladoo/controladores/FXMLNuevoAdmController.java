package pfinaladoo.controladores;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import pfinaladoo.modelos.ManejoDB;
import pfinaladoo.modelos.Usuario;

public class FXMLNuevoAdmController {

    private Usuario user;
    private Usuario userTemp; // sirve por si va modificar
    private boolean isMod;
    private boolean isADM;
    private VentanasController vController;
    private ManejoDB manejo;
    
    
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPaterno;
    @FXML
    private TextField txtMaterno;
    @FXML
    private TextField txtEmail;
    @FXML
    private RadioButton radioADM;
    @FXML
    private ToggleGroup tipoUsuario;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Text textNewMod;
    @FXML
    private TextField txtTel;
    @FXML
    private RadioButton radioEmp;
    @FXML
    void cancelar(ActionEvent event) 
    {
        this.vController.cerrarVentana(this.btnCancelar);
    }

    @FXML
    void guardar(ActionEvent event) throws SQLException 
    {
        // checamos si es mod
        if(this.isMod)
        {
            // cehamos los inputs llenos
            if(this.checkInputs())
            {
                this.setDatosUserTemp();
                
                if(!this.txtPass.getText().equals("")) // cambio la contra
                    userTemp.setPass(SecurityController.hashPass(txtPass.getText()));
                // mandamos la mod
                if(this.radioADM.isSelected())
                {
                    if(this.manejo.addModUsers(this.userTemp, isMod, true)){
                        this.vController.mostrarAlerta(null, "Correcto", "Modificacion correcta", new Alert(Alert.AlertType.INFORMATION));
                        this.vController.cerrarVentana(btnGuardar);
                    }else
                        this.vController.mostrarAlerta(null, "ERROR", "Ocurrio un error en la DB", new Alert(Alert.AlertType.ERROR));
                }else
                {
                    if(this.manejo.addModUsers(this.userTemp, isMod, false)){
                        this.vController.mostrarAlerta(null, "Correcto", "Modificacion correcta", new Alert(Alert.AlertType.INFORMATION));
                        this.vController.cerrarVentana(btnGuardar);
                    }else
                        this.vController.mostrarAlerta(null, "ERROR", "Ocurrio un error en la DB", new Alert(Alert.AlertType.ERROR));
                }
                
            }else
                this.vController.mostrarAlerta(null, "Error", "Llenar todos los campos",new Alert(Alert.AlertType.ERROR));
        }else
        {
            // es alta
            if(this.checkInputs())
            {
                // agregamos que no este vacio el pass
                if(!this.txtPass.getText().equals(""))
                {
                    this.setDatosUserTemp();
                    this.userTemp.setPass(SecurityController.hashPass(this.txtPass.getText()));
                    if(this.manejo.addModUsers(this.userTemp, isMod, this.radioADM.isSelected())){
                        this.vController.mostrarAlerta(null, "Correcto", "Nuevo usuario agregado", new Alert(Alert.AlertType.INFORMATION));
                        this.vController.cerrarVentana(btnGuardar);
                    }else
                        this.vController.mostrarAlerta(null, "ERROR", "Ocurrio un error en la DB", new Alert(Alert.AlertType.ERROR));
                }else
                    this.vController.mostrarAlerta(null, "ERROR", "Favor de llenar los campos", new Alert(Alert.AlertType.ERROR));
            }
        }
    }
    
    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert textNewMod != null : "fx:id=\"textNewMod\" was not injected: check your FXML file 'FXMLNuevoAdm.fxml'.";
        assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'FXMLNuevoAdm.fxml'.";
        assert txtPaterno != null : "fx:id=\"txtPaterno\" was not injected: check your FXML file 'FXMLNuevoAdm.fxml'.";
        assert txtMaterno != null : "fx:id=\"txtMaterno\" was not injected: check your FXML file 'FXMLNuevoAdm.fxml'.";
        assert txtTel != null : "fx:id=\"txtTel\" was not injected: check your FXML file 'FXMLNuevoAdm.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'FXMLNuevoAdm.fxml'.";
        assert radioADM != null : "fx:id=\"radioADM\" was not injected: check your FXML file 'FXMLNuevoAdm.fxml'.";
        assert tipoUsuario != null : "fx:id=\"tipoUsuario\" was not injected: check your FXML file 'FXMLNuevoAdm.fxml'.";
        assert btnCancelar != null : "fx:id=\"btnCancelar\" was not injected: check your FXML file 'FXMLNuevoAdm.fxml'.";
        assert btnGuardar != null : "fx:id=\"btnGuardar\" was not injected: check your FXML file 'FXMLNuevoAdm.fxml'.";
        assert txtPass != null : "fx:id=\"txtPass\" was not injected: check your FXML file 'FXMLNuevoAdm.fxml'.";
        this.vController = new VentanasController();
        this.manejo = new ManejoDB();
    }
    public void setDatosUserTemp()
    {
        this.userTemp.setNombre(this.txtNombre.getText());
        this.userTemp.setApeP(this.txtPaterno.getText());
        this.userTemp.setApeM(this.txtMaterno.getText());
        this.userTemp.setTel(this.txtTel.getText());
        this.userTemp.setEmail(this.txtEmail.getText());
    }
    public boolean checkInputs()
    {
        boolean ok = true;
        if(this.txtNombre.getText().equals("") ||
           this.txtPaterno.getText().equals("")||
           this.txtMaterno.getText().equals("")||
           this.txtTel.getText().equals("")
           )
        {
            ok = false;
        }
        
        
        return ok;
    }
    
    void obtenerDatos(Usuario user, Usuario tempUser, boolean mod, boolean isADM) throws SQLException 
    {
        this.isMod = mod;
        this.user = user;
        this.isADM = isADM;
        this.userTemp = new Usuario();
        if(isMod)
        {
            this.userTemp = this.manejo.getInfoUser(Integer.parseInt(tempUser.getIdUsuario()), this.isADM);
            this.textNewMod.setText("Modificar Usuario");
            this.txtEmail.setText(this.userTemp.getEmail());
            this.txtNombre.setText(this.userTemp.getNombre());
            this.txtMaterno.setText(this.userTemp.getApeM());
            this.txtPaterno.setText(this.userTemp.getApeP());
            this.txtTel.setText(this.userTemp.getTel());
            if(isADM)
            {
                this.radioADM.setDisable(true);
                this.radioEmp.setDisable(true);
                this.radioADM.setSelected(true);
                this.radioEmp.setSelected(false);
            }else
            {
                this.radioADM.setDisable(true);
                this.radioEmp.setSelected(true);
                this.radioADM.setSelected(false);
                this.radioEmp.setDisable(true);
            }
            
        }
        
    }
}
