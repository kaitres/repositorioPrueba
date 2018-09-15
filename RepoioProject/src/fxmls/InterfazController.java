/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmls;

import clases.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;

/**
 *
 * @author cmsan
 */
public class InterfazController implements Initializable {//Lo hizo el Carlos UwU
    Diagrama diagrama;
    
    public int posicionDefaultX = 100;
    public int posicionDefaultY = 100;
    
    public GraphicsContext gc;
    
    @FXML
    private Canvas canvas;
    @FXML
    private MenuItem r2;
    @FXML
    private MenuItem r3;
    @FXML
    private MenuItem r4;
    @FXML
    private MenuItem r5;
    @FXML
    private MenuItem r6;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        disableAllR();
        diagrama = new Diagrama();
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
    }    

    @FXML
    private void clear(ActionEvent event) {
        diagrama.clear();
        disableAllR();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        posicionDefaultX = 50;
        posicionDefaultY = 50;
    }

    @FXML
    private void crearEntidad(ActionEvent event) {
        
        Figura rec = new Figura();
        rec.rectangulo(posicionDefaultX, posicionDefaultY,25);
        
        Entidad ent = new Entidad("Entidad");
        ent.setFigura(rec);
        diagrama.addEntidad(ent);
        rec.dibujar(gc);
        posicionDefaultX+=20;
        posicionDefaultY+=20;
        
        switch (diagrama.getEntidades().size()){
            case 0: disableAllR(); break;
            case 1: r2.setDisable(false);
            case 2: break;
            case 3: r3.setDisable(false); break;
            case 4: r4.setDisable(false); break;
            case 5: r5.setDisable(false); break;
            case 6: r6.setDisable(false); break;
        }
    }

    @FXML
    private void crearRelacion2(ActionEvent event) {
        Figura 
        Rombo tri = new Rombo(posicionDefaultX, posicionDefaultY,20);
        Relacion rec = new Relacion();
        rec.setFigura(tri);
        diagrama.addRelacion(rec);
        tri.dibujar(gc);
        posicionDefaultX+=20;
        posicionDefaultY+=20;
    }
    
    @FXML
    private void crearRelacion3(ActionEvent event) {
        Triangulo tri = new Triangulo(posicionDefaultX, posicionDefaultY,40);
        Relacion rec = new Relacion();
        rec.setFigura(tri);
        diagrama.addRelacion(rec);
        tri.dibujar(gc);
        posicionDefaultX+=20;
        posicionDefaultY+=20;
    }

    @FXML
    private void crearRelacion4(ActionEvent event) {
        Rombo tri = new Rombo(posicionDefaultX, posicionDefaultY,20);
        Relacion rec = new Relacion();
        rec.setFigura(tri);
        diagrama.addRelacion(rec);
        tri.dibujar(gc);
        posicionDefaultX+=20;
        posicionDefaultY+=20;
    }

    @FXML
    private void crearRelacion5(ActionEvent event) {
        Pentagono tri = new Pentagono((int) (posicionDefaultX), (int)(posicionDefaultY),50);
        Relacion rec = new Relacion();
        rec.setFigura(tri);
        diagrama.addRelacion(rec);
        tri.dibujar(gc);
        posicionDefaultX+=20;
        posicionDefaultY+=20;
    }

    @FXML
    private void crearRelacion6(ActionEvent event) {
        Hexagono tri = new Hexagono(posicionDefaultX, posicionDefaultY,25);
        Relacion rec = new Relacion();
        rec.setFigura(tri);
        diagrama.addRelacion(rec);
        tri.dibujar(gc);
        posicionDefaultX+=20;
        posicionDefaultY+=20;
    }
    
    private void disableAllR(){
        r2.setDisable(true);
        r3.setDisable(true);
        r4.setDisable(true);
        r5.setDisable(true);
        r6.setDisable(true);
    }
    
}
