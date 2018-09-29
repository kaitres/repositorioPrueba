/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmls;

import clases.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author cmsan
 */
public class InterfazController implements Initializable {//Lo hizo el Carlos UwU
    public static Diagrama diagrama;
    
    public int posicionDefaultX = 370;
    public int posicionDefaultY = 285;
    
    public static String newEntidadNombre;
    public static boolean entidadValidacion;
    
    public static String newRelacionNombre;
    public static boolean relacionValidacion;
    public static ArrayList<Entidad> compRelacion; 
    
    public boolean arrastrando = false;
    
    public static boolean mostrarPuntos = false;
    Figura figuraMov;
    
    
    
    
    public GraphicsContext gc;
    
    @FXML
    private Canvas canvas;
    @FXML
    private Button rBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        compRelacion = new ArrayList<>();
        rBtn.setDisable(true);
        diagrama = new Diagrama();
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
    }    

    @FXML
    private void clear(ActionEvent event) {
        diagrama.clear();
        rBtn.setDisable(true);
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
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
            rec.dibujar(gc,mostrarPuntos);
        }
        
        if (!diagrama.getEntidades().isEmpty()){
            rBtn.setDisable(false);
        } else {
            rBtn.setDisable(true);
        }
    }
    
    @FXML
    private void crearRelacion(ActionEvent event) throws IOException {
        relacionValidacion = false;
        AbrirVentana.CargarVista(getClass().getResource("CrearRelacion.fxml"));
        
        if (relacionValidacion){
            Figura fig = new Figura();
            if (compRelacion.size()==1 ||
                    compRelacion.size()==2 ||
                    compRelacion.size()==4 ){
                fig.crearFigura(posicionDefaultX, posicionDefaultY, 20 , 4);
            } else {
                fig.crearFigura(posicionDefaultX, posicionDefaultY, 20 , compRelacion.size());
            }   
            Relacion rec = new Relacion(newRelacionNombre);
            rec.setFigura(fig);
            diagrama.addRelacion(rec);
            fig.dibujar(gc,mostrarPuntos);
            
            rec.setComponentes(compRelacion);
            rec.crearUniones();
            rec.dibujarUniones(gc);
        }
        InterfazController.compRelacion.clear();
    }
    
    @FXML
    private void ratonSinPresionar(MouseEvent event) {
        this.arrastrando=false;       
    }
    
    @FXML
    private void movimiento(MouseEvent event){
        Point2D mouse=new Point2D(event.getX(), event.getY());
        if(arrastrando){
            figuraMov.setPuntoCentral(mouse);
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            reDibujarTodo();
        }
        else if(dentroDeAlgunaFigura(mouse)){
            arrastrando=true;
            figuraMov =figuraEnMovimiento(mouse);
            figuraMov.setPuntoCentral(mouse);
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            reDibujarTodo();
        }
        
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
        for (Relacion entidade : diagrama.getRelaciones()) {
            
            if ((e.getX() > entidade.getFigura().getPuntoCentral().getX()-entidade.getFigura().calEscala()) &&
                    (e.getX() < entidade.getFigura().getPuntoCentral().getX()+entidade.getFigura().calEscala()) &&
                    (e.getY() > entidade.getFigura().getPuntoCentral().getY()-entidade.getFigura().calEscala()) &&
                    (e.getY() < entidade.getFigura().getPuntoCentral().getY()+entidade.getFigura().calEscala())){
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
        for (Relacion entidade : diagrama.getRelaciones()) {
            
            if ((e.getX() > entidade.getFigura().getPuntoCentral().getX()-entidade.getFigura().calEscala()) &&
                    (e.getX() < entidade.getFigura().getPuntoCentral().getX()+entidade.getFigura().calEscala()) &&
                    (e.getY() > entidade.getFigura().getPuntoCentral().getY()-entidade.getFigura().calEscala()) &&
                    (e.getY() < entidade.getFigura().getPuntoCentral().getY()+entidade.getFigura().calEscala())){
                return entidade.getFigura();
            }
        }
        return new Figura();
    }
    public void reDibujarTodo(){
        for (Entidad entidade : diagrama.getEntidades()) {
            entidade.getFigura().dibujar(gc,mostrarPuntos);
        }
        for (Relacion relacion : diagrama.getRelaciones()) {
            relacion.getFigura().dibujar(gc,mostrarPuntos);
            relacion.crearUniones();
            relacion.dibujarUniones(gc);
        }
    }    
    
    @FXML
    public void exportImage() throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("png files (.png)", ".png");
        fileChooser.getExtensionFilters().add(extFilter);
        final Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);
        WritableImage writableImage = canvas.snapshot(new SnapshotParameters(), null);
        canvas.snapshot(null, writableImage);
        RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
        try{
            ImageIO.write(renderedImage, "png", file);
        }catch( IOException r){
            
        }
        }

    @FXML
    private void mostrarPuntosDeControl(ActionEvent event) {
        this.mostrarPuntos = !mostrarPuntos;
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        reDibujarTodo();
    }
}
    

    

