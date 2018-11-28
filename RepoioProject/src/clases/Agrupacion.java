/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;


import java.awt.Point;
import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author The.N
 */
public class Agrupacion {
    ArrayList<Entidad> entidades = new ArrayList<>(); 
    ArrayList<Relacion> relaciones = new ArrayList<>();
    ArrayList<Herencia> herencias = new ArrayList<>();
    ArrayList<Point2D> coordenadas = new ArrayList<>();
    ArrayList<Point2D> cordCuadradoMov = new ArrayList<>();
    
    
    private Point2D getMayor(){
        double XMayor = entidades.get(0).getFigura().getCoordenadas().get(0).getX();
        double YMayor = entidades.get(0).getFigura().getCoordenadas().get(0).getY();
        for (Entidad entidad : entidades) {
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
        for (Relacion relacion : relaciones) {
            for (Point2D cord : relacion.getFigura().getCoordenadas()) {
                if(cord.getX()> XMayor){
                    XMayor = (int)cord.getX();
                }
                if(cord.getY()> YMayor){
                    YMayor = (int)cord.getY();
                }
            }
        }
        return new Point2D(XMayor, YMayor);
    }
    
    private Point2D getMenor(){
        double XMenor = entidades.get(0).getFigura().getCoordenadas().get(0).getX();
        double YMenor = entidades.get(0).getFigura().getCoordenadas().get(0).getY();
        
        for (Entidad entidad : entidades) {
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
        for (Relacion relacion : relaciones) {
            for (Point2D cord : relacion.getFigura().getCoordenadas()) {
                if(cord.getX()< XMenor){
                    XMenor = cord.getX();
                }
                if(cord.getY()< YMenor){
                    YMenor = cord.getY();
                }              
            }
        }
        return new Point2D(XMenor, YMenor);
    }
    private void obtenerCordenadas(){
        Point2D menor = getMenor();
        Point2D mayor = getMayor();
        coordenadas.add(new Point2D(menor.getX() - 10  , menor.getY() - 10 ));
        coordenadas.add(new Point2D(mayor.getX() + 10  , menor.getY() - 10 ));
        coordenadas.add(new Point2D(mayor.getX() + 10  , mayor.getY() + 10 ));
        coordenadas.add(new Point2D(menor.getX() - 10  , mayor.getY() + 10 ));
        
    }
    private void dibujarMarco(GraphicsContext gc){
        obtenerCordenadas();
        gc.strokeLine(coordenadas.get(0).getX(), coordenadas.get(0).getY(), coordenadas.get(1).getX(), coordenadas.get(1).getY());
        gc.strokeLine(coordenadas.get(1).getX(), coordenadas.get(1).getY(), coordenadas.get(2).getX(), coordenadas.get(2).getY());
        gc.strokeLine(coordenadas.get(2).getX(), coordenadas.get(2).getY(), coordenadas.get(3).getX(), coordenadas.get(3).getY());
        gc.strokeLine(coordenadas.get(3).getX(), coordenadas.get(3).getY(), coordenadas.get(0).getX(), coordenadas.get(0).getY());
        pintar(gc , coordenadas);
    }  
    private void pintar(GraphicsContext gc ,ArrayList<Point2D> coordenadas) {

        Point2D p1 = coordenadas.get(0);
        Point2D p2 = coordenadas.get(1);
        int largo = (int) (coordenadas.get(1).getY() - coordenadas.get(0).getY());
        
        gc.setStroke(Color.WHITE);
        p1= new Point2D(p1.getX()+1, p1.getY()+1);
        while(p1.getX() < p2.getX()){
            gc.strokeLine(p1.getX() , p1.getY() ,p1.getX() , p1.getY()+ largo-2 );
            p1 = new Point2D(p1.getX()+1 ,p1.getY());
        }
        
   
    }
    
    private void CuadradoMov(GraphicsContext gc){
        Point2D pCentro = coordenadas.get(1);
        cordCuadradoMov.add(new Point2D(pCentro.getX() - 10 , pCentro.getY() - 10 ));
        cordCuadradoMov.add(new Point2D(pCentro.getX() + 10 , pCentro.getY() - 10 ));
        cordCuadradoMov.add(new Point2D(pCentro.getX() + 10 , pCentro.getY() + 10 ));
        cordCuadradoMov.add(new Point2D(pCentro.getX() - 10 , pCentro.getY() + 10 ));
        dibujarCuadrado(gc, cordCuadradoMov);
        pintar(gc, coordenadas);
    }

    private void dibujarCuadrado(GraphicsContext gc, ArrayList<Point2D> cordCuadradoMov) {
        gc.strokeLine(coordenadas.get(0).getX(), coordenadas.get(0).getY(), coordenadas.get(1).getX(), coordenadas.get(1).getY());
        gc.strokeLine(coordenadas.get(1).getX(), coordenadas.get(1).getY(), coordenadas.get(2).getX(), coordenadas.get(2).getY());
        gc.strokeLine(coordenadas.get(2).getX(), coordenadas.get(2).getY(), coordenadas.get(3).getX(), coordenadas.get(3).getY());
        gc.strokeLine(coordenadas.get(3).getX(), coordenadas.get(3).getY(), coordenadas.get(0).getX(), coordenadas.get(0).getY());
    }
    
}
