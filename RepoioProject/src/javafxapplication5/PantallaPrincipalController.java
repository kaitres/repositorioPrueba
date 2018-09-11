/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication5;

import clases.Diagrama;
import clases.Entidad;
import clases.Hexagono;
import clases.Pentagono;
import clases.Rectangulo;
import clases.Rombo;
import clases.Triangulo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author The.N
 */
public class PantallaPrincipalController implements Initializable {
    Diagrama diagrama;
    
    public int posicionDefaultX = 50;
    public int posicionDefaultY = 50;
    
    public GraphicsContext gc;
    
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
        diagrama = new Diagrama();
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
    }    

    @FXML
    private void crearEntidad(ActionEvent event) {
        Pentagono rec = new Pentagono(posicionDefaultX, posicionDefaultY,30);
        Entidad ent = new Entidad();
        ent.setFigura(rec);
        diagrama.getEntidades().add(ent);
        rec.dibujar(gc);
        
        posicionDefaultX+=2;
        posicionDefaultY+=2;
    }

    @FXML
    private void crearRelacion(ActionEvent event) {
    }

    @FXML
    private void borrar(ActionEvent event) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        posicionDefaultX=10;
        posicionDefaultY=10;
    }
    
}
