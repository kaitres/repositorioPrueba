/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author IP-ROUTE
 */
public class CrearCardinalidadController implements Initializable {

    @FXML
    private Label labelEntidad1;
    @FXML
    private Label labelEntidad2;
    @FXML
    private ChoiceBox<String> choiceEntidad1;
    @FXML
    private ChoiceBox<String> choiceEntidad2;
    @FXML
    private Button butonAce;
    @FXML
    private Button butonCan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> itemTip = FXCollections.observableArrayList();
        itemTip.addAll("N","1");
        choiceEntidad1.setItems(itemTip);
        choiceEntidad2.setItems(itemTip);
        choiceEntidad1.setValue("1");
        choiceEntidad2.setValue("1");
        labelEntidad1.setText(InterfazController.relacionActual.getComponente(0).getNombre());
        if(InterfazController.relacionActual.getComponentes().size()==1){
            labelEntidad2.setText(InterfazController.relacionActual.getComponente(0).getNombre());
        }else{
            labelEntidad2.setText(InterfazController.relacionActual.getComponente(1).getNombre());
        }
        
        
    }    

    @FXML
    private void aceptar(ActionEvent event) {
        InterfazController.relacionActual.setEntidad1Cardinal(choiceEntidad1.getValue());
        InterfazController.relacionActual.setEntidad2Cardinal(choiceEntidad2.getValue());
        Stage stage = (Stage) butonAce.getScene().getWindow();
            stage.close();
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) butonCan.getScene().getWindow();
            stage.close();
    }
    
}
