    
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.*;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import sun.management.resources.agent;

/**
 *
 * @author cmsan
 */
public class InterfazController implements Initializable {//Lo hizo el Carlos UwU
    public static Diagrama diagrama;
    public static int id = 0;

    public static int indiceD =-1;
    public ArrayList<Diagrama> diagramas = new ArrayList();
    
    public static int posicionDefaultX = 370;
    public static int posicionDefaultY = 285;
    
    double canvasTX = 773;
    double canvasTY = 522;
    
    public static Agrupacion agrupacionActual;
    public static boolean relacionDebil = false;
    public static String newRelacionNombre;
    public static boolean relacionValidacion;
    public static ArrayList<Entidad> compRelacion; 
    
    public boolean arrastrando = false;
    public boolean elipse = false;
    public boolean figura = false;
    
    public static boolean nivelPropiedadCompuesta=true;
    
    public static boolean editar = false;
    boolean state = false;
    public static boolean mostrarPuntos = false;
    
    public static Entidad entidadActual;
    public static Relacion relacionActual;
    public static Herencia herenciaActual;
    public static String nombreActual;

    public static ArrayList<Propiedad> propiedadActual; 
    public static int cantProps;
 
    public ArrayList<Point2D> puntosDeCorte;
    
    public boolean agruMov = false;
    public Agrupacion agrupacionMovimiento;
    
    int zoomTime;
    
    Figura figuraMov;

    public GraphicsContext gc;
    
    @FXML
    private Canvas canvas;
    @FXML
    private Button rBtn;
    @FXML
    private Button pngBtn;
    @FXML
    private Button pdfBtn;
    @FXML
    private Button hBtn;
    @FXML
    private Button zoomOBTN;
    @FXML
    private Button zoomIBTN;
    @FXML
    private CheckBox checkBEditar;
    @FXML
    private Label debilLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        zoomTime = 0;
        puntosDeCorte = new ArrayList<>();
        propiedadActual = new ArrayList<>();
        compRelacion = new ArrayList<>();
        rBtn.setDisable(true);
        hBtn.setDisable(true);
        pngBtn.setDisable(true);
        pdfBtn.setDisable(true);
        diagrama = new Diagrama(id);
        puntoGuardado();
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        cantProps = 0;
        
        
    }    

    @FXML
    private void clear(ActionEvent event) {
        diagrama.clear();
        rBtn.setDisable(true);
        pngBtn.setDisable(true);
        pdfBtn.setDisable(true);
        hBtn.setDisable(true);
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        cantProps = 0;
        
    }
     @FXML
    private void agregacion() throws IOException{
        AbrirVentana.CargarVista(getClass().getResource("/fxmls/CrearAgregacion.fxml"));
        reDibujarTodo();
    }

