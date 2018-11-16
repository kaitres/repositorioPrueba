/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;


import clases.Diagrama;
import clases.Entidad;
import clases.Figura;
import clases.Propiedad;
import clases.Relacion;
import static controladores.InterfazController.compRelacion;
import static controladores.InterfazController.entidadActual;
import static controladores.InterfazController.posicionDefaultX;
import static controladores.InterfazController.posicionDefaultY;
import static controladores.InterfazController.relacionActual;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author The.N
 */
public class DatosRelacionController implements Initializable {

    public TextField nombre;
    public ListView lista;
    ObservableList<EntidadCheck> entidadCheck = FXCollections.observableArrayList();
    
    Alert alertEx = new Alert(Alert.AlertType.INFORMATION);
    
    @FXML private Button canBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InterfazController.propiedadActual = (ArrayList<Propiedad>) relacionActual.getPropiedades().clone();
        alertEx.setTitle("Error");
        alertEx.setHeaderText(null);
        alertEx.setContentText("Haz excedido el limite de 20 caracteres");
        
        nombre.setText(relacionActual.getNombre());
    }    
    
    @FXML
    public void modificar(){
        if(!(nombre.getText().equals(""))){
            if (nombre.getText().length()>20){
                alertEx.showAndWait();
            }else{
                relacionActual.setNombre(nombre.getText());
                relacionActual.setPropiedades((ArrayList<Propiedad>) InterfazController.propiedadActual.clone());
                Stage stage = (Stage) canBtn.getScene().getWindow();
                stage.close();
            } 
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("El nombre debe tener algun caracter");
            alert.showAndWait();
        }
        

    }
    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) canBtn.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    private void haciaPropiedad(ActionEvent event) throws IOException {
        AbrirVentana.CargarVista(getClass().getResource("/fxmls/EditarPropiedad.fxml"));
    }
    
    class EntidadCheck {
        private StringProperty nombre;
        private BooleanProperty check;

        public EntidadCheck(String nombre, boolean check) {
            this.nombre = new SimpleStringProperty(nombre);
            this.check = new SimpleBooleanProperty(check);
        }

        public StringProperty getNombre() { return nombre; }    
        public BooleanProperty getCheck() { return check; }
    }
    
    @FXML
    private void eliminarRelacion(){
        InterfazController.diagrama.getRelaciones().remove(relacionActual);
        
        
        Stage stage = (Stage) canBtn.getScene().getWindow();
        stage.close();
        
                
    }
}
