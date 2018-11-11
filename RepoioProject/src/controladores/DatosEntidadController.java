/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.Diagrama;
import clases.Figura;
import clases.Herencia;
import clases.Propiedad;
import clases.Relacion;
import clases.Tipo;
import static controladores.InterfazController.compRelacion;
import static controladores.InterfazController.entidadActual;
import static controladores.InterfazController.posicionDefaultX;
import static controladores.InterfazController.posicionDefaultY;
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
    
    
       
    @FXML
    public TextField nombre;
    @FXML 
    private Button canBtn;
    
    
    Alert alertEx = new Alert(Alert.AlertType.INFORMATION);
    
    
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
        InterfazController.propiedadActual = (ArrayList<Propiedad>) entidadActual.getPropiedades().clone();
        
        
        
        
    }    
    
    @FXML
    public void modificar(){
        
        if(!(nombre.getText().equals(""))){
            if (nombre.getText().length()>20){
                alertEx.showAndWait();
            }else{
                
                entidadActual.setNombre(nombre.getText());
                
                entidadActual.setPropiedades((ArrayList<Propiedad>) InterfazController.propiedadActual.clone());
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
    private void eliminarEntidad(){
        ArrayList <Relacion> eliminar = new ArrayList();
        for (Relacion relacion : Diagrama.relaciones) {
            if(relacion.getComponentes().contains(entidadActual)){
                relacion.getComponentes().remove(entidadActual);
                relacion.crearUniones();
                
                Figura fig = new Figura();
                if(relacion.getComponentes().size() == 0){
                    eliminar.add(relacion);
                }
                
                else if (relacion.getComponentes().size()==1 || relacion.getComponentes().size()==2 || relacion.getComponentes().size()==4 ){
                    fig.crearFigura(posicionDefaultX, posicionDefaultY, 20 , 4);
                } else {
                    fig.crearFigura(posicionDefaultX, posicionDefaultY, 20 , relacion.getComponentes().size());
                }   
                relacion.setFigura(fig);

            }          
        }
        ArrayList <Herencia> eliminar2 = new ArrayList();
        for (Herencia herencia : InterfazController.diagrama.getHerencias()) {
            if(herencia.getEntidades().contains(entidadActual)){
                herencia.getEntidades().remove(entidadActual);
            }
            if(herencia.getPadre().getNombre() == null ? entidadActual.getNombre() == null :
                    herencia.getPadre().getNombre().equals(entidadActual.getNombre())){
                eliminar2.add(herencia);
            }
            if(herencia.getEntidades().size()<1){
                eliminar2.add(herencia);
            }
        }
        Diagrama.herencias.removeAll(eliminar2);
        Diagrama.relaciones.removeAll(eliminar);
        Diagrama.entidades.remove(entidadActual);
        entidadActual=null;
        
        Stage stage = (Stage) canBtn.getScene().getWindow();
        stage.close(); 
    }

    @FXML
    private void haciaPropiedad(ActionEvent event) throws IOException {
        
        AbrirVentana.CargarVista(getClass().getResource("/fxmls/EditarPropiedad.fxml"));
    }
    
    
    
}