//---------------------------------------------------------------------------------------------
    private void puntoGuardado() {
/*
        if(indiceD < diagramas.size() - 1 ){
            while(indiceD != diagramas.size()-1 ){
                diagramas.remove(diagramas.size()-1);
            }

                
          
        }
        if(diagramas.size() > 20 ){
            diagramas.remove(0);
        }
        else{ 
            indiceD +=1;      
        }       
        diagramas.add(diagrama.clon(id));
        id+=1; 
*/
     }
    
    @FXML 
    private void deshacer(){
       
        if(indiceD > 0 ){
            indiceD-=1;
            diagrama = diagramas.get(indiceD).clon(id);
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            reDibujarTodo();
        }
        
    }
    
    @FXML
    private void rehacer(){
        
        
        if(indiceD < diagramas.size()-1){
            indiceD += 1;
            diagrama = diagramas.get(indiceD).clon(id);
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            reDibujarTodo();
        }
        
    }
    //-----------------------------------------------------------------------------------
    @FXML
    private void crearEntidad(ActionEvent event) throws IOException {
        
        AbrirVentana.CargarVista(getClass().getResource("/fxmls/CrearEntidad.fxml"));
        
        if (entidadActual!=null){
            
          
            diagrama.addEntidad(entidadActual);
            
            entidadActual.getFigura().dibujar(gc,mostrarPuntos);
            entidadActual.getFigura().pintar(gc);
            
            for (Propiedad prop : entidadActual.getPropiedades()){
                prop.getElip().dibujarElipse(gc, prop.getTipo());
                if(prop.getPropiedades()!=null){
                    for(Propiedad prop2 : prop.getPropiedades()){
                        prop2.getElip().dibujarElipse(gc, prop2.getTipo());
                    
                    }
                }
            }
                        
            
            puntoGuardado();
        }
        
        if (!diagrama.getEntidades().isEmpty()){
            rBtn.setDisable(false);
            pngBtn.setDisable(false);
            pdfBtn.setDisable(false);
            if (diagrama.getEntidades().size()>=2) {
                hBtn.setDisable(false);
            }else{
                hBtn.setDisable(true);
            }
        } else {
            rBtn.setDisable(true);
            pngBtn.setDisable(true);
            pdfBtn.setDisable(true);
            hBtn.setDisable(true);
            
        }
        entidadActual=null;
        propiedadActual= new ArrayList<>();
        debilLabel.setVisible(!debilConRelacion());
        
    }
    
    @FXML
    private void crearRelacion(ActionEvent event) throws IOException {
        relacionValidacion = false;
        AbrirVentana.CargarVista(getClass().getResource("/fxmls/CrearRelacion.fxml"));
        
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
            rec.setPropiedades(propiedadActual);

            if(hayClave(compRelacion) && 1==hayDebil(compRelacion)){
                ArrayList<Integer> debiles =cualesDependeran(compRelacion);
                if(!debiles.isEmpty()){
                    
                    rec.setPosicionDebiles(debiles);
                }
                relacionDebil=false;
            }
            rec.setComponentes(compRelacion);
            rec.metamorfosear();
            rec.crearUniones();
            if (rec.getComponentes().size()<3) {
                relacionActual=rec;
                AbrirVentana.CargarVista(getClass().getResource("/fxmls/CrearCardinalidad.fxml"));
                
                relacionActual = null;
            }
            diagrama.addRelacion(rec);
            fig.dibujar(gc,mostrarPuntos);
            
            
            rec.f(gc);
            fig.pintar(gc);
            for (Propiedad prop : rec.getPropiedades()){
                prop.getElip().dibujarElipse(gc, prop.getTipo());
                if(prop.getPropiedades()!=null){
                    for(Propiedad prop2 : prop.getPropiedades()){
                        prop2.getElip().dibujarElipse(gc, prop2.getTipo());
                    
                    }
                }
            }
            puntoGuardado();
           
        }
        
        relacionActual= null;
        propiedadActual= new ArrayList<>();
        InterfazController.compRelacion.clear();
        debilLabel.setVisible(!debilConRelacion());
    }
    
    public static int hayDebil(ArrayList<Entidad> componentes ){
        int i=0;
        for (Entidad componente : componentes) {
            if(componente.getFigura().isDebil()){
                i++;
               
            }
        }
        return i;
    }
    
    
    public static boolean hayClave(ArrayList<Entidad> componentes){
        for (Entidad componente : componentes) {
            for (Propiedad propiedad : componente.getPropiedades()) {
                if(propiedad.getTipo().equals(Tipo.clave)){
                    return true;
                }
            }
        }
        return false;     
    }
    
    
    private ArrayList<Integer> cualesDependeran(ArrayList<Entidad> componentes) throws IOException{
        ArrayList<Integer> debiles = new ArrayList<>();
        for (Entidad componente : componentes) {
            if(componente.getFigura().isDebil()){
                
                entidadActual=componente;
                AbrirVentana.CargarVista(getClass().getResource("/fxmls/RelacionDebil.fxml"));
                
                entidadActual=null;
                if(relacionDebil){
                    debiles.add(componentes.indexOf(componente));
                }
                
            }
        }
        return debiles;
    }
    @FXML
    private void ratonSinPresionar(MouseEvent event) {
        if(arrastrando){
            puntoGuardado();
        }
         this.agruMov=false;
        this.arrastrando=false;       
    }
    
    @FXML
    private void movimiento(MouseEvent event){
        
        
        if(!editar){
            Point2D mouse=new Point2D(event.getX(), event.getY());
            
            if(arrastrando){
                
                if(agruMov){
                    
                    agrupacionMovimiento.movimiento(mouse, gc);
                }
                else{
                    figuraMov.setPuntoCentral(mouse);
                }
                
                if (getMayores().getX()>770){ 
                    canvas.setWidth(getMayores().getX());
                }
                if (getMayores().getY()>520){
                    canvas.setHeight(getMayores().getY());
                }
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                reDibujarTodo();

            }
            else{
                if(dentroAgrupacion(mouse)){
                    arrastrando=true;
                    agruMov=true;
                    agrupacionMovimiento=agrupacionEnMovimiento(mouse);
                    agrupacionMovimiento.movimiento(mouse, gc);
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    reDibujarTodo();
                }
                if(dentroDeAlgunaFigura(mouse)){
                    arrastrando=true;
                    figuraMov =figuraEnMovimiento(mouse);
                    figuraMov.setPuntoCentral(mouse);
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    reDibujarTodo();
                }
                
            }
        }
    }
    
    public Agrupacion agrupacionEnMovimiento(Point2D e){
        for(int i=0 ; i<diagrama.getEntidades().size() ; i++){
               if(diagrama.getEntidades().get(i) instanceof Agrupacion){
                   Agrupacion entidade = (Agrupacion) diagrama.getEntidades().get(i);
                   if ((e.getX() > ((Agrupacion) entidade).getFigura().getCordCuadradoMov().get(0).getX()) &&
                       (e.getX() < ((Agrupacion) entidade).getFigura().getCordCuadradoMov().get(1).getX()) &&
                       (e.getY() > ((Agrupacion) entidade).getFigura().getCordCuadradoMov().get(0).getY()) &&
                       (e.getY() < ((Agrupacion) entidade).getFigura().getCordCuadradoMov().get(2).getY())){
                       return (Agrupacion) entidade;
               }
           }
        }
        return new Agrupacion(null , null);   
    }
    public boolean dentroAgrupacion(Point2D e){
        for(int i=0 ; i<diagrama.getEntidades().size() ; i++){
           if(diagrama.getEntidades().get(i) instanceof Agrupacion){
                Agrupacion entidade = (Agrupacion) diagrama.getEntidades().get(i);
                if ((e.getX() > ((Agrupacion) entidade).getFigura().getCordCuadradoMov().get(0).getX()) &&
                    (e.getX() < ((Agrupacion) entidade).getFigura().getCordCuadradoMov().get(1).getX()) &&
                    (e.getY() > ((Agrupacion) entidade).getFigura().getCordCuadradoMov().get(0).getY()) &&
                    (e.getY() < ((Agrupacion) entidade).getFigura().getCordCuadradoMov().get(2).getY())){
                        return true;
                }
           }    
        }
        return false;
        }
    public Agrupacion agrupacionMovimiento(Agrupacion agrupacion , Point2D e){
        if ((e.getX() > ((Agrupacion) agrupacion).getFigura().getCordCuadradoMov().get(0).getX()) &&
                    (e.getX() < ((Agrupacion) agrupacion).getFigura().getCordCuadradoMov().get(1).getX()) &&
                    (e.getY() > ((Agrupacion) agrupacion).getFigura().getCordCuadradoMov().get(0).getY()) &&
                    (e.getY() < ((Agrupacion) agrupacion).getFigura().getCordCuadradoMov().get(2).getY())){
                    return agrupacion;
        }
        return null;
    }
    
    public boolean dentroDeAlgunaFigura(Point2D e){     
        for(int i=0 ; i<diagrama.getEntidades().size();i++){
            if(!(diagrama.getEntidades().get(i) instanceof Agrupacion)){
                Entidad entidade = diagrama.getEntidades().get(i);
                if ((e.getX() > entidade.getFigura().getCoordenadas().get(0).getX()) &&
                (e.getX() < entidade.getFigura().getCoordenadas().get(1).getX()) &&
                (e.getY() > entidade.getFigura().getCoordenadas().get(0).getY()) &&
                (e.getY() < entidade.getFigura().getCoordenadas().get(2).getY())){
                    return true;
                }
                for (Propiedad prop: entidade.getPropiedades()){
                    if ((e.getX() > prop.getElip().getCoordenadas().get(0).getX()) &&
                    (e.getX() < prop.getElip().getCoordenadas().get(1).getX()) &&
                    (e.getY() > prop.getElip().getCoordenadas().get(0).getY()) &&
                    (e.getY() < prop.getElip().getCoordenadas().get(2).getY())) {
                        return true;
                    }
                    if(prop.getPropiedades()!=null){
                        for(Propiedad prop2 : prop.getPropiedades()){
                            if ((e.getX() > prop2.getElip().getCoordenadas().get(0).getX()) &&
                            (e.getX() < prop2.getElip().getCoordenadas().get(1).getX()) &&
                            (e.getY() > prop2.getElip().getCoordenadas().get(0).getY()) &&
                            (e.getY() < prop2.getElip().getCoordenadas().get(2).getY())) {
                                return true;
                            }
                        }
                    }
                }
            }
            
        }
        for (Relacion entidade : diagrama.getRelaciones()) {
            
            if ((e.getX() > entidade.getFigura().getPuntoCentral().getX()-entidade.getFigura().calEscala()) &&
                    (e.getX() < entidade.getFigura().getPuntoCentral().getX()+entidade.getFigura().calEscala()) &&
                    (e.getY() > entidade.getFigura().getPuntoCentral().getY()-entidade.getFigura().calEscala()) &&
                    (e.getY() < entidade.getFigura().getPuntoCentral().getY()+entidade.getFigura().calEscala())){
                return true;
            }
            for (Propiedad prop: entidade.getPropiedades()){
                if ((e.getX() > prop.getElip().getCoordenadas().get(0).getX()) &&
                (e.getX() < prop.getElip().getCoordenadas().get(1).getX()) &&
                (e.getY() > prop.getElip().getCoordenadas().get(0).getY()) &&
                (e.getY() < prop.getElip().getCoordenadas().get(2).getY())) {
                    return true;
                }
                if(prop.getPropiedades()!=null){
                    for(Propiedad prop2 : prop.getPropiedades()){
                        if ((e.getX() > prop2.getElip().getCoordenadas().get(0).getX()) &&
                        (e.getX() < prop2.getElip().getCoordenadas().get(1).getX()) &&
                        (e.getY() > prop2.getElip().getCoordenadas().get(0).getY()) &&
                        (e.getY() < prop2.getElip().getCoordenadas().get(2).getY())) {
                            return true;
                        }
                    }
                }
            }
        }
        for (Herencia herencia : diagrama.getHerencias()) {
            if ((e.getX() > herencia.getFigura().getPuntoCentral().getX()-herencia.getFigura().calEscala()) &&
                    (e.getX() < herencia.getFigura().getPuntoCentral().getX()+herencia.getFigura().calEscala()) &&
                    (e.getY() > herencia.getFigura().getPuntoCentral().getY()-herencia.getFigura().calEscala()) &&
                    (e.getY() < herencia.getFigura().getPuntoCentral().getY()+herencia.getFigura().calEscala())){
                return true;
            }
        }
        return false;
    }
    
  
    public Figura figuraEnMovimiento(Point2D e){
        for (int i = 0; i < diagrama.getEntidades().size(); i++){
            if(!(diagrama.getEntidades().get(i) instanceof Agrupacion)){
                Entidad entidad = diagrama.getEntidades().get(i);
                if ((e.getX() > entidad.getFigura().getCoordenadas().get(0).getX()) &&
                    (e.getX() < entidad.getFigura().getCoordenadas().get(1).getX()) &&
                    (e.getY() > entidad.getFigura().getCoordenadas().get(0).getY()) &&
                    (e.getY() < entidad.getFigura().getCoordenadas().get(2).getY())){

                    return entidad.getFigura();
                }
                for (Propiedad prop: entidad.getPropiedades()){
                    if ((e.getX() > prop.getElip().getCoordenadas().get(0).getX()) &&
                        (e.getX() < prop.getElip().getCoordenadas().get(1).getX()) &&
                        (e.getY() > prop.getElip().getCoordenadas().get(0).getY()) &&
                        (e.getY() < prop.getElip().getCoordenadas().get(2).getY())) {

                        return prop.getElip();
                    }
                    if(prop.getPropiedades()!=null){
                        for(Propiedad prop2 : prop.getPropiedades()){
                            if ((e.getX() > prop2.getElip().getCoordenadas().get(0).getX()) &&
                                (e.getX() < prop2.getElip().getCoordenadas().get(1).getX()) &&
                                (e.getY() > prop2.getElip().getCoordenadas().get(0).getY()) &&
                                (e.getY() < prop2.getElip().getCoordenadas().get(2).getY())) {
                                return prop2.getElip();
                            }
                        }
                    }
                }
            }
            
        }
        for (Relacion entidade : diagrama.getRelaciones()) {
            
            if ((e.getX() > entidade.getFigura().getPuntoCentral().getX()-entidade.getFigura().calEscala()) &&
                    (e.getX() < entidade.getFigura().getPuntoCentral().getX()+entidade.getFigura().calEscala()) &&
                    (e.getY() > entidade.getFigura().getPuntoCentral().getY()-entidade.getFigura().calEscala()) &&
                    (e.getY() < entidade.getFigura().getPuntoCentral().getY()+entidade.getFigura().calEscala())){
                return entidade.getFigura();
            }
            for (Propiedad prop: entidade.getPropiedades()){
                if ((e.getX() > prop.getElip().getCoordenadas().get(0).getX()) &&
                (e.getX() < prop.getElip().getCoordenadas().get(1).getX()) &&
                (e.getY() > prop.getElip().getCoordenadas().get(0).getY()) &&
                (e.getY() < prop.getElip().getCoordenadas().get(2).getY())) {
                    return prop.getElip();
                }
                if(prop.getPropiedades()!=null){
                    for(Propiedad prop2 : prop.getPropiedades()){
                        if ((e.getX() > prop2.getElip().getCoordenadas().get(0).getX()) &&
                        (e.getX() < prop2.getElip().getCoordenadas().get(1).getX()) &&
                        (e.getY() > prop2.getElip().getCoordenadas().get(0).getY()) &&
                        (e.getY() < prop2.getElip().getCoordenadas().get(2).getY())) {
                            return prop2.getElip();
                        }
                    }
                }
            }
        }
        for (Herencia herencia : diagrama.getHerencias()) {
            if ((e.getX() > herencia.getFigura().getPuntoCentral().getX()-herencia.getFigura().calEscala()) &&
                    (e.getX() < herencia.getFigura().getPuntoCentral().getX()+herencia.getFigura().calEscala()) &&
                    (e.getY() > herencia.getFigura().getPuntoCentral().getY()-herencia.getFigura().calEscala()) &&
                    (e.getY() < herencia.getFigura().getPuntoCentral().getY()+herencia.getFigura().calEscala())){
                return herencia.getFigura();
            }
        }
        return null;
    }
    
    
    public void reDibujarTodo(){
        System.out.println("relaciones"+ diagrama.relaciones);
        actualizarAgru();    
        eliminarRelacion();
        for (Object entidade : diagrama.entidades) {
            if(entidade instanceof Agrupacion){
//                System.out.println("nombre rel agre "+((Agrupacion) entidade).getRelacion().getNombre());
            }
        }
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = 0; i <  diagrama.getEntidades().size(); i++) {
            if(! (diagrama.getEntidades().get(i) instanceof Agrupacion)){
                
                diagrama.getEntidades().get(i).getFigura().dibujar(gc, mostrarPuntos);
                diagrama.getEntidades().get(i).f(gc);
                for (Propiedad prop : diagrama.getEntidades().get(i).getPropiedades()){
                    prop.getElip().dibujarElipse(gc, prop.getTipo());
                    if(prop.getPropiedades()!=null){
                        for(Propiedad prop2 : prop.getPropiedades()){
                            prop2.getElip().dibujarElipse(gc, prop2.getTipo());

                        }
                    }
                }
            }
        }
        
        for (Relacion relacion : diagrama.getRelaciones()) {
            System.out.println("afuera "+ relacion.getNombre());
            relacion.correccion();
            relacion.metamorfosear();
            relacion.getFigura().dibujar(gc,mostrarPuntos);
            relacion.crearUniones();
            relacion.f(gc);
            for (Propiedad prop : relacion.getPropiedades()){
                prop.getElip().dibujarElipse(gc, prop.getTipo());
                if(prop.getPropiedades()!=null){
                    for(Propiedad prop2 : prop.getPropiedades()){
                        prop2.getElip().dibujarElipse(gc, prop2.getTipo());
                    
                    }
                }
            }
        }
        for (Herencia herencia : diagrama.getHerencias()) {
            herencia.f(gc);
            herencia.getFigura().dibujarCirculo(gc);
        }
        
        for (Relacion relacion : diagrama.getRelaciones()) {
            relacion.getFigura().pintar(gc);
        }
        
         for (int i = 0; i <  diagrama.getEntidades().size(); i++) {
            if(!(diagrama.getEntidades().get(i) instanceof Agrupacion)){
                diagrama.getEntidades().get(i).getFigura().pintar(gc);
            }
        }
        
        for (Entidad entidade : diagrama.getEntidades()) {
            if(entidade instanceof Agrupacion){
                entidade.getFigura().dibujar(gc, ((Agrupacion) entidade).getRelacion(), (Agrupacion)entidade);
            }
        }
        
