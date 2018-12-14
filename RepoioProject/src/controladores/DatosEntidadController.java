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
import static controladores.InterfazController.hayClave;
import static controladores.InterfazController.hayDebil;
import static controladores.InterfazController.posicionDefaultX;
import static controladores.InterfazController.posicionDefaultY;
import static controladores.InterfazController.relacionActual;
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
import javafx.scene.control.CheckBox;
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

    private boolean hayParcial = false;
    private boolean hayClave = false;
       
    @FXML
    public TextField nombre;
    @FXML 
    private Button canBtn;
    
    Alert alertDebil = new Alert(Alert.AlertType.INFORMATION);
    Alert alertFuerte = new Alert(Alert.AlertType.INFORMATION);
    Alert alertEx = new Alert(Alert.AlertType.INFORMATION);
    
    
    private Button btEliminar;
    @FXML
    private CheckBox choiceDebil;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        InterfazController.propiedadActual.clear();
        alertDebil.setTitle("Error");
        alertDebil.setHeaderText(null);
        alertDebil.setContentText("La entidad debil debe tener una propiedad parcial");
        
        alertFuerte.setTitle("Error");
        alertFuerte.setHeaderText(null);
        alertFuerte.setContentText("La entidad fuerte debe tener una propiedad clave");
        
        alertEx.setTitle("Error");
        alertEx.setHeaderText(null);
        alertEx.setContentText("Haz excedido el limite de 20 caracteres");
        if(entidadActual.getFigura().isDebil()){
            choiceDebil.setSelected(true);
        }else{
            choiceDebil.setSelected(false);
        }
        nombre.setText(entidadActual.getNombre());
        InterfazController.propiedadActual = (ArrayList<Propiedad>) entidadActual.getPropiedades().clone();
        
        
        
        
    }    
    
    @FXML
    public void modificar(){
        
        if(!(nombre.getText().equals(""))){
            if (nombre.getText().length()>20){
                alertEx.showAndWait();
            }else{
                for (Propiedad p : InterfazController.propiedadActual){
                    if (p.getTipo() == Tipo.parcial){
                        hayParcial = true;
                    }
                    if (p.getTipo() == Tipo.clave){
                        hayClave = true;
                    }
                }
                if (choiceDebil.isSelected() && !hayParcial){
                    alertDebil.showAndWait();
                } else if (!choiceDebil.isSelected() && !hayClave){
                    alertFuerte.showAndWait();
                } else {
                    entidadActual.setNombre(nombre.getText());
                    entidadActual.setPropiedades((ArrayList<Propiedad>) InterfazController.propiedadActual.clone());
                    entidadActual.getFigura().setDebil(choiceDebil.isSelected());
                    Stage stage = (Stage) canBtn.getScene().getWindow();
                    stage.close(); 
                }
                
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
        for (Relacion relacion : InterfazController.diagrama.relaciones) {
            if(relacion.getComponentes().contains(entidadActual)){
                //if (entidadActual.getFigura().isDebil()) {
                    relacion.eliminarDebil(entidadActual);
                    relacion.metamorfosear();
                //}
                
                if(relacion.getComponentes().size()<3){
                    if(relacion.getComponentes().indexOf(entidadActual)==0){
                        relacion.setEntidad1Cardinal(relacion.getEntidad2Cardinal());
                        relacion.setEntidad2Cardinal("");
                    }
                    if(relacion.getComponentes().indexOf(entidadActual)==1){
                        relacion.setEntidad2Cardinal("");
                    }
                }
                relacion.getComponentes().remove(entidadActual);
                
                
                Figura fig = new Figura();
                
                if( relacion.getComponentes().isEmpty()){
                    eliminar.add(relacion);
                }
                
                
                else if (relacion.getComponentes().size()==1 || relacion.getComponentes().size()==2 || relacion.getComponentes().size()==4 ){
                    fig.crearFigura((int) relacion.getFigura().getPuntoCentral().getX(), (int) relacion.getFigura().getPuntoCentral().getY(), 20 , 4);
                } else {
                    fig.crearFigura((int) relacion.getFigura().getPuntoCentral().getX(), (int) relacion.getFigura().getPuntoCentral().getY(), 20 , relacion.getComponentes().size());
                }   
                relacion.setFigura(fig);
                
                if(!(hayClave(relacion.getComponentes()))){
                    relacion.getFigura().setDebil(false);
                }
                relacion.crearUniones();
                
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
        InterfazController.diagrama.herencias.removeAll(eliminar2);
        InterfazController.diagrama.relaciones.removeAll(eliminar);
        InterfazController.diagrama.entidades.remove(entidadActual);
        entidadActual=null;
        
        Stage stage = (Stage) canBtn.getScene().getWindow();
        stage.close(); 
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
