/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication5;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author The.N
 */
public class PantallaPrincipalController implements Initializable {

    @FXML
    private Button btEntidad;
    @FXML
    private Button btRelacion;
    @FXML
    private Canvas canvas;
    @FXML
    private Button btBorrar;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void crearEntidad(ActionEvent event) {
    }

    @FXML
    private void crearRelacion(ActionEvent event) {
    }

    @FXML
    private void borrar(ActionEvent event) {
    }
    
}
