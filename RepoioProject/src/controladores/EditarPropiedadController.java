/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

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
        items.addAll(Tipo.clave , Tipo.compuesto , Tipo.derivado , Tipo.generico , Tipo.multivaluado);

        comboBox.setItems(items);
        comboBox.setValue(Tipo.generico);
    }    

    @FXML
    private void aniadir(ActionEvent event) {
        if(!"".equals(propiedadField.getText())){
            
            propiedadesObj.add(new Propiedad(propiedadField.getText(), comboBox.getValue()));
            ObservableList<Propiedad> item = FXCollections.observableArrayList();
            item.addAll(propiedadesObj);
            listaPropiedadesView.setItems(item);
            propiedadField.setText("");
            
            comboBox.setValue(Tipo.generico);
        }
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
    }

    @FXML
    private void editar(ActionEvent event) {
        if ((!"".equals(propiedadField.getText())) 
                || !propiedadesObj.get(propiedadEditada).getTipo().equals(comboBox.getValue())) {
            
            
            propiedadesObj.set(propiedadEditada, new Propiedad(propiedadField.getText(), comboBox.getValue()));
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
