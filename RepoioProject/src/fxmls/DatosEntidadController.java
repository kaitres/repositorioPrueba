/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmls;

import static fxmls.InterfazController.entidadActual;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author The.N
 */
public class DatosEntidadController implements Initializable {
    public TextField nombre;
    @FXML private Button canBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nombre.setText(entidadActual.getNombre());
    }    
    
    @FXML
    public void modificar(){
        if(!(nombre.getText().equals(""))){
           entidadActual.setNombre(nombre.getText());
           Stage stage = (Stage) canBtn.getScene().getWindow();
           stage.close(); 
        }
        else{
            Alert alert = new Alert(AlertType.ERROR);
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
}
