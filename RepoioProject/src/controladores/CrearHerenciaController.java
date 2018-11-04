/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.Entidad;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author IP-ROUTE
 */
public class CrearHerenciaController implements Initializable {

    @FXML
    private ComboBox<Entidad> entChoice;
    @FXML
    private SplitMenuButton menuEntHijes;
    @FXML
    private ComboBox<String> tipChoice;
    @FXML
    private ListView<Entidad> listEntView;
    @FXML
    private Button canBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Entidad> itemEnt = FXCollections.observableArrayList();
        itemEnt.addAll(InterfazController.diagrama.getEntidades());
        entChoice.setItems(itemEnt);
        ObservableList<String> itemTip = FXCollections.observableArrayList();
        itemTip.addAll("D","S");
        tipChoice.setItems(itemTip);
        
        menuEntHijes.setDisable(true);
        
    
    }    

    @FXML
    private void seleccionEntPadre(ActionEvent event) {
        listEntView.getItems().clear();
        menuEntHijes.getItems().clear();
        ArrayList<Entidad> opciones = (ArrayList<Entidad>) InterfazController.diagrama.getEntidades().clone();
        opciones.remove(entChoice.getValue());
        
        for (Entidad e: opciones){
            MenuItem m = new MenuItem(e.getNombre());
            menuEntHijes.getItems().add(m);
            m.setOnAction(new EventHandler<ActionEvent>(){
                @Override public void handle(ActionEvent event) {
                    listEntView.getItems().add(e);
                    m.setDisable(true);
                    
                }
            });
        }
        menuEntHijes.setDisable(false);
    }
    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) canBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void aceptar(ActionEvent event) {
        int index = InterfazController.diagrama.getEntidades().indexOf(entChoice.getValue());
        ArrayList<Entidad> aux = new ArrayList<>();
        for (Entidad object : listEntView.getItems()) {
            aux.add(object);
        }
        InterfazController.diagrama.getEntidades().get(index).setHijos(aux);
        Stage stage = (Stage) canBtn.getScene().getWindow();
        stage.close();
        
    }
}