//        .out.println(((Agrupacion)diagrama.getEntidades().get(diagrama.getEntidades().size()-1)).getRelacion().getNombre());
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
        }catch( IllegalArgumentException r){
            
        }
        recortar(file);        
        }
    
    public void puntosCorte(){
        puntosDeCorte.clear();
        puntosDeCorte.add(getMenores());
        puntosDeCorte.add(getMayores());
    }
    
    private Point2D getMayores(){
        double XMayor = diagrama.getEntidades().get(0).getFigura().getCoordenadas().get(0).getX();
        double YMayor = diagrama.getEntidades().get(0).getFigura().getCoordenadas().get(0).getY();;
        for (Entidad entidad : diagrama.getEntidades()) {
            for (Point2D cord : entidad.getFigura().getCoordenadas()) {
                if(cord.getX()> XMayor){
                    XMayor = cord.getX();
                }
                if(cord.getY()> YMayor){
                    YMayor = cord.getY();
                }  
            }
            for (Propiedad prop : entidad.getPropiedades()){
                for (Point2D cord : prop.getElip().getCoordenadas()) {
                    if(cord.getX()> XMayor){
                        XMayor = cord.getX();
                    }
                    if(cord.getY()> YMayor){
                        YMayor = cord.getY();
                    }  
                }
            }
        }
        for (Relacion relacion : diagrama.getRelaciones()) {
            for (Point2D cord : relacion.getFigura().getCoordenadas()) {
                if(cord.getX()> XMayor){
                    XMayor = (int)cord.getX();
                }
                if(cord.getY()> YMayor){
                    YMayor = (int)cord.getY();
                }
            }
        }
        return new Point2D (XMayor+5, YMayor+5);
    }
    
    private Point2D getMenores(){
        double XMenor = diagrama.getEntidades().get(0).getFigura().getCoordenadas().get(0).getX();
        double YMenor = diagrama.getEntidades().get(0).getFigura().getCoordenadas().get(0).getY();
        
        for (Entidad entidad : diagrama.getEntidades()) {
            for (Point2D cord : entidad.getFigura().getCoordenadas()) {
                if(cord.getX()< XMenor){
                    XMenor = cord.getX();
                }
                if(cord.getY()< YMenor){
                    YMenor = cord.getY();
                }
            }
            for (Propiedad prop : entidad.getPropiedades()){
                for (Point2D cord : prop.getElip().getCoordenadas()) {
                    if(cord.getX()< XMenor){
                        XMenor = cord.getX();
                    }
                    if(cord.getY()< YMenor){
                        YMenor = cord.getY();
                    }  
                }
            }
        }
        for (Relacion relacion : diagrama.getRelaciones()) {
            for (Point2D cord : relacion.getFigura().getCoordenadas()) {
                if(cord.getX()< XMenor){
                    XMenor = cord.getX();
                }
                if(cord.getY()< YMenor){
                    YMenor = cord.getY();
                }              
            }
        }
        return new Point2D(XMenor-5, YMenor-5);
    }
    
    public void recortar(File file) throws IOException{
        puntosCorte();
        java.awt.Image image = new ImageIcon(file.getPath()).getImage();
        BufferedImage recorte = ImageIO.read(file);
        int XMenor=(int)puntosDeCorte.get(0).getX();
        int YMenor=(int)puntosDeCorte.get(0).getY();
        int XMayor=(int)puntosDeCorte.get(1).getX();
        int YMayor=(int)puntosDeCorte.get(1).getY();
        if(XMenor-50<0){
            XMenor=3;
        }
        if(YMenor-50<0){
            YMenor=3;
        }
        if(XMayor >canvas.getWidth()-1){
            
            XMayor=(int)canvas.getWidth()-4;
            
        }
        if(YMayor >canvas.getHeight()-1){
            YMayor=(int)canvas.getHeight()-4;
            
        }
        try{
            BufferedImage tmp_Recorte = ((BufferedImage) recorte).getSubimage((int)XMenor -3 ,(int) YMenor -3 ,(int) XMayor -XMenor+3  ,(int) YMayor -YMenor+ 3) ;
        
            ImageIO.write(tmp_Recorte, "png",file);
        } catch (IOException | IllegalArgumentException e) {
            
        }
    }
    
    @FXML
    private void exportPDF() throws IOException, DocumentException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("pdf files (.pdf)", ".pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        final Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);
        OutputStream archivo = null;
        try {
            archivo = new FileOutputStream(file);
        }catch(Exception e){
        }

        WritableImage writableImage = canvas.snapshot(new SnapshotParameters(), null);
        canvas.snapshot(null, writableImage);
        File file2 = new File("chart.png");
        RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
        try {
           ImageIO.write(renderedImage, "png", file2); 
        }catch( IllegalArgumentException r){

        }
        recortar(file2);
        try{
            float ancho = (float) (puntosDeCorte.get(1).getX() - puntosDeCorte.get(0).getX()) +50;
            float alto =(float) (puntosDeCorte.get(1).getY() - puntosDeCorte.get(0).getY()) +50;
            Rectangle rectangle = new Rectangle( ancho + 5 , alto +5);
            Document doc = new Document(rectangle);
            PdfWriter.getInstance(doc, archivo);
            Image img = Image.getInstance("chart.png");
            img.setBorderColor(BaseColor.BLACK);           
            doc.setMargins(0, ancho, 0, alto);
            doc.open();
            doc.add(img);
            doc.close();
            file2.delete();
        }catch(Exception e){

        }
        
        
    }
    
    
    @FXML
    private void mostrarPuntosDeControl(ActionEvent event) {
        this.mostrarPuntos = !mostrarPuntos;
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        reDibujarTodo();
    }
    
    @FXML
    private void editar(ActionEvent event) {
        this.editar = !editar;
        this.state = !state;
    }
    
      @FXML
    public void modificar(MouseEvent event) throws IOException{
        
        if(editar){
            Point2D e=new Point2D(event.getX(), event.getY());
            for(Object entidad : diagrama.getEntidades() ){
                if(!(entidad instanceof Agrupacion)){
                    if ((e.getX() > ((Entidad)entidad).getFigura().getCoordenadas().get(0).getX()) &&
                            (e.getX() < ((Entidad)entidad).getFigura().getCoordenadas().get(1).getX()) &&
                            (e.getY() > ((Entidad)entidad).getFigura().getCoordenadas().get(0).getY()) &&
                            (e.getY() < ((Entidad)entidad).getFigura().getCoordenadas().get(2).getY())){
                        entidadActual=((Entidad)entidad);
                        AbrirVentana.CargarVista(getClass().getResource("/fxmls/DatosEntidad.fxml"));
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        reDibujarTodo();
                        puntoGuardado();
                        break;
                    }
                }
            }
            for (Relacion relacion : diagrama.getRelaciones()) {
            
                if ((e.getX() > relacion.getFigura().getPuntoCentral().getX()-relacion.getFigura().calEscala()) &&
                        (e.getX() < relacion.getFigura().getPuntoCentral().getX()+relacion.getFigura().calEscala()) &&
                        (e.getY() > relacion.getFigura().getPuntoCentral().getY()-relacion.getFigura().calEscala()) &&
                        (e.getY() < relacion.getFigura().getPuntoCentral().getY()+relacion.getFigura().calEscala())){
                        relacionActual = relacion;
                        AbrirVentana.CargarVista(getClass().getResource("/fxmls/DatosRelacion.fxml"));
                        if(relacionActual == null){
                            
                            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                            reDibujarTodo();
                            puntoGuardado();
                            break;
                        }
                        if(hayClave(relacionActual.getComponentes()) && 0<hayDebil(relacionActual.getComponentes())){
                            ArrayList<Integer> debiles =cualesDependeran(relacionActual.getComponentes());
                            if(debiles.isEmpty()){
                                relacionActual.getFigura().setDebil(false);
                            }
                            relacionActual.setPosicionDebiles(debiles);
                            
                            relacionDebil=false;
                        }else{
                            relacionActual.getFigura().setDebil(false);
                            relacionActual.setPosicionDebiles(new ArrayList<Integer>());
                        }
                        relacionActual.metamorfosear();
                        relacionActual.crearUniones();
                        
                        
                        if (relacion.getComponentes().size()<3) {
                            
                            AbrirVentana.CargarVista(getClass().getResource("/fxmls/CrearCardinalidad.fxml"));
                
                            relacionActual = null;
                        }
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        reDibujarTodo();
                        puntoGuardado();
                        break;
                }
            }
            for(int i=0 ; i<diagrama.getEntidades().size() ; i++){
               if(diagrama.getEntidades().get(i) instanceof Agrupacion){
                   Agrupacion entidade = (Agrupacion) diagrama.getEntidades().get(i);
                   if ((e.getX() > ((Agrupacion) entidade).getFigura().getCordCuadradoMov().get(0).getX()) &&
                       (e.getX() < ((Agrupacion) entidade).getFigura().getCordCuadradoMov().get(1).getX()) &&
                       (e.getY() > ((Agrupacion) entidade).getFigura().getCordCuadradoMov().get(0).getY()) &&
                       (e.getY() < ((Agrupacion) entidade).getFigura().getCordCuadradoMov().get(2).getY())){
                       entidadActual=entidade;
                       AbrirVentana.CargarVista(getClass().getResource("/fxmls/DatosAgregacion.fxml"));
                       entidadActual=null;
                       gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                       reDibujarTodo();
                    }
                }
            }
            for (Herencia relacion : diagrama.getHerencias()) {
            
                if ((e.getX() > relacion.getFigura().getPuntoCentral().getX()-relacion.getFigura().calEscala()) &&
                        (e.getX() < relacion.getFigura().getPuntoCentral().getX()+relacion.getFigura().calEscala()) &&
                        (e.getY() > relacion.getFigura().getPuntoCentral().getY()-relacion.getFigura().calEscala()) &&
                        (e.getY() < relacion.getFigura().getPuntoCentral().getY()+relacion.getFigura().calEscala())){
                        herenciaActual = relacion;
                        AbrirVentana.CargarVista(getClass().getResource("/fxmls/EditarHerencia.fxml"));
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        reDibujarTodo();
                        puntoGuardado();
                        break;
                }
            }
            propiedadActual= new ArrayList<>();
            entidadActual=null;
            if (diagrama.getEntidades().isEmpty()) {
                rBtn.setDisable(true);
                pngBtn.setDisable(true);
                pdfBtn.setDisable(true);
                hBtn.setDisable(true);
            }
            
            if (diagrama.getEntidades().size()<2) {
                hBtn.setDisable(true);
            }
            
        }
        debilLabel.setVisible(!debilConRelacion());
    }

    @FXML
    private void zoomOut(ActionEvent event) {
        if (zoomTime == 2){
            canvas.setWidth(canvas.getWidth()/1.5);
            canvas.setHeight(canvas.getHeight()/1.5);
            canvas.setTranslateX(canvas.getWidth()/9);
            canvas.setTranslateY(canvas.getHeight()/9);
            canvas.setScaleX(canvas.getScaleX()/1.5);
            canvas.setScaleY(canvas.getScaleY()/1.5);
        }
        if (zoomTime == 1){
            canvas.setWidth(canvas.getWidth()/1.2);
            canvas.setHeight(canvas.getHeight()/1.2);
            canvas.setTranslateX(0);
            canvas.setTranslateY(0);
            canvas.setScaleX(canvas.getScaleX()/1.2);
            canvas.setScaleY(canvas.getScaleY()/1.2);
        }
        if (zoomTime == 0){
            canvas.setTranslateX(0);
            canvas.setTranslateY(0);
            canvas.setScaleX(canvas.getScaleX()/1.2);
            canvas.setScaleY(canvas.getScaleY()/1.2);
        }
        if (zoomTime==-1){
            canvas.setTranslateX(0);
            canvas.setTranslateY(0);
            canvas.setScaleX(canvas.getScaleX()/1.5);
            canvas.setScaleY(canvas.getScaleY()/1.5);
        }
        
        zoomTime -= 1;
        if (zoomTime <= -2){
            zoomOBTN.setDisable(true);
            checkBEditar.setDisable(true);
            if (!state){
                editar = true;
            }
        } else{
            zoomOBTN.setDisable(false);   
            zoomIBTN.setDisable(false);
            checkBEditar.setDisable(false);
            editar = state;
        }
    }
    @FXML
    private void zoomIn(ActionEvent event) {
        if (zoomTime == 1){
            canvas.setWidth(canvas.getWidth()*1.5);
            canvas.setHeight(canvas.getHeight()*1.5);
            canvas.setScaleX(canvas.getScaleX()*1.5);
            canvas.setScaleY(canvas.getScaleY()*1.5);
            canvas.setTranslateX(canvas.getWidth()*0.4);
            canvas.setTranslateY(canvas.getHeight()*0.4);
        }
        if (zoomTime == 0){
            canvas.setWidth(canvas.getWidth()*1.2);
            canvas.setHeight(canvas.getHeight()*1.2);
            canvas.setScaleX(canvas.getScaleX()*1.2);
            canvas.setScaleY(canvas.getScaleY()*1.2);
            canvas.setTranslateX(canvas.getWidth()*0.1);
            canvas.setTranslateY(canvas.getHeight()*0.1);
        }
        if (zoomTime==-1){
            canvas.setTranslateX(0);
            canvas.setTranslateY(0);
            canvas.setScaleX(canvas.getScaleX()*1.2);
            canvas.setScaleY(canvas.getScaleY()*1.2);
        }
        if (zoomTime==-2){
            canvas.setTranslateX(0);
            canvas.setTranslateY(0);
            canvas.setScaleX(canvas.getScaleX()*1.5);
            canvas.setScaleY(canvas.getScaleY()*1.5);
        }
        zoomTime += 1;
        if (zoomTime >= 2){
            zoomIBTN.setDisable(true);
            checkBEditar.setDisable(true);
            if (!state){
                editar = true;
            }
        } else{
            zoomIBTN.setDisable(false);   
            zoomOBTN.setDisable(false);
            checkBEditar.setDisable(false);
            editar = state;
        }
    }
    @FXML
    private void crearHerencia(ActionEvent event) throws IOException {
        AbrirVentana.CargarVista(getClass().getResource("/fxmls/crearHerencia.fxml"));
        if (herenciaActual!=null){
            for (Herencia ad : diagrama.getHerencias()) {
                if (ad.getPadre().getNombre()==herenciaActual.getPadre().getNombre()
                    && ad.getTipo()==herenciaActual.getTipo()) {
                    ad.getEntidades().addAll(herenciaActual.getEntidades());
                    ad.f(gc);
                    ad.getFigura().dibujarCirculo(gc);
                    return;
                }
            }
            InterfazController.diagrama.getHerencias().add(herenciaActual);
        
            herenciaActual.f(gc);
            
            herenciaActual.getFigura().dibujarCirculo(gc);
            herenciaActual=null;
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            reDibujarTodo();
            puntoGuardado();
            
        }
    }
    public static boolean elemHerenciaPropiedad(){
        if(entidadActual!=null){
            for (Herencia herencia : diagrama.herencias) {
                if(herencia.esHijo(entidadActual)){
                    for (Propiedad p : herencia.getPadre().getPropiedades()) {
                        if (p.getNombre().equals(nombreActual)){
                            return true;
                        }else{
                            if (p.getTipo()==Tipo.compuesto) {
                                for (Propiedad p2 : p.getPropiedades()) {
                                    if (p.getNombre().equals(nombreActual)){
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    
    private boolean debilConRelacion(){
        int rdebil = 0;
        int edebil = 0;
        for (Relacion r : diagrama.getRelaciones())
            if (r.getFigura().isDebil())
                rdebil++;
        for (Entidad e : diagrama.getEntidades())
            if (e.getFigura().isDebil())
                edebil++;
        if (rdebil == edebil)
            return true;
        return false;
    }
    public boolean buscar(Agrupacion a){
        for (Relacion relacione : diagrama.relaciones) {
            if(relacione.getNombre() == a.getRelacion().getNombre()){
                return true;
            }
        }
        return false;
    }
    
    
    public void actualizarAgru(Agrupacion a){
        if(!buscar(a)){
            System.out.println("eliminar");
            a.borrarRelacion();
        }
        else{
            for (Object componente : a.getRelacion().getComponentes()) {
                if(componente instanceof Agrupacion){
                    actualizarAgru((Agrupacion) componente);
                }
            }
        }
    }
    public void actualizarAgru(){
        System.out.println(".........");
        for(Object o :diagrama.entidades){
            if (o instanceof Agrupacion){
                actualizarAgru((Agrupacion) o);
            }
        }
        System.out.println(".........");
    }
    public void eliminarRelacion(Agrupacion a){
        if(a.getRelacion() == null){
            a.borrarRelacion();
        }
        else{
            for (Object o : a.getRelacion().getComponentes()) {
                if(o instanceof Agrupacion){
                    eliminarRelacion((Agrupacion) o);
                }
            }
        }
    }
    public void eliminarRelacion(){
        ArrayList eliminar= new ArrayList();
        for (Object o : diagrama.entidades) {
            if(o instanceof Agrupacion){
                eliminarRelacion((Agrupacion) o);
            }
        }
        for(int i=0 ; i  < diagrama.entidades.size(); i++) {
            if(diagrama.entidades.get(i) instanceof Agrupacion){
                if(((Agrupacion)diagrama.entidades.get(i)).getRelacion()==null){
                    eliminar.add(((Agrupacion)diagrama.entidades.get(i)));
                }
            }
        }
        diagrama.entidades.removeAll(eliminar);
        
    }
    //public boolean agruEnHerencia(Herencia h){
    //    for (Herencia herencia : diagrama.herencias) {
        
    //}
    //public void eliminarHerencia(){
    //    ArrayList eliminar= new ArrayList();
    //    for (Herencia herencia : diagrama.herencias) {
    //       if(herencia.getPadre())
    //    }
    //}

    }
