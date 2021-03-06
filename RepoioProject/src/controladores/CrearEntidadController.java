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
    
    
    @FXML
    private TextField nombre;
    @FXML
    private Button canBtn;
    @FXML
    private Button aceBtn;
    
    Alert alertNombre = new Alert(Alert.AlertType.INFORMATION); 
    Alert alertDebil = new Alert(Alert.AlertType.INFORMATION);
    Alert alertFuerte = new Alert(Alert.AlertType.INFORMATION);
    Alert alertEx = new Alert(Alert.AlertType.INFORMATION);
    
    private boolean debil = false;
    private boolean hayParcial = false;
    private boolean hayClave = false;
    
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
        
        alertNombre.setTitle("Error");
        alertNombre.setHeaderText(null);
        alertNombre.setContentText("La entidad tiene el mismo nombre que otro elemento en el diagrama");
        
        alertEx.setTitle("Error");
        alertEx.setHeaderText(null);
        alertEx.setContentText("Haz excedido el limite de 20 caracteres");
        
        nombreE="";
        
        
        
    }    
    
    @FXML
    private void debil(ActionEvent event) {
        this.debil = !debil;
    }

    @FXML
    private void cancelar(ActionEvent event) {
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
                for (Propiedad p : InterfazController.propiedadActual){
                    if (p.getTipo() == Tipo.parcial){
                        hayParcial = true;
                    }
                    if (p.getTipo() == Tipo.clave){
                        hayClave = true;
                    }
                }
                if (debil && !hayParcial){
                    alertDebil.showAndWait();
                }else if (!debil && !hayClave){
                    alertFuerte.showAndWait();
                }else{
                    InterfazController.nombreActual = nombre.getText();
                    if (!entidadIgual(nombre.getText())){
                        nombreE=nombre.getText();
                        InterfazController.entidadActual = new Entidad(nombreE);
                        Figura f = new Figura();
                        f.setDebil(debil);
                        f.rectangulo(InterfazController.posicionDefaultX, InterfazController.posicionDefaultY, 25);
                        InterfazController.entidadActual.setFigura(f);
                        InterfazController.entidadActual.setPropiedades((ArrayList<Propiedad>) InterfazController.propiedadActual.clone());
                        Stage stage = (Stage) aceBtn.getScene().getWindow();
                        stage.close(); 
                    } else {
                        alertNombre.showAndWait();
                    }
                     
                }
                
            }  
        }else{
           for (Propiedad p : InterfazController.propiedadActual){
                if (p.getTipo() == Tipo.parcial){
                    hayParcial = true;
                }
                if (p.getTipo() == Tipo.clave){
                    hayClave = true;
                }
            }
            if (debil && !hayParcial){
                alertDebil.showAndWait();
            }else if (!debil && !hayClave){
                alertFuerte.showAndWait();
            }else{
                if (!entidadIgual("e"+(InterfazController.diagrama.getEntidades().size()+1))){
                    nombreE="e"+(InterfazController.diagrama.getEntidades().size()+1);
                    InterfazController.entidadActual = new Entidad(nombreE);
                    Figura f = new Figura();
                    f.setDebil(debil);
                    f.rectangulo(InterfazController.posicionDefaultX, InterfazController.posicionDefaultY, 25);
                    InterfazController.entidadActual.setFigura(f);
                    InterfazController.entidadActual.setPropiedades((ArrayList<Propiedad>) InterfazController.propiedadActual.clone());
                    Stage stage = (Stage) aceBtn.getScene().getWindow();
                    stage.close();
                }
            }
        }
        
    }

    @FXML
    private void txtField(ActionEvent event) {
        if (nombre.getText().length()!=0){
            if(nombre.getText().length()>20){
                alertEx.showAndWait();
            } else{
                for (Propiedad p : InterfazController.propiedadActual){
                    if (p.getTipo() == Tipo.parcial){
                        hayParcial = true;
                    }
                    if (p.getTipo() == Tipo.clave){
                        hayClave = true;
                    }
                }
                if (debil && !hayParcial){
                    alertDebil.showAndWait();
                }else if (!debil && !hayClave){
                    alertFuerte.showAndWait();
                }else{
                    InterfazController.nombreActual = nombre.getText();
                    if (!entidadIgual(nombre.getText())){
                        nombreE=nombre.getText();
                        InterfazController.entidadActual = new Entidad(nombreE);
                        Figura f = new Figura();
                        f.setDebil(debil);
                        f.rectangulo(InterfazController.posicionDefaultX, InterfazController.posicionDefaultY, 25);
                        InterfazController.entidadActual.setFigura(f);
                        InterfazController.entidadActual.setPropiedades((ArrayList<Propiedad>) InterfazController.propiedadActual.clone());
                        Stage stage = (Stage) aceBtn.getScene().getWindow();
                        stage.close(); 
                    } else {
                        alertNombre.showAndWait();
                    }
                }
            } 
        } else{
            for (Propiedad p : InterfazController.propiedadActual){
                if (p.getTipo() == Tipo.parcial){
                    hayParcial = true;
                }
                if (p.getTipo() == Tipo.clave){
                    hayClave = true;
                }
            }
            if (debil && !hayParcial){
                alertDebil.showAndWait();
            }else if (!debil && !hayClave){
                alertFuerte.showAndWait();
            }else{
                if (!entidadIgual("e"+(InterfazController.diagrama.getEntidades().size()+1))){
                    nombreE="e"+(InterfazController.diagrama.getEntidades().size()+1);
                    InterfazController.entidadActual = new Entidad(nombreE);
                    Figura f = new Figura();
                    f.rectangulo(InterfazController.posicionDefaultX, InterfazController.posicionDefaultY, 25);
                    InterfazController.entidadActual.setFigura(f);
                    InterfazController.entidadActual.setPropiedades((ArrayList<Propiedad>) InterfazController.propiedadActual.clone());
                    Stage stage = (Stage) aceBtn.getScene().getWindow();
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
    
    private boolean entidadIgual(String nombre){
        for (Entidad e: InterfazController.diagrama.getEntidades()) {
            if (nombre.equals(e.getNombre())){
                return true;
            }
        }
        return false;
    }
    
}
