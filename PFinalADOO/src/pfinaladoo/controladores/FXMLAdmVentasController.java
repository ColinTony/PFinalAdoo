package pfinaladoo.controladores;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.StackedBarChart;
import pfinaladoo.modelos.Usuario;

public class FXMLAdmVentasController {
    private Usuario user;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackedBarChart<?, ?> ventasChart;

    @FXML
    private JFXButton btnVolver;

    @FXML
    void cerrar(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert ventasChart != null : "fx:id=\"ventasChart\" was not injected: check your FXML file 'FXMLAdmVentas.fxml'.";
        assert btnVolver != null : "fx:id=\"btnVolver\" was not injected: check your FXML file 'FXMLAdmVentas.fxml'.";

    }

    void obtenerDatos(Usuario adm) {
       user = adm;
    }
}
