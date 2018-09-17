/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author The.N
 */
public class Union {
    ArrayList<Point2D> unionEntidad;
    ArrayList<Point2D> unionRelacion;

    
    public void ConeccionEntidadRelacion(Relacion relacion , Entidad entidad){       
        Point2D minimoEntidad=new Point2D(0,0);
        Point2D minimoRelacion=new Point2D(0,0);;
        for (int i = 0; i < relacion.figura.coordenadasConeccion.size() ; i++) {
            for (int j = 0; j < entidad.figura.coordenadasConeccion.size(); j++) {
                if(j==0 && i==0){
                    minimoRelacion = relacion.figura.coordenadasConeccion.get(i);
                    minimoEntidad = entidad.figura.coordenadasConeccion.get(j) ;
                }
                if(minimoEntidad.distance(minimoRelacion) > relacion.figura.coordenadasConeccion.get(i).distance(entidad.figura.coordenadasConeccion.get(j) )){                 
                   minimoRelacion = relacion.figura.coordenadasConeccion.get(i);
                   minimoEntidad = entidad.figura.coordenadasConeccion.get(j);
                }
            }
        }
        relacion.figura.coordenadasConeccion.remove(minimoRelacion);
        entidad.figura.coordenadasConeccion.remove(minimoEntidad);
        
        unionEntidad.add(minimoEntidad);
        unionRelacion.add(minimoRelacion);
    }
    
    public void obtenerPuntosConeccion(Relacion relacion){
        for (Entidad entidad : relacion.getComponentes()) {
            ConeccionEntidadRelacion(relacion, entidad);        
        }
    }
    
    public void dibujarUniones(GraphicsContext gc){
        for (int i = 0; i < unionRelacion.size(); i++) {
            gc.strokeLine(unionRelacion.get(i).getX() , unionRelacion.get(i).getY() , unionEntidad.get(i).getX() , unionEntidad.get(i).getY());
        }
        
    }
    
    
    
    
}
