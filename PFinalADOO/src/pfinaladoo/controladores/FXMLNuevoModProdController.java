package pfinaladoo.controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pfinaladoo.modelos.ManejoDB;
import pfinaladoo.modelos.Producto;
import pfinaladoo.modelos.Tipo;
import pfinaladoo.modelos.Usuario;

public class FXMLNuevoModProdController {

    private Producto prod;
    private Usuario user;
    private boolean isMod;
    private ManejoDB manejo;
    private VentanasController vController;
    private ArrayList<Tipo>tipos;
    
    @FXML
    private Text txtTitulo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtDisp;

    @FXML
    private JFXComboBox<String> comboTipo;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnGuardar;

    @FXML
    void cerrar(ActionEvent event) 
    {
        this.vController.cerrarVentana(btnCancelar);
    }

    @FXML
    void guardarDatos(ActionEvent event) throws SQLException 
    {
        obtenerDatos();
        if(this.isMod)
        {
            if(this.manejo.modificarAddProducto(prod, isMod))
                this.vController.mostrarAlerta(null, "Confirmar", "Se guardo modificaion", new Alert(Alert.AlertType.INFORMATION));
            else
                this.vController.mostrarAlerta(null, "Error", "No se puede modificar", new Alert(Alert.AlertType.ERROR));
            
        }else
        {   
            if(this.manejo.modificarAddProducto(prod, isMod))
                this.vController.mostrarAlerta(null, "Confirmar", "Se guardo nuevo producto", new Alert(Alert.AlertType.INFORMATION));
            else
                this.vController.mostrarAlerta(null, "Error", "No se puede dar de alta", new Alert(Alert.AlertType.ERROR));
        }
        this.vController.cerrarVentana(btnGuardar);
    }
    public void obtenerDatos() throws SQLException
    {
        if(this.checkInputs())
        {
            Tipo tipo = this.manejo.getTipoByName(this.comboTipo.getSelectionModel().getSelectedItem());
            this.prod.setNombre(this.txtNombre.getText());
            this.prod.setPrecio(this.txtPrecio.getText());
            this.prod.setDisponibilidad(this.txtDisp.getText());
            this.prod.setTipo(tipo.getTipo());
            this.prod.setIdTipo(tipo.getIdTipo());
            
        }
        
    }
    public boolean checkInputs()
    {
        boolean ok=true;
        if(this.txtDisp.getText().equals("")   ||
           this.txtNombre.getText().equals("") ||
           this.txtPrecio.getText().equals(""))
        {
            ok = false;
        }
        return ok;
    }
    
    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        assert txtTitulo != null : "fx:id=\"txtTitulo\" was not injected: check your FXML file 'FXMLNuevoModProd.fxml'.";
        assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'FXMLNuevoModProd.fxml'.";
        assert txtPrecio != null : "fx:id=\"txtPrecio\" was not injected: check your FXML file 'FXMLNuevoModProd.fxml'.";
        assert txtDisp != null : "fx:id=\"txtDisp\" was not injected: check your FXML file 'FXMLNuevoModProd.fxml'.";
        assert comboTipo != null : "fx:id=\"comboTipo\" was not injected: check your FXML file 'FXMLNuevoModProd.fxml'.";
        assert btnCancelar != null : "fx:id=\"btnCancelar\" was not injected: check your FXML file 'FXMLNuevoModProd.fxml'.";
        assert btnGuardar != null : "fx:id=\"btnGuardar\" was not injected: check your FXML file 'FXMLNuevoModProd.fxml'.";
        this.manejo = new ManejoDB();
        this.vController = new VentanasController();
    }

    void obtenerDatos(Usuario adm, Producto prod, boolean mod) throws SQLException {
        this.user = adm;
        this.isMod = mod;
        this.prod = prod;
        this.obtenerArrayTipo();
        this.llenarCombo();
        if(mod)
        {
            Tipo tipo = this.manejo.getTipoByID(prod.getIdTipo());
            this.prod.setTipo(tipo.getTipo());
            this.prod = prod;
            this.txtTitulo.setText("Modificar Preoducto");
            this.txtNombre.setText(prod.getNombre());
            this.txtPrecio.setText(prod.getPrecio());
            this.txtDisp.setText(prod.getDisponibilidad());
            this.comboTipo.getSelectionModel().select(this.prod.getTipo());
        }
    }
    public void obtenerArrayTipo() throws SQLException
    {
        this.tipos = this.manejo.getTiposDB();
    }
    public void llenarCombo()
    {
        this.comboTipo.getItems().removeAll(this.comboTipo.getItems());
        for(Tipo tip : tipos) 
            this.comboTipo.getItems().add(tip.getTipo());
    }
    public void getTipoNameById() throws SQLException
    {
       
    }
}
