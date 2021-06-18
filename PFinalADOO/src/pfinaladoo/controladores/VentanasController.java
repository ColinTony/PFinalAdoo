
package pfinaladoo.controladores;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pfinaladoo.modelos.Producto;
import pfinaladoo.modelos.Usuario;

/**
 *
 * @author colin
 */
public class VentanasController 
{
    private final String RUTA_VISTAS = "/pfinaladoo/vistas/";
   
    
    public void cerrarVentana(JFXButton btnCerrar)
    {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }
    
    public void cambioVentana(String nameFXML,String titulo)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.RUTA_VISTAS+nameFXML+".fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            
            stage.setTitle(titulo);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(VentanasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cambioVentanaLoginADM(String nameFXML,String titulo,Usuario adm) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.RUTA_VISTAS+nameFXML+".fxml"));
        
        Parent root1 = (Parent) fxmlLoader.load();
        FXMLInicioADMController controller = fxmlLoader.<FXMLInicioADMController>getController();
        controller.obtenerDatos(adm);
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        
        stage.setTitle(titulo);
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
    public void cambioVentanaLoginEmpleado(String nameFXML,String titulo,Usuario empl) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.RUTA_VISTAS+nameFXML+".fxml"));
        
        Parent root1 = (Parent) fxmlLoader.load();
        FXMLInicioEmpleadoController controller = fxmlLoader.<FXMLInicioEmpleadoController>getController();
        controller.obtenerDatos(empl);
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        
        stage.setTitle(titulo);
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
    public void cambioVentanaAdmAdms(String nameFXML,String titulo,Usuario user,boolean isAdms) throws IOException, SQLException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.RUTA_VISTAS+nameFXML+".fxml"));
        
        Parent root1 = (Parent) fxmlLoader.load();
        FXMLAdmAdminsController controller = fxmlLoader.<FXMLAdmAdminsController>getController();
        controller.obtenerDatos(user,isAdms);
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        
        stage.setTitle(titulo);
        stage.setScene(new Scene(root1));
        stage.show();
    }
    public void cambioVentanaAdmInventario(String nameFXML,String titulo,Usuario adm) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.RUTA_VISTAS+nameFXML+".fxml"));
        
        Parent root1 = (Parent) fxmlLoader.load();
        FXMLAdmInventarioController controller = fxmlLoader.<FXMLAdmInventarioController>getController();
        controller.obtenerDatos(adm);
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        
        stage.setTitle(titulo);
        stage.setScene(new Scene(root1));
        stage.show();
    }
    public void cambioVentanaAdmVentas(String nameFXML,String titulo,Usuario adm) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.RUTA_VISTAS+nameFXML+".fxml"));
        
        Parent root1 = (Parent) fxmlLoader.load();
        FXMLAdmVentasController controller = fxmlLoader.<FXMLAdmVentasController>getController();
        controller.obtenerDatos(adm);
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        
        stage.setTitle(titulo);
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
    public void cambioVentanaNuevoModProd(String nameFXML, String titulo, Usuario adm, Producto prod,boolean isMod) throws IOException, SQLException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.RUTA_VISTAS+nameFXML+".fxml"));
        
        Parent root1 = (Parent) fxmlLoader.load();
        FXMLNuevoModProdController controller = fxmlLoader.<FXMLNuevoModProdController>getController();
        controller.obtenerDatos(adm,prod,isMod);
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        
        stage.setTitle(titulo);
        stage.setScene(new Scene(root1));
        stage.show();
    }
    public void cambioVentanaModAltaUsers(String nameFXML,String titulo,Usuario user,Usuario tempUser,boolean isMod,boolean isADM) throws IOException, SQLException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.RUTA_VISTAS+nameFXML+".fxml"));
        
        Parent root1 = (Parent) fxmlLoader.load();
        FXMLNuevoAdmController controller = fxmlLoader.<FXMLNuevoAdmController>getController();
        controller.obtenerDatos(user,tempUser,isMod,isADM);
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        
        stage.setTitle(titulo);
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
    
    // Mensajes
    public void mostrarAlerta(String headerText,String title,String msg,Alert alert)
    {
        alert.setHeaderText(headerText);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
    public boolean mostrarAlertaConfirm(String headerText,String title,String msg)
    {
        boolean ok = false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(headerText);
        alert.setTitle(title);
        alert.setContentText(msg);
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.get() == ButtonType.OK){
            ok = true;
        }else
        {
            ok = false;
        }
        return ok;
    }
}
