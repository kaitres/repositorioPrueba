/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmls;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cmsan
 */
public class CrearEntidadController implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private Button canBtn;
    @FXML
    private Button aceBtn;
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Debes ponerle un nombre");
    }    

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) canBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void aceptar(ActionEvent event) {
        if (nombre.getText().length()!=0){
            InterfazController.newEntidadNombre = nombre.getText();
            InterfazController.entidadValidacion = true;
            Stage stage = (Stage) aceBtn.getScene().getWindow();
            stage.close();
        } else{
            alert.showAndWait();
        }
        
    }

    @FXML
    private void txtField(ActionEvent event) {
        if (nombre.getText().length()!=0){
            InterfazController.newEntidadNombre = nombre.getText();
            InterfazController.entidadValidacion = true;
            Stage stage = (Stage) nombre.getScene().getWindow();
            stage.close();
        } else{
            alert.showAndWait();
        }
    }
    
}
