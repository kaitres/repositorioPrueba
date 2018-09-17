/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmls;

import clases.Entidad;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cmsan
 */
public class CrearRelacionController implements Initializable {

    @FXML
    private Button canBtn;
    @FXML
    private Button aceBtn;
    @FXML
    private TextField nombre;

    Alert alertName = new Alert(Alert.AlertType.INFORMATION);
    Alert alertEmpty = new Alert(Alert.AlertType.INFORMATION);
    
    @FXML
    private ListView<String> lista;
    @FXML
    private SplitMenuButton entChoicer;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Entidad e: InterfazController.diagrama.getEntidades()){
            MenuItem m = new MenuItem(e.getNombre());
            entChoicer.getItems().add(m);
            m.setOnAction(new EventHandler<ActionEvent>(){
                @Override public void handle(ActionEvent event) {
                    lista.getItems().add(e.getNombre());
                    m.setDisable(true);
                    InterfazController.compRelacion.add(e);
                }
            });
        }
        
        alertName.setTitle("Error");
        alertName.setHeaderText(null);
        alertName.setContentText("Debes ponerle un nombre");
        alertEmpty.setTitle("Error");
        alertEmpty.setHeaderText(null);
        alertEmpty.setContentText("Debe tener elementos");
    }    

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) canBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void aceptar(ActionEvent event) {
        if (nombre.getText().length()==0){
            alertName.showAndWait();
        } else if (InterfazController.compRelacion.isEmpty()){
            alertEmpty.showAndWait();
        } else{
            InterfazController.newRelacionNombre = nombre.getText();
            InterfazController.relacionValidacion = true;
            Stage stage = (Stage) nombre.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void txtField(ActionEvent event) {
        if (nombre.getText().length()==0){
            alertName.showAndWait();
        } else if (InterfazController.compRelacion.isEmpty()){
            alertEmpty.showAndWait();
        } else{
            InterfazController.newRelacionNombre = nombre.getText();
            InterfazController.relacionValidacion = true;
            Stage stage = (Stage) nombre.getScene().getWindow();
            stage.close();
        }
    }
    
}
