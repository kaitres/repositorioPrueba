/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.Agrupacion;
import clases.Entidad;
import clases.Relacion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author The.N
 */
public class CrearAgregacionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox<Relacion> relChoice;
    
    
    
    Alert alertName = new Alert(Alert.AlertType.INFORMATION);
    Alert alertEmpty = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private TextField nombre;
    @FXML
    private Button canBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Relacion> itemEnt = FXCollections.observableArrayList();
        itemEnt.addAll(InterfazController.diagrama.getRelaciones());
        relChoice.setItems(itemEnt);
        alertName.setTitle("Error");
        alertName.setHeaderText(null);
        alertName.setContentText("Debes seleccionar una entidad");
        alertEmpty.setTitle("Error");
        alertEmpty.setHeaderText(null);
        alertEmpty.setContentText("Debe tener entidades");
    }    
    
    public int buscarRelacion(Relacion r){
        for (int i = 0; i < InterfazController.diagrama.relaciones.size(); i++) {
            if(InterfazController.diagrama.relaciones.get(i).getNombre() == r.getNombre()){
                return i;
            }
        }
        return -1;
    }
    
    @FXML
    private void aceptar(ActionEvent event) {
        if (relChoice.getValue()==null){
                alertName.showAndWait();
            }
        else{
            if(nombre.getText()!=""){
                System.out.println( buscarRelacion(relChoice.getValue()));
                InterfazController.diagrama.entidades.add(new Agrupacion(nombre.getText(), InterfazController.diagrama.relaciones.get(buscarRelacion(relChoice.getValue()))));
                Stage stage = (Stage) canBtn.getScene().getWindow();
                stage.close();
            }
        }
                
                
            
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) canBtn.getScene().getWindow();
        stage.close();
    }
    
}
