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
    @FXML
    private ChoiceBox<String> choiceEntidad1Linea;
    @FXML
    private ChoiceBox<String> choiceEntidad2Linea;

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
        
        
        choiceEntidad1.setValue(InterfazController.relacionActual.getEntidad1Cardinal());
        choiceEntidad2.setValue(InterfazController.relacionActual.getEntidad2Cardinal());
        
        if(InterfazController.relacionActual.getFigura().isDebil()){
            choiceEntidad1Linea.setDisable(true);
            choiceEntidad2Linea.setDisable(true);
        }
        
        ObservableList<String> itemLinea = FXCollections.observableArrayList();
        itemLinea.addAll("Doble","Simple");
        
        choiceEntidad1Linea.setItems(itemLinea);
        choiceEntidad2Linea.setItems(itemLinea);
        
        choiceEntidad1Linea.setValue(InterfazController.relacionActual.getEntidad1Linea());
        choiceEntidad2Linea.setValue(InterfazController.relacionActual.getEntidad2Linea());
        
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
        if(!InterfazController.relacionActual.getFigura().isDebil()){
            InterfazController.relacionActual.setEntidad1Linea(choiceEntidad1Linea.getValue());
            InterfazController.relacionActual.setEntidad2Linea(choiceEntidad2Linea.getValue());
        
        }
        
        
        Stage stage = (Stage) butonAce.getScene().getWindow();
            stage.close();
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) butonCan.getScene().getWindow();
            stage.close();
    }
    
}
