/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.Entidad;
import clases.Propiedad;
import clases.Tipo;
import java.io.IOException;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author IP-ROUTE
 */
public class EditarPropiedadController implements Initializable {
    int propiedadEditada;
    
    public ArrayList<Propiedad> propiedadesObj;
    
    @FXML
    private ListView<Propiedad> listaPropiedadesView;
    
    @FXML
    private TextField propiedadField;
    
    @FXML
    private Button bAniadir;
    @FXML
    private Button bQuitar;
    @FXML
    private Button aceBtn;
    @FXML
    private Button btEditar;
    @FXML
    private Button canBtn;
    @FXML
    private ComboBox<Tipo> comboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(InterfazController.propiedadActual==null){
            propiedadesObj= new ArrayList<>();
        }else{
            propiedadesObj=(ArrayList<Propiedad>) InterfazController.propiedadActual.clone();
        }
        ObservableList<Propiedad> item = FXCollections.observableArrayList();
        item.addAll(propiedadesObj);
        listaPropiedadesView.setItems(item);
        listaPropiedadesView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listaPropiedadesView.setEditable(true);
        btEditar.setDisable(true);
        bQuitar.setDisable(true);
        ObservableList<Tipo> items = FXCollections.observableArrayList();
        if(InterfazController.nivelPropiedadCompuesta){
            items.addAll(Tipo.parcial,Tipo.clave , Tipo.compuesto , Tipo.derivado , Tipo.generico , Tipo.multivaluado);
        }else{
            items.addAll(Tipo.generico);
        }
        
        
        comboBox.setItems(items);
        comboBox.setValue(Tipo.generico);
    }    

    @FXML
    private void aniadir(ActionEvent event) throws IOException {
        if(!"".equals(propiedadField.getText())){
            if(comboBox.getValue()==Tipo.compuesto ){
                InterfazController.nivelPropiedadCompuesta=false;
                InterfazController.propiedadActual.clear();
                InterfazController.propiedadActual=null;
                AbrirVentana.CargarVista(getClass().getResource("/fxmls/EditarPropiedad.fxml"));
                InterfazController.nivelPropiedadCompuesta=true;
                ArrayList<Propiedad> auxiliar=InterfazController.propiedadActual;
                propiedadesObj.add(new Propiedad(propiedadField.getText(), comboBox.getValue(), (ArrayList<Propiedad>) auxiliar.clone()));
            }else{

                    propiedadesObj.add(new Propiedad(propiedadField.getText(), comboBox.getValue()));

            }
            ObservableList<Propiedad> item = FXCollections.observableArrayList();
            item.addAll(propiedadesObj);
            listaPropiedadesView.setItems(item);
            propiedadField.setText("");

            comboBox.setValue(Tipo.generico); 
            
        }
        else {
            if(comboBox.getValue()==Tipo.compuesto ){
                InterfazController.nivelPropiedadCompuesta=false;
                InterfazController.propiedadActual.clear();
                InterfazController.propiedadActual=null;
                AbrirVentana.CargarVista(getClass().getResource("/fxmls/EditarPropiedad.fxml"));
                InterfazController.nivelPropiedadCompuesta=true;
                ArrayList<Propiedad> auxiliar=InterfazController.propiedadActual;
                propiedadesObj.add(new Propiedad("p"+(propiedadesObj.size()+1), comboBox.getValue(), (ArrayList<Propiedad>) auxiliar.clone()));
            }else{
                propiedadesObj.add(new Propiedad("p"+(InterfazController.cantProps+1), comboBox.getValue()));
            }
            ObservableList<Propiedad> item = FXCollections.observableArrayList();
            item.addAll(propiedadesObj);
            listaPropiedadesView.setItems(item);
            propiedadField.setText("");
            
            comboBox.setValue(Tipo.generico);
        }
        InterfazController.cantProps += 1;
    }

    @FXML
    private void quitar(ActionEvent event) {
        
        
        
            
        propiedadesObj.remove(propiedadEditada);
        
        
        ObservableList<Propiedad> item = FXCollections.observableArrayList();
        item.addAll(propiedadesObj);
        
        listaPropiedadesView.setItems(item);
        
        bQuitar.setDisable(true);
        btEditar.setDisable(true);
        propiedadField.setText("");
        comboBox.setValue(Tipo.generico);
        InterfazController.cantProps -= 1;
    }

    @FXML
    private void editar(ActionEvent event) throws IOException {
        if ((!"".equals(propiedadField.getText())) 
                || !propiedadesObj.get(propiedadEditada).getTipo().equals(comboBox.getValue())) {
            
            if( comboBox.getValue()==Tipo.compuesto){
                
                InterfazController.nivelPropiedadCompuesta=false;
                InterfazController.propiedadActual=propiedadesObj.get(propiedadEditada).getPropiedades();
                AbrirVentana.CargarVista(getClass().getResource("/fxmls/EditarPropiedad.fxml"));
                InterfazController.nivelPropiedadCompuesta=true;
                    propiedadesObj.set(propiedadEditada, new Propiedad(propiedadField.getText(),
                            comboBox.getValue(), (ArrayList<Propiedad>) InterfazController.propiedadActual.clone()));
                
            }else{
                
                    propiedadesObj.set(propiedadEditada, new Propiedad(propiedadField.getText(), comboBox.getValue()));
                
            }
            ObservableList<Propiedad> item = FXCollections.observableArrayList();
            item.addAll(propiedadesObj);
            
            listaPropiedadesView.setItems(item);
            btEditar.setDisable(true);
            bQuitar.setDisable(true);
            
        }
        propiedadField.setText("");
        comboBox.setValue(Tipo.generico);
    }

    @FXML
    private void cancelar(ActionEvent event) {
        propiedadesObj.clear();
        ObservableList<Propiedad> item = FXCollections.observableArrayList();
        item.addAll(propiedadesObj);
        listaPropiedadesView.setItems(item);
        Stage stage = (Stage) canBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void seleccionar(MouseEvent event) {
        if(!propiedadesObj.isEmpty()){
            try{
               ObservableList<Integer> modificado = listaPropiedadesView.getSelectionModel().getSelectedIndices();
                propiedadEditada = (int) modificado.get(0);
                propiedadField.setText(propiedadesObj.get(propiedadEditada).getNombre());
                comboBox.setValue(propiedadesObj.get(propiedadEditada).getTipo());
                btEditar.setDisable(false); 
                bQuitar.setDisable(false);
            }catch(Exception e){
                System.out.println("Error salvaje aparece, pero try catch salva el dia denuevo");
            }
            
        }
    }

    @FXML
    private void aceptar(ActionEvent event) {
        
        InterfazController.propiedadActual=propiedadesObj;
        Stage stage = (Stage) aceBtn.getScene().getWindow();
        stage.close();
    }

    
    
}
