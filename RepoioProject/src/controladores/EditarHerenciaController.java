/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.Entidad;
import clases.Herencia;
import clases.Propiedad;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author IP-ROUTE
 */
public class EditarHerenciaController implements Initializable {
    Herencia herenciaObj;
    ArrayList<Entidad> entidadesObj;
    ArrayList<Entidad> entidadesSelec;
    int propiedadEditada;
    
    
    @FXML
    private ChoiceBox<String> choiceTipo;
    @FXML
    private ListView<Entidad> listViewEntidades;
    @FXML
    private Button btEliminar;
    @FXML
    private ChoiceBox<Entidad> choiceEntidad;
    @FXML
    private Button btAniadir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        herenciaObj = InterfazController.herenciaActual;
        ObservableList<String> itemTip = FXCollections.observableArrayList();
        itemTip.addAll("D","S");
        choiceTipo.setItems(itemTip);
        choiceTipo.setValue(herenciaObj.getTipo());
        
        ObservableList<Entidad> item = FXCollections.observableArrayList();
        entidadesObj =(ArrayList<Entidad>) herenciaObj.getEntidades().clone();
        item.addAll(entidadesObj);
        listViewEntidades.setItems(item);
        listViewEntidades.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        ObservableList<Entidad> itemEnt = FXCollections.observableArrayList();
        removerEntidades(itemEnt);
        choiceEntidad.setItems(itemEnt);
        if(!itemEnt.isEmpty()){
            choiceEntidad.setValue(itemEnt.get(0));  
        }
        
        
        btEliminar.setDisable(true);
        
    }    

    @FXML
    private void aniadir(ActionEvent event) {
        entidadesObj.add(choiceEntidad.getValue());
        entidadesSelec.remove(choiceEntidad.getValue());
        
        ObservableList<Entidad> item = FXCollections.observableArrayList();
        item.addAll(entidadesObj);
        listViewEntidades.setItems(item);
        
        
        ObservableList<Entidad> itemEnt = FXCollections.observableArrayList();
        removerEntidades(itemEnt);
        choiceEntidad.setItems(itemEnt);
        if(!itemEnt.isEmpty()){
            choiceEntidad.setValue(itemEnt.get(0));
        }else{
            btAniadir.setDisable(true);
        }
        
    }

    @FXML
    private void eliminarEntidad(ActionEvent event) {
        entidadesObj.remove(propiedadEditada);
        
        
        ObservableList<Entidad> item = FXCollections.observableArrayList();
        item.addAll(entidadesObj);
        listViewEntidades.setItems(item);
        
        ObservableList<Entidad> itemEnt = FXCollections.observableArrayList();
        removerEntidades(itemEnt);
        choiceEntidad.setItems(itemEnt);
        if(!itemEnt.isEmpty()){
            choiceEntidad.setValue(itemEnt.get(0));  
        }
        btEliminar.setDisable(true);
        btAniadir.setDisable(false);
    }

    @FXML
    private void eliminar(ActionEvent event) {
        InterfazController.diagrama.getHerencias().remove(InterfazController.herenciaActual);
        InterfazController.herenciaActual=null;
        Stage stage = (Stage) btEliminar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void modificar(ActionEvent event) {
        InterfazController.herenciaActual.setTipo(choiceTipo.getValue());
        InterfazController.herenciaActual.setEntidades(entidadesObj);
        if(InterfazController.herenciaActual.getEntidades().size()==0){
           InterfazController.diagrama.herencias.remove(InterfazController.herenciaActual);
        }
        Stage stage = (Stage) btEliminar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) btEliminar.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void seleccionar(MouseEvent event) {
        if(!entidadesObj.isEmpty()){
            try{
                ObservableList<Integer> modificado = listViewEntidades.getSelectionModel().getSelectedIndices();
                propiedadEditada = (int) modificado.get(0);
                btEliminar.setDisable(false);
            }catch(Exception e){
                System.out.println("Error salvaje aparece, pero try catch salva el dia denuevo");
            }
            
        }
    }
    private void removerEntidades(ObservableList<Entidad> x){
        ArrayList<Entidad> y = (ArrayList<Entidad>) InterfazController.diagrama.getEntidades().clone();
        y.remove(herenciaObj.getPadre());
        for (Entidad entidade : entidadesObj) {
            y.remove(entidade);
        }
        for (Herencia herencia : InterfazController.diagrama.getHerencias()) {
            if(herencia.esHijo(herenciaObj.getPadre()) && y.indexOf(herencia.getPadre())>=0){
                y.remove(herencia.getPadre());
            }
        }
        entidadesSelec = y;
        x.addAll(y);
    }
}
