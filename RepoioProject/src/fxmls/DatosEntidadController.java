/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmls;

import clases.Propiedad;
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
    ArrayList<Propiedad> propiedades;
    ArrayList <String> propsObs;
    ObservableList<String> listProp;
    
    @FXML
    public TextField nombre;
    @FXML 
    private Button canBtn;
    @FXML
    private ListView<String> listaPropiedades;
    
    Alert alertEx = new Alert(Alert.AlertType.INFORMATION);
    
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
        alertEx.setTitle("Error");
        alertEx.setHeaderText(null);
        alertEx.setContentText("Haz excedido el limite de 20 caracteres");
        
        nombre.setText(entidadActual.getNombre());
        propiedades = entidadActual.getPropiedades();
        System.out.println(propiedades.get(0).getNombre());
        propsObs = new ArrayList<>();
        for (Propiedad prop : propiedades){
            propsObs.add(prop.getNombre());
        }
        listProp = FXCollections.observableArrayList(propsObs);
        listaPropiedades.setItems(listProp);
        listaPropiedades.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        btEditar.setDisable(true);
    }    
    
    @FXML
    public void modificar(){
        if(!(nombre.getText().equals(""))){
            if (nombre.getText().length()>20){
                alertEx.showAndWait();
            }else{
                entidadActual.setNombre(nombre.getText());
                entidadActual.setPropiedades(propiedades);
                Stage stage = (Stage) canBtn.getScene().getWindow();
                stage.close(); 
            }
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
            propsObs.set(propiedadEditada, propiedadAEditar.getText());
            listProp = FXCollections.observableArrayList(propsObs);
            listaPropiedades.setItems(listProp);
            btEditar.setDisable(true);
        }
    }

    @FXML
    private void seleccionar(MouseEvent event) {
        if(!propiedades.isEmpty()){
            try{
               ObservableList<Integer> modificado = listaPropiedades.getSelectionModel().getSelectedIndices();
                propiedadEditada = (int) modificado.get(0);
                propiedadAEditar.setText(propsObs.get(propiedadEditada));
                btEditar.setDisable(false); 
            }catch(Exception e){
                System.out.println("Error salvaje aparece, pero try catch salva el dia denuevo");
            }
            
        }
        
    }

    @FXML
    private void aniadir(ActionEvent event) {
        if (!"".equals(propiedadAEditar.getText())) {
            propsObs.add(propiedadAEditar.getText());
            listProp = FXCollections.observableArrayList(propsObs);
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

                propsObs.set((int)eliminado,"");
            });
            int i=0;
            while(i<propiedades.size()){
                if("".equals(propiedades.get(i))){
                    propiedades.remove(i);
                }else{
                    i+=1;
                }
            }
            listProp = FXCollections.observableArrayList(propsObs);
            listaPropiedades.setItems(listProp);
            btEditar.setDisable(true);
            if(propiedades.isEmpty()){
                btEliminar.setDisable(true);
            }
        }
    }
}
