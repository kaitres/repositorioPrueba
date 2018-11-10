/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.Entidad;
import clases.Herencia;
import clases.Diagrama;
import clases.Figura;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    Alert alertName = new Alert(Alert.AlertType.INFORMATION);
    Alert alertEmpty = new Alert(Alert.AlertType.INFORMATION);
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
        tipChoice.setValue("D");
        menuEntHijes.setDisable(true);
        alertName.setTitle("Error");
        alertName.setHeaderText(null);
        alertName.setContentText("Debes seleccionar una entidad");
        alertEmpty.setTitle("Error");
        alertEmpty.setHeaderText(null);
        alertEmpty.setContentText("Debe tener entidades");
    
    }    

    @FXML
    private void seleccionEntPadre(ActionEvent event) {
        listEntView.getItems().clear();
        menuEntHijes.getItems().clear();
        ArrayList<Entidad> opciones = (ArrayList<Entidad>) InterfazController.diagrama.getEntidades().clone();
        opciones.remove(entChoice.getValue());
        removerHijos(opciones,entChoice.getValue());
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
        if (entChoice.getValue()==null){
            alertName.showAndWait();
        } else if (listEntView.getItems().isEmpty()){
            alertEmpty.showAndWait();
        } else{
            int index = InterfazController.diagrama.getEntidades().indexOf(entChoice.getValue());
            ArrayList<Entidad> aux = new ArrayList<>();
            for (Entidad object : listEntView.getItems()) {
                aux.add(object);
            }
            InterfazController.diagrama.getEntidades().get(index).getHijos().addAll(aux);
            //InterfazController.diagrama.getEntidades().get(index).setHijos(aux);
            
            Figura f = new Figura();
            f.circulo(InterfazController.posicionDefaultX, InterfazController.posicionDefaultY);
            InterfazController.herenciaActual=new Herencia(tipChoice.getValue(),
                    aux, f, entChoice.getValue());
            Stage stage = (Stage) canBtn.getScene().getWindow();
            stage.close();
        }
    }
    private void removerHijos(ArrayList<Entidad> opciones, Entidad e){
        for (Entidad obj : e.getHijos()) {
            opciones.remove(obj);
        }
    } 
}
