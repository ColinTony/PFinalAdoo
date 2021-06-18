package pfinaladoo.controladores;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import pfinaladoo.modelos.ManejoDB;
import pfinaladoo.modelos.Usuario;

public class FXMLAdmAdminsController {
    // data
    private Usuario user;
    private Usuario tempUser;
    private ManejoDB manejo;
    private ArrayList<Usuario> users;
    private VentanasController vController;
    private boolean isADMS;
    
    // View
    @FXML
    private TableView<Usuario> tableUsers;

    @FXML
    private TableColumn<Usuario, String> colID;

    @FXML
    private TableColumn<Usuario, String> colNombre;

    @FXML
    private TableColumn<Usuario, String> colEmail;

    @FXML
    private TableColumn<Usuario, String> colTel;

    @FXML
    private JFXButton btnEdt;

    @FXML
    private JFXButton btnElim;

    @FXML
    private JFXButton btnVolver;

    @FXML
    private JFXButton btnNuevo;

    @FXML
    private JFXButton btnActualizar;
    
    @FXML
    private Text txtTitulo;

    @FXML
    void editarUser(ActionEvent event) throws IOException, SQLException 
    {
        this.obtenerSelect();
        this.vController.cambioVentanaModAltaUsers("FXMLNuevoAdm", "Editar Usuario", this.user, this.tempUser, true,this.isADMS);
    }

    @FXML
    void eliminarUser(ActionEvent event) throws SQLException 
    {
        this.obtenerSelect();
        boolean ok =this.vController.mostrarAlertaConfirm(null, "Confirmar", "¿Seguro quieres eliminar al usuario?");
        // nota , nos podemos eliminar a nosotros mismos xD
        if (ok)// eliminamos al usuario
        {
            boolean notElimYouSelf = false;
            if(this.tempUser.getIdUsuario().equals(this.user.getIdUsuario()) && this.isADMS)
                notElimYouSelf = true;
            
            if(notElimYouSelf)
                this.vController.mostrarAlerta(null, "Error", "No te puedes eliminar a ti mismo", new Alert(Alert.AlertType.ERROR));
            else
            {
                if(this.manejo.elimUsuarios(this.tempUser, this.isADMS))
                    this.vController.mostrarAlerta(null, "Eliminar", "Usuario eliminado", new Alert(Alert.AlertType.INFORMATION));
                else
                    this.vController.mostrarAlerta(null, "Error", "No se pudo eliminar", new Alert(Alert.AlertType.ERROR));
            }
                
            
        }
        this.refreshTable(event);
    }

    @FXML
    void nuevoAdm(ActionEvent event) throws IOException, SQLException 
    {
        this.obtenerSelect();
        this.vController.cambioVentanaModAltaUsers("FXMLNuevoAdm", "Nuevo ADM", this.user, this.tempUser, false,this.isADMS);
    }

    @FXML
    void refreshTable(ActionEvent event) throws SQLException 
    {
        this.obtenerUsuarios();
        this.tableUsers.refresh();
    }

    @FXML
    void volver(ActionEvent event) throws IOException
    {
        this.vController.cerrarVentana(btnEdt);
        this.vController.cambioVentanaLoginADM("FXMLInicioADM", "Bienvenido Administrador", user);
        
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        this.vController = new VentanasController();
        assert tableUsers != null : "fx:id=\"tableUsers\" was not injected: check your FXML file 'FXMLAdmAdmins.fxml'.";
        assert colID != null : "fx:id=\"colID\" was not injected: check your FXML file 'FXMLAdmAdmins.fxml'.";
        assert colNombre != null : "fx:id=\"colNombre\" was not injected: check your FXML file 'FXMLAdmAdmins.fxml'.";
        assert colEmail != null : "fx:id=\"colEmail\" was not injected: check your FXML file 'FXMLAdmAdmins.fxml'.";
        assert colTel != null : "fx:id=\"colTel\" was not injected: check your FXML file 'FXMLAdmAdmins.fxml'.";
        assert btnEdt != null : "fx:id=\"btnEdt\" was not injected: check your FXML file 'FXMLAdmAdmins.fxml'.";
        assert btnElim != null : "fx:id=\"btnElim\" was not injected: check your FXML file 'FXMLAdmAdmins.fxml'.";
        assert btnVolver != null : "fx:id=\"btnVolver\" was not injected: check your FXML file 'FXMLAdmAdmins.fxml'.";
        assert btnNuevo != null : "fx:id=\"btnNuevo\" was not injected: check your FXML file 'FXMLAdmAdmins.fxml'.";
        assert btnActualizar != null : "fx:id=\"btnActualizar\" was not injected: check your FXML file 'FXMLAdmAdmins.fxml'.";
        assert txtTitulo != null : "fx:id=\"txtTitulo\" was not injected: check your FXML file 'FXMLAdmAdmins.fxml'.";

        this.users = new ArrayList<>();
        // obteniendo usuarios de la bd
        this.manejo = new ManejoDB();
        
        // configurando la tabla
        this.colID.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        this.obtenerUsuarios();
        
    }
    
    public void obtenerSelect()
    {
        this.tempUser = this.tableUsers.getSelectionModel().getSelectedItem();
    }
    
    public ObservableList<Usuario> observableList()
    {
        ObservableList<Usuario> ObsUsers = FXCollections.observableArrayList();
        ObsUsers.addAll(this.users);
        return ObsUsers;
    }
    
    public void obtenerUsuarios() throws SQLException
    {
        if(this.isADMS)
        {
            this.users = this.manejo.verUsuarios(true);
            this.tableUsers.setItems(observableList());
        }else
        {
            this.users = this.manejo.verUsuarios(false);
            this.tableUsers.setItems(observableList());
        }
    }
    
    void obtenerDatos(Usuario user,boolean isAdms) throws SQLException 
    {
        this.user = user;
        this.isADMS = isAdms;
        if(!isAdms)
            this.txtTitulo.setText("Lista de empleados");
        this.obtenerUsuarios();
    }
}
