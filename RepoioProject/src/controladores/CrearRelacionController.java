/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.Agrupacion;
import clases.Entidad;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cmsan
 */
public class CrearRelacionController implements Initializable {

    @FXML
    private Button canBtn;
    @FXML
    private Button aceBtn;
    @FXML
    private TextField nombre;

    Alert alertName = new Alert(Alert.AlertType.INFORMATION);
    Alert alertNombre = new Alert(Alert.AlertType.INFORMATION);
    Alert alertEmpty = new Alert(Alert.AlertType.INFORMATION);
    Alert alertEx = new Alert(Alert.AlertType.INFORMATION);
    Alert alertExCmp = new Alert(Alert.AlertType.INFORMATION);
    
    @FXML
    private ListView<String> lista;
    @FXML
    private SplitMenuButton entChoicer;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Entidad e: InterfazController.diagrama.getEntidades()){
            
            MenuItem m = new MenuItem(e.getNombre());
            entChoicer.getItems().add(m);
            m.setOnAction(new EventHandler<ActionEvent>(){
                @Override public void handle(ActionEvent event) {
                    lista.getItems().add(e.getNombre());
                    m.setDisable(true);
                    InterfazController.compRelacion.add(e);
                }
            });
        }
        
        alertNombre.setTitle("Error");
        alertNombre.setHeaderText(null);
        alertNombre.setContentText("La relacion tiene el mismo nombre que otro elemento en el diagrama");
        alertName.setTitle("Error");
        alertName.setHeaderText(null);
        alertName.setContentText("Debes ponerle un nombre");
        alertEmpty.setTitle("Error");
        alertEmpty.setHeaderText(null);
        alertEmpty.setContentText("Debe tener elementos");
        alertEx.setTitle("Error");
        alertEx.setHeaderText(null);
        alertEx.setContentText("Haz excedido el limite de 20 caracteres");
        alertExCmp.setTitle("Error");
        alertExCmp.setHeaderText(null);
        alertExCmp.setContentText("Las relaciones deben ser solo binarias");
    }    

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) canBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void aceptar(ActionEvent event) {
        if (nombre.getText().length()==0){
            if (!InterfazController.compRelacion.isEmpty()){
                if (InterfazController.compRelacion.size()>2){
                    alertExCmp.showAndWait();
                }else{
                    InterfazController.newRelacionNombre = "r"+(InterfazController.diagrama.getRelaciones().size()+1);
                    InterfazController.relacionValidacion = true;
                    Stage stage = (Stage) nombre.getScene().getWindow();
                    stage.close();
                }
            }else{
                alertEmpty.showAndWait();
            }
        } else if (InterfazController.compRelacion.isEmpty()){
            alertEmpty.showAndWait();
        } else{
            if(nombre.getText().length()>20){
                alertEx.showAndWait();
            } else{
                if (InterfazController.compRelacion.size()>2){
                    alertExCmp.showAndWait();
                }else{
                    InterfazController.nombreActual = nombre.getText();
                    InterfazController.newRelacionNombre = nombre.getText();
                    InterfazController.relacionValidacion = true;
                    Stage stage = (Stage) nombre.getScene().getWindow();
                    stage.close();
                }
            }   
        }
    }

    @FXML
    private void txtField(ActionEvent event) {
        if (nombre.getText().length()==0){
            if (!InterfazController.compRelacion.isEmpty()){
                if (InterfazController.compRelacion.size()>2){
                    alertExCmp.showAndWait();
                }else{
                    InterfazController.newRelacionNombre = "r"+(InterfazController.diagrama.getRelaciones().size()+1);
                    InterfazController.relacionValidacion = true;
                    Stage stage = (Stage) nombre.getScene().getWindow();
                    stage.close();
                }
            }else{
                alertEmpty.showAndWait();
            }
        } else if (InterfazController.compRelacion.isEmpty()){
            alertEmpty.showAndWait();
        } else{
            if(nombre.getText().length()>20){
                alertEx.showAndWait();
            } else{
                if (InterfazController.compRelacion.size()>2){
                    alertExCmp.showAndWait();
                }else{
                    InterfazController.nombreActual = nombre.getText();
                    InterfazController.newRelacionNombre = nombre.getText();
                    InterfazController.relacionValidacion = true;
                    Stage stage = (Stage) nombre.getScene().getWindow();
                    stage.close();
                }
            }
        }
    }

    @FXML
    private void haciaPropiedad(ActionEvent event) throws IOException {
        if (nombre.getText().length()!=0){
            if(nombre.getText().length()>20){
                alertEx.showAndWait();
            }
        } else{
            InterfazController.nombreActual="e"+(InterfazController.diagrama.getEntidades().size()+1);
        }
        AbrirVentana.CargarVista(getClass().getResource("/fxmls/EditarPropiedad.fxml"));
    }
    
}
