/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmls;

import clases.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author cmsan
 */
public class InterfazController implements Initializable {//Lo hizo el Carlos UwU
    Diagrama diagrama;
    
    public int posicionDefaultX = 100;
    public int posicionDefaultY = 100;
    
    public static String newEntidadNombre;
    public static boolean entidadValidacion;
    
    public static String newRelacionNombre;
    public static boolean relacionValidacion;
    
    public boolean arrastrando= false;
    
    
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
    private void crearEntidad(ActionEvent event) throws IOException {
        entidadValidacion = false;
        AbrirVentana.CargarVista(getClass().getResource("CrearEntidad.fxml"));
        
        if (entidadValidacion){
        
            Figura rec = new Figura();
            rec.rectangulo(posicionDefaultX, posicionDefaultY,25);

            Entidad ent = new Entidad(newEntidadNombre);
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
    }

    @FXML
    private void crearRelacion2(ActionEvent event) throws IOException {
        relacionValidacion = false;
        AbrirVentana.CargarVista(getClass().getResource("CrearRelacion.fxml"));
        
        if (relacionValidacion){
            Figura tri = new Figura();
            tri.crearFigura(posicionDefaultX, posicionDefaultY, 20 , 4);

            Relacion rec = new Relacion(newRelacionNombre);
            rec.setFigura(tri);
            diagrama.addRelacion(rec);
            tri.dibujar(gc);
            posicionDefaultX+=20;
            posicionDefaultY+=20;
            
        }
    }
    
    @FXML
    private void crearRelacion3(ActionEvent event) throws IOException {
        relacionValidacion = false;
        AbrirVentana.CargarVista(getClass().getResource("CrearRelacion.fxml"));
        
        if (relacionValidacion){
            Figura tri = new Figura();
            tri.crearFigura(posicionDefaultX, posicionDefaultY, 20 , 3);


            Relacion rec = new Relacion(newRelacionNombre);;
            rec.setFigura(tri);
            diagrama.addRelacion(rec);
            tri.dibujar(gc);
            posicionDefaultX+=20;
            posicionDefaultY+=20;
            
        }
    }

    @FXML
    private void crearRelacion4(ActionEvent event) throws IOException {
        relacionValidacion = false;
        AbrirVentana.CargarVista(getClass().getResource("CrearRelacion.fxml"));
        
        if (relacionValidacion){
            Figura tri = new Figura();
        tri.crearFigura(posicionDefaultX, posicionDefaultY, 20 , 4);
        
        Relacion rec = new Relacion(newRelacionNombre);
        rec.setFigura(tri);
        diagrama.addRelacion(rec);
        tri.dibujar(gc);
        posicionDefaultX+=20;
        posicionDefaultY+=20;
        
        }
        
    }

    @FXML
    private void crearRelacion5(ActionEvent event) throws IOException {
        relacionValidacion = false;
        AbrirVentana.CargarVista(getClass().getResource("CrearRelacion.fxml"));
        
        if (relacionValidacion){
            Figura tri = new Figura();
            tri.crearFigura(posicionDefaultX, posicionDefaultY, 20 , 5);

            Relacion rec = new Relacion(newRelacionNombre);
            rec.setFigura(tri);
            diagrama.addRelacion(rec);
            tri.dibujar(gc);
            posicionDefaultX+=20;
            posicionDefaultY+=20;
            
        }
    }

    @FXML
    private void crearRelacion6(ActionEvent event) throws IOException {
        relacionValidacion = false;
        AbrirVentana.CargarVista(getClass().getResource("CrearRelacion.fxml"));
        
        if (relacionValidacion){
            Figura tri = new Figura();
            tri.crearFigura(posicionDefaultX, posicionDefaultY, 20 , 6);

            Relacion rec = new Relacion(newRelacionNombre);
            rec.setFigura(tri);
            diagrama.addRelacion(rec);
            tri.dibujar(gc);
            posicionDefaultX+=20;
            posicionDefaultY+=20;
            
        }
    }
    @FXML
    private void ratonPresionado(MouseEvent event) {
        
        Point2D coorMov = new Point2D(event.getX(), event.getY());
        if (!arrastrando) {

            if (dentroDeAlgunaFigura(coorMov)) {
                
                arrastrando = true;
            }
        }else{

            figuraEnMovimiento(coorMov).setPuntoCentral(new Point2D(event.getX(),event.getY())); 
            
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            reDibujarTodo();
            
        }
    }

    @FXML
    private void ratonSinPresionar(MouseEvent event) {
        
        this.arrastrando=false;
    }
    public boolean dentroDeAlgunaFigura(Point2D e){        
        for (Entidad entidade : diagrama.getEntidades()) {
            if ((e.getX() > entidade.getFigura().getCoordenadas().get(0).getX()) &&
                    (e.getX() < entidade.getFigura().getCoordenadas().get(1).getX()) &&
                    (e.getY() > entidade.getFigura().getCoordenadas().get(0).getY()) &&
                    (e.getY() < entidade.getFigura().getCoordenadas().get(2).getY())){
                return true;
            }
        }
        return false;
    }
    public Figura figuraEnMovimiento(Point2D e){
        for (Entidad entidad : diagrama.getEntidades()) {
            if ((e.getX() > entidad.getFigura().getCoordenadas().get(0).getX()) &&
                    (e.getX() < entidad.getFigura().getCoordenadas().get(1).getX()) &&
                    (e.getY() > entidad.getFigura().getCoordenadas().get(0).getY()) &&
                    (e.getY() < entidad.getFigura().getCoordenadas().get(2).getY())){
                
                return entidad.getFigura();
            }
        }
        
        return new Figura();
    }
    public void reDibujarTodo(){
        for (Entidad entidade : diagrama.getEntidades()) {
            entidade.getFigura().dibujar(gc);
        }
        for (Relacion relacion : diagrama.getRelaciones()) {
            relacion.getFigura().dibujar(gc);
        }
    }
    private void disableAllR(){
        r2.setDisable(true);
        r3.setDisable(true);
        r4.setDisable(true);
        r5.setDisable(true);
        r6.setDisable(true);
    }
    
}
