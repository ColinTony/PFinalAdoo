package pfinaladoo.controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pfinaladoo.modelos.ManejoDB;
import pfinaladoo.modelos.Producto;
import pfinaladoo.modelos.Tipo;
import pfinaladoo.modelos.Usuario;

public class FXMLAdmInventarioController {

    private Usuario user;
    private VentanasController vController;
    private Producto prod;
    private Tipo tipo;
    private ManejoDB manejo;
    private ArrayList<Producto> productos;
    private ArrayList<Tipo> tipos;
    
    @FXML
    private TableView<Producto> tableProd;

    @FXML
    private TableColumn<Producto, String> colId;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, String> colPrecio;

    @FXML
    private TableColumn<Producto, String> colDisponible;

    @FXML
    private JFXButton btnActualizar;

    @FXML
    private JFXButton btnNuevoProd;

    @FXML
    private JFXButton btnProducto;

    @FXML
    private JFXButton btnModificar;

    @FXML
    private JFXButton btnVolver;

    @FXML
    private JFXComboBox<String> comboTipo;

    @FXML
    private TextField txtTipo;

    @FXML
    private JFXButton btnAddTipo;

    @FXML
    void cerrar(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        this.vController.cambioVentanaLoginADM("FXMLInicioADM", "Bienvenido", user);
        this.vController.cerrarVentana(btnVolver);
    }

    @FXML
    void eliminarProd(ActionEvent event) throws SQLException 
    {
        this.obtenerProdSelect();
        if(this.vController.mostrarAlertaConfirm(null, "Eliminar", "¿Deseas eleiminar el producto?"));
        {
            if(this.manejo.eliminarProducto(this.prod))
                this.vController.mostrarAlerta(null, "Eliminar", "Eliminacion correcta", new Alert(Alert.AlertType.INFORMATION));
            else
                this.vController.mostrarAlerta(null, "Error", "Error no se pudo eliminar", new Alert(Alert.AlertType.ERROR));
        }
        this.refreshTable(event);
    }

    @FXML
    void modificar(ActionEvent event) throws IOException, SQLException 
    {
        this.obtenerProdSelect();
        this.vController.cambioVentanaNuevoModProd("FXMLNuevoModProd", "Modificar", user, this.prod, true);
    }

    @FXML
    void nuevoProd(ActionEvent event) throws IOException, SQLException 
    {
        this.obtenerProdSelect();
        this.vController.cambioVentanaNuevoModProd("FXMLNuevoModProd", "Nuevo Producto", user, this.prod, false);
    }

    @FXML
    void nuevoTipo(ActionEvent event) throws SQLException 
    {
        if(!this.txtTipo.getText().equals(""))
        {
            if(this.manejo.nuevoTipo(this.txtTipo.getText()))
                this.vController.mostrarAlerta(null, "Correct", "Nuevo tipo añadido", new Alert(Alert.AlertType.INFORMATION));
            else
                this.vController.mostrarAlerta(null, "Error", "Nuevo tipo no se pudo añadir", new Alert(Alert.AlertType.ERROR));
                
        }else
            this.vController.mostrarAlerta(null, "Error", "llenar el campo tipo",new Alert(Alert.AlertType.ERROR));
        
        this.txtTipo.setText("");
        this.obtenerArrayTipo();
        this.llenarCombo();
    }

    @FXML
    void obtenerTipos(ActionEvent event) throws SQLException 
    {
        this.obtenerArrayProd(true);
    }

    @FXML
    void refreshTable(ActionEvent event) throws SQLException
    {
        this.obtenerArrayTipo();
        this.obtenerArrayProd(false);
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException 
    {
        assert tableProd != null : "fx:id=\"tableProd\" was not injected: check your FXML file 'FXMLAdmInventario.fxml'.";
        assert colId != null : "fx:id=\"colId\" was not injected: check your FXML file 'FXMLAdmInventario.fxml'.";
        assert colNombre != null : "fx:id=\"colNombre\" was not injected: check your FXML file 'FXMLAdmInventario.fxml'.";
        assert colPrecio != null : "fx:id=\"colPrecio\" was not injected: check your FXML file 'FXMLAdmInventario.fxml'.";
        assert colDisponible != null : "fx:id=\"colDisponible\" was not injected: check your FXML file 'FXMLAdmInventario.fxml'.";
        assert btnActualizar != null : "fx:id=\"btnActualizar\" was not injected: check your FXML file 'FXMLAdmInventario.fxml'.";
        assert btnNuevoProd != null : "fx:id=\"btnNuevoProd\" was not injected: check your FXML file 'FXMLAdmInventario.fxml'.";
        assert btnProducto != null : "fx:id=\"btnProducto\" was not injected: check your FXML file 'FXMLAdmInventario.fxml'.";
        assert btnModificar != null : "fx:id=\"btnModificar\" was not injected: check your FXML file 'FXMLAdmInventario.fxml'.";
        assert btnVolver != null : "fx:id=\"btnVolver\" was not injected: check your FXML file 'FXMLAdmInventario.fxml'.";
        assert comboTipo != null : "fx:id=\"comboTipo\" was not injected: check your FXML file 'FXMLAdmInventario.fxml'.";
        assert txtTipo != null : "fx:id=\"txtTipo\" was not injected: check your FXML file 'FXMLAdmInventario.fxml'.";
        assert btnAddTipo != null : "fx:id=\"btnAddTipo\" was not injected: check your FXML file 'FXMLAdmInventario.fxml'.";
        this.vController = new VentanasController();
        // configurando tabla
        this.colId.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colDisponible.setCellValueFactory(new PropertyValueFactory<>("disponibilidad"));
        
        // obtener Arrays de la DB
        this.manejo = new ManejoDB();
        this.obtenerArrayTipo();
        this.obtenerArrayProd(false);
        // llenado el combo
        this.llenarCombo();
    }
    public void llenarCombo()
    {
        this.comboTipo.getItems().removeAll(this.comboTipo.getItems());
        for(Tipo tip : tipos) 
            this.comboTipo.getItems().add(tip.getTipo());
    }
    public ObservableList<Producto> observableList()
    {
        ObservableList<Producto> ObsProd = FXCollections.observableArrayList();
        ObsProd.addAll(this.productos);
        return ObsProd;
    }
    
    public void obtenerArrayTipo() throws SQLException
    {
        this.tipos = this.manejo.getTiposDB();
    }
    public void obtenerArrayProd(boolean forType) throws SQLException
    {
        if(forType)
            this.productos = this.manejo.verInventario(forType, this.comboTipo.getSelectionModel().getSelectedItem());
        else
            this.productos = this.manejo.verInventario(forType, "");
        
        this.tableProd.setItems(this.observableList());
    }

    void obtenerDatos(Usuario adm) {
        this.user = adm;
    }
    public void obtenerProdSelect()
    {
        this.prod = this.tableProd.getSelectionModel().getSelectedItem();
        System.out.println(this.prod.getDisponibilidad());
    }
    
}
