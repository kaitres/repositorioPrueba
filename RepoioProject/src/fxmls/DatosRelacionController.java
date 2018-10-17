/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmls;


import clases.Diagrama;
import static clases.Diagrama.entidades;
import clases.Entidad;
import static fxmls.InterfazController.relacionActual;
import java.net.URL;
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
    
}
