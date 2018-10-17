/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmls;

import static fxmls.InterfazController.entidadActual;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author The.N
 */
public class DatosEntidadController implements Initializable {
    
    int propiedadEditada;
    ArrayList<String> propiedades;
    ObservableList<String> listProp;
    
    @FXML
    public TextField nombre;
    @FXML 
    private Button canBtn;
    @FXML
    private ListView<String> listaPropiedades;
    
    @FXML
    private TextField propiedadAEditar;
    @FXML
    private Button btEditar;
    @FXML
    private Button btEliminar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nombre.setText(entidadActual.getNombre());
        propiedades= (ArrayList<String>) entidadActual.getPropiedades().clone();
        listProp = FXCollections.observableArrayList(propiedades);
        listaPropiedades.setItems(listProp);
        listaPropiedades.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        btEditar.setDisable(true);
    }    
    
    @FXML
    public void modificar(){
        if(!(nombre.getText().equals(""))){
           entidadActual.setNombre(nombre.getText());
           entidadActual.setPropiedades(propiedades);
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

    @FXML
    private void editar(ActionEvent event) {
        if (!"".equals(propiedadAEditar.getText())) {
            propiedades.set(propiedadEditada, propiedadAEditar.getText());
            listProp = FXCollections.observableArrayList(propiedades);
            listaPropiedades.setItems(listProp);
            btEditar.setDisable(true);
        }
    }

    @FXML
    private void seleccionar(MouseEvent event) {
        if(!propiedades.isEmpty()){
            ObservableList<Integer> modificado = listaPropiedades.getSelectionModel().getSelectedIndices();
            propiedadEditada = (int) modificado.get(0);
            propiedadAEditar.setText(propiedades.get(propiedadEditada));
            btEditar.setDisable(false);
        }
        
    }

    @FXML
    private void aniadir(ActionEvent event) {
        if (!"".equals(propiedadAEditar.getText())) {
            propiedades.add(propiedadAEditar.getText());
            listProp = FXCollections.observableArrayList(propiedades);
            listaPropiedades.setItems(listProp);
            btEditar.setDisable(true);
            btEliminar.setDisable(false);
        }
    }

    @FXML
    private void eliminar(ActionEvent event) {
        ObservableList<Integer> eliminados = listaPropiedades.getSelectionModel().getSelectedIndices();
        if(eliminados.size()>0){
            eliminados.forEach((eliminado) -> {

                propiedades.set((int)eliminado,"");
            });
            int i=0;
            while(i<propiedades.size()){
                if("".equals(propiedades.get(i))){
                    propiedades.remove(i);
                }else{
                    i+=1;
                }
            }
            listProp = FXCollections.observableArrayList(propiedades);
            listaPropiedades.setItems(listProp);
            btEditar.setDisable(true);
            if(propiedades.isEmpty()){
                btEliminar.setDisable(true);
            }
        }
    }
}
