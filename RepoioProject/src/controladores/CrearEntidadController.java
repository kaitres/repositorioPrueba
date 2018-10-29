/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.Entidad;
import clases.Figura;
import clases.Propiedad;
import clases.Tipo;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cmsan
 */
public class CrearEntidadController implements Initializable {
    public String nombreE;
    public ArrayList<String> propiedades;
    public ArrayList<Propiedad> propiedadesObj;
    @FXML
    private TextField nombre;
    @FXML
    private Button canBtn;
    @FXML
    private Button aceBtn;
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    Alert alertEx = new Alert(Alert.AlertType.INFORMATION);
    
    @FXML
    private TextField propiedadField;
    @FXML
    private ListView<String> listaPropiedadesView;
    @FXML
    private Button bQuitar;
    ObservableList<String> listProp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Debes ponerle un nombre");
        
        alertEx.setTitle("Error");
        alertEx.setHeaderText(null);
        alertEx.setContentText("Haz excedido el limite de 20 caracteres");
        
        nombreE="";
        propiedades= new ArrayList<>();
        propiedadesObj= new ArrayList<>();
        listProp = FXCollections.observableArrayList(propiedades);
        listaPropiedadesView.setItems(listProp);
        listaPropiedadesView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }    

    @FXML
    private void cancelar(ActionEvent event) {
        propiedades.clear();
        listProp = FXCollections.observableArrayList(propiedades);
        listaPropiedadesView.setItems(listProp);
        nombreE="";
        Stage stage = (Stage) canBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void aceptar(ActionEvent event) {
        if (nombre.getText().length()!=0){
            if(nombre.getText().length()>20){
                alertEx.showAndWait();
            } else{
                nombreE=nombre.getText();
                InterfazController.entidadActual = new Entidad(nombreE);
                Figura f = new Figura();
                f.rectangulo(InterfazController.posicionDefaultX, InterfazController.posicionDefaultY, 25);
                InterfazController.entidadActual.setFigura(f);
                InterfazController.entidadActual.setPropiedades(propiedadesObj);
                Stage stage = (Stage) aceBtn.getScene().getWindow();
                stage.close();  
            }  
        }else{
            alert.showAndWait();
        }
        
    }

    @FXML
    private void txtField(ActionEvent event) {
        if (nombre.getText().length()!=0){
            if(nombre.getText().length()>20){
                alertEx.showAndWait();
            } else{
                nombreE=nombre.getText();
                InterfazController.entidadActual = new Entidad(nombreE);
                Figura f = new Figura();
                f.rectangulo(InterfazController.posicionDefaultX, InterfazController.posicionDefaultY, 25);
                InterfazController.entidadActual.setFigura(f);
                InterfazController.entidadActual.setPropiedades(propiedadesObj);
                Stage stage = (Stage) aceBtn.getScene().getWindow();
                stage.close();  
            } 
        } else{
            alert.showAndWait();
        }
    }

    @FXML
    private void aniadir(ActionEvent event) {
        if(!"".equals(propiedadField.getText())){
            propiedades.add(propiedadField.getText());
            propiedadesObj.add(new Propiedad(propiedadField.getText(), Tipo.generico));
            listProp = FXCollections.observableArrayList(propiedades);
            listaPropiedadesView.setItems(listProp);
            propiedadField.setText("");
            bQuitar.setDisable(false);
        }
    }

    @FXML
    private void quitar(ActionEvent event) {
        ObservableList<Integer> eliminados = listaPropiedadesView.getSelectionModel().getSelectedIndices();
        
        eliminados.forEach((eliminado) -> {
            
            propiedades.set((int)eliminado,"");
        });
        int i=0;
        while(i<propiedades.size()){
            if("".equals(propiedades.get(i))){
                propiedades.remove(i);
                propiedadesObj.remove(i);
            }else{
                i+=1;
            }
        }
        listProp = FXCollections.observableArrayList(propiedades);
        listaPropiedadesView.setItems(listProp);
        if(propiedades.isEmpty()){
            bQuitar.setDisable(true);
        }
    }
    
}
